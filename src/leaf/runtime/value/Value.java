package leaf.runtime.value;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Index;
import leaf.runtime.exception.ErrorType;
import leaf.runtime.exception.ErrorWrite;

public abstract class Value implements IValue {
	private ValueType type;

	protected Value(ValueType type) {
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

	public IValue chain(Engine engine, Index index) {
		ValueFunction method = this.getType().getMethod(index);
		if (method != null) {
			engine.setSelf(this);
			return method;
		}

		return null;
	}

	public ValueType getType() {
		return this.type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}

	private <T extends Value> T castError() {
		throw new ErrorType();
	}

	public ValueArray castArray() {
		return this.castError();
	}

	public ValueBoolean castBoolean() {
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

	public ValueType castType() {
		return this.castError();
	}
}
