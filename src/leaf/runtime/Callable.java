package leaf.runtime;

import java.util.List;

import leaf.runtime.exception.ErrorArguments;
import leaf.runtime.value.Reference;

public abstract class Callable {
	public abstract boolean arguments(List<Value> arguments);
	public abstract Reference execute(Engine engine, List<Value> arguments);
	
	public Reference call(Engine engine, List<Value> arguments) {
		if (!this.arguments(arguments)) {
			throw new ErrorArguments();
		}
		
		return this.execute(engine, arguments);
	}
}
