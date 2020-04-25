package leaf.structure;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueClass;
import leaf.runtime.value.ValueFunction;

public class Function extends Expression {
	private String name;
	private Expression type;
	private List<Variable> parameters;
	private Expression body;
	
	public Function(String name, Expression type, List<Variable> parameters, Expression body) {
		this.name = name;
		this.type = type;
		this.parameters = parameters;
		this.body = body;
	}
	
	@Override
	public Value run(Engine engine) {
		ValueClass type;
		if (this.type != null) {
			type = this.type.run(engine).read().castClass();
		} else {
			type = null;
		}
		
		ValueFunction function = engine.getValues().getFunction(this.name, type, this.parameters, this.body);
		if (this.name != null) {
			engine.newVariable(this.name, null, function);
		}
		
		return function;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addParameter(Variable parameter) {
		this.parameters.add(parameter);
	}
	
	public void setBody(Expression body) {
		this.body = body;
	}
}
