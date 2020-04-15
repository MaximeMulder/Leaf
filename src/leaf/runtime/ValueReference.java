package leaf.runtime;

public class ValueReference extends Value {
	private Value value;
	
	public ValueReference(ValueClass type, Value value) {
		super(type);
		this.value = value;
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
