package leaf.runtime.primitive;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;
import leaf.runtime.reference.Reference;
import leaf.runtime.reference.Variable;

public class PrimitiveArrayCopy extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getArray());
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		List<Variable> elements = new ArrayList<Variable>();
		for (Reference element : arguments.get(0).getData().asArray().getElements()) {
			elements.add(new Variable(engine.getTypes().getObject(), element.read()));
		}

		return engine.getValues().getArray(elements);
	}
}
