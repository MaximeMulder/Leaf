package leaf.structure;

public class ValueNull extends Value {
	@Override
	ValueClass getType(Engine engine) {
		return null;
	}
	
	@Override
	ValueNull castNull(Engine engine) {
		return this;
	}
}
