package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;

public class Decimal extends Expression {
	private String string;

	public Decimal(String string) {
		this.string = string;
	}
	
	@Override
	public IValue run(Engine engine) {
		return engine.getValues().getInteger(Integer.parseInt(this.string));
	}
	
	public void setString(String string) {
		this.string = string;
	}
}
