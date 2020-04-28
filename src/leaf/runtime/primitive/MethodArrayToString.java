package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Reference;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueArray;

public class MethodArrayToString extends MethodArray {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 0;
	}
	
	@Override
	public Value execute(Engine engine, ValueArray self, List<Value> parameters) {
		String string = "[";
		List<Reference> elements = self.getElements();
		for (Reference element : elements) {
			Value value = element.read();
			string += value.getType().getMethod(Index.name("to_string")).call(engine, value).castString().getPrimitive() + ", ";
		}
		
		if (elements.size() > 0) {
			string = string.substring(0, string.length() - 2);
		}
		
		return engine.getValues().getString(string + "]");
	}
}
