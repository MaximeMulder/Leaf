package leaf.runtime;

import java.util.HashMap;
import java.util.Map;

public class ValueClass extends ValueName {
	private ValueClass parent;
	private Map<String, ValueFunction> methods;
	private Map<String, ValueFunction> binaries;
	private Map<String, ValueFunction> pres;
	private Map<String, ValueFunction> posts;
	
	public ValueClass(ValueClass type,  String name, ValueClass parent) {
		super(type, name);
		this.parent = parent;
		this.methods = new HashMap<String, ValueFunction>();
		this.binaries = new HashMap<String, ValueFunction>();
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
	
	public void setPre(String operator, ValueFunction function) {
		this.pres.put(operator, function);
	}
	
	public void setPost(String operator, ValueFunction function) {
		this.posts.put(operator, function);
	}
}
