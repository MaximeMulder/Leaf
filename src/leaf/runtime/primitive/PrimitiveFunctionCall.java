package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Reference;

public class PrimitiveFunctionCall implements Callable {
	public Reference call(Engine engine, List<Value> arguments) {
		return arguments.get(0).getData().asFunction().getCallable().call(engine, arguments.subList(1, arguments.size()));
	}
}
