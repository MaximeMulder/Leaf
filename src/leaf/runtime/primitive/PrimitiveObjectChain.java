package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive3;
import leaf.runtime.exception.ErrorUndefined;
import leaf.runtime.reference.Reference;

public class PrimitiveObjectChain extends Primitive3 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getObject());
		parameters.add(engine.getTypes().getString());
	}

	@Override
	public Value execute(Engine engine, List<Reference> arguments) {
		Reference expression = arguments.get(0);
		String name = arguments.get(1).read().getData().asString().getPrimitive();
		Value self = expression.read();
		Value method = self.getType().getData().asType().getMethod(Index.name(name));
		if (method != null) {
			engine.setSelf(expression);
			return method;
		}

		throw new ErrorUndefined();
	}
}
