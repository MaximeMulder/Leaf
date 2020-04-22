package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Value;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;

public class Loop extends Expression {
	private Expression body;
	
	public Loop(Expression body) {
		this.body = body;
	}
	
	@Override
	public IValue run(Engine engine) {
		List<Value> values = new ArrayList<Value>();
		while (true) {
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
		
		return engine.getValues().getArray(values);
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
}
