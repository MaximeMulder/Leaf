package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive4;
import leaf.runtime.data.DataType;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;
import leaf.runtime.reference.Variable;

public class PrimitiveTypeChain extends Primitive4 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getType());
		parameters.add(engine.getTypes().getString());
	}

	@Override
	public Reference execute(Engine engine, List<Reference> arguments) {
		Reference expression = arguments.get(0);
		String name = arguments.get(1).read().getData().asString().getPrimitive();
		Value self = expression.read();
		Value method = self.getType().getData().asType().getMethod(Index.name(name));
		if (method != null) {
			engine.setSelf(expression);
			return new Constant(method);
		}

		DataType data = self.getData().asType();
		Reference member = data.getStatic(Index.name(name));
		if (member == null) {
			member = new Variable(null, null);
			data.setStatic(Index.name(name), member);
		}

		return member;
	}
}
