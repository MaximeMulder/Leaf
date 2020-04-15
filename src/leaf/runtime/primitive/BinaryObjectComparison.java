package leaf.runtime.primitive;

import leaf.runtime.Engine;
import leaf.runtime.Value;

public class BinaryObjectComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getBoolean(left == right);
	}
}
