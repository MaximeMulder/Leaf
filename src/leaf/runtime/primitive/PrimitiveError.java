package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;

public class PrimitiveError extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return true;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		System.err.println(arguments.get(0).getType().getMethod("to_string").call(engine, arguments).castString().getPrimitive());
		return null;
	}
}
