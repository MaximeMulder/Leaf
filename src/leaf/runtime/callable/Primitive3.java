package leaf.runtime.callable;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorArguments;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public abstract class Primitive3 implements Callable {
	@Override
	public Reference call(Engine engine, List<Reference> arguments) {
		List<Value> types = new ArrayList<Value>();
		this.parameters(engine, types);
		if (types.size() != arguments.size()) {
			throw new ErrorArguments();
		}

		for (int i = 0; i < arguments.size(); i++) {
			arguments.get(i).read().cast(types.get(i));
		}
		
		return new Constant(this.execute(engine, arguments));
	}

	public abstract void parameters(Engine engine, List<Value> parameters);
	public abstract Value execute(Engine engine, List<Reference> arguments);
}
