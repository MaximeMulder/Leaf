package leaf.callable;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryObjectComparison extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getBoolean(left == right);
	}
}
