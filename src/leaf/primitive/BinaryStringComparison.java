package leaf.primitive;

import leaf.exception.Fail;
import leaf.structure.*;

public class BinaryStringComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		try {
			return engine.newBoolean(engine.castString(left).getPrimitive().equals(engine.castString(right).getPrimitive()));
		} catch (Fail fail) {
			return engine.newFalse();
		}
	}
}
