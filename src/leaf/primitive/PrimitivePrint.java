package leaf.primitive;

import java.util.List;

import leaf.structure.Engine;
import leaf.structure.Value;
import leaf.structure.ValueFunction;

public class PrimitivePrint extends ValueFunction {
	@Override
	public boolean arguments(List<Value> arguments) {
		return true;
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		for (Value argument : arguments) {
			System.out.println(engine.castString(argument).getPrimitive());
		}
		
		return null;
	}
}
