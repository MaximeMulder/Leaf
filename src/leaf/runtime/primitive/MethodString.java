package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueString;

public abstract class MethodString extends Method {
	@Override
	public Value execute(Engine engine, Value self, List<Value> arguments) {
		return this.execute(engine, self.castString(), arguments);
	}
	
	public abstract Value execute(Engine engine, ValueString self, List<Value> arguments);
}
