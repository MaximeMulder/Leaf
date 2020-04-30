package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class MethodStringOrderLesser extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 2;
	}

	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		return new Constant(engine.getValues().getBoolean(self.getData().asString().getPrimitive().compareTo(arguments.get(0).cast(engine.getTypes().getString()).getData().asString().getPrimitive()) < 0));
	}
}
