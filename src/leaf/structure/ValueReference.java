package leaf.structure;

public class ValueReference extends Value {
	private Value value;
	
	ValueReference(Value value) {
		super();
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

	@Override
	ValueClass getType(Engine engine) {
		return engine.getTypeReference();
	}
}
