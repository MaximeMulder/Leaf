package leaf.callable;

import leaf.exception.Error;
import leaf.structure.Engine;
import leaf.structure.Value;

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
