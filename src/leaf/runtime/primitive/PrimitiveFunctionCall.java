package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Reference;

public class PrimitiveFunctionCall extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() >= 1;
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		return arguments.get(0).getData().asFunction().getCallable().execute(engine, arguments.subList(1, arguments.size()));
	}
}
