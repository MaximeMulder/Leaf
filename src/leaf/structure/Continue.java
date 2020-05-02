package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ControlContinue;
import leaf.runtime.reference.Reference;

public class Continue extends Expression {
	private Expression expression;

	public Continue(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public Reference run(Engine engine) {
		Value value;
		if (this.expression != null) {
			value = this.expression.run(engine).read();
		} else {
			value = null;
		}
		
		throw new ControlContinue(value);
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
