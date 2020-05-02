package leaf.runtime.reference;

import leaf.runtime.Value;
import leaf.runtime.exception.ErrorWrite;

public class Constant implements Reference {
	private Value value;
	
	public Constant(Value value) {
		this.value = value;
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
