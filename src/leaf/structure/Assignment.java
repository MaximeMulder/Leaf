package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class Assignment extends Expression {
	private Expression reference;
	private Expression expression;
	
	public Assignment(Expression reference, Expression expression) {
		this.reference = reference;
		this.expression = expression;
	}
	
	@Override
	public Reference run(Engine engine) {
		this.reference.run(engine).write(this.expression.run(engine).read());
		return new Constant(null);
	}
	
	public void setReference(Expression reference) {
		this.reference = reference;
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
