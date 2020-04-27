package leaf.runtime.value;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Reference;

public class ValueInstance extends Value {
	private Map<String, Reference> attributes;
	
	public ValueInstance(ValueClass type) {
		super(type);
		this.attributes = new HashMap<String, Reference>();
	}
	
	@Override
	public IValue member(Engine engine, String name) {
		IValue member;
		member = super.member(engine, name);
		if (member != null) {
			return member;
		}
		
		member = this.getAttribute(name);
		if (member != null) {
			return member;
		}

		return this.newAttribute(name, null);
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
		Reference reference = new Reference(null, value);
		this.attributes.put(name, reference);
		return reference;
	}
}
