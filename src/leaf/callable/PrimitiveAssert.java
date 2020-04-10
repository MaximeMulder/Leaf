package leaf.callable;

import java.util.List;

import leaf.exception.ErrorAssertion;
import leaf.structure.Callable;
import leaf.structure.Engine;
import leaf.structure.Value;

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
