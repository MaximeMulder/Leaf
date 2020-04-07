package leaf.structure;

import java.util.HashMap;
import java.util.Map;

public class ValueInstance extends Value {
	private ValueClass type;
	private Map<String, Variable> attributes;
	
	public ValueInstance(ValueClass type) {
		super();
		this.type = type;
		this.attributes = new HashMap<String, Variable>();
	}
	
	@Override
	ValueClass getType(Engine engine) {
		return this.type;
	}
	
	@Override
	ValueInstance castInstance(Engine engine) {
		return this;
	}
	
	Variable getAttribute(String name) {
		return this.attributes.get(name);
	}
	
	void setAttribute(Variable variable) {
		this.attributes.put(variable.getName(), variable);
	}
}
