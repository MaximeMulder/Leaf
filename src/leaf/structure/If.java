package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

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
	public Reference run(Engine engine) {
		if (this.condition.run(engine).read().cast(engine.getTypes().getBoolean()).getData().asBoolean().getPrimitive()) {
			return this.accept.run(engine);
		} else if (this.reject != null) {
			return this.reject.run(engine);
		}
		
		return new Constant(null);
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
