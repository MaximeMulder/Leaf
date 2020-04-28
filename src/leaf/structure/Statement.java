package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.value.ValueName;

public class Statement extends Expression {
	private Expression expression;
	
	public Statement(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public IValue run(Engine engine) {
		if (this.expression instanceof Structure) {
			ValueName value = ((Structure) this.expression).run(engine);
			String name = value.getName();
			if (name != null) {
				engine.newVariable(name, null, value);
			}
			
			return value;
		}
		
		return this.expression.run(engine);
	}
}
