package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueFunction;

public class Function extends Expression {
	private String name;
	private List<String> parameters;
	private Expression body;
	
	public Function(String name, List<String> parameters, Expression body) {
		this.name = name;
		this.parameters = parameters;
		this.body = body;
	}
	
	@Override
	public Value run(Engine engine) {
		ValueFunction function = engine.getValues().getFunction(this.name, this.parameters, this.body);
		if (this.name != null) {
			engine.setVariable(this.name, function);
		}
		
		return function;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addParameter(String parameter) {
		this.parameters.add(parameter);
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
}
