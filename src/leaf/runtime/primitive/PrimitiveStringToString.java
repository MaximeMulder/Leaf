package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveStringToString extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getString());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		return new Constant(arguments.get(0));
	}
}
