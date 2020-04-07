package leaf.primitive;

import leaf.exception.Error;
import leaf.structure.*;

public class BinaryStringComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		try {
			return engine.getValues().getBoolean(engine.castString(left).getPrimitive().equals(engine.castString(right).getPrimitive()));
		} catch (Error error) {
			return engine.getValues().getBooleanFalse();
		}
	}
}
