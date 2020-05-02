package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.reference.Reference;

public abstract class Expression {
	public abstract Reference run(Engine engine);
}
