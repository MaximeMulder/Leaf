package leaf.runtime.data;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.reference.Reference;

public class DataType extends DataName {
	private Value parent;
	private Map<Index, Reference> statics;
	private Map<Index, Value> methods;

	public DataType(String name, Value parent) {
		super(name);
		this.parent = parent;
		this.statics = new HashMap<Index, Reference>();
		this.methods = new HashMap<Index, Value>();
	}

	public Value getParent() {
		return this.parent;
	}

	public void setParent(Value parent) {
		this.parent = parent;
	}
	
	public Map<Index, Reference> getStatics() {
		return this.statics;
	}

	public Reference getStatic(Index index) {
		return this.statics.get(index);
	}

	public void setStatic(Index index, Reference reference) {
		this.statics.put(index, reference);
	}
	
	public Map<Index, Value> getMethods() {
		return this.methods;
	}

	public Value getMethod(Index index) {
		Value method = this.methods.get(index);
		if (method == null && this.parent != null) {
			method = this.parent.getData().asType().getMethod(index);
		}

		return method;
	}

	public void setMethod(Index index, Value function) {
		this.methods.put(index, function);
	}
}
