package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorUndefined;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class PrimitiveObjectChain extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getObject());
		parameters.add(engine.getTypes().getString());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		Value self = arguments.get(0);
		Value method = self.getType().getData().asType().getMethod(Index.name(arguments.get(1).getData().asString().getPrimitive()));
		if (method != null) {
			engine.setSelf(self);
			return new Constant(method);
		}

		throw new ErrorUndefined();
	}
}
