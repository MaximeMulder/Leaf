package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueArray;

public class MethodArrayRemove extends MethodArray {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 1;
	}

	@Override
	public Value execute(Engine engine, ValueArray self, List<Value> arguments) {
		return self.getElements().remove(arguments.get(0).castInteger().getPrimitive()).read();
	}
}
