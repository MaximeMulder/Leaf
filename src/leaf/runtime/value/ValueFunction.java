package leaf.runtime.value;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.exception.ErrorArguments;

public class ValueFunction extends ValueName {
	private Callable callable;
	
	public ValueFunction(ValueType type, String name, Callable callable) {
		super(type, name);
		this.callable = callable;
	}
	
	@Override
	public ValueFunction castFunction() {
		return this;
	}
	
	public Value call(Engine engine, Value argument) {
		List<Value> arguments = new ArrayList<Value>();
		arguments.add(argument);
		return this.callable.execute(engine, arguments);
	}
	
	public Value call(Engine engine, List<Value> arguments) {
		if (!this.callable.arguments(arguments)) {
			throw new ErrorArguments();
		}
		
		return this.callable.execute(engine, arguments);
	}
}
