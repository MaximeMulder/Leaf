package leaf.structure;

import java.util.ArrayList;
import java.util.List;

import leaf.Visitor;
import leaf.exception.ErrorUndefined;
import leaf.factory.*;
import leaf.language_leaf.Node;
import leaf.primitive.*;

public class Engine {
	private FactoryValues values;
	
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
		this.values = new FactoryValues(this);
		
		FactoryTypes types = new FactoryTypes();
		
		this.typeBoolean   = types.getBoolean();
		this.typeClass     = types.getType();
		this.typeFunction  = types.getFunction();
		this.typeInteger   = types.getInteger();
		this.typeObject    = types.getObject();
		this.typeReference = types.getReference();
		this.typeString    = types.getString();
		
		this.scope = this.newScope();

		this.visitor = new Visitor(this);
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
		this.scope.setVariable(variable);
		
		return variable;
	}
	
	public void setVariable(String name, Value value) {
		this.scope.setVariable(this.values.getVariable(name, value));
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
		ValueFunction function = left.getType(this).getOperator(operator);
		if (function == null) {
			throw new ErrorUndefined();
		}
		
		List<Value> operands = new ArrayList<Value>();
		operands.add(left);
		operands.add(right);
		return this.functionCall(function, operands);
	}
	
	public ValueFunction getMethod(Value value, String name) {
		return value.getType(this).getMethod(name);
	}
	
	public Value functionCall(ValueFunction function, List<Value> arguments) {
		return function.call(this, arguments);
	}
	
	// Cast functions.
	
	public ValueBoolean castBoolean(Value value) {
		return value.castBoolean(this);
	}
	
	public ValueClass castClass(Value value) {
		return value.castClass(this);
	}
	
	public ValueFunction castFunction(Value value) {
		return value.castFunction(this);
	}
	
	public ValueInteger castInteger(Value value) {
		return value.castInteger(this);
	}
	
	public ValueNull castNull(Value value) {
		return value.castNull(this);
	}
	
	public ValueInstance castInstance(Value value) {
		return value.castInstance(this);
	}
	
	public ValueReference castReference(Value value) {
		return value.castReference(this);
	}
	
	public ValueString castString(Value value) {
		return value.castString(this);
	}
	
	// Private utilities.
	
	private Scope newScope() {
		Scope scope = new Scope(null);
		/* ArrayList<IValue> values = new ArrayList<IValue>();
		values.add(this.typeBoolean);
		
		for (IValue value : values) {
			scope.setVariable(this.values.getVariable(value.getName(), value));
		} */
		
		scope.setVariable(this.values.getVariable("Boolean",   this.typeBoolean));
		scope.setVariable(this.values.getVariable("Class",     this.typeClass));
		scope.setVariable(this.values.getVariable("Function",  this.typeFunction));
		scope.setVariable(this.values.getVariable("Integer",   this.typeInteger));
		scope.setVariable(this.values.getVariable("Object",    this.typeObject));
		scope.setVariable(this.values.getVariable("String",    this.typeString));
		scope.setVariable(this.values.getVariable("Reference", this.typeReference));

		scope.setVariable(this.values.getVariable("assert", new PrimitiveAssert()));
		scope.setVariable(this.values.getVariable("error",  new PrimitiveError()));
		scope.setVariable(this.values.getVariable("exit",   new PrimitiveExit()));
		scope.setVariable(this.values.getVariable("new",    new PrimitiveNew()));
		scope.setVariable(this.values.getVariable("print",  new PrimitivePrint()));
		
		return scope;
	}
}
