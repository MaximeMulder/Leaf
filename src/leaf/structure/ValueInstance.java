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
	public ValueClass getType(Engine engine) {
		return this.type;
	}
	
	@Override
	public ValueInstance castInstance(Engine engine) {
		return this;
	}
	
	public Variable getAttribute(String name) {
		return this.attributes.get(name);
	}
	
	public void setAttribute(Variable variable) {
		this.attributes.put(variable.getName(), variable);
	}
}
