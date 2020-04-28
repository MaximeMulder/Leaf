package leaf.runtime.primitive;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.value.Value;

public class MethodObjectOrderGreaterEqual extends Method {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 1;
	}

	@Override
	public Value execute(Engine engine, Value self, List<Value> parameters) {
		ArrayList<Value> arguments = new ArrayList<Value>();
		arguments.add(self);
		arguments.add(parameters.get(0));
		return engine.getValues().getBoolean(
			self.getType().getMethod(Index.binary(">")).call(engine, arguments).castBoolean().getPrimitive() ||
			self.getType().getMethod(Index.binary("==")).call(engine, arguments).castBoolean().getPrimitive()
		);
	}
}
