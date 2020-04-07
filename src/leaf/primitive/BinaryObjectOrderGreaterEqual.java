package leaf.primitive;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryObjectOrderGreaterEqual extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getBoolean(
			engine.castBoolean(engine.operation(">",  left, right)).getPrimitive() ||
			engine.castBoolean(engine.operation("==", left, right)).getPrimitive()
		);
	}
}
