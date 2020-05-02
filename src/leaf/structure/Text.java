package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.reference.Constant;
import leaf.runtime.reference.Reference;

public class Text extends Expression {
	private String string;

	public Text(String string) {
		this.string = string;
	}
	
	@Override
	public Reference run(Engine engine) {
		return new Constant(engine.getValues().getString(this.string.substring(1, this.string.length() - 1)));
	}
	
	public void setString(String string) {
		this.string = string;
	}
}
