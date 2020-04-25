package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;
import leaf.runtime.value.Value;

public class Loop extends Expression {
	private Expression body;
	
	public Loop(Expression body) {
		this.body = body;
	}
	
	@Override
	public IValue run(Engine engine) {
		List<Value> results = new ArrayList<Value>();
		while (true) {
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
		}
		
		return engine.getValues().getArray(null, results);
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
}
