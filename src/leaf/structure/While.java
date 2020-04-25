package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;
import leaf.runtime.value.Value;

public class While extends Expression {
	private Expression condition;
	private Expression body;
	
	public While(Expression condition, Expression body) {
		this.condition = condition;
		this.body = body;
	}
	
	@Override
	public IValue run(Engine engine) {
		List<Value> values = new ArrayList<Value>();
		while (this.condition.run(engine).read().castBoolean().getPrimitive()) {
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
		}
		
		return engine.getValues().getArray(null, values);
	}
	
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
}
