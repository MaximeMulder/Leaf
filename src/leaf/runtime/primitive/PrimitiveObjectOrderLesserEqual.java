package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive3;
import leaf.runtime.reference.Reference;

public class PrimitiveObjectOrderLesserEqual extends Primitive3 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getObject());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Value execute(Engine engine, List<Reference> arguments) {
		Reference self = arguments.get(0);
		return engine.getValues().getBoolean(
			engine.callFunction(self, Index.binary("<"), arguments).read().getData().asBoolean().getPrimitive() ||
			engine.callFunction(self, Index.binary("=="), arguments).read().getData().asBoolean().getPrimitive()
		);
	}
}
