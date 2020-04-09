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
	
	public ValueBoolean castBoolean(Engine engine) {
		this.cast(engine.getTypeBoolean());
		return null;
	}
	
	public ValueClass castClass(Engine engine) {
		this.cast(engine.getTypeClass());
		return null;
	}
	
	public ValueFunction castFunction(Engine engine) {
		this.cast(engine.getTypeFunction());
		return null;
	}
	
	public ValueInteger castInteger(Engine engine) {
		this.cast(engine.getTypeInteger());
		return null;
	}
	
	public ValueInstance castInstance(Engine engine) {
		this.cast(null);
		return null;
	}
	
	public ValueNull castNull(Engine engine) {
		this.cast(null);
		return null;
	}
	
	public ValueReference castReference(Engine engine) {
		this.cast(engine.getTypeReference());
		return null;
	}
	
	public ValueString castString(Engine engine) {
		this.cast(engine.getTypeString());
		return null;
	}

	public abstract ValueClass getType(Engine engine);
}
