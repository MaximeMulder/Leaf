package leaf.runtime.data;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorArguments;
import leaf.runtime.value.Reference;
import leaf.runtime.value.Variable;

public class DataArray extends Data {
	private Value type;
	private List<Variable> elements;
	
	public DataArray(Value type, List<Value> values) {
		this.type = type;
		List<Variable> elements = new ArrayList<Variable>();
		for (Value value : values) {
			elements.add(new Variable(this.type, value));
		}
		
		this.elements = elements;
	}
	
	public Value getType() {
		return this.type;
	}

	public List<Variable> getElements() {
		return this.elements;
	}
	
	public Reference access(Engine engine, List<Value> arguments) {
		if (arguments.size() != 1) {
			throw new ErrorArguments();
		}
		
		return this.elements.get(arguments.get(0).cast(engine.getTypes().getInteger()).getData().asInteger().getPrimitive());
	}
}
