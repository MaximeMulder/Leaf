package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.value.ValueName;

public abstract class Structure extends Expression {
	@Override
	public abstract ValueName run(Engine engine);
}
