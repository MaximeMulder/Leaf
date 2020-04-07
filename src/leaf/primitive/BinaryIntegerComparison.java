package leaf.primitive;

import leaf.exception.Error;
import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryIntegerComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		try {
			return engine.getValues().getBoolean(engine.castInteger(left).getPrimitive() == engine.castInteger(right).getPrimitive());
		} catch (Error error) {
			return engine.getValues().getBooleanFalse();
		}
	}
}
