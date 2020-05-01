package leaf.runtime.primitive;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.data.DataArray;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveArrayCopy extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getArray());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		DataArray data = arguments.get(0).getData().asArray();
		List<Value> values = new ArrayList<Value>();
		for (Reference element : data.getElements()) {
			values.add(element.read());
		}

		return new Constant(engine.getValues().getArray(data.getType(), values));
	}
}
