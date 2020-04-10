package leaf.callable;

import java.util.List;

import leaf.structure.Callable;
import leaf.structure.Engine;
import leaf.structure.Value;

public class PrimitivePrint extends Callable {
	@Override
	public boolean arguments(List<Value> arguments) {
		return arguments.size() == 1;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		System.out.println(arguments.get(0).castString().getPrimitive());
		
		return null;
	}
}
