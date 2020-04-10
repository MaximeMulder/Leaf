package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.Visitor;
import leaf.exception.ErrorUndefined;
import leaf.factory.*;
import leaf.language_leaf.Node;

public class Engine {
	private FactoryPrimitives primitives;
	private FactoryTypes      types;
	private FactoryValues     values;
	
	private Visitor visitor;
	
	private Scope scope;
	
	private ValueClass typeBoolean;
	private ValueClass typeClass;
	private ValueClass typeFunction;
	private ValueClass typeInteger;
	private ValueClass typeObject;
	private ValueClass typeReference;
	private ValueClass typeString;
	
	public Engine() {
		this.primitives = new FactoryPrimitives(this);
		this.types      = new FactoryTypes(this);
		this.values     = new FactoryValues(this);

		this.typeClass     = this.types.getType();
		
		this.typeBoolean   = this.types.getBoolean();
		this.typeFunction  = this.types.getFunction();
		this.typeInteger   = this.types.getInteger();
		this.typeObject    = this.types.getObject();
		this.typeReference = this.types.getReference();
		this.typeString    = this.types.getString();
		
		this.scope = this.newScope();

		this.visitor = new Visitor(this);
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
	
	public void visit(Node node) {
		this.visitor.visit(node);
	}
	
	// Getters for type objects.
	
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
	
	// Other operations.
	
	public Value operation(String operator, Value left, Value right) {
		ValueFunction function = left.getType().getOperator(operator);
		if (function == null) {
			throw new ErrorUndefined();
		}
		
		List<Value> operands = new ArrayList<Value>();
		operands.add(left);
		operands.add(right);
		return this.functionCall(function, operands);
	}
	
	public ValueFunction getMethod(Value value, String name) {
		return value.getType().getMethod(name);
	}
	
	public Value functionCall(ValueFunction function, List<Value> arguments) {
		return function.call(this, arguments);
	}
	
	// Private utilities.
	
	private Scope newScope() {
		Scope scope = new Scope(null);
		ArrayList<ValueName> values = new ArrayList<ValueName>();
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
