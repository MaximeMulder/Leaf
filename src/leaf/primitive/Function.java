package leaf.primitive;

import java.util.List;

import leaf.exception.ControlReturn;
import leaf.language_leaf.NBlock;
import leaf.structure.Engine;
import leaf.structure.Scope;
import leaf.structure.Value;
import leaf.structure.ValueFunction;

public class Function extends ValueFunction {
	Scope scope;
	List<String> parameters;
	NBlock body;
	
	public Function(String name, Scope scope, List<String> parameters, NBlock body) {
		super(name);
		this.scope = scope;
		this.parameters = parameters;
		this.body = body;
	}

	@Override
	public boolean arguments(List<Value> arguments) {
		return this.parameters.size() == arguments.size();
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		engine.setFrame(this.scope);
		for (int i = 0; i < this.parameters.size(); i++) {
			engine.setVariable(this.parameters.get(i), arguments.get(i));
		}
		
		Value value = null;
		
		try {
			engine.visit(this.body);
		} catch (ControlReturn control) {
			value = control.getValue();
		}
		
		engine.setScope(this.scope);
			
		return value;
	}
}
