package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;
import leaf.runtime.reference.Constant;

public class PrimitiveOptionToString extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getOption());
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		String string = "Option";
		Value value = arguments.get(0).getData().asOption().getValue();
		if (value != null) {
			string += "(" + engine.callMethod(new Constant(value), Index.name("to_string")).read().getData().asString().getPrimitive() + ")";
		} else {
			string += ".Null";
		}
		
		return engine.getValues().getString(string);
	}
}
