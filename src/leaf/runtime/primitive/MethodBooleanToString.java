package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueBoolean;

public class MethodBooleanToString extends MethodBoolean {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 0;
	}
	
	@Override
	public Value execute(Engine engine, ValueBoolean self, List<Value> arguments) {
		return engine.getValues().getString(Boolean.toString(self.castBoolean().getPrimitive()));
	}
}
