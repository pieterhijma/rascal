package org.rascalmpl.interpreter.result.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.imp.pdb.facts.IValue;
import org.rascalmpl.interpreter.result.Result;

public class MemoizationCache {
	
	private static final int PRIME1 = (int) 2654435761L;
	private static final int PRIME2 = (int) 2246822519L;
	private static final int PRIME3 = (int) 3266489917L;
	private static final int PRIME4 = 668265263;
	private static final int PRIME5 = 0x165667b1;
	
	private static int calculateHash(IValue[] params, Map<String, IValue> keyArgs) {
		//xxHash
		int h;
		int len = params.length;
		int i = 0;
		if (len >= 4) {
			int v1 = PRIME5 + PRIME1 + PRIME2;
			int v2 = PRIME5 + PRIME2;
			int v3 = PRIME5 + 0;
			int v4 = PRIME5 - PRIME1;
			int limit = len - 4;
			do {
				v1 += params[i++].hashCode() * PRIME2;
				v1 = Integer.rotateLeft(v1, 13) * PRIME1;
				v2 += params[i++].hashCode() * PRIME2;
				v2 = Integer.rotateLeft(v2, 13) * PRIME1;
				v3 += params[i++].hashCode() * PRIME2;
				v3 = Integer.rotateLeft(v3, 13) * PRIME1;
				v4 += params[i++].hashCode() * PRIME2;
				v4 = Integer.rotateLeft(v4, 13) * PRIME1;
			} while(i < limit);
			h = Integer.rotateLeft(v1, 1)
			  + Integer.rotateLeft(v2, 7)
			  + Integer.rotateLeft(v3, 12)
			  + Integer.rotateLeft(v4, 18);
		}
		else {
			h = PRIME5;
		}
		
		while (i < len) {
			h += params[i++].hashCode() * PRIME3;
			h = Integer.rotateLeft(h, 17) * PRIME4;
		}
		
		if (keyArgs != null) {
			h += keyArgs.hashCode() * PRIME3;
			h = Integer.rotateLeft(h, 17) * PRIME4;
		}

		h ^= h >>> 15;
		h *= PRIME2;
		h ^= h >>> 13;
		h *= PRIME3;
		h ^= h >>> 16;
		
		return h;
	}
	
	private class CacheKey {
		private final int storedHash;
		@SuppressWarnings("rawtypes")
		private final KeySoftReference[] params;
		private final int keyArgsSize;
		private final Map<String, KeySoftReference<IValue>> keyArgs;
		
		public CacheKey(IValue[] params, Map<String, IValue> keyArgs, @SuppressWarnings("rawtypes") ReferenceQueue queue) {
			this.storedHash = calculateHash(params, keyArgs);
			
			this.params = new KeySoftReference[params.length];
			for (int i = 0; i < params.length; i++) {
				this.params[i] = new KeySoftReference<IValue>(params[i], this, queue);
			}
			
			if (keyArgs != null) {
				this.keyArgs = new HashMap<>(keyArgs.size());
				for (Entry<String, IValue> e : keyArgs.entrySet()) {
					this.keyArgs.put(e.getKey(), new KeySoftReference<IValue>(e.getValue(), this, queue));
				}
				this.keyArgsSize = keyArgs.size();
			}
			else {
				this.keyArgs = null;
				this.keyArgsSize = 0;
			}
			
		}
		
		@Override
		public int hashCode() {
			return storedHash;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) 
				return true;
			if (obj instanceof CacheKey) {
				CacheKey other = (CacheKey)obj;
				if (other.storedHash != this.storedHash) 
					return false;
				if (other.params.length != this.params.length)
					return false;
				for (int i = 0; i < params.length; i++) {
					IValue tp = (IValue)params[i].get();
					IValue op = (IValue)other.params[i].get();
					if (tp == null || op == null) 
						return false; 
					if (!tp.isEqual(op))
						return false;
				}
				
				if (other.keyArgsSize != keyArgsSize)
					return false;
				if (keyArgsSize > 0) {
					for (Entry<String, KeySoftReference<IValue>> kv: keyArgs.entrySet()) {
						IValue tp = kv.getValue().get();
						IValue op = other.keyArgs.get(kv.getKey()).get();
						if (tp == null || op == null) 
							return false; 
						if (!tp.isEqual(op))
							return false;
					}
				}
				return true;
				
			}
			if (obj instanceof LookupKey) {
				return ((LookupKey)obj).equals(this);
			}
			return false;
		}
	}
	
	private class LookupKey {
		
		private final int storedHash;
		private final IValue[] params;
		private final Map<String, IValue> keyArgs;
		private final int keyArgsSize;

		public LookupKey(IValue[] params, Map<String, IValue> keyArgs) {
			this.storedHash = calculateHash(params, keyArgs);
			this.params = params;
			this.keyArgs = keyArgs;
			this.keyArgsSize = keyArgs == null ? 0 : keyArgs.size();
		}
		
		@Override
		public int hashCode() {
			return storedHash;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof CacheKey) {
				CacheKey other = (CacheKey)obj;
				if (other.storedHash != this.storedHash) 
					return false;
				if (other.params.length != this.params.length)
					return false;
				for (int i = 0; i < params.length; i++) {
					IValue tp = params[i];
					IValue op = (IValue)other.params[i].get();
					if (tp == null || op == null) 
						return false; 
					if (!tp.isEqual(op))
						return false;
				}
				
				if (other.keyArgsSize != keyArgsSize)
					return false;
				if (keyArgsSize > 0) {
					for (Entry<String, IValue> kv: keyArgs.entrySet()) {
						IValue tp = kv.getValue();
						IValue op = other.keyArgs.get(kv.getKey()).get();
						if (tp == null || op == null) 
							return false; 
						if (!tp.isEqual(op))
							return false;
					}
				}
				return true;
			}
			return false;
		}
	}
	
	private class KeySoftReference<T> extends SoftReference<T> {
		private CacheKey key;

		@SuppressWarnings("unchecked")
		public KeySoftReference(T ref, CacheKey key, @SuppressWarnings("rawtypes") ReferenceQueue queue) {
			super(ref, queue);
			this.key = key;
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	private void cleanupCache() {
		List<CacheKey> toCleanup = new LinkedList<>();
		synchronized (queue) {
			Reference cleared = null;
			do {
				cleared = queue.poll();
				if (cleared != null) {
					if (cleared instanceof KeySoftReference<?>) {
						toCleanup.add(((KeySoftReference<?>)cleared).key);
					}
				}
			}
			while (cleared != null);
		}
		for (CacheKey cl : toCleanup) {
			cache.remove(cl);
			cl.keyArgs.clear();
			for (int i =0; i < cl.params.length; i++) 
				cl.params[i] = null;
		}
	}
	@SuppressWarnings("rawtypes")
	private final ReferenceQueue queue = new ReferenceQueue();
	private final Map<Object, KeySoftReference<Result<IValue>>> cache = new HashMap<>();
	
	public Result<IValue> getStoredResult(IValue[] params, Map<String, IValue> keyArgs) {
		cleanupCache();
		KeySoftReference<Result<IValue>> result = cache.get(new LookupKey(params, keyArgs));
		return result == null ? null : result.get();
	}
	

	public void storeResult(IValue[] params, Map<String, IValue> keyArgs, Result<IValue> result) {
		cleanupCache();
		CacheKey newKey = new CacheKey(params, keyArgs, queue);
		cache.put(newKey, new KeySoftReference<Result<IValue>>(result, newKey, queue));
	}
}