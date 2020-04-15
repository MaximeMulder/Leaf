package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Value;
import leaf.runtime.exception.ControlReturn;

public class Return extends Expression {
	private Expression expression;

	public Return(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public IValue run(Engine engine) {
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
