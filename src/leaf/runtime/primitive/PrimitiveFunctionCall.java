package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.callable.Callable;
import leaf.runtime.reference.Reference;

public class PrimitiveFunctionCall implements Callable {
	public Reference call(Engine engine, List<Reference> arguments) {
		return arguments.get(0).read().getData().asFunction().getCallable().call(engine, arguments.subList(1, arguments.size()));
	}
}
