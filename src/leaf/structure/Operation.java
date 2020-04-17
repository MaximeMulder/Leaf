package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.Value;

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
	public IValue run(Engine engine) {
		List<Value> arguments = new ArrayList<Value>();
		arguments.add(this.left.run(engine).read());
		arguments.add(this.right.run(engine).read());
		return arguments.get(0).getType().getBinary(this.operator).call(engine, arguments);
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
