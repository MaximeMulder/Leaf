package leaf.callable;

import java.util.List;

import leaf.structure.Callable;
import leaf.structure.Engine;
import leaf.structure.Value;

public abstract class Binary extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 2;
	}

	@Override
	public Value execute(Engine engine, List<Value> parameters) {
		return this.execute(engine, parameters.get(0), parameters.get(1));
	}
	
	public abstract Value execute(Engine engine, Value left, Value right);
}
