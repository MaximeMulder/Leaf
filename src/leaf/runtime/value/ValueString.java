package leaf.runtime.value;

public class ValueString extends Value {
	private String primitive;
	
	public ValueString(ValueClass type, String primitive) {
		super(type);
		this.primitive = primitive;
	}
	
	@Override
	public ValueString castString() {
		return this;
	}
	
	public String getPrimitive() {
		return this.primitive;
	}
}
