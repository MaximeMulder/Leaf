package leaf.primitive;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryIntegerAddition extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getInteger(engine.castInteger(left).getPrimitive() + engine.castInteger(right).getPrimitive());
	}
}
