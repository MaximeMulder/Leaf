package leaf.runtime.primitive;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Reference;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueArray;

public class MethodArrayCopy extends MethodArray {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 0;
	}

	@Override
	public Value execute(Engine engine, ValueArray self, List<Value> arguments) {
		List<Value> values = new ArrayList<Value>();
		for (Reference element : self.getElements()) {
			values.add(element.read());
		}
		
		return engine.getValues().getArray(self.getGeneric(), values);
	}
}
