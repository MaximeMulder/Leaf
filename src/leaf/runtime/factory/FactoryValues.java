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
	private FactoryTypes types;

	public FactoryValues(Engine engine) {
		this.engine = engine;
		this.types = engine.getTypes();
	}

	public Value getArray(List<Variable> elements) {
		return new Value(this.types.getArray(), new DataArray(elements));
	}

	public Value getBoolean(boolean primitive) {
		return new Value(this.types.getBoolean(), new DataBoolean(primitive));
	}

	public Value getBooleanTrue() {
		return this.getBoolean(true);
	}

	public Value getBooleanFalse() {
		return this.getBoolean(false);
	}

	public Value getType(String name, Value parent) {
		return new Value(this.types.getType(), new DataType(name, parent));
	}

	public Value getFunction(String name, Value type, List<Declaration> parameters, Expression body) {
		return new Value(this.types.getFunction(), new DataFunction(name, new Function(this.engine.getScope(), type, parameters, body)));
	}

	public Value getInteger(int primitive) {
		return new Value(this.types.getInteger(), new DataInteger(primitive));
	}

	public Value getInstance(Value type) {
		return new Value(type, new DataInstance());
	}

	public Value getOption(Value value) {
		return new Value(this.types.getOption(), new DataOption(value));
	}

	public Value getOptionNull() {
		return new Value(this.types.getOption(), new DataOption(null));
	}

	public Value getReference(Reference reference) {
		return new Value(this.types.getReference(), new DataReference(reference));
	}

	public Value getString(String primitive) {
		return new Value(this.types.getString(), new DataString(primitive));
	}
}
