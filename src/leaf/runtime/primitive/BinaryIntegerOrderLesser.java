package leaf.runtime.primitive;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;

public class BinaryIntegerOrderLesser extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getBoolean(left.castInteger().getPrimitive() < right.castInteger().getPrimitive());
	}
}
