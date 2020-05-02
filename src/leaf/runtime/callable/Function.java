package leaf.runtime.callable;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Scope;
import leaf.runtime.Value;
import leaf.runtime.exception.Control;
import leaf.runtime.exception.ControlReturn;
import leaf.runtime.exception.ErrorArguments;
import leaf.runtime.exception.ErrorControl;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;
import leaf.structure.Expression;
import leaf.structure.Declaration;

public class Function implements Callable {
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
	public Reference call(Engine engine, List<Reference> arguments) {
		if (this.parameters.size() != arguments.size()) {
			throw new ErrorArguments();
		}

		Scope scope = engine.pushFrame(this.scope);
		for (int i = 0; i < this.parameters.size(); i++) {
			this.parameters.get(i).run(engine).write(arguments.get(i).read());
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
