package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.exception.Error;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueBoolean;

public class MethodBooleanComparison extends MethodBoolean {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Value execute(Engine engine, ValueBoolean self, List<Value> arguments) {
		try {
			return engine.getValues().getBoolean(self.getPrimitive() == arguments.get(0).castBoolean().getPrimitive());
		} catch (Error error) {
			return engine.getValues().getBooleanFalse();
		}
	}
}
