package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;
import leaf.runtime.reference.Variable;

public class Array extends Expression {
	private List<Expression> values;
	
	public Array(List<Expression> values) {
		this.values = values;
	}
	
	@Override
	public Reference run(Engine engine) {
		List<Variable> elements = new ArrayList<Variable>();
		for (Expression value : this.values) {
			elements.add(new Variable(engine.getTypes().getObject(), value.run(engine).read()));
		}
		
		return new Constant(engine.getValues().getArray(elements));
	}
	
	public void setValues(List<Expression> values) {
		this.values = values;
	}
}
