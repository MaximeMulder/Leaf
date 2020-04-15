package leaf.runtime;

public class ValueNull extends Value {
	public ValueNull(ValueClass type) {
		super(type);
	}

	@Override
	public ValueNull castNull() {
		return this;
	}
}
