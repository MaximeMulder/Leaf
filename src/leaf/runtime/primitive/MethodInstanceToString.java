package leaf.runtime.primitive;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Reference;
import leaf.runtime.value.Value;

public class MethodInstanceToString extends Method {
	@Override
	public boolean arguments(Value self, List<Value> arguments) {
		return arguments.size() == 0;
	}
	
	@Override
	public Value execute(Engine engine, Value self, List<Value> parameters) {
		String string = "{";
		Map<Index, Reference> attributes = new TreeMap<Index, Reference>(self.castInstance().getAttributes());
		for (Entry<Index, Reference> attribute : attributes.entrySet()) {
			Value value = attribute.getValue().read();
			string += attribute.getKey() + ": " + value.getType().getMethod(Index.name("to_string")).call(engine, value).castString().getPrimitive() + ", ";
		}
		
		if (attributes.size() > 0) {
			string = string.substring(0, string.length() - 2);
		}
		
		return engine.getValues().getString(string + "}");
	}
}
