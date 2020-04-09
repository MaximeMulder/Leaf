package leaf.structure;

public class ValueInteger extends Value {
	private int primitive;
	
	public ValueInteger(int primitive) {
		super();
		this.primitive = primitive;
	}

	@Override
	public ValueClass getType(Engine engine) {
		return engine.getTypeInteger();
	}
	
	@Override
	public ValueInteger castInteger(Engine engine) {
		return this;
	}
	
	public int getPrimitive() {
		return this.primitive;
	}
}
