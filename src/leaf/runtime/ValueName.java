package leaf.runtime;

public abstract class ValueName extends Value {
	private String name;
	
	protected ValueName(ValueClass type, String name) {
		super(type);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
