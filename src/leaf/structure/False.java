package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public class False extends Expression {
	@Override
	public Reference run(Engine engine) {
		return new Constant(engine.getValues().getBooleanFalse());
	}
}
