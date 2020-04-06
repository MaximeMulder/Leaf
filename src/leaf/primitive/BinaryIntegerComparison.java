package leaf.primitive;

import leaf.exception.Fail;
import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryIntegerComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		try {
			return engine.newBoolean(engine.castInteger(left).getPrimitive() == engine.castInteger(right).getPrimitive());
		} catch (Fail fail) {
			return engine.newFalse();
		}
	}
}
