package leaf.runtime.factory;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Function;
import leaf.runtime.Value;
import leaf.runtime.ValueBoolean;
import leaf.runtime.ValueClass;
import leaf.runtime.ValueFunction;
import leaf.runtime.ValueInstance;
import leaf.runtime.ValueInteger;
import leaf.runtime.ValueNull;
import leaf.runtime.ValueString;
import leaf.runtime.Variable;
import leaf.structure.Expression;

public class FactoryValues {
	private Engine engine;
	
	public FactoryValues(Engine engine) {
		this.engine = engine;
	}
	
	public Variable getVariable(String name) {
		return this.getVariable(name, null);
	}
	
	public Variable getVariable(String name, Value value) {
		return new Variable(name, value);
	}
	
	public ValueBoolean getBoolean(boolean primitive) {
		return new ValueBoolean(this.engine.getTypeBoolean(), primitive);
	}
	
	public ValueBoolean getBooleanOpposite(ValueBoolean value) {
		return this.getBoolean(!value.getPrimitive());
	}
	
	public ValueBoolean getBooleanTrue() {
		return this.getBoolean(true);
	}
	
	public ValueBoolean getBooleanFalse() {
		return this.getBoolean(false);
	}
	
	public ValueClass getType(String name, ValueClass parent) {
		return new ValueClass(this.engine.getTypeClass(), name, parent);
	}
	
	public ValueFunction getFunction(String name, List<String> parameters, Expression body) {
		return new ValueFunction(this.engine.getTypeFunction(), name, new Function(this.engine.getScope(), parameters, body));
	}
	
	public ValueInteger getInteger(int primitive) {
		return new ValueInteger(this.engine.getTypeInteger(), primitive);
	}
	
	public ValueInstance getInstance(ValueClass type) {
		return new ValueInstance(type);
	}
	
	public ValueNull getNull() {
		return new ValueNull(null);
	}
	
	public ValueString getString(String primitive) {
		return new ValueString(this.engine.getTypeString(), primitive);
	}
}
