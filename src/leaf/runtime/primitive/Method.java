package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;

public abstract class Method extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() >= 1 && this.arguments(arguments.get(0), arguments.subList(1, arguments.size()));
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		return this.execute(engine, arguments.get(0), arguments.subList(1, arguments.size()));
	};
	
	public abstract boolean arguments(Value self, List<Value> arguments);
	
	public abstract Value execute(Engine engine, Value self, List<Value> arguments);
}
