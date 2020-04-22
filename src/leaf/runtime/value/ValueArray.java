package leaf.runtime.value;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Reference;
import leaf.runtime.exception.ErrorArguments;

public class ValueArray extends Value {
	private List<Reference> elements;
	
	public ValueArray(ValueClass type, List<Value> values) {
		super(type);
		List<Reference> elements = new ArrayList<Reference>();
		for (Value value : values) {
			elements.add(new Reference(value));
		}
		
		this.elements = elements;
	}
	
	@Override
	public ValueArray castArray() {
		return this;
	}
	
	public List<Reference> getElements() {
		return this.elements;
	}
	
	public Reference access(List<Value> arguments) {
		if (arguments.size() != 1) {
			throw new ErrorArguments();
		}
		
		return this.elements.get(arguments.get(0).castInteger().getPrimitive());
	}
}