package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.value.Value;

public class Array extends Expression {
	private List<Expression> values;
	
	public Array(List<Expression> values) {
		this.values = values;
	}
	
	@Override
	public IValue run(Engine engine) {
		List<Value> values = new ArrayList<Value>();
		for (Expression value : this.values) {
			values.add(value.run(engine).read());
		}
		
		return engine.getValues().getArray(values);
	}
	
	public void setValues(List<Expression> values) {
		this.values = values;
	}
}
