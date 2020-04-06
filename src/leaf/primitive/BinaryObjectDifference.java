package leaf.primitive;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryObjectDifference extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.newBooleanOpposite(engine.castBoolean(engine.operation("==", left, right)));
	}
}
