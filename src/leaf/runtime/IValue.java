package leaf.runtime;

public interface IValue {
	public void write(Value value);
	public Value read();
}
