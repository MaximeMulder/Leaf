package leaf.runtime.value;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Index;
import leaf.runtime.Reference;

public class ValueInstance extends Value {
	private Map<Index, Reference> attributes;
	
	public ValueInstance(ValueClass type) {
		super(type);
		this.attributes = new HashMap<Index, Reference>();
	}
	
	@Override
	public IValue chain(Engine engine, Index index) {
		IValue member;
		member = super.chain(engine, index);
		if (member != null) {
			return member;
		}
		
		member = this.getAttribute(index);
		if (member != null) {
			return member;
		}

		return this.newAttribute(index, null);
	}
	
	@Override
	public ValueInstance castInstance() {
		return this;
	}
	
	public Map<Index, Reference> getAttributes() {
		return this.attributes;
	}
	
	public Reference getAttribute(Index index) {
		return this.attributes.get(index);
	}
	
	public Reference newAttribute(Index index, Value value) {
		Reference reference = new Reference(null, value);
		this.attributes.put(index, reference);
		return reference;
	}
}
