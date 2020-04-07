package leaf.factory;

import java.util.List;

import leaf.language_leaf.NBlock;
import leaf.primitive.Function;
import leaf.structure.Engine;
import leaf.structure.ValueBoolean;
import leaf.structure.ValueClass;
import leaf.structure.ValueFunction;
import leaf.structure.ValueInstance;
import leaf.structure.ValueInteger;
import leaf.structure.ValueNull;
import leaf.structure.ValueString;

public class FactoryValues {
	private Engine engine;
	
	public FactoryValues(Engine engine) {
		this.engine = engine;
	}
	
	public ValueBoolean getBoolean(boolean primitive) {
		return new ValueBoolean(primitive);
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
	
	public ValueFunction getFunction(List<String> parameters, NBlock body) {
		return new Function(this.engine.getScope(), parameters, body);
	}
	
	public ValueInteger getInteger(int primitive) {
		return new ValueInteger(primitive);
	}
	
	public ValueInstance getInstance(ValueClass type) {
		return new ValueInstance(type);
	}
	
	public ValueNull getNull() {
		return new ValueNull();
	}
	
	public ValueString getString(String primitive) {
		return new ValueString(primitive);
	}
}
