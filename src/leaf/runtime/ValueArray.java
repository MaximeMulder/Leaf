package leaf.runtime;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.exception.ErrorArguments;

public class ValueArray extends Value {
	private List<Variable> elements;
	
	public ValueArray(ValueClass type, List<Value> values) {
		super(type);
		List<Variable> elements = new ArrayList<Variable>();
		for (Value value : values) {
			elements.add(new Variable(null, value));
		}
		
		this.elements = elements;
	}
	
	@Override
	public ValueArray castArray() {
		return this;
	}
	
	public List<Variable> getElements() {
		return this.elements;
	}
	
	public IValue access(List<Value> arguments) {
		if (arguments.size() != 1) {
			throw new ErrorArguments();
		}
		
		return this.elements.get(arguments.get(0).castInteger().getPrimitive());
	}
}
