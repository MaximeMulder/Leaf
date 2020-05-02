package leaf.runtime.reference;

import leaf.runtime.Value;
import leaf.runtime.exception.ErrorWrite;

public class Constant extends Reference {	
	public Constant(Value value) {
		super(null, value);
	}
	
	@Override
	public Value read() {
		return this.value;
	}

	@Override
	public void write(Value object) {
		throw new ErrorWrite();
	}
}
