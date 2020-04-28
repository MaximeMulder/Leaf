package leaf.runtime.value;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Index;
import leaf.runtime.Reference;

public class ValueType extends ValueName {
	private ValueType parent;
	private Map<Index, Reference> statics;
	private Map<Index, ValueFunction> methods;

	public ValueType(ValueType type,  String name, ValueType parent) {
		super(type, name);
		this.parent = parent;
		this.statics = new HashMap<Index, Reference>();
		this.methods = new HashMap<Index, ValueFunction>();
	}

	@Override
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


	@Override
	public ValueType castType() {
		return this;
	}

	public ValueType getParent() {
		return this.parent;
	}

	public void setParent(ValueType parent) {
		this.parent = parent;
	}

	public Reference getStatic(Index index) {
		return this.statics.get(index);
	}

	public Reference newStatic(Index index, Value value) {
		Reference reference = new Reference(null, value);
		this.statics.put(index, reference);
		return reference;
	}

	public ValueFunction getMethod(Index index) {
		ValueFunction method = this.methods.get(index);
		if (method == null && this.parent != null) {
			method = this.parent.getMethod(index);
		}

		return method;
	}

	public void newMethod(Index index, ValueFunction function) {
		this.methods.put(index, function);
	}

	public boolean is(ValueType type) {
		if (this == type) {
			return true;
		}

		if (this.parent != null) {
			return this.parent.is(type);
		}

		return false;
	}
}
