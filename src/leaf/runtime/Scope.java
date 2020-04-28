package leaf.runtime;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.value.Value;
import leaf.runtime.value.ValueType;

public class Scope {
	private Scope parent;
	private Map<String, Reference> variables;
	
	public Scope(Scope parent) {
		this.parent = parent;
		this.variables = new HashMap<String, Reference>();
	}
	
	public Scope getParent() {
		return this.parent;
	}
	
	public Reference getVariable(String name) {
		Reference variable = this.variables.get(name);
		if (variable != null) {
			return variable;
		}
		
		if (this.parent != null) {
			return this.parent.getVariable(name);
		}
		
		return null;
	}
	
	public Reference newVariable(String name, ValueType type, Value value) {
		Reference variable = new Reference(type, value);
		this.variables.put(name, variable);
		return variable;
	}
}
