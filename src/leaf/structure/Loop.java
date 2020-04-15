package leaf.structure;

import leaf.runtime.Engine;
import leaf.runtime.IValue;
import leaf.runtime.exception.ControlBreak;
import leaf.runtime.exception.ControlContinue;

public class Loop extends Expression {
	private Expression body;
	
	public Loop(Expression body) {
		this.body = body;
	}
	
	@Override
	public IValue run(Engine engine) {
		try {
			while (true) {
				try {
					this.body.run(engine);
				} catch (ControlContinue control) {}
			}
		} catch (ControlBreak control) {}
		
		return null;
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
}
