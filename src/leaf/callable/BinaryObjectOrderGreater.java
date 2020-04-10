package leaf.callable;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryObjectOrderGreater extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getBoolean(
			!engine.operation("<",  left, right).castBoolean().getPrimitive() &&
			!engine.operation("==", left, right).castBoolean().getPrimitive()
		);
	}
}
