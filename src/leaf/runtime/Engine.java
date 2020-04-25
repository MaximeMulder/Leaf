package leaf.runtime;

import leaf.runtime.factory.*;
import leaf.runtime.value.Value;
import leaf.runtime.value.ValueClass;

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
	
	public Scope getScope() {
		return this.scope;
	}
	
	public Scope pushFrame(Scope frame) {
		Scope scope = this.scope;
		this.scope = new Scope(frame);
		return scope;
	}
	
	public void popFrame(Scope frame) {
		this.scope = frame;
	}

	public void pushScope() {
		this.scope = new Scope(this.scope);
	}
	
	public void popScope() {
		this.scope = this.scope.getParent();
	}
	
	public Reference getVariable(String name) {
		return this.scope.getVariable(name);
	}
	
	public Reference newVariable(String name, ValueClass type, Value value) {
		return this.scope.newVariable(name, type, value);
	}
	
	public Value getSelf() {
		Value self = this.self;
		this.self = null;
		return self;
	}
	
	public void setSelf(Value self) {
		this.self = self;
	}
}
