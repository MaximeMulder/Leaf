package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueType;

public class FunctionNew extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() <= 1;
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		ValueType type;
		if (arguments.size() == 1) {
			type = arguments.get(0).castType();
		} else {
			type = engine.getTypes().getInstance();
		}

		return engine.getValues().getInstance(type);
	}
}
