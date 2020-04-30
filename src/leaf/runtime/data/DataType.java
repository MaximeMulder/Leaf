package leaf.runtime.data;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.value.Variable;

public class DataType extends DataName {
	private Value parent;
	private Map<Index, Variable> statics;
	private Map<Index, Value> methods;

	public DataType(String name, Value parent) {
		super(name);
		this.parent = parent;
		this.statics = new HashMap<Index, Variable>();
		this.methods = new HashMap<Index, Value>();
	}

	public Value getParent() {
		return this.parent;
	}

	public void setParent(Value parent) {
		this.parent = parent;
	}
	
	public Map<Index, Variable> getStatics() {
		return this.statics;
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

	/* @Override
	public IValue chain(Engine engine, Index index) {
		IValue member = super.chain(engine, index);
		if (member != null) {
			return member;
		}

		member = this.getStatic(index);
		if (member != null) {
			return member;
		}

		return this.newStatic(index, null);
	}

	public DataType getParent() {
		return this.parent;
	}

	public void setParent(DataType parent) {
		this.parent = parent;
	}

	public Reference getStatic(Index index) {
		return this.statics.get(index);
	}

	public Reference newStatic(Index index, Value value) {
		Reference reference = new Reference(null, value);
		this.statics.put(index, reference);
		return reference;
	} */
}
