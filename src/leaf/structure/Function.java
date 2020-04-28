package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.ValueType;
import leaf.runtime.value.ValueName;

public class Function extends Structure {
	private String name;
	private Expression type;
	private List<Variable> parameters;
	private Expression body;

	public Function(String name, Expression type, List<Variable> parameters, Expression body) {
		this.name = name;
		this.type = type;
		this.parameters = parameters;
		this.body = body;
	}

	@Override
	public ValueName run(Engine engine) {
		ValueType type;
		if (this.type != null) {
			type = this.type.run(engine).read().castType();
		} else {
			type = null;
		}

		return engine.getValues().getFunction(this.name, type, this.parameters, this.body);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addParameter(Variable parameter) {
		this.parameters.add(parameter);
	}

	public void setBody(Expression body) {
		this.body = body;
	}
}
