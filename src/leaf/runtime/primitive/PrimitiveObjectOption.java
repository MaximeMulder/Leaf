package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;

public class PrimitiveObjectOption extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getObject());
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		return engine.getValues().getOption(arguments.get(0));
	}
}
