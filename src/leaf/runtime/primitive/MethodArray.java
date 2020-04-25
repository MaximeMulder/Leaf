package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueArray;

public abstract class MethodArray extends Method {
	@Override
	public Value execute(Engine engine, Value self, List<Value> arguments) {
		return this.execute(engine, self.castArray(), arguments);
	}
	
	public abstract Value execute(Engine engine, ValueArray self, List<Value> arguments);
}
