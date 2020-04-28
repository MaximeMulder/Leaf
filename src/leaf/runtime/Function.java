package leaf.runtime;

import java.util.List;

import leaf.runtime.exception.Control;
import leaf.runtime.exception.ControlReturn;
import leaf.runtime.exception.ErrorControl;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueType;
import leaf.structure.Expression;
import leaf.structure.Variable;

public class Function extends Callable {
	Scope scope;
	ValueType type;
	List<Variable> parameters;
	Expression body;
	
	public Function(Scope scope, ValueType type, List<Variable> parameters, Expression body) {
		this.scope = scope;
		this.type = type;
		this.parameters = parameters;
		this.body = body;
	}

	@Override
	public boolean arguments(List<Value> arguments) {
		return this.parameters.size() == arguments.size();
	}
	
	@Override
	public Value execute(Engine engine, List<Value> arguments) {
		Scope scope = engine.pushFrame(this.scope);
		for (int i = 0; i < this.parameters.size(); i++) {
			this.parameters.get(i).run(engine).write(arguments.get(i));
		}
		
		Reference result = new Reference(this.type, null);
		
		try {
			this.body.run(engine);
		} catch (ControlReturn control) {
			result.write(control.getValue());
		} catch (Control control) {
			throw new ErrorControl();
		}
		
		engine.popFrame(scope);
		
		return result.read();
	}
}
