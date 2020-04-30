package leaf.runtime.primitive;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;
import leaf.runtime.value.Variable;

public class MethodInstanceToString extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		String string = "{";
		Map<String, Variable> attributes = new TreeMap<String, Variable>(self.getData().asInstance().getAttributes());
		for (Entry<String, Variable> attribute : attributes.entrySet()) {
			Value value = attribute.getValue().read();
			string += attribute.getKey() + ": " + value.callMethod(Index.name("to_string"), engine).read().getData().asString().getPrimitive() + ", ";
		}
		
		if (attributes.size() > 0) {
			string = string.substring(0, string.length() - 2);
		}
		
		return new Constant(engine.getValues().getString(string + "}"));
	}
}
