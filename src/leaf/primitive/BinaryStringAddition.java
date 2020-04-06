package leaf.primitive;

import leaf.structure.*;

public class BinaryStringAddition extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.newString(engine.castString(left).getPrimitive() + engine.castString(right).getPrimitive());
	}
}
