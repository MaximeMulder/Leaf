package leaf.runtime;

import leaf.runtime.factory.*;
import leaf.runtime.value.Value;

public class Engine {
	private Scope scope;
	private Value self;
	private FactoryTypes      types;
	private FactoryPrimitives primitives;
	private FactoryValues     values;
	
	public Engine() {
		this.scope = new Scope(null);
		this.self = null;
		
		this.types      = new FactoryTypes();
		this.primitives = new FactoryPrimitives(this);
		this.values     = new FactoryValues(this);
	}
	
	public FactoryPrimitives getPrimitives() {
		return this.primitives;
	}
	
	public FactoryTypes getTypes() {
		return this.types;
	}
	
	public FactoryValues getValues() {
		return this.values;
	}
	
	// Complex getters.
	
	public Reference getVariable(String name) {
		Reference variable = this.scope.getVariable(name);
		if (variable != null) {
			return variable;
		}
		
		variable = this.scope.newVariable(name, null);
		
		return variable;
	}
	
	public void setVariable(String name, Value value) {
		this.scope.newVariable(name, value);
	}
	
	// Scope operations.
	
	public void setFrame(Scope scope) {
		this.scope = new Scope(scope);
	}
	
	public Scope getScope() {
		return this.scope;
	}
	
	public void setScope(Scope scope) {
		this.scope = scope;
	}
	
	public void pushScope() {
		this.scope = new Scope(this.scope);
	}
	
	public void popScope() {
		this.scope = this.scope.getParent();
	}
	
	public void setSelf(Value self) {
		this.self = self;
	}
	
	public Value getSelf() {
		Value self = this.self;
		this.self = null;
		return self;
	}
}
