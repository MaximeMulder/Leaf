package leaf.runtime.primitive;

import java.util.List;

import leaf.runtime.*;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class MethodStringAddition extends Method {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 2;
	}

	@Override
	public Reference execute(Engine engine, Value self, List<Value> arguments) {
		return new Constant(engine.getValues().getString(self.getData().asString().getPrimitive() + arguments.get(0).cast(engine.getTypes().getString()).getData().asString().getPrimitive()));
	}
}
