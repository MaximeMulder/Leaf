package leaf.runtime;

import leaf.runtime.value.Value;

public class Reference implements IValue {
	private Value value;
	
	public Reference(Value value) {
		this.value = value;
	}

	@Override
	public Value read() {
		return this.value;
	}

	@Override
	public void write(Value value) {
		this.value = value;
	}
}
