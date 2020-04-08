package leaf.structure;

import java.util.List;

import leaf.exception.ErrorArguments;

public abstract class ValueFunction extends Value implements IName {
	private String name;
	
	protected ValueFunction(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	ValueClass getType(Engine engine) {
		return engine.getTypeFunction();
	}
	
	@Override
	ValueFunction castFunction(Engine engine) {
		return this;
	}
	
	public Value call(Engine engine, List<Value> arguments) {
		if (!this.arguments(arguments)) {
			throw new ErrorArguments();
		}
		
		return this.execute(engine, arguments);
	}
	
	public abstract boolean arguments(List<Value> arguments);
	
	public abstract Value execute(Engine engine, List<Value> arguments);
}
