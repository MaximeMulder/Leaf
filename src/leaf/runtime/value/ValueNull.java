package leaf.runtime.value;

public class ValueNull extends Value {
	public ValueNull(ValueType type) {
		super(type);
	}
	
	@Override
	public ValueNull castNull() {
		return this;
	}
}
