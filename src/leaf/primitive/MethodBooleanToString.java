package leaf.primitive;

import java.util.List;

import leaf.structure.Engine;
import leaf.structure.Value;
import leaf.structure.ValueFunction;

public class MethodBooleanToString extends ValueFunction {
	public MethodBooleanToString() {
		super(null);
	}

	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		return engine.getValues().getString(Boolean.toString(engine.castBoolean(arguments.get(0)).getPrimitive()));
	}
}
