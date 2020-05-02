package leaf.runtime.data;

import leaf.runtime.Value;

public class DataOption extends Data {
	public Value value;
	
	public DataOption(Value value) {
		this.value = value;
	}
	
	public Value getValue() {
		return this.value;
	}
}
