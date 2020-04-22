package leaf.runtime.primitive;

import leaf.runtime.*;
import leaf.runtime.exception.Error;
import leaf.runtime.value.Value;

public class BinaryStringComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		try {
			return engine.getValues().getBoolean(left.castString().getPrimitive().equals(right.castString().getPrimitive()));
		} catch (Error error) {
			return engine.getValues().getBooleanFalse();
		}
	}
}
