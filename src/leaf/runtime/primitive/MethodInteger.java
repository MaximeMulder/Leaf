package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueInteger;

public abstract class MethodInteger extends Method {
	@Override
	public Value execute(Engine engine, Value self, List<Value> arguments) {
		return this.execute(engine, self.castInteger(), arguments);
	}
	
	public abstract Value execute(Engine engine, ValueInteger self, List<Value> arguments);
}
