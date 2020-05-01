package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.data.DataArray;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;
import leaf.runtime.value.Variable;

public class PrimitiveArrayInsert extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getArray());
		parameters.add(engine.getTypes().getInteger());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		DataArray data = arguments.get(0).getData().asArray();
		data.getElements().add(arguments.get(1).getData().asInteger().getPrimitive(), new Variable(data.getType(), arguments.get(2)));
		return new Constant(null);
	}
}
