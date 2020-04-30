package leaf.runtime.data;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.value.Variable;

public class DataInstance extends Data {
	private Map<String, Variable> attributes;
	
	public DataInstance() {
		this.attributes = new HashMap<String, Variable>();
	}
	
	public Map<String, Variable> getAttributes() {
		return this.attributes;
	}
	
	public Variable getAttribute(String index) {
		return this.attributes.get(index);
	}
	
	public void setAttribute(String index, Variable variable) {
		this.attributes.put(index, variable);
	}
	
	/* @Override
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
	
	public Map<Index, Variable> getAttributes() {
		return this.attributes;
	}
	
	public Variable getAttribute(Index index) {
		return this.attributes.get(index);
	}
	
	public Variable newAttribute(Index index, Value value) {
		Variable reference = new Variable(null, value);
		this.attributes.put(index, reference);
		return reference;
	} */
}
