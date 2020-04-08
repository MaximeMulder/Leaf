package leaf.structure;

public class Variable implements IValue {
	private String name;
	private Value value;
	
	Variable(String name) {
		super();
		this.name = name;
		this.value = null;
	}
	
	@Override
	public Value read() {
		return this.value;
	}

	@Override
	public void write(Value value) {
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
}
