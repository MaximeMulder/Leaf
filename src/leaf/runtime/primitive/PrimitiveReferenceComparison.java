package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;
import leaf.runtime.reference.Reference;

public class PrimitiveReferenceComparison extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getReference());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		Value other = arguments.get(1);
		if (!other.isa(engine.getTypes().getReference())) {
			return engine.getValues().getBooleanFalse();
		}

		Reference left  = arguments.get(0).getData().asReference().getReference();
		Reference right = other.getData().asReference().getReference();
		return engine.getValues().getBoolean(left == right);
	}
}
