package leaf.structure;

public class ValueBoolean extends Value {
	private boolean primitive;
	
	public ValueBoolean(boolean primitive) {
		super();
		this.primitive = primitive;
	}

	@Override
	ValueClass getType(Engine engine) {
		return engine.getTypeBoolean();
	}
	
	@Override
	ValueBoolean castBoolean(Engine engine) {
		return this;
	}
	
	public boolean getPrimitive() {
		return this.primitive;
	}
}
