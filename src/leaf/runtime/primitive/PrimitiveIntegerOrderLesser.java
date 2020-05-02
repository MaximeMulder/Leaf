package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;

public class PrimitiveIntegerOrderLesser extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getInteger());
		parameters.add(engine.getTypes().getInteger());
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		return engine.getValues().getBoolean(arguments.get(0).getData().asInteger().getPrimitive() < arguments.get(1).getData().asInteger().getPrimitive());
	}
}
