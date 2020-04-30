package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class MethodIntegerComparison extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 2;
	}

	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		Value result = null;
		Value other = arguments.get(0);
		if (other.isa(engine.getTypes().getInteger())) {
			result = engine.getValues().getBoolean(self.getData().asInteger().getPrimitive() == other.getData().asInteger().getPrimitive());
		} else {
			result = engine.getValues().getBooleanFalse();
		}
		
		return new Constant(result);
	}
}
