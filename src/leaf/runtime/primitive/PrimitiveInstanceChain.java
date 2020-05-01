package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.data.DataInstance;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;
import leaf.runtime.value.Variable;

public class PrimitiveInstanceChain extends Primitive {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getInstance());
		parameters.add(engine.getTypes().getString());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		String name = arguments.get(1).getData().asString().getPrimitive();
		Value self = arguments.get(0);
		Value method = self.getType().getData().asType().getMethod(Index.name(name));
		if (method != null) {
			engine.setSelf(self);
			return new Constant(method);
		}

		DataInstance data = self.getData().asInstance();
		Variable member = data.getAttribute(name);
		if (member == null) {
			member = new Variable(null, null);
			data.setAttribute(name, member);
		}

		return member;
	}
}
