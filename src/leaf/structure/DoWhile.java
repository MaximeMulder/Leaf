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
		List<Value> results = new ArrayList<Value>();
		do {
			Value result = null;
			try {
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
				if (result != null) {
					results.add(result);
				}
			}
		} while (this.condition.run(engine).read().castBoolean().getPrimitive());
		
		return engine.getValues().getArray(null, results);
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
	
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
}
