package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;

public class Chain extends Expression {
	private Expression expression;
	private String member;

	public Chain(Expression expression, String member) {
		this.expression = expression;
		this.member = member;
	}
	
	@Override
	public IValue run(Engine engine) {
		return expression.run(engine).read().member(engine, this.member);
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public void setMember(String member) {
		this.member = member;
	}
}
