package leaf.structure;

public class ValueInteger extends Value {
	private int primitive;
	
	public ValueInteger(ValueClass type, int primitive) {
		super(type);
		this.primitive = primitive;
	}
	
	@Override
	public ValueInteger castInteger() {
		return this;
	}
	
	public int getPrimitive() {
		return this.primitive;
	}
}
