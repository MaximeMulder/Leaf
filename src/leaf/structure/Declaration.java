package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Reference;
import leaf.runtime.value.Variable;

public class Declaration extends Expression {
	private String name;
	private Expression type;

	public Declaration(String name, Expression type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public Reference run(Engine engine) {
		Value type;
		if (this.type != null) {
			type = this.type.run(engine).read().cast(engine.getTypes().getType());
		} else {
			type = null;
		}

		Variable variable = new Variable(type, null);
		engine.setVariable(this.name, variable);
		return variable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Expression type) {
		this.type = type;
	}
}
