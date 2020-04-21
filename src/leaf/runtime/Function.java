package leaf.runtime;

import java.util.List;

import leaf.runtime.exception.Control;
import leaf.runtime.exception.ControlReturn;
import leaf.runtime.exception.ErrorControl;
import leaf.structure.Expression;

public class Function extends Callable {
	Scope scope;
	List<String> parameters;
	Expression body;
	
	public Function(Scope scope, List<String> parameters, Expression body) {
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
			this.body.run(engine);
		} catch (ControlReturn control) {
			value = control.getValue();
		} catch (Control control) {
			throw new ErrorControl();
		}
		
		engine.setScope(this.scope);
		
		return value;
	}
}
