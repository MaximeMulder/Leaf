package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive1;
import leaf.runtime.reference.Variable;

public class PrimitiveArrayInsert extends Primitive1 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getArray());
		parameters.add(engine.getTypes().getInteger());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		arguments.get(0).getData().asArray().getElements().add(arguments.get(1).getData().asInteger().getPrimitive(), new Variable(engine.getTypes().getObject(), arguments.get(2)));
		return null;
	}
}
