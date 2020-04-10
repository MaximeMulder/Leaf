package leaf.callable;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryIntegerOrderLesser extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getBoolean(left.castInteger().getPrimitive() < right.castInteger().getPrimitive());
	}
}
