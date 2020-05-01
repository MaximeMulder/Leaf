package leaf.runtime.primitive;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ErrorArguments;
import leaf.runtime.value.Reference;

public abstract class Primitive implements Callable {	
	public Reference call(Engine engine, List<Value> arguments) {
		List<Value> parameters = new ArrayList<Value>();
		this.parameters(engine, parameters);
		if (parameters.size() != arguments.size()) {
			throw new ErrorArguments();
		}
		
		for (int i = 0; i < parameters.size(); i++) {
			arguments.get(i).cast(parameters.get(i));
		}
		
		return this.execute(engine, arguments);
	}

	public abstract void parameters(Engine engine, List<Value> parameters);
	public abstract Reference execute(Engine engine, List<Value> arguments);
}
