package leaf.callable;

import leaf.exception.Error;
import leaf.structure.*;

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
