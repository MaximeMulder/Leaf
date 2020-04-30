package leaf.runtime.data;

public class DataBoolean extends Data {
	private boolean primitive;
	
	public DataBoolean(boolean primitive) {
		this.primitive = primitive;
	}
	
	public boolean getPrimitive() {
		return this.primitive;
	}
}
