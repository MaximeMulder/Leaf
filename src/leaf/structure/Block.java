package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Reference;

public class Block extends Expression {
	List<Statement> statements;

	public Block(List<Statement> statements) {
		this.statements = statements;
	}
	
	@Override
	public Reference run(Engine engine) {
		engine.pushScope();
		Reference reference = null;
		for (Statement statement : this.statements) {
			reference = statement.run(engine);
		}

		engine.popScope();
		return reference;
	}
}
