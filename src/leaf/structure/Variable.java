package leaf.structure;

public class Variable extends Data {
	private String name;
	private Value value;
	
	Variable(String name) {
		super();
		this.name = name;
		this.value = null;
	}
	
	@Override
	Value read() {
		return this.value;
	}

	@Override
	void write(Value value) {
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
}
