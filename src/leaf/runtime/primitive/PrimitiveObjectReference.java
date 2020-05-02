package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.callable.Callable;
import leaf.runtime.exception.ErrorArguments;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public class PrimitiveObjectReference implements Callable {
	@Override
	public Reference call(Engine engine, List<Reference> arguments) {
		if (arguments.size() != 1) {
			throw new ErrorArguments();
		}
		
		return new Constant(engine.getValues().getReference(arguments.get(0)));
	}
}
