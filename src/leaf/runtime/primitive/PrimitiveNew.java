package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.ValueClass;

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
			type = engine.getTypeInstance();
		}
		
		return engine.getValues().getInstance(type);
	}
}
