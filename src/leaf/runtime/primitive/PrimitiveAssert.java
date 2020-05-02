package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;
import leaf.runtime.exception.ErrorAssertion;

public class PrimitiveAssert extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getBoolean());
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		if (!arguments.get(0).getData().asBoolean().getPrimitive()) {
			throw new ErrorAssertion();
		}

		return null;
	}
}
