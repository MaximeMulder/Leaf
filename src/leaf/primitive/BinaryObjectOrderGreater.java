package leaf.primitive;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryObjectOrderGreater extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.newBoolean(
			!engine.castBoolean(engine.operation("<",  left, right)).getPrimitive() &&
			!engine.castBoolean(engine.operation("==", left, right)).getPrimitive()
		);
	}
}
