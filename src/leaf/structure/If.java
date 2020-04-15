package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;

public class If extends Expression {
	private Expression condition;
	private Expression accept;
	private Expression reject;
	
	public If(Expression condition, Expression accept, Expression reject) {
		this.condition = condition;
		this.accept = accept;
		this.reject = reject;
	}
	
	@Override
	public IValue run(Engine engine) {
		if (this.condition.run(engine).read().castBoolean().getPrimitive()) {
			return this.accept.run(engine);
		} else if (this.reject != null) {
			return this.reject.run(engine);
		}
		
		return null;
	}
	
	public void setCondition(Expression condition) {
		this.condition = condition;
	}
	
	public void setAccept(Expression accept) {
		this.accept = accept;
	}
	
	public void setReject(Expression reject) {
		this.reject = reject;
	}
}
