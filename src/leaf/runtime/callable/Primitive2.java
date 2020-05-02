package leaf.runtime.callable;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorArguments;
import leaf.runtime.reference.Reference;

public abstract class Primitive2 implements Callable {
	@Override
	public Reference call(Engine engine, List<Reference> arguments) {
		List<Value> types = new ArrayList<Value>();
		this.parameters(engine, types);
		if (types.size() != arguments.size()) {
			throw new ErrorArguments();
		}

		List<Value> parameters = new ArrayList<Value>();
		for (int i = 0; i < arguments.size(); i++) {
			parameters.add(arguments.get(i).read().cast(types.get(i)));
		}
		
		return this.execute(engine, parameters);
	}

	public abstract void parameters(Engine engine, List<Value> parameters);
	public abstract Reference execute(Engine engine, List<Value> arguments);
}
