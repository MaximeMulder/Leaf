package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.*;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveStringAddition extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getString());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		return new Constant(engine.getValues().getString(arguments.get(0).getData().asString().getPrimitive() + arguments.get(1).callMethod(Index.name("to_string"), engine).read().getData().asString().getPrimitive()));
	}
}
