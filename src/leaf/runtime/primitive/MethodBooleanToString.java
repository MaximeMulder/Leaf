package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;

public class MethodBooleanToString extends Method {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 0;
	}
	
	@Override
	public Value execute(Engine engine, Value self, List<Value> arguments) {
		return engine.getValues().getString(Boolean.toString(self.castBoolean().getPrimitive()));
	}
}
