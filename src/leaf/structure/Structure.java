package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.reference.Reference;

public abstract class Structure extends Expression {
	@Override
	public abstract Reference run(Engine engine);
}
