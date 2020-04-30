package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class FunctionExit extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() <= 1;
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		int code;
		if (arguments.size() == 1) {
			code = arguments.get(0).cast(engine.getTypes().getInteger()).getData().asInteger().getPrimitive();
		} else {
			code = 0;
		}

		System.exit(code);
		return new Constant(null);
	}
}
