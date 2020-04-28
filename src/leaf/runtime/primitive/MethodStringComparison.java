package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.*;
import leaf.runtime.exception.Error;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueString;

public class MethodStringComparison extends MethodString {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 1;
	}

	@Override
	public Value execute(Engine engine, ValueString self, List<Value> arguments) {
		try {
			return engine.getValues().getBoolean(self.getPrimitive().equals(arguments.get(0).castString().getPrimitive()));
		} catch (Error error) {
			return engine.getValues().getBooleanFalse();
		}
	}
}
