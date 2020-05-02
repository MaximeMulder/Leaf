package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;
import leaf.runtime.reference.Variable;

public class For extends Expression {
	private Declaration element;
	private Expression array;
	private Expression body;
	
	public For(Declaration element, Expression array, Expression body) {
		this.element = element;
		this.array = array;
		this.body = body;
	}
	
	@Override
	public Reference run(Engine engine) {
		List<Variable> results = new ArrayList<Variable>();
		for (Reference element : this.array.run(engine).read().cast(engine.getTypes().getArray()).getData().asArray().getElements()) {
			Value result = null;
			try {
				engine.pushScope();
				this.element.run(engine).write(element.read());
				Reference reference = this.body.run(engine);
				if (reference != null) {
					result = reference.read();
				}
			} catch (ControlContinue control) {
				result = control.getValue();
				continue;
			} catch (ControlBreak control) {
				result = control.getValue();
				break;
			} finally {
				engine.popScope();
				if (result != null) {
					results.add(new Variable(engine.getTypes().getObject(), result));
				}
			}
		}
		
		return new Constant(engine.getValues().getArray(results));
	}

	public void setElement(Declaration element) {
		this.element = element;
	}

	public void setArray(Expression array) {
		this.array = array;
	}

	public void setBody(Expression body) {
		this.body = body;
	}
}
