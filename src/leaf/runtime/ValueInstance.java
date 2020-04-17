package leaf.runtime;

import java.util.HashMap;
import java.util.Map;

public class ValueInstance extends Value {
	private Map<String, Variable> attributes;
	
	public ValueInstance(ValueClass type) {
		super(type);
		this.attributes = new HashMap<String, Variable>();
	}
	
	@Override
	public ValueInstance castInstance() {
		return this;
	}
	
	public Variable getAttribute(String name) {
		return this.attributes.get(name);
	}
	
	public void setAttribute(Variable variable) {
		this.attributes.put(variable.getName(), variable);
	}
}
