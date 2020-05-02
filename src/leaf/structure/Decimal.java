package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public class Decimal extends Expression {
	private String string;

	public Decimal(String string) {
		this.string = string;
	}
	
	@Override
	public Reference run(Engine engine) {
		return new Constant(engine.getValues().getInteger(Integer.parseInt(this.string)));
	}
	
	public void setString(String string) {
		this.string = string;
	}
}
