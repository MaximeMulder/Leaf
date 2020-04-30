package leaf.runtime.data;

public class DataInteger extends Data {
	private int primitive;
	
	public DataInteger(int primitive) {
		this.primitive = primitive;
	}
	
	public int getPrimitive() {
		return this.primitive;
	}
}
