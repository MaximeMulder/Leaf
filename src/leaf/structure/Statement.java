package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;

public class Statement extends Expression {
	private Expression expression;
	
	public Statement(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public IValue run(Engine engine) {
		return this.expression.run(engine);
	}
}
