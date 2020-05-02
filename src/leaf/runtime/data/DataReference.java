package leaf.runtime.data;

import leaf.runtime.reference.Reference;

public class DataReference extends Data {
	private Reference reference;
	
	public DataReference(Reference reference) {
		this.reference = reference;
	}
	
	public Reference getReference() {
		return this.reference;
	}
}
