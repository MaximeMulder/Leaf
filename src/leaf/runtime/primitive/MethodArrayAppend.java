package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.data.DataArray;
import leaf.runtime.value.Reference;
import leaf.runtime.value.Variable;

public class MethodArrayAppend extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 2;
	}

	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		DataArray data = self.getData().asArray();
		data.getElements().add(new Variable(data.getType(), arguments.get(0)));
		return null;
	}
}
