package leaf.runtime;

import java.util.HashMap;
import java.util.Map;

public class Scope {
	private Scope parent;
	private Map<String, Variable> variables;
	
	Scope(Scope parent) {
		this.parent = parent;
		this.variables = new HashMap<String, Variable>();
	}
	
	Scope getParent() {
		return this.parent;
	}
	
	Variable getVariable(String name) {
		Variable variable = this.variables.get(name);
		if (variable != null) {
			return variable;
		}
		
		if (parent != null) {
			return this.parent.getVariable(name);
		}
		
		return null;
	}
	
	void addVariable(Variable variable) {
		this.variables.put(variable.getName(), variable);
	}
}
