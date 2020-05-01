package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveIntegerDivision extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getInteger());
		parameters.add(engine.getTypes().getInteger());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		return new Constant(engine.getValues().getInteger(arguments.get(0).getData().asInteger().getPrimitive() / arguments.get(1).getData().asInteger().getPrimitive()));
	}
}
