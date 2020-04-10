package leaf.callable;

import java.util.List;

import leaf.structure.Callable;
import leaf.structure.Engine;
import leaf.structure.Value;
import leaf.structure.ValueClass;

public class PrimitiveNew extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() <= 1;
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		ValueClass type;
		if (arguments.size() == 1) {
			type = arguments.get(0).castClass();
		} else {
			type = engine.getTypeObject();
		}
		
		return engine.getValues().getInstance(type);
	}
}
