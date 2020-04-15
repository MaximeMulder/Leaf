package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;

public class PrimitiveExit extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() <= 1;
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		int code;
		
		if (arguments.size() == 1) {
			code = arguments.get(0).castInteger().getPrimitive();
		} else {
			code = 0;
		}

		System.exit(code);
		return null;
	}
}
