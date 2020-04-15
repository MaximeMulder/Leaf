package leaf.runtime;

public class Variable implements IValue {
	private String name;
	private Value value;
	
	public Variable(String name, Value value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
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
