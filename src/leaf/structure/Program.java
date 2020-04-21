package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.exception.Control;
import leaf.runtime.exception.ErrorControl;

public class Program extends Expression {
	List<Statement> statements;

	public Program(List<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public IValue run(Engine engine) {
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
