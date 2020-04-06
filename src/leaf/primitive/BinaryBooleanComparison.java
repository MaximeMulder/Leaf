package leaf.primitive;

import leaf.exception.Fail;
import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryBooleanComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		try {
			return engine.newBoolean(engine.castBoolean(left).getPrimitive() == engine.castBoolean(right).getPrimitive());
		} catch (Fail fail) {
			return engine.newFalse();
		}
	}
}
