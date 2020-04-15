package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorAssertion;

public class PrimitiveAssert extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		if (!arguments.get(0).castBoolean().getPrimitive()) {
			throw new ErrorAssertion();
		}
		
		return null;
	}
}
