package leaf.runtime.factory;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Function;
import leaf.runtime.value.*;
import leaf.structure.Expression;

public class FactoryValues {
	private Engine engine;
	
	public FactoryValues(Engine engine) {
		this.engine = engine;
	}
	
	public ValueArray getArray(List<Value> values) {		
		return new ValueArray(this.engine.getTypes().getArray(), values);
	}
	
	public ValueBoolean getBoolean(boolean primitive) {
		return new ValueBoolean(this.engine.getTypes().getBoolean(), primitive);
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
		return new ValueClass(this.engine.getTypes().getType(), name, parent);
	}
	
	public ValueFunction getFunction(String name, List<String> parameters, Expression body) {
		return new ValueFunction(this.engine.getTypes().getFunction(), name, new Function(this.engine.getScope(), parameters, body));
	}
	
	public ValueInteger getInteger(int primitive) {
		return new ValueInteger(this.engine.getTypes().getInteger(), primitive);
	}
	
	public ValueInstance getInstance(ValueClass type) {
		return new ValueInstance(type);
	}
	
	public ValueNull getNull() {
		return new ValueNull(null);
	}
	
	public ValueString getString(String primitive) {
		return new ValueString(this.engine.getTypes().getString(), primitive);
	}
}
