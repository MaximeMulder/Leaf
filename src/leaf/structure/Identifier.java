package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.value.Reference;

public class Identifier extends Expression {
	private String string;
	
	public Identifier(String string) {
		this.string = string;
	}
	
	@Override
	public Reference run(Engine engine) {
		return engine.getVariable(this.string);
	}
	
	public void setString(String string) {
		this.string = string;
	}
}
