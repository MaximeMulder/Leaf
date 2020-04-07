package leaf.structure;

import java.util.HashMap;
import java.util.Map;

public class ValueClass extends Value {
	private Map<String, ValueFunction> methods;
	private Map<String, ValueFunction> operators;
	
	public ValueClass() {
		super();
		this.methods   = new HashMap<String, ValueFunction>();
		this.operators = new HashMap<String, ValueFunction>();
	}
	
	@Override
	ValueClass getType(Engine engine) {
		return engine.getTypeClass();
	}
	
	@Override
	ValueClass castClass(Engine engine) {
		return this;
	}
	
	ValueFunction getMethod(String method) {
		return this.methods.get(method);
	}
	
	public void addMethod(String name, ValueFunction function) {
		this.methods.put(name, function);
	}
	
	ValueFunction getOperator(String operator) {
		return this.operators.get(operator);
	}
	
	public void addOperator(String operator, ValueFunction function) {
		this.operators.put(operator, function);
	}
}
