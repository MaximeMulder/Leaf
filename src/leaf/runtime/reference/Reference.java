package leaf.runtime.reference;

import leaf.runtime.Value;
import leaf.runtime.exception.ErrorUndefined;

public abstract class Reference {
	protected Value type;
	protected Value value;
	
	public Reference(Value type, Value value) {
		this.type = type;
		if (value != null) {
			if (this.type != null) {
				value.cast(this.type);
			} else {
				this.type = value.getType();
			}
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
