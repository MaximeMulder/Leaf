package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;

public class PrimitiveFunctionToString extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getFunction());
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		String string = "Function(";
		String name = arguments.get(0).getData().asFunction().getName();
		if (name != null) {
			string += name;
		}
		
		return engine.getValues().getString(string + ")");
	}
}
