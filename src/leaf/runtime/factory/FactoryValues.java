package leaf.runtime.factory;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Function;
import leaf.runtime.data.*;
import leaf.runtime.reference.Reference;
import leaf.runtime.reference.Variable;
import leaf.structure.Expression;
import leaf.structure.Declaration;

public class FactoryValues {
	private Engine engine;
	
	public FactoryValues(Engine engine) {
		this.engine = engine;
	}
	
	public Value getArray(List<Variable> elements) {
		return new Value(this.engine.getTypes().getArray(), new DataArray(elements));
	}
	
	public Value getBoolean(boolean primitive) {
		return new Value(this.engine.getTypes().getBoolean(), new DataBoolean(primitive));
	}
	
	public Value getBooleanTrue() {
		return this.getBoolean(true);
	}
	
	public Value getBooleanFalse() {
		return this.getBoolean(false);
	}
	
	public Value getType(String name, Value parent) {
		return new Value(this.engine.getTypes().getType(), new DataType(name, parent));
	}
	
	public Value getFunction(String name, Value type, List<Declaration> parameters, Expression body) {
		return new Value(this.engine.getTypes().getFunction(), new DataFunction(name, new Function(this.engine.getScope(), type, parameters, body)));
	}
	
	public Value getInteger(int primitive) {
		return new Value(this.engine.getTypes().getInteger(), new DataInteger(primitive));
	}
	
	public Value getInstance(Value type) {
		return new Value(type, new DataInstance());
	}
	
	public Value getNull() {
		return new Value(engine.getTypes().getObject(), null);
	}
	
	public Value getReference(Reference reference) {
		return new Value(engine.getTypes().getReference(), new DataReference(reference));
	}
	
	public Value getString(String primitive) {
		return new Value(this.engine.getTypes().getString(), new DataString(primitive));
	}
}
