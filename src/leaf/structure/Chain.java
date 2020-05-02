package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public class Chain extends Expression {
	private Expression expression;
	private String member;

	public Chain(Expression expression, String member) {
		this.expression = expression;
		this.member = member;
	}
	
	@Override
	public Reference run(Engine engine) {
		Reference expression = this.expression.run(engine);
		Constant name = new Constant(engine.getValues().getString(this.member));
		List<Reference> arguments = new ArrayList<Reference>();
		arguments.add(name);
		return engine.callMethod(expression, Index.special("."), arguments);
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	public void setMember(String member) {
		this.member = member;
	}
}
