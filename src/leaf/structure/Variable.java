package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.value.ValueType;

public class Variable extends Expression {
	private String name;
	private Expression type;

	public Variable(String name, Expression type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public IValue run(Engine engine) {
		ValueType type;
		if (this.type != null) {
			type = this.type.run(engine).read().castType();
		} else {
			type = null;
		}

		return engine.newVariable(this.name, type, null);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Expression type) {
		this.type = type;
	}
}
