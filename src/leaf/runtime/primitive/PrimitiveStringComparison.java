package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.*;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveStringComparison extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getString());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		Value result = null;
		Value other = arguments.get(0);
		if (other.isa(engine.getTypes().getBoolean())) {
			result = engine.getValues().getBoolean(arguments.get(0).getData().asString().getPrimitive().equals(other.getData().asString().getPrimitive()));
		} else {
			result = engine.getValues().getBooleanFalse();
		}

		return new Constant(result);
	}
}
