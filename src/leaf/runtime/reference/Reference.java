package leaf.runtime.reference;

import leaf.runtime.Value;

public interface Reference {
	public Value read();
	public void write(Value object);
}
