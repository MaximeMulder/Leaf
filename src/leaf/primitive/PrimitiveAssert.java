package leaf.primitive;

import java.util.List;

import leaf.exception.ErrorAssertion;
import leaf.structure.Engine;
import leaf.structure.Value;
import leaf.structure.ValueFunction;

public class PrimitiveAssert extends ValueFunction {
	public PrimitiveAssert() {
		super(null);
	}
	
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		if (!engine.castBoolean(arguments.get(0)).getPrimitive()) {
			throw new ErrorAssertion();
		}
		
		return null;
	}
}
