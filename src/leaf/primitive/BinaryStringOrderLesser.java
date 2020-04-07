package leaf.primitive;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryStringOrderLesser extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getBoolean(engine.castString(left).getPrimitive().compareTo(engine.castString(right).getPrimitive()) < 0);
	}
}
