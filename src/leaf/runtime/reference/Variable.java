package leaf.runtime.reference;

import leaf.runtime.Value;

public class Variable extends Reference {
	public Variable(Value type, Value value) {
		super(type, value);
	}

	@Override
	public void write(Value value) {
		if (this.type == null) {
			this.type = value.getType();
		}

		this.value = value.cast(this.type);
	}
}
