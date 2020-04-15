package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;

public class While extends Expression {
	private Expression condition;
	private Expression body;
	
	public While(Expression condition, Expression body) {
		this.condition = condition;
		this.body = body;
	}
	
	@Override
	public IValue run(Engine engine) {
		try {
			while (this.condition.run(engine).read().castBoolean().getPrimitive()) {
				try {
					this.body.run(engine);
				} catch (ControlContinue control) {}
			}
		} catch (ControlBreak control) {}
		
		return null;
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
}
