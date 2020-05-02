package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;
import leaf.runtime.reference.Variable;

public class PrimitiveArrayToString extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getArray());
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		String string = "[";
		List<Variable> elements = arguments.get(0).getData().asArray().getElements();
		for (Variable element : elements) {
			string += engine.callMethod(element, Index.name("to_string")).read().getData().asString().getPrimitive() + ", ";
		}

		if (elements.size() > 0) {
			string = string.substring(0, string.length() - 2);
		}

		return engine.getValues().getString(string + "]");
	}
}
