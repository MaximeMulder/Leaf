package leaf.runtime.data;

import java.util.List;

import leaf.runtime.reference.Variable;

public class DataArray extends Data {
	private List<Variable> elements;
	
	public DataArray(List<Variable> elements) {
		this.elements = elements;
	}

	public List<Variable> getElements() {
		return this.elements;
	}
}
