package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;

public class False extends Expression {
	@Override
	public IValue run(Engine engine) {
		return engine.getValues().getBooleanFalse();
	}
}
