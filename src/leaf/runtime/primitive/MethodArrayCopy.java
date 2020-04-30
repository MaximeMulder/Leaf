package leaf.runtime.primitive;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.data.DataArray;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class MethodArrayCopy extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}

	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		DataArray data = self.getData().asArray();
		List<Value> values = new ArrayList<Value>();
		for (Reference element : data.getElements()) {
			values.add(element.read());
		}
		
		return new Constant(engine.getValues().getArray(data.getType(), values));
	}
}
