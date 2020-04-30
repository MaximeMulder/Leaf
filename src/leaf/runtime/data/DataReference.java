package leaf.runtime.data;

import leaf.runtime.value.Variable;

public class DataReference extends Data {
	private Variable variable;
	
	public DataReference(Variable variable) {
		this.variable = variable;
	}
	
	public Variable getVariable() {
		return this.variable;
	}
}
