package leaf.primitive;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryIntegerOrderLesser extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.newBoolean(engine.castInteger(left).getPrimitive() < engine.castInteger(right).getPrimitive());
	}
}
