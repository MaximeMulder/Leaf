package leaf.structure;

public class ValueString extends Value {
	private String primitive;
	
	ValueString(String primitive) {
		super();
		this.primitive = primitive;
	}

	@Override
	ValueClass getType(Engine engine) {
		return engine.getTypeString();
	}
	
	@Override
	ValueString castString(Engine engine) {
		return this;
	}
	
	public String getPrimitive() {
		return this.primitive;
	}
}
