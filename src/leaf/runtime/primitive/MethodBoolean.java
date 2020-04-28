package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueBoolean;

public abstract class MethodBoolean extends Method {
	@Override
	public Value execute(Engine engine, Value self, List<Value> arguments) {
		return this.execute(engine, self.castBoolean(), arguments);
	}
	
	public abstract Value execute(Engine engine, ValueBoolean self, List<Value> arguments);
}
