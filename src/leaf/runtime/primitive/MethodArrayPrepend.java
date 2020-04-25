package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Reference;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueArray;

public class MethodArrayPrepend extends MethodArray {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 1;
	}

	@Override
	public Value execute(Engine engine, ValueArray self, List<Value> arguments) {
		self.getElements().add(0, new Reference(self.getGeneric(), arguments.get(0)));
		return null;
	}
}
