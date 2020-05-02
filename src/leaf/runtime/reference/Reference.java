package leaf.runtime.reference;

import leaf.runtime.Value;
import leaf.runtime.exception.ErrorUndefined;

public abstract class Reference {
	protected Value type;
	protected Value value;
	
	public Reference(Value type, Value value) {
		if (value != null) {
			if (type != null) {
				this.type = type;
				value.cast(this.type);
			} else {
				this.type = value.getType();
			}
		} else {
			this.type = null;
		}

		this.value = value;
	}
	
	public Value read() {
		if (this.value == null) {
			throw new ErrorUndefined();
		}
		
		return this.value;
	}
	
	public abstract void write(Value object);
}
