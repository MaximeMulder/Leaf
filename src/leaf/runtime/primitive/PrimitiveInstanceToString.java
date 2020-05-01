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

public class PrimitiveInstanceToString extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getInstance());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		String string = "{";
		Map<String, Variable> attributes = new TreeMap<String, Variable>(arguments.get(0).getData().asInstance().getAttributes());
		for (Entry<String, Variable> attribute : attributes.entrySet()) {
			string += attribute.getKey() + ": " + attribute.getValue().read().callMethod(Index.name("to_string"), engine).read().getData().asString().getPrimitive() + ", ";
		}

		if (attributes.size() > 0) {
			string = string.substring(0, string.length() - 2);
		}

		return new Constant(engine.getValues().getString(string + "}"));
	}
}
