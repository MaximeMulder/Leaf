package leaf.primitive;

import java.util.List;

import leaf.structure.Engine;
import leaf.structure.Value;
import leaf.structure.ValueClass;
import leaf.structure.ValueFunction;

public class PrimitiveNew extends ValueFunction {
	public PrimitiveNew() {
		super(null);
	}
	
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() <= 1;
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		ValueClass type;
		if (arguments.size() == 1) {
			type = engine.castClass(arguments.get(0));
		} else {
			type = engine.getTypeObject();
		}
		
		return engine.getValues().getInstance(type);
	}
}
