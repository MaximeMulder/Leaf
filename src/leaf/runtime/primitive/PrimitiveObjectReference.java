package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive4;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public class PrimitiveObjectReference extends Primitive4 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Reference execute(Engine engine, List<Reference> arguments) {
		return new Constant(engine.getValues().getReference(arguments.get(0)));
	}
}
