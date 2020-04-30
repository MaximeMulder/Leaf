package leaf.runtime.value;

import leaf.runtime.Value;

public interface Reference {
	public Value read();
	public void write(Value object);
}
