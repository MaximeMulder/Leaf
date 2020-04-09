package leaf.structure;

public class ValueNull extends Value {
	@Override
	public ValueClass getType(Engine engine) {
		return null;
	}
	
	@Override
	public ValueNull castNull(Engine engine) {
		return this;
	}
}
