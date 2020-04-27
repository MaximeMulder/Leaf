package leaf.runtime.value;

import java.util.HashMap;
import java.util.Map;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Reference;

public class ValueClass extends ValueName {
	private ValueClass parent;
	private Map<String, Reference> statics;
	private Map<String, ValueFunction> methods;
	private Map<String, ValueFunction> binaries;
	
	public ValueClass(ValueClass type,  String name, ValueClass parent) {
		super(type, name);
		this.parent = parent;
		this.statics = new HashMap<String, Reference>();
		this.methods = new HashMap<String, ValueFunction>();
		this.binaries = new HashMap<String, ValueFunction>();
	}
	
	@Override
	public IValue member(Engine engine, String name) {
		IValue member;
		member = super.member(engine, name);
		if (member != null) {
			return member;
		}
		
		member = this.getStatic(name);
		if (member != null) {
			return member;
		}

		return this.newStatic(name, null);
	}
	
	
	@Override
	public ValueClass castClass() {
		return this;
	}
	
	public ValueClass getParent() {
		return this.parent;
	}
	
	public void setParent(ValueClass parent) {
		this.parent = parent;
	}
	
	public ValueFunction getMethod(String name) {
		ValueFunction method = this.methods.get(name);
		if (method == null && this.parent != null) {
			method = this.parent.getMethod(name);
		}
		
		return method;
	}
	
	public void setMethod(String name, ValueFunction function) {
		this.methods.put(name, function);
	}
	
	public ValueFunction getBinary(String symbol) {
		ValueFunction operator = this.binaries.get(symbol);
		if (operator == null && this.parent != null) {
			operator = this.parent.getBinary(symbol);
		}

		return operator;
	}
	
	public void setBinary(String operator, ValueFunction function) {
		this.binaries.put(operator, function);
	}
	
	public Reference getStatic(String name) {
		return this.statics.get(name);
	}
	
	public Reference newStatic(String name, Value value) {
		Reference reference = new Reference(null, value);
		this.statics.put(name, reference);
		return reference;
	}
	
	public boolean is(ValueClass type) {
		if (this == type) {
			return true;
		}
		
		if (this.parent != null) {
			return this.parent.is(type);
		}
		
		return false;
	}
}
