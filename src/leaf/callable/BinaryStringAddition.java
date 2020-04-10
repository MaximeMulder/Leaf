package leaf.callable;

import leaf.structure.*;

public class BinaryStringAddition extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		return engine.getValues().getString(left.castString().getPrimitive() + right.castString().getPrimitive());
	}
}
