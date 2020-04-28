package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.value.ValueClass;
import leaf.runtime.value.ValueFunction;
import leaf.runtime.value.ValueName;

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
	public ValueName run(Engine engine) {
		ValueClass parent;
		if (this.parent != null) {
			parent = this.parent.run(engine).read().castClass();
		} else {
			parent = engine.getTypes().getInstance();
		}
		
		ValueClass type = engine.getValues().getType(this.name, parent);
		for (Function method : this.methods) {
			ValueFunction function = method.run(engine).read().castFunction();
			type.newMethod(Index.name(function.getName()), function);
		}
		
		return type;
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
