package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueInteger;

public class MethodIntegerAddition extends MethodInteger {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Value execute(Engine engine, ValueInteger self, List<Value> arguments) {
		return engine.getValues().getInteger(self.getPrimitive() + arguments.get(0).castInteger().getPrimitive());
	}
}
