package leaf.structure;

public abstract class Data {
	Data() {}
	
	abstract void write(Value value);
	
	abstract Value read();
}
