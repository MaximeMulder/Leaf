package leaf.runtime;

import java.util.List;

import leaf.runtime.exception.Control;
import leaf.runtime.exception.ControlReturn;
import leaf.runtime.exception.ErrorControl;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;
import leaf.structure.Expression;
import leaf.structure.Declaration;

public class Function extends Callable {
	Scope scope;
	Value type;
	List<Declaration> parameters;
	Expression body;
	
	public Function(Scope scope, Value type, List<Declaration> parameters, Expression body) {
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
	public Reference execute(Engine engine, List<Value> arguments) {
		Scope scope = engine.pushFrame(this.scope);
		for (int i = 0; i < this.parameters.size(); i++) {
			this.parameters.get(i).run(engine).write(arguments.get(i));
		}
		
		Value result = null;
		try {
			this.body.run(engine);
		} catch (ControlReturn control) {
			result = control.getValue().cast(this.type);
		} catch (Control control) {
			throw new ErrorControl();
		} finally {
			engine.popFrame(scope);
		}
		
		return new Constant(result);
	}
}
