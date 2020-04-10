package leaf.structure;

import java.util.List;

import leaf.exception.ErrorArguments;

public class ValueFunction extends ValueName {
	Callable callable;
	
	public ValueFunction(ValueClass type, String name, Callable callable) {
		super(type, name);
		this.callable = callable;
	}
	
	public Value call(Engine engine, List<Value> arguments) {
		if (!this.callable.arguments(arguments)) {
			throw new ErrorArguments();
		}
		
		return this.callable.execute(engine, arguments);
	}
	
	@Override
	public ValueFunction castFunction() {
		return this;
	}
}
