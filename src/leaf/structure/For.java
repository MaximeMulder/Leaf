package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Reference;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;
import leaf.runtime.value.Value;

public class For extends Expression {
	private Variable element;
	private Expression array;
	private Expression body;
	
	public For(Variable element, Expression array, Expression body) {
		this.element = element;
		this.array = array;
		this.body = body;
	}
	
	@Override
	public IValue run(Engine engine) {
		List<Value> results = new ArrayList<Value>();
		for (Reference element : this.array.run(engine).read().castArray().getElements()) {
			Value result = null;
			try {
				engine.pushScope();
				this.element.run(engine).write(element.read());
				IValue value = this.body.run(engine);
				if (value != null) {
					result = value.read();
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
					results.add(result);
				}
			}
		}
		
		return engine.getValues().getArray(null, results);
	}

	public void setElement(Variable element) {
		this.element = element;
	}

	public void setArray(Expression array) {
		this.array = array;
	}

	public void setBody(Expression body) {
		this.body = body;
	}
}
