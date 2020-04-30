package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Reference;

public class MethodArrayAccess extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 2;
	}

	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		return self.getData().asArray().getElements().get(arguments.get(0).cast(engine.getTypes().getInteger()).getData().asInteger().getPrimitive());
	}
}
