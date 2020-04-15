package leaf.runtime.primitive;

import leaf.runtime.Engine;
import leaf.runtime.Value;

public class BinaryStringOrderLesser extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getBoolean(left.castString().getPrimitive().compareTo(right.castString().getPrimitive()) < 0);
	}
}
