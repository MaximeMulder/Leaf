package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class FunctionError extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return true;
	}
	
	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		System.err.println(arguments.get(0).callMethod(Index.name("to_string"), engine).read().getData().asString().getPrimitive());
		return new Constant(null);
	}
}
