package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;
import leaf.runtime.value.Variable;

public class MethodArrayToString extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		String string = "[";
		List<Variable> elements = self.getData().asArray().getElements();
		for (Variable element : elements) {
			Value value = element.read();
			string += value.callMethod(Index.name("to_string"), engine).read().getData().asString().getPrimitive() + ", ";
		}
		
		if (elements.size() > 0) {
			string = string.substring(0, string.length() - 2);
		}
		
		return new Constant(engine.getValues().getString(string + "]"));
	}
}
