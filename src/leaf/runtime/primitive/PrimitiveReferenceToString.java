package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;

public class PrimitiveReferenceToString extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getReference());
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		return engine.getValues().getString("Reference(" + engine.callMethod(arguments.get(0).getData().asReference().getReference(), Index.name("to_string")).read().getData().asString().getPrimitive() + ")");
	}
}
