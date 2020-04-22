package leaf.runtime.primitive;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.Variable;

public class MethodArrayCopy extends Method {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 0;
	}

	@Override
	public Value execute(Engine engine, Value self, List<Value> arguments) {
		List<Value> values = new ArrayList<Value>();
		for (Variable element : self.castArray().getElements()) {
			values.add(element.read());
		}
		
		return engine.getValues().getArray(values);
	}
}