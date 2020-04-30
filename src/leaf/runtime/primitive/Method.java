package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Reference;

public abstract class Method extends Callable {
	@Override
	public Reference execute(Engine engine, List<Value> arguments) {
		return this.execute(engine, arguments.get(0), arguments.subList(1, arguments.size()));
	};
	
	public abstract Reference execute(Engine engine, Value self, List<Value> arguments);
}
