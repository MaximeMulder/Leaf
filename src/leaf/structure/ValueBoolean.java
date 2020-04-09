package leaf.structure;

public class ValueBoolean extends Value {
	private boolean primitive;
	
	public ValueBoolean(boolean primitive) {
		super();
		this.primitive = primitive;
	}

	@Override
	public ValueClass getType(Engine engine) {
		return engine.getTypeBoolean();
	}
	
	@Override
	public ValueBoolean castBoolean(Engine engine) {
		return this;
	}
	
	public boolean getPrimitive() {
		return this.primitive;
	}
}
