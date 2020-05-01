package leaf.runtime.primitive;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveObjectOrderGreater extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getObject());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		Value self = arguments.get(0);
		List<Value> values = new ArrayList<Value>();
		values.add(arguments.get(1));
		return new Constant(engine.getValues().getBoolean(
			!self.callMethod(Index.binary("<"), engine, values).read().getData().asBoolean().getPrimitive() &&
			!self.callMethod(Index.binary("=="), engine, values).read().getData().asBoolean().getPrimitive()
		));
	}
}
