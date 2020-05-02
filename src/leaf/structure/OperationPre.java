package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.reference.Reference;

public class OperationPre extends Expression {
	private Expression expression;
	private String operator;
	
	public OperationPre(Expression expression, String operator) {
		this.expression = expression;
		this.operator = operator;
	}
	
	@Override
	public Reference run(Engine engine) {
		return engine.callMethod(this.expression.run(engine), Index.pre(this.operator));
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
