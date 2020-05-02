package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public class True extends Expression {
	@Override
	public Reference run(Engine engine) {
		return new Constant(engine.getValues().getBooleanTrue());
	}
}
