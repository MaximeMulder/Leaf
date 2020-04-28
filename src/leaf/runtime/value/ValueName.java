package leaf.runtime.value;

public abstract class ValueName extends Value {
	private String name;
	
	protected ValueName(ValueType type, String name) {
		super(type);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
