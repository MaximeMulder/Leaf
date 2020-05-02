package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive3;
import leaf.runtime.reference.Reference;

public class PrimitiveError extends Primitive3 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Value execute(Engine engine, List<Reference> arguments) {
		System.err.println(engine.callMethod(arguments.get(0), Index.name("to_string")).read().getData().asString().getPrimitive());
		return null;
	}
}
