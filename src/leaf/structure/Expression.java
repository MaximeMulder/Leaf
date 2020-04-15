package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;

public abstract class Expression {
	public abstract IValue run(Engine engine);
}
