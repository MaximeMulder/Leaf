package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Value;
import leaf.runtime.Variable;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;

public class For extends Expression {
	private String element;
	private Expression array;
	private Expression body;
	
	public For(String element, Expression array, Expression body) {
		this.element = element;
		this.array = array;
		this.body = body;
	}
	
	@Override
	public IValue run(Engine engine) {
		List<Value> values = new ArrayList<Value>();
		for (Variable element : this.array.run(engine).read().castArray().getElements()) {
			Value value = null;
			try {
				engine.pushScope();
				engine.setVariable(this.element, element.read());
				IValue variable = this.body.run(engine);
				if (variable != null) {
					value = variable.read();
				}
			} catch (ControlContinue control) {
				value = control.getValue();
				continue;
			} catch (ControlBreak control) {
				value = control.getValue();
				break;
			} finally {
				engine.popScope();
				if (value != null) {
					values.add(value);
				}
			}
		}
		
		return engine.getValues().getArray(values);
	}

	public void setElement(String element) {
		this.element = element;
	}

	public void setArray(Expression array) {
		this.array = array;
	}

	public void setBody(Expression body) {
		this.body = body;
	}
}
