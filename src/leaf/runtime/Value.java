package leaf.runtime;

import leaf.runtime.exception.ErrorType;
import leaf.runtime.exception.ErrorWrite;

public abstract class Value implements IValue {
	private ValueClass type;
	
	protected Value(ValueClass type) {
		this.type = type;
	}
	
	@Override
	public Value read() {
		return this;
	}

	@Override
	public void write(Value value) {
		throw new ErrorWrite();
	}
	
	public ValueClass getType() {
		return this.type;
	}

	private <ValueType extends Value> ValueType castError() {
		throw new ErrorType();
	}
	
	public ValueArray castArray() {
		return this.castError();
	}
	
	public ValueBoolean castBoolean() {
		return this.castError();
	}
	
	public ValueClass castClass() {
		return this.castError();
	}
	
	public ValueFunction castFunction() {
		return this.castError();
	}
	
	public ValueInteger castInteger() {
		return this.castError();
	}
	
	public ValueInstance castInstance() {
		return this.castError();
	}
	
	public ValueNull castNull() {
		return this.castError();
	}
	
	public ValueReference castReference() {
		return this.castError();
	}
	
	public ValueString castString() {
		return this.castError();
	}
}
