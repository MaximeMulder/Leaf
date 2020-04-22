package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.value.Value;

public class PrimitivePrint extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		System.out.println(arguments.get(0).getType().getMethod("to_string").call(engine, arguments).castString().getPrimitive());
		return null;
	}
}
