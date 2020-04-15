package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Value;
import leaf.runtime.ValueFunction;
import leaf.runtime.ValueInstance;
import leaf.runtime.Variable;

public class Chain extends Expression {
	private Expression expression;
	private String member;

	public Chain(Expression expression, String member) {
		this.expression = expression;
		this.member = member;
	}
	
	@Override
	public IValue run(Engine engine) {
		Value value = expression.run(engine).read();
		ValueFunction method = value.getType().getMethod(this.member);
		if (method != null) {
			engine.setSelf(value);
			return method;
		}
		
		if (value instanceof ValueInstance) {
			ValueInstance instance = value.castInstance();
			Variable attribute = instance.getAttribute(this.member);
			if (attribute == null) {
				attribute = engine.getValues().getVariable(this.member);
				instance.setAttribute(attribute);
			}

			return attribute;
		}

		return null;
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public void setMember(String member) {
		this.member = member;
	}
}
