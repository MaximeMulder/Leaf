package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveBooleanComparison extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getBoolean());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		Value result = null;
		Value other = arguments.get(1);
		if (other.isa(engine.getTypes().getBoolean())) {
			result = engine.getValues().getBoolean(arguments.get(0).getData().asBoolean().getPrimitive() == other.getData().asBoolean().getPrimitive());
		} else {
			result = engine.getValues().getBooleanFalse();
		}

		return new Constant(result);
	}
}
