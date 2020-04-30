package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.value.Constant;
import leaf.runtime.value.Reference;

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
