package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.*;
import leaf.runtime.callable.Primitive3;
import leaf.runtime.reference.Reference;

public class PrimitiveStringAddition extends Primitive3 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getString());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Value execute(Engine engine, List<Reference> arguments) {
		return engine.getValues().getString(arguments.get(0).read().getData().asString().getPrimitive() + engine.callMethod(arguments.get(1), Index.name("to_string")).read().getData().asString().getPrimitive());
	}
}
