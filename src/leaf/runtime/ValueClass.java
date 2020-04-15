package leaf.runtime;

import java.util.HashMap;
import java.util.Map;

public class ValueClass extends ValueName {
	private ValueClass parent;
	private Map<String, ValueFunction> methods;
	private Map<String, ValueFunction> operators;
	
	public ValueClass(ValueClass type,  String name, ValueClass parent) {
		super(type, name);
		this.parent = parent;
		this.methods   = new HashMap<String, ValueFunction>();
		this.operators = new HashMap<String, ValueFunction>();
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
	
	public ValueFunction getOperator(String symbol) {
		ValueFunction operator = this.operators.get(symbol);
		if (operator == null && this.parent != null) {
			operator = this.parent.getOperator(symbol);
		}

		return operator;
	}
	
	public void setOperator(String operator, ValueFunction function) {
		this.operators.put(operator, function);
	}
	
	@Override
	public ValueClass castClass() {
		return this;
	}
}
