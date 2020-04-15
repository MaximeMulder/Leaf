package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;

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
