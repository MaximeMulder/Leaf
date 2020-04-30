package leaf.runtime.data;

public class DataString extends Data {
	private String primitive;
	
	public DataString(String primitive) {
		this.primitive = primitive;
	}
	
	public String getPrimitive() {
		return this.primitive;
	}
}
