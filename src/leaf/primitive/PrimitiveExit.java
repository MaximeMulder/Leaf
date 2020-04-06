package leaf.primitive;

import java.util.List;

import leaf.structure.Engine;
import leaf.structure.Value;
import leaf.structure.ValueFunction;

public class PrimitiveExit extends ValueFunction {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() <= 1;
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		int code;
		
		if (arguments.size() == 1) {
			code = engine.castInteger(arguments.get(0)).getPrimitive();
		} else {
			code = 0;
		}

		System.exit(code);
		return null;
	}
}
