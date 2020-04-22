package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.Variable;

public class MethodArrayToString extends Method {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 0;
	}
	
	@Override
	public Value execute(Engine engine, Value self, List<Value> parameters) {
		String string = "[";
		List<Variable> elements = self.castArray().getElements();
		for (Variable variable : elements) {
			Value value = variable.read();
			string += value.getType().getMethod("to_string").call(engine, value).castString().getPrimitive() + ", ";
		}
		
		if (elements.size() > 0) {
			string = string.substring(0, string.length() - 2);
		}
		
		return engine.getValues().getString(string + "]");
	}
}
