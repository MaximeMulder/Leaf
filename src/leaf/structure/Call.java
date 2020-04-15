package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Value;

public class Call extends Expression {
	private Expression expression;
	private List<Expression> arguments;
	
	public Call(Expression expression, List<Expression> arguments) {
		this.expression = expression;
		this.arguments = arguments;
	}
	
	@Override
	public IValue run(Engine engine) {
		Value value = this.expression.run(engine).read();
		List<Value> arguments = new ArrayList<Value>();
		Value self = engine.getSelf();
		if (self != null) {
			arguments.add(self);
		}
		
		for (Expression argument : this.arguments) {
			arguments.add(argument.run(engine).read());
		}
		
		return value.castFunction().call(engine, arguments);
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public void addArgument(Expression argument) {
		this.arguments.add(argument);
	}
}
