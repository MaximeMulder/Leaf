package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;
import leaf.runtime.value.Value;

public class DoWhile extends Expression {
	private Expression body;
	private Expression condition;
	
	public DoWhile(Expression body, Expression condition) {
		this.body = body;
		this.condition = condition;
	}
	
	@Override
	public IValue run(Engine engine) {
		List<Value> values = new ArrayList<Value>();
		do {
			Value value = null;
			try {
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
				if (value != null) {
					values.add(value);
				}
			}
		} while (this.condition.run(engine).read().castBoolean().getPrimitive());
		
		return engine.getValues().getArray(values);
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
	
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
}
