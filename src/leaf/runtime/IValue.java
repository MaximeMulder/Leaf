package leaf.runtime;

import leaf.runtime.value.Value;

public interface IValue {
	public void write(Value value);
	public Value read();
}
