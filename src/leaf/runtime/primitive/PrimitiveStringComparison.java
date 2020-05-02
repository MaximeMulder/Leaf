package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.*;
import leaf.runtime.callable.Primitive1;

public class PrimitiveStringComparison extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getString());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		Value other = arguments.get(0);
		if (!other.isa(engine.getTypes().getBoolean())) {
			return engine.getValues().getBooleanFalse();
		}

		return engine.getValues().getBoolean(arguments.get(0).getData().asString().getPrimitive().equals(other.getData().asString().getPrimitive()));
	}
}
