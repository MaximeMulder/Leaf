package leaf.callable;

import java.util.List;

import leaf.structure.Callable;
import leaf.structure.Engine;
import leaf.structure.Value;

public class PrimitiveError extends Callable {
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
