package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;

public class Block extends Expression {
	List<Statement> statements;

	public Block(List<Statement> statements) {
		this.statements = statements;
	}
	
	@Override
	public IValue run(Engine engine) {
		engine.pushScope();
		
		IValue value = null;
		for (Statement statement : this.statements) {
			value = statement.run(engine);
		}

		engine.popScope();
		
		if (value != null) {
			return value.read();
		}
		
		return null;
	}
}
