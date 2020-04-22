package leaf.runtime.primitive;

import leaf.runtime.Engine;
import leaf.runtime.exception.Error;
import leaf.runtime.value.Value;

public class BinaryBooleanComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		try {
			return engine.getValues().getBoolean(left.castBoolean().getPrimitive() == right.castBoolean().getPrimitive());
		} catch (Error error) {
			return engine.getValues().getBooleanFalse();
		}
	}
}
