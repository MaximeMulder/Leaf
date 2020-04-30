package leaf.runtime;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.value.Reference;

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
	
	public void setVariable(String name, Reference reference) {
		this.variables.put(name, reference);
	}
}
