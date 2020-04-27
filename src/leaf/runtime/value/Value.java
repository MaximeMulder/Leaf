package leaf.runtime.value;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
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
	
	public IValue member(Engine engine, String name) {
		ValueFunction method = this.getType().getMethod(name);
		if (method != null) {
			engine.setSelf(this);
			return method;
		}
		
		return null;
	}
	
	public ValueClass getType() {
		return this.type;
	}
	
	public void setType(ValueClass type) {
		this.type = type;
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
	
	public ValueInstance castInstance() {
		return this.castError();
	}
	
	public ValueInteger castInteger() {
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
