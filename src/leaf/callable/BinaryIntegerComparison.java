package leaf.callable;

import leaf.exception.Error;
import leaf.structure.Engine;
import leaf.structure.Value;

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
