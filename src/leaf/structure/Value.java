package leaf.structure;

import leaf.exception.ErrorType;
import leaf.exception.ErrorWrite;

public abstract class Value implements IValue {
	private ValueClass type;
	
	protected Value(ValueClass type) {
		this.type = type;
	}
	
	public ValueClass getType() {
		return this.type;
	}
	
	public ValueFunction getMethod(String name) {
		return this.getType().getMethod(name);
	}
	
	public ValueFunction getOperator(String name) {
		return this.getType().getOperator(name);
	}
	
	@Override
	public Value read() {
		return this;
	}

	@Override
	public void write(Value value) {
		throw new ErrorWrite();
	}

	private <ValueType extends Value> ValueType castError() {
		throw new ErrorType();
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
