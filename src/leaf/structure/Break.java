package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.value.Reference;

public class Break extends Expression {
	private Expression expression;

	public Break(Expression expression) {
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
		
		throw new ControlBreak(value);
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
