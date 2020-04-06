package leaf.structure;

public class ValueNull extends Value {
	ValueNull() {
		super();
	}

	@Override
	ValueClass getType(Engine engine) {
		return null;
	}
	
	@Override
	ValueNull castNull(Engine engine) {
		return this;
	}
}
