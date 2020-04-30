package leaf.runtime.value;

import leaf.runtime.Value;

public class Variable implements Reference {
	private Value type;
	private Value value;
	
	public Variable(Value type, Value value) {
		this.type = type;
		this.value = value;
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
			// this.type = value.getType();
		}

		/* if (false !value.getType().is(this.type)) {
			throw new ErrorType();
		}*/

		this.value = value;
	}
}
