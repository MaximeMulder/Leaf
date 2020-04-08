package leaf.primitive;

import java.util.List;

import leaf.structure.Engine;
import leaf.structure.Value;
import leaf.structure.ValueFunction;

public class PrimitiveError extends ValueFunction {
	public PrimitiveError() {
		super(null);
	}
	
	@Override
	public boolean arguments(List<Value> arguments) {
		return true;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		System.err.println(arguments.get(0));
		return null;
	}
}
