package leaf.runtime.primitive;

import java.util.ArrayList;

import leaf.runtime.Engine;
import leaf.runtime.Value;

public class BinaryObjectOrderLesserEqual extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		ArrayList<Value> arguments = new ArrayList<Value>();
		arguments.add(left);
		arguments.add(right);
		return engine.getValues().getBoolean(
			left.getType().getOperator("<").call(engine, arguments).castBoolean().getPrimitive() ||
			right.getType().getOperator("==").call(engine, arguments).castBoolean().getPrimitive()
		);
	}
}
