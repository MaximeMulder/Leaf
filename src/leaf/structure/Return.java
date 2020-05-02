package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ControlReturn;
import leaf.runtime.reference.Reference;

public class Return extends Expression {
	private Expression expression;

	public Return(Expression expression) {
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
		
		throw new ControlReturn(value);
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
