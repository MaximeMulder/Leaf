package leaf.primitive;

import java.util.List;

import leaf.exception.Fail;
import leaf.structure.Engine;
import leaf.structure.Value;
import leaf.structure.ValueFunction;

public class PrimitiveAssert extends ValueFunction {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		if (!engine.castBoolean(arguments.get(0)).getPrimitive()) {
			throw new Fail("Assertion error.");
		}
		
		return null;
	}
}
