package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Reference;

public class PrimitiveArrayAccess extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getArray());
		parameters.add(engine.getTypes().getInteger());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		return arguments.get(0).getData().asArray().getElements().get(arguments.get(1).getData().asInteger().getPrimitive());
	}
}
