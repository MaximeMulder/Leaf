package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public class Type extends Structure {
	private String name;
	private Expression parent;
	private List<Function> methods;

	public Type(String name, Expression parent, List<Function> methods) {
		this.name = name;
		this.parent = parent;
		this.methods = methods;
	}

	@Override
	public Reference run(Engine engine) {
		Value parent;
		if (this.parent != null) {
			parent = this.parent.run(engine).read().cast(engine.getTypes().getType());
		} else {
			parent = engine.getTypes().getInstance();
		}

		Value type = engine.getValues().getType(this.name, parent);
		for (Function method : this.methods) {
			Value function = method.run(engine).read();
			type.getData().asType().setMethod(Index.name(function.getData().asFunction().getName()), function);
		}

		return new Constant(type);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Expression parent) {
		this.parent = parent;
	}

	public void addMethod(Function method) {
		this.methods.add(method);
	}
}
