package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.value.Reference;

public class Chain extends Expression {
	private Expression expression;
	private String member;

	public Chain(Expression expression, String member) {
		this.expression = expression;
		this.member = member;
	}
	
	@Override
	public Reference run(Engine engine) {
		List<Value> arguments = new ArrayList<Value>();
		arguments.add(engine.getValues().getString(this.member));
		return expression.run(engine).read().callMethod(Index.special("."), engine, arguments);
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public void setMember(String member) {
		this.member = member;
	}
}
