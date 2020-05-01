package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveIntegerComparison extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getInteger());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		Value result = null;
		Value other = arguments.get(1);
		if (other.isa(engine.getTypes().getInteger())) {
			result = engine.getValues().getBoolean(arguments.get(0).getData().asInteger().getPrimitive() == other.getData().asInteger().getPrimitive());
		} else {
			result = engine.getValues().getBooleanFalse();
		}

		return new Constant(result);
	}
}
