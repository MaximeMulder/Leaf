package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class MethodBooleanToString extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		return new Constant(engine.getValues().getString(Boolean.toString(self.getData().asBoolean().getPrimitive())));
	}
}
