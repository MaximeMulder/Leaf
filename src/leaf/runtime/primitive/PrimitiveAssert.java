package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorAssertion;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveAssert extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getBoolean());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		if (!arguments.get(0).getData().asBoolean().getPrimitive()) {
			throw new ErrorAssertion();
		}

		return new Constant(null);
	}
}
