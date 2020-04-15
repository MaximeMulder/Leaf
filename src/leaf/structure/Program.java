package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;

public class Program extends Expression {
	List<Statement> statements;

	public Program(List<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public IValue run(Engine engine) {
		for (Statement statement : this.statements) {
			statement.run(engine);
		}

		return null;
	}
}
