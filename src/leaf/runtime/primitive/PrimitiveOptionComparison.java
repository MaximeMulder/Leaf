package leaf.runtime.primitive;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.callable.Primitive2;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public class PrimitiveOptionComparison extends Primitive2 {
	@Override
	public void parameters(Engine engine, List<Value> parameters) {
		parameters.add(engine.getTypes().getOption());
		parameters.add(engine.getTypes().getObject());
	}

	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		Value other = arguments.get(1);
		if (!other.isa(engine.getTypes().getOption())) {
			return new Constant(engine.getValues().getBooleanFalse());
		}

		Value left  = arguments.get(0).getData().asOption().getValue();
		Value right = other.getData().asOption().getValue();
		if (left == null && right == null) {
			return new Constant(engine.getValues().getBooleanTrue());
		}
		
		if (left == null || right == null) {
			return new Constant(engine.getValues().getBooleanFalse());
		}
		
		List<Reference> references = new ArrayList<Reference>();
		references.add(new Constant(right));
		return engine.callMethod(new Constant(left), Index.binary("=="), references);
	}
}
