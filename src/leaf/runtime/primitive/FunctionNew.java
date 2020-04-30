package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class FunctionNew extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() <= 1;
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		Value type;
		if (arguments.size() == 1) {
			type = arguments.get(0).cast(engine.getTypes().getType());
		} else {
			type = engine.getTypes().getInstance();
		}

		return new Constant(engine.getValues().getInstance(type));
	}
}
