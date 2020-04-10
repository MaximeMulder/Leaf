package leaf.callable;

import java.util.ArrayList;

import leaf.structure.Engine;
import leaf.structure.Value;

public class BinaryObjectDifference extends Binary {
	@Override
	public Value execute(Engine engine, Value left, Value right) {
		ArrayList<Value> arguments = new ArrayList<Value>();
		arguments.add(left);
		arguments.add(right);
		return engine.getValues().getBooleanOpposite(left.getOperator("==").call(engine, arguments).castBoolean());
	}
}
