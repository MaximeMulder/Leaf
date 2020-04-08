package leaf.structure;

import leaf.exception.ErrorType;
import leaf.exception.ErrorWrite;

public abstract class Value implements IValue {
	@Override
	public Value read() {
		return this;
	}

	@Override
	public void write(Value value) {
		throw new ErrorWrite();
	}

	private void cast(ValueClass type) {
		throw new ErrorType();
	}
	
	ValueBoolean castBoolean(Engine engine) {
		this.cast(engine.getTypeBoolean());
		return null;
	}
	
	ValueClass castClass(Engine engine) {
		this.cast(engine.getTypeClass());
		return null;
	}
	
	ValueFunction castFunction(Engine engine) {
		this.cast(engine.getTypeFunction());
		return null;
	}
	
	ValueInteger castInteger(Engine engine) {
		this.cast(engine.getTypeInteger());
		return null;
	}
	
	ValueInstance castInstance(Engine engine) {
		this.cast(null);
		return null;
	}
	
	ValueNull castNull(Engine engine) {
		this.cast(null);
		return null;
	}
	
	ValueReference castReference(Engine engine) {
		this.cast(engine.getTypeReference());
		return null;
	}
	
	ValueString castString(Engine engine) {
		this.cast(engine.getTypeString());
		return null;
	}

	abstract ValueClass getType(Engine engine);
}
