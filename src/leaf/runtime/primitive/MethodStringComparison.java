package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.*;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class MethodStringComparison extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 2;
	}
	
	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		Value result = null;
		Value other = arguments.get(0);
		if (other.isa(engine.getTypes().getBoolean())) {
			result = engine.getValues().getBoolean(self.getData().asString().getPrimitive().equals(other.getData().asString().getPrimitive()));
		} else {
			result = engine.getValues().getBooleanFalse();
		}
		
		return new Constant(result);
	}
}
