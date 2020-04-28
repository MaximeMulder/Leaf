package leaf.runtime.value;

public class ValueReference extends Value {
	private Value value;
	
	public ValueReference(ValueType type, Value value) {
		super(type);
		this.value = value;
	}
	
	@Override
	public ValueReference castReference() {
		return this;
	}
	
	@Override
	public Value read() {
		return this.value;
	}
	
	@Override
	public void write(Value value) {
		this.value = value;
	}
}
