package leaf.callable;

import java.util.List;

import leaf.structure.Callable;
import leaf.structure.Engine;
import leaf.structure.Value;

public class MethodBooleanToString extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		return engine.getValues().getString(Boolean.toString(arguments.get(0).castBoolean().getPrimitive()));
	}
}
