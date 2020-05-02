package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.exception.Control;
import leaf.runtime.exception.ErrorControl;
import leaf.runtime.reference.Reference;

public class Program extends Expression {
	List<Statement> statements;

	public Program(List<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public Reference run(Engine engine) {
		try {
			for (Statement statement : this.statements) {
				statement.run(engine);
			}
		} catch (Control control) {
			throw new ErrorControl();
		}

		return null;
	}
}
