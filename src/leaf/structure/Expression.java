package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.value.Reference;

public abstract class Expression {
	public abstract Reference run(Engine engine);
}
