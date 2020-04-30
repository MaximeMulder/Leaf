package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorAssertion;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class FunctionAssert extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		if (!arguments.get(0).cast(engine.getTypes().getBoolean()).getData().asBoolean().getPrimitive()) {
			throw new ErrorAssertion();
		}
		
		return new Constant(null);
	}
}
