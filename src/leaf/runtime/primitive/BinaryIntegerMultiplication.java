package leaf.runtime.primitive;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;

public class BinaryIntegerMultiplication extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getInteger(left.castInteger().getPrimitive() * right.castInteger().getPrimitive());
	}
}
