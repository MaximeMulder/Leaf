package leaf.runtime;

import leaf.runtime.exception.ErrorType;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueClass;

public class Reference implements IValue {
	private ValueClass type;
	private Value value;
	
	public Reference(ValueClass type, Value value) {
		this.type = type;
		this.value = null;
		if (value != null) {
			this.write(value);
		}
	}

	@Override
	public Value read() {
		return this.value;
	}

	@Override
	public void write(Value value) {
		if (this.type == null) {
			this.type = value.getType();
		}

		if (!value.getType().is(this.type)) {
			throw new ErrorType();
		}

		this.value = value;
	}
}
