package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;

public class Assignment extends Expression {
	private Expression reference;
	private Expression expression;
	
	public Assignment(Expression reference, Expression expression) {
		this.reference = reference;
		this.expression = expression;
	}
	
	@Override
	public IValue run(Engine engine) {
		this.reference.run(engine).write(this.expression.run(engine).read());
		return null;
	}
	
	public void setReference(Expression reference) {
		this.reference = reference;
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
