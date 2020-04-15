package leaf.runtime;

public class ValueString extends Value {
	private String primitive;
	
	public ValueString(ValueClass type, String primitive) {
		super(type);
		this.primitive = primitive;
	}
	
	public String getPrimitive() {
		return this.primitive;
	}
	
	@Override
	public ValueString castString() {
		return this;
	}
}
