package leaf.structure;

public class ValueString extends Value {
	private String primitive;
	
	public ValueString(String primitive) {
		super();
		this.primitive = primitive;
	}

	@Override
	public ValueClass getType(Engine engine) {
		return engine.getTypeString();
	}
	
	@Override
	public ValueString castString(Engine engine) {
		return this;
	}
	
	public String getPrimitive() {
		return this.primitive;
	}
}
