package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Value;
import leaf.runtime.value.Reference;

public class Operation extends Expression {
	private String operator;
	private Expression left;
	private Expression right;
	
	public Operation(String operator, Expression left, Expression right) {
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public Reference run(Engine engine) {
		List<Value> arguments = new ArrayList<Value>();
		arguments.add(this.right.run(engine).read());
		return this.left.run(engine).read().callMethod(Index.binary(this.operator), engine, arguments);
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public void setLeft(Expression left) {
		this.left = left;
	}
	
	public void setRight(Expression right) {
		this.right = right;
	}
}
