package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.*;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueString;

public class MethodStringAddition extends MethodString {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 1;
	}

	@Override
	public Value execute(Engine engine, ValueString self, List<Value> arguments) {
		return engine.getValues().getString(self.getPrimitive() + arguments.get(0).castString().getPrimitive());
	}
}
