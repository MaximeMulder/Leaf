package leaf.structure;

public class ValueBoolean extends Value {
	private boolean primitive;
	
	public ValueBoolean(ValueClass type, boolean primitive) {
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
