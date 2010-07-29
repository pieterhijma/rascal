package org.rascalmpl.library;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;

import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.IListWriter;
import org.eclipse.imp.pdb.facts.ISourceLocation;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.rascalmpl.parser.sgll.util.ArrayList;

public class SystemAPI {
	private final static int STREAM_READ_SEGMENT_SIZE = 8192;

	private final IValueFactory values;

	public SystemAPI(IValueFactory values) {
		this.values = values;
	}

	public IValue getSystemProperty(IString v) {
		return values.string(java.lang.System.getProperty(v.getValue()));
	}

	private IList readLines(Reader in, java.lang.String... regex_replacement)
			throws IOException {
		ArrayList<char[]> segments = new ArrayList<char[]>();

		// Gather segments.
		int nrOfWholeSegments = -1;
		int bytesRead;
		do {
			char[] segment = new char[STREAM_READ_SEGMENT_SIZE];
			bytesRead = in.read(segment, 0, STREAM_READ_SEGMENT_SIZE);
			segments.add(segment);
			nrOfWholeSegments++;
		} while (bytesRead == STREAM_READ_SEGMENT_SIZE);
		// Glue the segments together.
		char[] segment = segments.get(nrOfWholeSegments);
		char[] input;
		if (bytesRead != -1) {
			input = new char[(nrOfWholeSegments * STREAM_READ_SEGMENT_SIZE)
					+ bytesRead];
			System.arraycopy(segment, 0, input,
					(nrOfWholeSegments * STREAM_READ_SEGMENT_SIZE), bytesRead);
		} else {
			input = new char[(nrOfWholeSegments * STREAM_READ_SEGMENT_SIZE)];
		}
		for (int i = nrOfWholeSegments - 1; i >= 0; i--) {
			segment = segments.get(i);
			System.arraycopy(segment, 0, input, (i * STREAM_READ_SEGMENT_SIZE),
					STREAM_READ_SEGMENT_SIZE);
		}
		java.lang.String b = java.lang.String.valueOf(input);
		java.lang.String[] d = regex_replacement.length == 0 ? b.split("\n")
				: new java.lang.String[] { b };
		IListWriter r = values.listWriter(TypeFactory.getInstance()
				.stringType());
		for (java.lang.String e : d) {
			if (regex_replacement.length == 4) {
				java.lang.String[] f = e.split(regex_replacement[0]);
				StringBuffer a = new StringBuffer();
				for (int i = 0; i < f.length; i++) {
					if (i % 2 == 1) {
						a.append(regex_replacement[1]);
						a.append(regex_replacement[0]);
						a.append(f[i].replaceAll(regex_replacement[2],
								regex_replacement[3]));
						a.append(regex_replacement[0]);
						a.append(regex_replacement[1]);
					} else
						a.append(f[i]);
				}
				e = a.toString();
			}
			r.append(values.string(e));
		}
		return r.done();
	}

	public IValue getFileContent(IString g) {
		java.lang.String s = File.separator
				+ this.getClass().getCanonicalName().replaceAll("\\.",
						File.separator);
		s = s.substring(0, s.lastIndexOf(File.separatorChar));
		if (g != null)
			s += (File.separator + g.getValue());
		InputStreamReader a = new InputStreamReader(getClass()
				.getResourceAsStream(s));
		try {
			IList r = readLines(a);
			// System.out.println(r);
			return r;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public IValue getRascalFileContent(ISourceLocation g) {
		try {
			FileReader a = new FileReader(g.getURI().getPath());
			IList r = readLines(a, "`", "\"", "\"", "\\\\\"");
			// System.out.println(((IString) r.get(0)).getValue());
			return r.get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public IValue getLibraryPath(IString g) {
		try {
			java.lang.String s = File.separator
					+ this.getClass().getCanonicalName().replaceAll("\\.",
							File.separator);
			s = s.substring(0, s.lastIndexOf(File.separatorChar));
			if (g != null)
				s += (File.separator + g.getValue());
			IValue v = values.sourceLocation(getClass().getResource(s).toURI());
			return v;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
