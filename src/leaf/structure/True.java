package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class True extends Expression {
	@Override
	public Reference run(Engine engine) {
		return new Constant(engine.getValues().getBooleanTrue());
	}
}
