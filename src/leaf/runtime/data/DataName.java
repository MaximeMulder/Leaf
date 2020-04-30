package leaf.runtime.data;

public abstract class DataName extends Data {
	private String name;
	
	protected DataName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
