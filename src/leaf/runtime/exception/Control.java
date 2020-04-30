package leaf.runtime.exception;

import leaf.runtime.Value;

public abstract class Control extends RuntimeException {
	private static final long serialVersionUID = -4893149490447761996L;
	private Value value;
	
	public Control(Value value) {
		this.value = value;
	}
	
	public Value getValue() {
		return this.value;
	}
}
