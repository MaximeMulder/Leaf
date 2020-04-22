package leaf.runtime.primitive;

import leaf.runtime.Engine;
import leaf.runtime.exception.Error;
import leaf.runtime.value.Value;

public class BinaryIntegerComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		try {
			return engine.getValues().getBoolean(left.castInteger().getPrimitive() == right.castInteger().getPrimitive());
		} catch (Error error) {
			return engine.getValues().getBooleanFalse();
		}
	}
}
