package leaf.runtime.value;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.Reference;

public class ValueInstance extends Value {
	private Map<String, Reference> attributes;
	
	public ValueInstance(ValueClass type) {
		super(type);
		this.attributes = new HashMap<String, Reference>();
	}
	
	@Override
	public ValueInstance castInstance() {
		return this;
	}
	
	public Map<String, Reference> getAttributes() {
		return this.attributes;
	}
	
	public Reference getAttribute(String name) {
		return this.attributes.get(name);
	}
	
	public Reference newAttribute(String name, Value value) {
		Reference reference = new Reference(value);
		this.attributes.put(name, reference);
		return reference;
	}
}
