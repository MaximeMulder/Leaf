package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class Array extends Expression {
	private List<Expression> values;
	
	public Array(List<Expression> values) {
		this.values = values;
	}
	
	@Override
	public Reference run(Engine engine) {
		List<Value> values = new ArrayList<Value>();
		for (Expression value : this.values) {
			values.add(value.run(engine).read());
		}
		
		return new Constant(engine.getValues().getArray(null, values));
	}
	
	public void setValues(List<Expression> values) {
		this.values = values;
	}
}
