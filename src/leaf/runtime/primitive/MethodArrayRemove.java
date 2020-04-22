package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;

public class MethodArrayRemove extends Method {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 1;
	}

	@Override
	public Value execute(Engine engine, Value self, List<Value> arguments) {
		return self.castArray().getElements().remove(arguments.get(0).castInteger().getPrimitive()).read();
	}
}
