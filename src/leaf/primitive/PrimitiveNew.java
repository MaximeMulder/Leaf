package leaf.primitive;

import java.util.List;

import leaf.structure.Engine;
import leaf.structure.Value;
import leaf.structure.ValueFunction;

public class PrimitiveNew extends ValueFunction {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 0;
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		return engine.getValues().getInstance(engine.getTypeObject());
	}
}
