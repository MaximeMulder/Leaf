package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.data.DataInstance;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;
import leaf.runtime.value.Variable;

public class MethodInstanceChain extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 2;
	}
	
	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		String name = arguments.get(0).getData().asString().getPrimitive();
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
