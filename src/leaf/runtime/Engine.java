package leaf.runtime;

import java.util.ArrayList;

import leaf.runtime.factory.*;

public class Engine {
	private FactoryPrimitives primitives;
	private FactoryTypes      types;
	private FactoryValues     values;

	private ValueClass typeArray;
	private ValueClass typeBoolean;
	private ValueClass typeClass;
	private ValueClass typeFunction;
	private ValueClass typeInteger;
	private ValueClass typeObject;
	private ValueClass typeReference;
	private ValueClass typeString;
	
	private Scope scope;
	private Value self;
	
	public Engine() {
		this.primitives = new FactoryPrimitives(this);
		this.types      = new FactoryTypes(this);
		this.values     = new FactoryValues(this);

		this.typeClass     = this.types.getType();
		this.typeObject    = this.types.getObject();
		this.typeArray     = this.types.getArray();
		this.typeBoolean   = this.types.getBoolean();
		this.typeFunction  = this.types.getFunction();
		this.typeInteger   = this.types.getInteger();
		this.typeReference = this.types.getReference();
		this.typeString    = this.types.getString();
		
		this.typeClass.setParent(this.typeObject);
		
		this.scope = this.newScope();
		this.self = null;
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
	
	// Getters for type objects.
	
	public ValueClass getTypeArray() {
		return this.typeArray;
	}
	
	public ValueClass getTypeBoolean() {
		return this.typeBoolean;
	}
	
	public ValueClass getTypeClass() {
		return this.typeClass;
	}
	
	public ValueClass getTypeFunction() {
		return this.typeFunction;
	}
	
	public ValueClass getTypeInteger() {
		return this.typeInteger;
	}
	
	public ValueClass getTypeNull() {
		return null;
	}
	
	public ValueClass getTypeObject() {
		return this.typeObject;
	}
	
	public ValueClass getTypeReference() {
		return this.typeReference;
	}
	
	public ValueClass getTypeString() {
		return this.typeString;
	}
	
	// Complex getters.
	
	public Variable getVariable(String name) {
		Variable variable = this.scope.getVariable(name);
		if (variable != null) {
			return variable;
		}
		
		variable = this.values.getVariable(name);
		this.scope.addVariable(variable);
		
		return variable;
	}
	
	public void setVariable(String name, Value value) {
		this.scope.addVariable(this.values.getVariable(name, value));
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
	
	// Private utilities.
	
	private Scope newScope() {
		Scope scope = new Scope(null);
		ArrayList<ValueName> values = new ArrayList<ValueName>();
		values.add(this.getTypeArray());
		values.add(this.getTypeBoolean());
		values.add(this.getTypeClass());
		values.add(this.getTypeFunction());
		values.add(this.getTypeInteger());
		values.add(this.getTypeObject());
		values.add(this.getTypeString());
		values.add(this.getTypeReference());
		values.add(this.getPrimitives().getPrimitiveAssert());
		values.add(this.getPrimitives().getPrimitiveError());
		values.add(this.getPrimitives().getPrimitiveExit());
		values.add(this.getPrimitives().getPrimitiveNew());
		values.add(this.getPrimitives().getPrimitivePrint());
		for (ValueName value : values) {
			scope.addVariable(this.values.getVariable(value.getName(), value));
		}
		
		return scope;
	}
}
