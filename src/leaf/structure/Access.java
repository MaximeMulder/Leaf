package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.reference.Reference;

public class Access extends Expression {
	private Expression expression;
	private List<Expression> arguments;
	
	public Access(Expression expression, List<Expression> arguments) {
		this.expression = expression;
		this.arguments = arguments;
	}
	
	@Override
	public Reference run(Engine engine) {
		Reference expression = this.expression.run(engine);
		List<Reference> arguments = new ArrayList<Reference>();
		for (Expression argument : this.arguments) {
			arguments.add(argument.run(engine));
		}
		
		return engine.callMethod(expression, Index.special("[]"), arguments);
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public void addArgument(Expression argument) {
		this.arguments.add(argument);
	}
}
