package leaf.runtime;

public class Index {
	private static enum Type {
		NAME,
		PRE,
		POST,
		BINARY,
		SPECIAL,
	};
	
	private String string;
	private Type type;
	
	private Index(Type type, String string) {
		this.string = string;
		this.type = type;
	}
	
	public static Index name(String string) {
		return new Index(Type.NAME, string);
	}
	
	public static Index pre(String string) {
		return new Index(Type.PRE, string);
	}
	
	public static Index post(String string) {
		return new Index(Type.POST, string);
	}
	
	public static Index binary(String string) {
		return new Index(Type.BINARY, string);
	}
	
	public static Index special(String string) {
		return new Index(Type.SPECIAL, string);
	}
	
	@Override
	public boolean equals(Object object) {
		Index index = (Index) object;
		return this.string.equals(index.string) && this.type == index.type;
	}
	
    @Override
    public int hashCode() {
		return this.type.hashCode() * this.string.hashCode();
    }
    
    public String toString() {
    	return this.type.toString() + " " + this.string;
    }
}
