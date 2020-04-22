package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.Variable;

public class MethodArrayPrepend extends Method {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 1;
	}

	@Override
	public Value execute(Engine engine, Value self, List<Value> arguments) {
		self.castArray().getElements().add(0, new Variable(null, arguments.get(0)));
		return null;
	}
}
