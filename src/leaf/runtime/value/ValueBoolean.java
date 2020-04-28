package leaf.runtime.value;

public class ValueBoolean extends Value {
	private boolean primitive;
	
	public ValueBoolean(ValueType type, boolean primitive) {
		super(type);
		this.primitive = primitive;
	}
	
	@Override
	public ValueBoolean castBoolean() {
		return this;
	}
	
	public boolean getPrimitive() {
		return this.primitive;
	}
}
