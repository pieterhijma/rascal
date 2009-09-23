package org.meta_environment.rascal.ast; 
import org.eclipse.imp.pdb.facts.INode; 
public abstract class Expression extends AbstractAST { 
  public org.meta_environment.rascal.ast.Type getType() { throw new UnsupportedOperationException(); } public org.meta_environment.rascal.ast.Parameters getParameters() { throw new UnsupportedOperationException(); } public java.util.List<org.meta_environment.rascal.ast.Statement> getStatements() { throw new UnsupportedOperationException(); } public boolean hasType() { return false; } public boolean hasParameters() { return false; } public boolean hasStatements() { return false; } public boolean isClosure() { return false; }
static public class Closure extends Expression {
/** type:Type parameters:Parameters "{" statements:Statement+ "}" -> Expression {cons("Closure")} */
	public Closure(INode node, org.meta_environment.rascal.ast.Type type, org.meta_environment.rascal.ast.Parameters parameters, java.util.List<org.meta_environment.rascal.ast.Statement> statements) {
		this.node = node;
		this.type = type;
		this.parameters = parameters;
		this.statements = statements;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionClosure(this);
	}

	public boolean isClosure() { return true; }

	public boolean hasType() { return true; }
	public boolean hasParameters() { return true; }
	public boolean hasStatements() { return true; }

private final org.meta_environment.rascal.ast.Type type;
	public org.meta_environment.rascal.ast.Type getType() { return type; }
	private final org.meta_environment.rascal.ast.Parameters parameters;
	public org.meta_environment.rascal.ast.Parameters getParameters() { return parameters; }
	private final java.util.List<org.meta_environment.rascal.ast.Statement> statements;
	public java.util.List<org.meta_environment.rascal.ast.Statement> getStatements() { return statements; }	
}
static public class Ambiguity extends Expression {
  private final java.util.List<org.meta_environment.rascal.ast.Expression> alternatives;
  public Ambiguity(INode node, java.util.List<org.meta_environment.rascal.ast.Expression> alternatives) {
	this.alternatives = java.util.Collections.unmodifiableList(alternatives);
         this.node = node;
  }
  public java.util.List<org.meta_environment.rascal.ast.Expression> getAlternatives() {
	return alternatives;
  }
  
  public <T> T accept(IASTVisitor<T> v) {
     return v.visitExpressionAmbiguity(this);
  }
} public boolean isVoidClosure() { return false; }
static public class VoidClosure extends Expression {
/** parameters:Parameters "{" statements:Statement* "}" -> Expression {cons("VoidClosure")} */
	public VoidClosure(INode node, org.meta_environment.rascal.ast.Parameters parameters, java.util.List<org.meta_environment.rascal.ast.Statement> statements) {
		this.node = node;
		this.parameters = parameters;
		this.statements = statements;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionVoidClosure(this);
	}

	public boolean isVoidClosure() { return true; }

	public boolean hasParameters() { return true; }
	public boolean hasStatements() { return true; }

private final org.meta_environment.rascal.ast.Parameters parameters;
	public org.meta_environment.rascal.ast.Parameters getParameters() { return parameters; }
	private final java.util.List<org.meta_environment.rascal.ast.Statement> statements;
	public java.util.List<org.meta_environment.rascal.ast.Statement> getStatements() { return statements; }	
} public abstract <T> T accept(IASTVisitor<T> visitor); public boolean isNonEmptyBlock() { return false; } static public class NonEmptyBlock extends Expression {
/** "{" statements:Statement+ "}" -> Expression {cons("NonEmptyBlock")} */
	public NonEmptyBlock(INode node, java.util.List<org.meta_environment.rascal.ast.Statement> statements) {
		this.node = node;
		this.statements = statements;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionNonEmptyBlock(this);
	}

	public boolean isNonEmptyBlock() { return true; }

	public boolean hasStatements() { return true; }

private final java.util.List<org.meta_environment.rascal.ast.Statement> statements;
	public java.util.List<org.meta_environment.rascal.ast.Statement> getStatements() { return statements; }	
} public org.meta_environment.rascal.ast.Expression getExpression() { throw new UnsupportedOperationException(); } public boolean hasExpression() { return false; } public boolean isBracket() { return false; }
static public class Bracket extends Expression {
/** "(" expression:Expression ")" -> Expression {cons("Bracket"), bracket} */
	public Bracket(INode node, org.meta_environment.rascal.ast.Expression expression) {
		this.node = node;
		this.expression = expression;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionBracket(this);
	}

	public boolean isBracket() { return true; }

	public boolean hasExpression() { return true; }

private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }	
} public org.meta_environment.rascal.ast.Expression getFirst() { throw new UnsupportedOperationException(); } public org.meta_environment.rascal.ast.Expression getLast() { throw new UnsupportedOperationException(); } public boolean hasFirst() { return false; } public boolean hasLast() { return false; } public boolean isRange() { return false; }
static public class Range extends Expression {
/** "[" first:Expression ".."  last:Expression "]" -> Expression {cons("Range")} */
	public Range(INode node, org.meta_environment.rascal.ast.Expression first, org.meta_environment.rascal.ast.Expression last) {
		this.node = node;
		this.first = first;
		this.last = last;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionRange(this);
	}

	public boolean isRange() { return true; }

	public boolean hasFirst() { return true; }
	public boolean hasLast() { return true; }

private final org.meta_environment.rascal.ast.Expression first;
	public org.meta_environment.rascal.ast.Expression getFirst() { return first; }
	private final org.meta_environment.rascal.ast.Expression last;
	public org.meta_environment.rascal.ast.Expression getLast() { return last; }	
} public org.meta_environment.rascal.ast.Expression getSecond() { throw new UnsupportedOperationException(); } public boolean hasSecond() { return false; } public boolean isStepRange() { return false; }
static public class StepRange extends Expression {
/** "[" first:Expression "," second:Expression ".." last:Expression "]" -> Expression {cons("StepRange")} */
	public StepRange(INode node, org.meta_environment.rascal.ast.Expression first, org.meta_environment.rascal.ast.Expression second, org.meta_environment.rascal.ast.Expression last) {
		this.node = node;
		this.first = first;
		this.second = second;
		this.last = last;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionStepRange(this);
	}

	public boolean isStepRange() { return true; }

	public boolean hasFirst() { return true; }
	public boolean hasSecond() { return true; }
	public boolean hasLast() { return true; }

private final org.meta_environment.rascal.ast.Expression first;
	public org.meta_environment.rascal.ast.Expression getFirst() { return first; }
	private final org.meta_environment.rascal.ast.Expression second;
	public org.meta_environment.rascal.ast.Expression getSecond() { return second; }
	private final org.meta_environment.rascal.ast.Expression last;
	public org.meta_environment.rascal.ast.Expression getLast() { return last; }	
} public boolean isReifyType() { return false; }
static public class ReifyType extends Expression {
/** "#" type:Type -> Expression {cons("ReifyType")} */
	public ReifyType(INode node, org.meta_environment.rascal.ast.Type type) {
		this.node = node;
		this.type = type;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionReifyType(this);
	}

	public boolean isReifyType() { return true; }

	public boolean hasType() { return true; }

private final org.meta_environment.rascal.ast.Type type;
	public org.meta_environment.rascal.ast.Type getType() { return type; }	
} public org.meta_environment.rascal.ast.BasicType getBasicType() { throw new UnsupportedOperationException(); } public java.util.List<org.meta_environment.rascal.ast.Expression> getArguments() { throw new UnsupportedOperationException(); } public boolean hasBasicType() { return false; } public boolean hasArguments() { return false; } public boolean isReifiedType() { return false; } static public class ReifiedType extends Expression {
/** basicType:BasicType "(" arguments:{Expression ","}* ")" -> Expression {cons("ReifiedType")} */
	public ReifiedType(INode node, org.meta_environment.rascal.ast.BasicType basicType, java.util.List<org.meta_environment.rascal.ast.Expression> arguments) {
		this.node = node;
		this.basicType = basicType;
		this.arguments = arguments;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionReifiedType(this);
	}

	public boolean isReifiedType() { return true; }

	public boolean hasBasicType() { return true; }
	public boolean hasArguments() { return true; }

private final org.meta_environment.rascal.ast.BasicType basicType;
	public org.meta_environment.rascal.ast.BasicType getBasicType() { return basicType; }
	private final java.util.List<org.meta_environment.rascal.ast.Expression> arguments;
	public java.util.List<org.meta_environment.rascal.ast.Expression> getArguments() { return arguments; }	
} public boolean isCallOrTree() { return false; } static public class CallOrTree extends Expression {
/** expression:Expression "(" arguments:{Expression ","}* ")" -> Expression {cons("CallOrTree")} */
	public CallOrTree(INode node, org.meta_environment.rascal.ast.Expression expression, java.util.List<org.meta_environment.rascal.ast.Expression> arguments) {
		this.node = node;
		this.expression = expression;
		this.arguments = arguments;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionCallOrTree(this);
	}

	public boolean isCallOrTree() { return true; }

	public boolean hasExpression() { return true; }
	public boolean hasArguments() { return true; }

private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }
	private final java.util.List<org.meta_environment.rascal.ast.Expression> arguments;
	public java.util.List<org.meta_environment.rascal.ast.Expression> getArguments() { return arguments; }	
} public org.meta_environment.rascal.ast.Name getKey() { throw new UnsupportedOperationException(); }
	public org.meta_environment.rascal.ast.Expression getReplacement() { throw new UnsupportedOperationException(); } public boolean hasKey() { return false; }
	public boolean hasReplacement() { return false; }
public boolean isFieldUpdate() { return false; }
static public class FieldUpdate extends Expression {
/** expression:Expression "[" key:Name "=" replacement:Expression "]" -> Expression {cons("FieldUpdate")} */
	public FieldUpdate(INode node, org.meta_environment.rascal.ast.Expression expression, org.meta_environment.rascal.ast.Name key, org.meta_environment.rascal.ast.Expression replacement) {
		this.node = node;
		this.expression = expression;
		this.key = key;
		this.replacement = replacement;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionFieldUpdate(this);
	}

	public boolean isFieldUpdate() { return true; }

	public boolean hasExpression() { return true; }
	public boolean hasKey() { return true; }
	public boolean hasReplacement() { return true; }

private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }
	private final org.meta_environment.rascal.ast.Name key;
	public org.meta_environment.rascal.ast.Name getKey() { return key; }
	private final org.meta_environment.rascal.ast.Expression replacement;
	public org.meta_environment.rascal.ast.Expression getReplacement() { return replacement; }	
} public org.meta_environment.rascal.ast.Name getField() { throw new UnsupportedOperationException(); } public boolean hasField() { return false; }
public boolean isFieldAccess() { return false; }
static public class FieldAccess extends Expression {
/** expression:Expression "." field:Name -> Expression {cons("FieldAccess")} */
	public FieldAccess(INode node, org.meta_environment.rascal.ast.Expression expression, org.meta_environment.rascal.ast.Name field) {
		this.node = node;
		this.expression = expression;
		this.field = field;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionFieldAccess(this);
	}

	public boolean isFieldAccess() { return true; }

	public boolean hasExpression() { return true; }
	public boolean hasField() { return true; }

private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }
	private final org.meta_environment.rascal.ast.Name field;
	public org.meta_environment.rascal.ast.Name getField() { return field; }	
} public java.util.List<org.meta_environment.rascal.ast.Field> getFields() { throw new UnsupportedOperationException(); } public boolean hasFields() { return false; }
public boolean isFieldProject() { return false; }
static public class FieldProject extends Expression {
/** expression:Expression "<" fields:{Field ","}+ ">" -> Expression {cons("FieldProject")} */
	public FieldProject(INode node, org.meta_environment.rascal.ast.Expression expression, java.util.List<org.meta_environment.rascal.ast.Field> fields) {
		this.node = node;
		this.expression = expression;
		this.fields = fields;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionFieldProject(this);
	}

	public boolean isFieldProject() { return true; }

	public boolean hasExpression() { return true; }
	public boolean hasFields() { return true; }

private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }
	private final java.util.List<org.meta_environment.rascal.ast.Field> fields;
	public java.util.List<org.meta_environment.rascal.ast.Field> getFields() { return fields; }	
} public java.util.List<org.meta_environment.rascal.ast.Expression> getSubscripts() { throw new UnsupportedOperationException(); } public boolean hasSubscripts() { return false; }
public boolean isSubscript() { return false; }
static public class Subscript extends Expression {
/** expression:Expression "[" subscripts: {Expression ","}+"]" -> Expression {cons("Subscript")} */
	public Subscript(INode node, org.meta_environment.rascal.ast.Expression expression, java.util.List<org.meta_environment.rascal.ast.Expression> subscripts) {
		this.node = node;
		this.expression = expression;
		this.subscripts = subscripts;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionSubscript(this);
	}

	public boolean isSubscript() { return true; }

	public boolean hasExpression() { return true; }
	public boolean hasSubscripts() { return true; }

private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }
	private final java.util.List<org.meta_environment.rascal.ast.Expression> subscripts;
	public java.util.List<org.meta_environment.rascal.ast.Expression> getSubscripts() { return subscripts; }	
} public org.meta_environment.rascal.ast.Expression getArgument() { throw new UnsupportedOperationException(); } public boolean hasArgument() { return false; } public boolean isIsDefined() { return false; }
static public class IsDefined extends Expression {
/** argument:Expression "?" -> Expression {cons("IsDefined")} */
	public IsDefined(INode node, org.meta_environment.rascal.ast.Expression argument) {
		this.node = node;
		this.argument = argument;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionIsDefined(this);
	}

	public boolean isIsDefined() { return true; }

	public boolean hasArgument() { return true; }

private final org.meta_environment.rascal.ast.Expression argument;
	public org.meta_environment.rascal.ast.Expression getArgument() { return argument; }	
} public boolean isNegation() { return false; }
static public class Negation extends Expression {
/** "!" argument:Expression -> Expression {cons("Negation")} */
	public Negation(INode node, org.meta_environment.rascal.ast.Expression argument) {
		this.node = node;
		this.argument = argument;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionNegation(this);
	}

	public boolean isNegation() { return true; }

	public boolean hasArgument() { return true; }

private final org.meta_environment.rascal.ast.Expression argument;
	public org.meta_environment.rascal.ast.Expression getArgument() { return argument; }	
} public boolean isNegative() { return false; }
static public class Negative extends Expression {
/** "-" argument:Expression -> Expression {cons("Negative")} */
	public Negative(INode node, org.meta_environment.rascal.ast.Expression argument) {
		this.node = node;
		this.argument = argument;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionNegative(this);
	}

	public boolean isNegative() { return true; }

	public boolean hasArgument() { return true; }

private final org.meta_environment.rascal.ast.Expression argument;
	public org.meta_environment.rascal.ast.Expression getArgument() { return argument; }	
} public boolean isTransitiveReflexiveClosure() { return false; }
static public class TransitiveReflexiveClosure extends Expression {
/** argument:Expression "*" -> Expression {cons("TransitiveReflexiveClosure")} */
	public TransitiveReflexiveClosure(INode node, org.meta_environment.rascal.ast.Expression argument) {
		this.node = node;
		this.argument = argument;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionTransitiveReflexiveClosure(this);
	}

	public boolean isTransitiveReflexiveClosure() { return true; }

	public boolean hasArgument() { return true; }

private final org.meta_environment.rascal.ast.Expression argument;
	public org.meta_environment.rascal.ast.Expression getArgument() { return argument; }	
} public boolean isTransitiveClosure() { return false; }
static public class TransitiveClosure extends Expression {
/** argument:Expression "+" -> Expression {cons("TransitiveClosure")} */
	public TransitiveClosure(INode node, org.meta_environment.rascal.ast.Expression argument) {
		this.node = node;
		this.argument = argument;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionTransitiveClosure(this);
	}

	public boolean isTransitiveClosure() { return true; }

	public boolean hasArgument() { return true; }

private final org.meta_environment.rascal.ast.Expression argument;
	public org.meta_environment.rascal.ast.Expression getArgument() { return argument; }	
} public org.meta_environment.rascal.ast.Name getName() { throw new UnsupportedOperationException(); } public boolean hasName() { return false; } public boolean isGetAnnotation() { return false; }
static public class GetAnnotation extends Expression {
/** expression:Expression "@" name:Name -> Expression {cons("GetAnnotation")} */
	public GetAnnotation(INode node, org.meta_environment.rascal.ast.Expression expression, org.meta_environment.rascal.ast.Name name) {
		this.node = node;
		this.expression = expression;
		this.name = name;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionGetAnnotation(this);
	}

	public boolean isGetAnnotation() { return true; }

	public boolean hasExpression() { return true; }
	public boolean hasName() { return true; }

private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }
	private final org.meta_environment.rascal.ast.Name name;
	public org.meta_environment.rascal.ast.Name getName() { return name; }	
} public org.meta_environment.rascal.ast.Expression getValue() { throw new UnsupportedOperationException(); } public boolean hasValue() { return false; }
public boolean isSetAnnotation() { return false; }
static public class SetAnnotation extends Expression {
/** expression:Expression "[" "@" name:Name "=" value: Expression "]" -> Expression {cons("SetAnnotation")} */
	public SetAnnotation(INode node, org.meta_environment.rascal.ast.Expression expression, org.meta_environment.rascal.ast.Name name, org.meta_environment.rascal.ast.Expression value) {
		this.node = node;
		this.expression = expression;
		this.name = name;
		this.value = value;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionSetAnnotation(this);
	}

	public boolean isSetAnnotation() { return true; }

	public boolean hasExpression() { return true; }
	public boolean hasName() { return true; }
	public boolean hasValue() { return true; }

private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }
	private final org.meta_environment.rascal.ast.Name name;
	public org.meta_environment.rascal.ast.Name getName() { return name; }
	private final org.meta_environment.rascal.ast.Expression value;
	public org.meta_environment.rascal.ast.Expression getValue() { return value; }	
} public org.meta_environment.rascal.ast.Expression getLhs() { throw new UnsupportedOperationException(); } public org.meta_environment.rascal.ast.Expression getRhs() { throw new UnsupportedOperationException(); } public boolean hasLhs() { return false; } public boolean hasRhs() { return false; } public boolean isComposition() { return false; }
static public class Composition extends Expression {
/** lhs:Expression "o" rhs:Expression -> Expression {cons("Composition"), left} */
	public Composition(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionComposition(this);
	}

	public boolean isComposition() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isProduct() { return false; }
static public class Product extends Expression {
/** lhs:Expression "*" rhs:Expression -> Expression {cons("Product"), left} */
	public Product(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionProduct(this);
	}

	public boolean isProduct() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isJoin() { return false; }
static public class Join extends Expression {
/** lhs:Expression "join" rhs:Expression -> Expression {cons("Join"), left} */
	public Join(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionJoin(this);
	}

	public boolean isJoin() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isDivision() { return false; }
static public class Division extends Expression {
/** lhs:Expression "/" rhs:Expression -> Expression {cons("Division"), left} */
	public Division(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionDivision(this);
	}

	public boolean isDivision() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isModulo() { return false; }
static public class Modulo extends Expression {
/** lhs:Expression "%" rhs:Expression -> Expression {cons("Modulo"), left} */
	public Modulo(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionModulo(this);
	}

	public boolean isModulo() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isIntersection() { return false; }
static public class Intersection extends Expression {
/** lhs:Expression "&" rhs:Expression -> Expression {cons("Intersection"), left} */
	public Intersection(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionIntersection(this);
	}

	public boolean isIntersection() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isAddition() { return false; }
static public class Addition extends Expression {
/** lhs:Expression "+" rhs:Expression -> Expression {cons("Addition"), left} */
	public Addition(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionAddition(this);
	}

	public boolean isAddition() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isSubtraction() { return false; }
static public class Subtraction extends Expression {
/** lhs:Expression "-" rhs:Expression -> Expression {cons("Subtraction"), left} */
	public Subtraction(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionSubtraction(this);
	}

	public boolean isSubtraction() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isNotIn() { return false; }
static public class NotIn extends Expression {
/** lhs:Expression "notin" rhs:Expression -> Expression {non-assoc, cons("NotIn")} */
	public NotIn(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionNotIn(this);
	}

	public boolean isNotIn() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isIn() { return false; }
static public class In extends Expression {
/** lhs:Expression "in" rhs:Expression -> Expression {non-assoc, cons("In")} */
	public In(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionIn(this);
	}

	public boolean isIn() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isLessThan() { return false; }
static public class LessThan extends Expression {
/** lhs:Expression "<" rhs:Expression -> Expression {non-assoc, cons("LessThan")} */
	public LessThan(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionLessThan(this);
	}

	public boolean isLessThan() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isLessThanOrEq() { return false; }
static public class LessThanOrEq extends Expression {
/** lhs:Expression "<=" rhs:Expression -> Expression {non-assoc, cons("LessThanOrEq")} */
	public LessThanOrEq(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionLessThanOrEq(this);
	}

	public boolean isLessThanOrEq() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isGreaterThan() { return false; }
static public class GreaterThan extends Expression {
/** lhs:Expression ">" rhs:Expression -> Expression {non-assoc, cons("GreaterThan")} */
	public GreaterThan(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionGreaterThan(this);
	}

	public boolean isGreaterThan() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isGreaterThanOrEq() { return false; }
static public class GreaterThanOrEq extends Expression {
/** lhs:Expression ">=" rhs:Expression -> Expression {non-assoc, cons("GreaterThanOrEq")} */
	public GreaterThanOrEq(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionGreaterThanOrEq(this);
	}

	public boolean isGreaterThanOrEq() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isEquals() { return false; } static public class Equals extends Expression {
/** lhs:Expression "==" rhs:Expression -> Expression {left, cons("Equals")} */
	public Equals(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionEquals(this);
	}

	public boolean isEquals() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isNonEquals() { return false; }
static public class NonEquals extends Expression {
/** lhs:Expression "!=" rhs:Expression -> Expression {left, cons("NonEquals")} */
	public NonEquals(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionNonEquals(this);
	}

	public boolean isNonEquals() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public org.meta_environment.rascal.ast.Expression getCondition() { throw new UnsupportedOperationException(); } public org.meta_environment.rascal.ast.Expression getThenExp() { throw new UnsupportedOperationException(); } public org.meta_environment.rascal.ast.Expression getElseExp() { throw new UnsupportedOperationException(); } public boolean hasCondition() { return false; } public boolean hasThenExp() { return false; } public boolean hasElseExp() { return false; } public boolean isIfThenElse() { return false; } static public class IfThenElse extends Expression {
/** condition:Expression "?" thenExp:Expression ":" elseExp:Expression -> Expression {right, cons("IfThenElse")} */
	public IfThenElse(INode node, org.meta_environment.rascal.ast.Expression condition, org.meta_environment.rascal.ast.Expression thenExp, org.meta_environment.rascal.ast.Expression elseExp) {
		this.node = node;
		this.condition = condition;
		this.thenExp = thenExp;
		this.elseExp = elseExp;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionIfThenElse(this);
	}

	public boolean isIfThenElse() { return true; }

	public boolean hasCondition() { return true; }
	public boolean hasThenExp() { return true; }
	public boolean hasElseExp() { return true; }

private final org.meta_environment.rascal.ast.Expression condition;
	public org.meta_environment.rascal.ast.Expression getCondition() { return condition; }
	private final org.meta_environment.rascal.ast.Expression thenExp;
	public org.meta_environment.rascal.ast.Expression getThenExp() { return thenExp; }
	private final org.meta_environment.rascal.ast.Expression elseExp;
	public org.meta_environment.rascal.ast.Expression getElseExp() { return elseExp; }	
} public boolean isIfDefinedOtherwise() { return false; } static public class IfDefinedOtherwise extends Expression {
/** lhs:Expression "?" rhs:Expression -> Expression {non-assoc, cons("IfDefinedOtherwise")} */
	public IfDefinedOtherwise(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionIfDefinedOtherwise(this);
	}

	public boolean isIfDefinedOtherwise() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isImplication() { return false; }
static public class Implication extends Expression {
/** lhs:Expression "==>" rhs:Expression -> Expression {right, cons("Implication")} */
	public Implication(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionImplication(this);
	}

	public boolean isImplication() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isEquivalence() { return false; }
static public class Equivalence extends Expression {
/** lhs:Expression "<==>" rhs:Expression -> Expression {right, cons("Equivalence")} */
	public Equivalence(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionEquivalence(this);
	}

	public boolean isEquivalence() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isAnd() { return false; }
static public class And extends Expression {
/** lhs:Expression "&&" rhs:Expression -> Expression {left, cons("And")} */
	public And(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionAnd(this);
	}

	public boolean isAnd() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} public boolean isOr() { return false; }
static public class Or extends Expression {
/** lhs:Expression "||" rhs:Expression -> Expression {left, cons("Or")} */
	public Or(INode node, org.meta_environment.rascal.ast.Expression lhs, org.meta_environment.rascal.ast.Expression rhs) {
		this.node = node;
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionOr(this);
	}

	public boolean isOr() { return true; }

	public boolean hasLhs() { return true; }
	public boolean hasRhs() { return true; }

private final org.meta_environment.rascal.ast.Expression lhs;
	public org.meta_environment.rascal.ast.Expression getLhs() { return lhs; }
	private final org.meta_environment.rascal.ast.Expression rhs;
	public org.meta_environment.rascal.ast.Expression getRhs() { return rhs; }	
} static public class Lexical extends Expression {
	private final String string;
         public Lexical(INode node, String string) {
		this.node = node;
		this.string = string;
	}
	public String getString() {
		return string;
	}

 	public <T> T accept(IASTVisitor<T> v) {
     		return v.visitExpressionLexical(this);
  	}
} public org.meta_environment.rascal.ast.Expression getPattern() { throw new UnsupportedOperationException(); } public boolean hasPattern() { return false; } public boolean isMatch() { return false; }
static public class Match extends Expression {
/** pattern:Expression ":=" expression:Expression -> Expression {non-assoc, cons("Match")} */
	public Match(INode node, org.meta_environment.rascal.ast.Expression pattern, org.meta_environment.rascal.ast.Expression expression) {
		this.node = node;
		this.pattern = pattern;
		this.expression = expression;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionMatch(this);
	}

	public boolean isMatch() { return true; }

	public boolean hasPattern() { return true; }
	public boolean hasExpression() { return true; }

private final org.meta_environment.rascal.ast.Expression pattern;
	public org.meta_environment.rascal.ast.Expression getPattern() { return pattern; }
	private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }	
} public boolean isNoMatch() { return false; }
static public class NoMatch extends Expression {
/** pattern:Expression "!:=" expression:Expression -> Expression {non-assoc, cons("NoMatch")} */
	public NoMatch(INode node, org.meta_environment.rascal.ast.Expression pattern, org.meta_environment.rascal.ast.Expression expression) {
		this.node = node;
		this.pattern = pattern;
		this.expression = expression;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionNoMatch(this);
	}

	public boolean isNoMatch() { return true; }

	public boolean hasPattern() { return true; }
	public boolean hasExpression() { return true; }

private final org.meta_environment.rascal.ast.Expression pattern;
	public org.meta_environment.rascal.ast.Expression getPattern() { return pattern; }
	private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }	
} public boolean isEnumerator() { return false; }
static public class Enumerator extends Expression {
/** pattern:Expression "<-" expression:Expression -> Expression {prefer, cons("Enumerator")} */
	public Enumerator(INode node, org.meta_environment.rascal.ast.Expression pattern, org.meta_environment.rascal.ast.Expression expression) {
		this.node = node;
		this.pattern = pattern;
		this.expression = expression;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionEnumerator(this);
	}

	public boolean isEnumerator() { return true; }

	public boolean hasPattern() { return true; }
	public boolean hasExpression() { return true; }

private final org.meta_environment.rascal.ast.Expression pattern;
	public org.meta_environment.rascal.ast.Expression getPattern() { return pattern; }
	private final org.meta_environment.rascal.ast.Expression expression;
	public org.meta_environment.rascal.ast.Expression getExpression() { return expression; }	
} 
public org.meta_environment.rascal.ast.Comprehension getComprehension() { throw new UnsupportedOperationException(); }
public boolean hasComprehension() { return false; }
public boolean isComprehension() { return false; }
static public class Comprehension extends Expression {
/** comprehension:Comprehension -> Expression {cons("Comprehension")} */
	public Comprehension(INode node, org.meta_environment.rascal.ast.Comprehension comprehension) {
		this.node = node;
		this.comprehension = comprehension;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionComprehension(this);
	}

	public boolean isComprehension() { return true; }

	public boolean hasComprehension() { return true; }

private final org.meta_environment.rascal.ast.Comprehension comprehension;
	public org.meta_environment.rascal.ast.Comprehension getComprehension() { return comprehension; }	
} public java.util.List<org.meta_environment.rascal.ast.Expression> getGenerators() { throw new UnsupportedOperationException(); } public boolean hasGenerators() { return false; } public boolean isAll() { return false; }
static public class All extends Expression {
/** "all" "(" generators:{Expression ","}+ ")" -> Expression {cons("All")} */
	public All(INode node, java.util.List<org.meta_environment.rascal.ast.Expression> generators) {
		this.node = node;
		this.generators = generators;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionAll(this);
	}

	public boolean isAll() { return true; }

	public boolean hasGenerators() { return true; }

private final java.util.List<org.meta_environment.rascal.ast.Expression> generators;
	public java.util.List<org.meta_environment.rascal.ast.Expression> getGenerators() { return generators; }	
} public boolean isAny() { return false; }
static public class Any extends Expression {
/** "any" "(" generators:{Expression ","}+ ")" -> Expression {cons("Any")} */
	public Any(INode node, java.util.List<org.meta_environment.rascal.ast.Expression> generators) {
		this.node = node;
		this.generators = generators;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionAny(this);
	}

	public boolean isAny() { return true; }

	public boolean hasGenerators() { return true; }

private final java.util.List<org.meta_environment.rascal.ast.Expression> generators;
	public java.util.List<org.meta_environment.rascal.ast.Expression> getGenerators() { return generators; }	
} 
public org.meta_environment.rascal.ast.Literal getLiteral() { throw new UnsupportedOperationException(); }
public boolean hasLiteral() { return false; }
public boolean isLiteral() { return false; }
static public class Literal extends Expression {
/** literal:Literal -> Expression {cons("Literal")} */
	public Literal(INode node, org.meta_environment.rascal.ast.Literal literal) {
		this.node = node;
		this.literal = literal;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionLiteral(this);
	}

	public boolean isLiteral() { return true; }

	public boolean hasLiteral() { return true; }

private final org.meta_environment.rascal.ast.Literal literal;
	public org.meta_environment.rascal.ast.Literal getLiteral() { return literal; }	
} public org.meta_environment.rascal.ast.QualifiedName getQualifiedName() { throw new UnsupportedOperationException(); } public boolean hasQualifiedName() { return false; } public boolean isQualifiedName() { return false; }
static public class QualifiedName extends Expression {
/** qualifiedName:QualifiedName -> Expression {cons("QualifiedName")} */
	public QualifiedName(INode node, org.meta_environment.rascal.ast.QualifiedName qualifiedName) {
		this.node = node;
		this.qualifiedName = qualifiedName;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionQualifiedName(this);
	}

	public boolean isQualifiedName() { return true; }

	public boolean hasQualifiedName() { return true; }

private final org.meta_environment.rascal.ast.QualifiedName qualifiedName;
	public org.meta_environment.rascal.ast.QualifiedName getQualifiedName() { return qualifiedName; }	
} public java.util.List<org.meta_environment.rascal.ast.Expression> getElements() { throw new UnsupportedOperationException(); } public boolean hasElements() { return false; } public boolean isList() { return false; }
static public class List extends Expression {
/** "[" elements:{Expression ","}* "]" -> Expression {cons("List")} */
	public List(INode node, java.util.List<org.meta_environment.rascal.ast.Expression> elements) {
		this.node = node;
		this.elements = elements;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionList(this);
	}

	public boolean isList() { return true; }

	public boolean hasElements() { return true; }

private final java.util.List<org.meta_environment.rascal.ast.Expression> elements;
	public java.util.List<org.meta_environment.rascal.ast.Expression> getElements() { return elements; }	
} public boolean isSet() { return false; }
static public class Set extends Expression {
/** "{" elements:{Expression ","}* "}" -> Expression {cons("Set")} */
	public Set(INode node, java.util.List<org.meta_environment.rascal.ast.Expression> elements) {
		this.node = node;
		this.elements = elements;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionSet(this);
	}

	public boolean isSet() { return true; }

	public boolean hasElements() { return true; }

private final java.util.List<org.meta_environment.rascal.ast.Expression> elements;
	public java.util.List<org.meta_environment.rascal.ast.Expression> getElements() { return elements; }	
} public boolean isTuple() { return false; }
static public class Tuple extends Expression {
/** "<" elements:{Expression ","}+ ">" -> Expression {cons("Tuple")} */
	public Tuple(INode node, java.util.List<org.meta_environment.rascal.ast.Expression> elements) {
		this.node = node;
		this.elements = elements;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionTuple(this);
	}

	public boolean isTuple() { return true; }

	public boolean hasElements() { return true; }

private final java.util.List<org.meta_environment.rascal.ast.Expression> elements;
	public java.util.List<org.meta_environment.rascal.ast.Expression> getElements() { return elements; }	
} 
public java.util.List<org.meta_environment.rascal.ast.Mapping> getMappings() { throw new UnsupportedOperationException(); }
public boolean hasMappings() { return false; }
public boolean isMap() { return false; }
static public class Map extends Expression {
/** "(" mappings:{Mapping ","}* ")" -> Expression {cons("Map")} */
	public Map(INode node, java.util.List<org.meta_environment.rascal.ast.Mapping> mappings) {
		this.node = node;
		this.mappings = mappings;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionMap(this);
	}

	public boolean isMap() { return true; }

	public boolean hasMappings() { return true; }

private final java.util.List<org.meta_environment.rascal.ast.Mapping> mappings;
	public java.util.List<org.meta_environment.rascal.ast.Mapping> getMappings() { return mappings; }	
} public boolean isTypedVariable() { return false; }
static public class TypedVariable extends Expression {
/** type:Type name:Name -> Expression {cons("TypedVariable")} */
	public TypedVariable(INode node, org.meta_environment.rascal.ast.Type type, org.meta_environment.rascal.ast.Name name) {
		this.node = node;
		this.type = type;
		this.name = name;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionTypedVariable(this);
	}

	public boolean isTypedVariable() { return true; }

	public boolean hasType() { return true; }
	public boolean hasName() { return true; }

private final org.meta_environment.rascal.ast.Type type;
	public org.meta_environment.rascal.ast.Type getType() { return type; }
	private final org.meta_environment.rascal.ast.Name name;
	public org.meta_environment.rascal.ast.Name getName() { return name; }	
} public boolean isMultiVariable() { return false; }
static public class MultiVariable extends Expression {
/** qualifiedName:QualifiedName "*" -> Expression {cons("MultiVariable")} */
	public MultiVariable(INode node, org.meta_environment.rascal.ast.QualifiedName qualifiedName) {
		this.node = node;
		this.qualifiedName = qualifiedName;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionMultiVariable(this);
	}

	public boolean isMultiVariable() { return true; }

	public boolean hasQualifiedName() { return true; }

private final org.meta_environment.rascal.ast.QualifiedName qualifiedName;
	public org.meta_environment.rascal.ast.QualifiedName getQualifiedName() { return qualifiedName; }	
} public boolean isDescendant() { return false; }
static public class Descendant extends Expression {
/** "/" pattern:Expression -> Expression {cons("Descendant")} */
	public Descendant(INode node, org.meta_environment.rascal.ast.Expression pattern) {
		this.node = node;
		this.pattern = pattern;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionDescendant(this);
	}

	public boolean isDescendant() { return true; }

	public boolean hasPattern() { return true; }

private final org.meta_environment.rascal.ast.Expression pattern;
	public org.meta_environment.rascal.ast.Expression getPattern() { return pattern; }	
} public boolean isVariableBecomes() { return false; }
static public class VariableBecomes extends Expression {
/** name:Name ":" pattern:Expression -> Expression {cons("VariableBecomes")} */
	public VariableBecomes(INode node, org.meta_environment.rascal.ast.Name name, org.meta_environment.rascal.ast.Expression pattern) {
		this.node = node;
		this.name = name;
		this.pattern = pattern;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionVariableBecomes(this);
	}

	public boolean isVariableBecomes() { return true; }

	public boolean hasName() { return true; }
	public boolean hasPattern() { return true; }

private final org.meta_environment.rascal.ast.Name name;
	public org.meta_environment.rascal.ast.Name getName() { return name; }
	private final org.meta_environment.rascal.ast.Expression pattern;
	public org.meta_environment.rascal.ast.Expression getPattern() { return pattern; }	
} public boolean isTypedVariableBecomes() { return false; }
static public class TypedVariableBecomes extends Expression {
/** type:Type name:Name ":" pattern:Expression -> Expression {cons("TypedVariableBecomes")} */
	public TypedVariableBecomes(INode node, org.meta_environment.rascal.ast.Type type, org.meta_environment.rascal.ast.Name name, org.meta_environment.rascal.ast.Expression pattern) {
		this.node = node;
		this.type = type;
		this.name = name;
		this.pattern = pattern;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionTypedVariableBecomes(this);
	}

	public boolean isTypedVariableBecomes() { return true; }

	public boolean hasType() { return true; }
	public boolean hasName() { return true; }
	public boolean hasPattern() { return true; }

private final org.meta_environment.rascal.ast.Type type;
	public org.meta_environment.rascal.ast.Type getType() { return type; }
	private final org.meta_environment.rascal.ast.Name name;
	public org.meta_environment.rascal.ast.Name getName() { return name; }
	private final org.meta_environment.rascal.ast.Expression pattern;
	public org.meta_environment.rascal.ast.Expression getPattern() { return pattern; }	
} public boolean isGuarded() { return false; }
static public class Guarded extends Expression {
/** "[" type:Type "]" pattern:Expression -> Expression {cons("Guarded")} */
	public Guarded(INode node, org.meta_environment.rascal.ast.Type type, org.meta_environment.rascal.ast.Expression pattern) {
		this.node = node;
		this.type = type;
		this.pattern = pattern;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionGuarded(this);
	}

	public boolean isGuarded() { return true; }

	public boolean hasType() { return true; }
	public boolean hasPattern() { return true; }

private final org.meta_environment.rascal.ast.Type type;
	public org.meta_environment.rascal.ast.Type getType() { return type; }
	private final org.meta_environment.rascal.ast.Expression pattern;
	public org.meta_environment.rascal.ast.Expression getPattern() { return pattern; }	
} public boolean isAnti() { return false; }
static public class Anti extends Expression {
/** "!" pattern:Expression -> Expression {cons("Anti")} */
	public Anti(INode node, org.meta_environment.rascal.ast.Expression pattern) {
		this.node = node;
		this.pattern = pattern;
	}
	public <T> T accept(IASTVisitor<T> visitor) {
		return visitor.visitExpressionAnti(this);
	}

	public boolean isAnti() { return true; }

	public boolean hasPattern() { return true; }

private final org.meta_environment.rascal.ast.Expression pattern;
	public org.meta_environment.rascal.ast.Expression getPattern() { return pattern; }	
}
}