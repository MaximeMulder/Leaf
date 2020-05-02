package leaf.runtime;

import leaf.runtime.data.Data;
import leaf.runtime.exception.ErrorType;

public class Value {
	private Value type;
	private Data data;

	public Value(Value type, Data data) {
		this.type = type;
		this.data = data;
	}

	public Value getType() {
		return this.type;
	}

	public void setType(Value type) {
		this.type = type;
	}

	public Data getData() {
		return this.data;
	}

	public boolean isa(Value type) {
		Value self = this.type;
		while (self != null) {
			if (self == type) {
				return true;
			}

			self = self.getData().asType().getParent();
		};
		
		return false;
	}

	public Value cast(Value type) {
		if (!this.isa(type)) {
			throw new ErrorType();
		}
		
		return this;
	}
}
