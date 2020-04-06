package leaf.structure;

public class ValueInteger extends Value {
	private int primitive;
	
	ValueInteger(int primitive) {
		super();
		this.primitive = primitive;
	}

	@Override
	ValueClass getType(Engine engine) {
		return engine.getTypeInteger();
	}
	
	@Override
	ValueInteger castInteger(Engine engine) {
		return this;
	}
	
	public int getPrimitive() {
		return this.primitive;
	}
}
