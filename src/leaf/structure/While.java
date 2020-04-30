package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Value;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

public class While extends Expression {
	private Expression condition;
	private Expression body;
	
	public While(Expression condition, Expression body) {
		this.condition = condition;
		this.body = body;
	}
	
	@Override
	public Reference run(Engine engine) {
		List<Value> results = new ArrayList<Value>();
		while (this.condition.run(engine).read().cast(engine.getTypes().getBoolean()).getData().asBoolean().getPrimitive()) {
			Value result = null;
			try {
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
				if (result != null) {
					results.add(result);
				}
			}
		}
		
		return new Constant(engine.getValues().getArray(null, results));
	}
	
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
}
