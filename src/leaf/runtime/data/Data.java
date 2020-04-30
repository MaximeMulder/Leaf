package leaf.runtime.data;

public class Data {
	public DataArray asArray() {
		return (DataArray) this;
	}

	public DataBoolean asBoolean() {
		return (DataBoolean) this;
	}

	public DataFunction asFunction() {
		return (DataFunction) this;
	}

	public DataInstance asInstance() {
		return (DataInstance) this;
	}

	public DataInteger asInteger() {
		return (DataInteger) this;
	}

	public DataName asName() {
		return (DataName) this;
	}

	public DataReference asReference() {
		return (DataReference) this;
	}

	public DataString asString() {
		return (DataString) this;
	}

	public DataType asType() {
		return (DataType) this;
	}
}
