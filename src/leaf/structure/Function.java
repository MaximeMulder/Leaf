package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class Function extends Structure {
	private String name;
	private Expression type;
	private List<Declaration> parameters;
	private Expression body;

	public Function(String name, Expression type, List<Declaration> parameters, Expression body) {
		this.name = name;
		this.type = type;
		this.parameters = parameters;
		this.body = body;
	}

	@Override
	public Reference run(Engine engine) {
		Value type;
		if (this.type != null) {
			type = this.type.run(engine).read().cast(engine.getTypes().getType());
		} else {
			type = null;
		}

		return new Constant(engine.getValues().getFunction(this.name, type, this.parameters, this.body));
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addParameter(Declaration parameter) {
		this.parameters.add(parameter);
	}

	public void setBody(Expression body) {
		this.body = body;
	}
}
