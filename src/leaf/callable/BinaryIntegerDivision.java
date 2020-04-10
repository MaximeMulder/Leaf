package leaf.callable;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryIntegerDivision extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getInteger(left.castInteger().getPrimitive() / right.castInteger().getPrimitive());
	}
}
