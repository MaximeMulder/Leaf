package leaf.structure;

import java.util.List;

import leaf.exception.Fail;

public abstract class ValueFunction extends Value {
	protected ValueFunction() {
		super();
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
			throw new Fail("Arguments error.");
		}
		
		return this.execute(engine, arguments);
	}
	
	public abstract boolean arguments(List<Value> arguments);
	
	public abstract Value execute(Engine engine, List<Value> arguments);
}
