package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.reference.Reference;

public class OperationBinary extends Expression {
	private Expression left;
	private Expression right;
	private String operator;
	
	public OperationBinary(Expression left, Expression right, String operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	@Override
	public Reference run(Engine engine) {
		Reference left =  this.left.run(engine);
		Reference right = this.right.run(engine);
		List<Reference> arguments = new ArrayList<Reference>();
		arguments.add(left);
		arguments.add(right);
		return engine.callFunction(left, Index.binary(this.operator), arguments);
	}
	
	public void setLeft(Expression left) {
		this.left = left;
	}
	
	public void setRight(Expression right) {
		this.right = right;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
