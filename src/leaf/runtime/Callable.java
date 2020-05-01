package leaf.runtime;

import java.util.List;

import leaf.runtime.value.Reference;

public interface Callable {
	public Reference call(Engine engine, List<Value> arguments);
}
