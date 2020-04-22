package leaf.runtime;

import java.util.List;

import leaf.runtime.value.Value;

public abstract class Callable {
	public abstract boolean arguments(List<Value> arguments);
	public abstract Value execute(Engine engine, List<Value> arguments);
}
