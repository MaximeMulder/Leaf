package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorUndefined;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class MethodObjectChain extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 2;
	}

	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		Value method = self.getType().getData().asType().getMethod(Index.name(arguments.get(0).getData().asString().getPrimitive()));
		if (method != null) {
			engine.setSelf(self);
			return new Constant(method);
		}

		throw new ErrorUndefined();
	}
}
