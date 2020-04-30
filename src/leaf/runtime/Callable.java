package leaf.runtime;

import java.util.List;

import leaf.runtime.value.Reference;

public abstract class Callable {
	public abstract boolean arguments(List<Value> arguments);
	public abstract Reference execute(Engine engine, List<Value> arguments);
}
