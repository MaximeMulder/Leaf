package leaf.runtime;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.data.Data;
import leaf.runtime.exception.ErrorType;
import leaf.runtime.value.Reference;

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
	
	public Reference callMethod(Index index, Engine engine) {
		return this.callMethod(index, engine, new ArrayList<Value>());
	}
	
	public Reference callMethod(Index index, Engine engine, List<Value> arguments) {
		arguments = new ArrayList<Value>(arguments);
		arguments.add(0, this);
		Value self = engine.getSelf();
		if (self != null) {
			arguments.add(self);
		}
		
		return this.getType().getData().asType().getMethod(index).getData().asFunction().getCallable().call(engine, arguments);
	}
}
