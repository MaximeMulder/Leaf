package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive2;
import leaf.runtime.reference.Reference;

public class PrimitiveReferenceGet extends Primitive2 {
@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getReference());
	}
	
	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		return arguments.get(0).getData().asReference().getReference();
	}
}
