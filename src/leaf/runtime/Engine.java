package leaf.runtime;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.factory.FactoryPrimitives;
import leaf.runtime.factory.FactoryTypes;
import leaf.runtime.factory.FactoryValues;
import leaf.runtime.reference.Reference;

public class Engine {
	private Scope scope;
	private Reference self;
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
	
	public void setVariable(String name, Reference reference) {
		this.scope.setVariable(name, reference);
	}
	
	public Reference getSelf() {
		Reference self = this.self;
		this.self = null;
		return self;
	}
	
	public void setSelf(Reference self) {
		this.self = self;
	}
	
	public Reference callFunction(Reference function, Index index) {
		return this.callFunction(function, index, new ArrayList<Reference>());
	}
	
	public Reference callFunction(Reference function, Index index, List<Reference> arguments) {
		return function.read().getType().getData().asType().getMethod(index).getData().asFunction().getCallable().call(this, arguments);
	}
	
	public Reference callMethod(Reference self, Index index) {
		return this.callMethod(self, index, new ArrayList<Reference>());
	}
	
	public Reference callMethod(Reference self, Index index, List<Reference> arguments) {
		arguments = new ArrayList<Reference>(arguments);
		arguments.add(0, self);
		return self.read().getType().getData().asType().getMethod(index).getData().asFunction().getCallable().call(this, arguments);
	}
}
