package leaf.runtime.data;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorArguments;
import leaf.runtime.value.Reference;

public class DataFunction extends DataName {
	private Callable callable;
	
	public DataFunction(String name, Callable callable) {
		super(name);
		this.callable = callable;
	}
	
	public Reference call(Engine engine, List<Value> arguments) {
		if (!this.callable.arguments(arguments)) {
			throw new ErrorArguments();
		}
		
		return this.callable.execute(engine, arguments);
	}
}
