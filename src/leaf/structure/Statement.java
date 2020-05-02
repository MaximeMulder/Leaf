package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.reference.Reference;

public class Statement extends Expression {
	private Expression expression;
	
	public Statement(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public Reference run(Engine engine) {
		Reference reference = this.expression.run(engine);
		if (this.expression instanceof Structure) {
			String name = reference.read().getData().asName().getName();
			if (name != null) {
				engine.setVariable(name, reference);
			}
		}
		
		return reference;
	}
}
