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
		FactoryTypes types = new FactoryTypes();
		
		this.typeBoolean   = types.getBoolean();
		this.typeClass     = types.getType();
		this.typeFunction  = types.getFunction();
		this.typeInteger   = types.getInteger();
		this.typeObject    = types.getObject();
		this.typeReference = types.getReference();
		this.typeString    = types.getString();
		
		this.scope = this.newScope();
		
		this.values = new FactoryValues(this);

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
		
		variable = new Variable(name);
		this.scope.setVariable(variable);
		
		return variable;
	}
	
	public void setVariable(String name, Value value) {
		this.scope.setVariable(this.newVariable(name, value));
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
	
	public Variable getAttribute(ValueInstance instance, String name) {
		Variable attribute = instance.getAttribute(name);
		if (attribute != null) {
			return attribute;
		}
		
		attribute = new Variable(name);
		instance.setAttribute(attribute);
		return attribute;
	}
	
	public ValueFunction getMethod(Value value, String name) {
		return value.getType(this).getMethod(name);
	}
	
	public Value functionCall(ValueFunction function, List<Value> arguments) {
		return function.call(this, arguments);
	}
	
	// Read and write.
	
	public Value read(Data data) {
		return data.read();
	}
	
	public void write(Data data, Value value) {
		data.write(value);
	}
	
	// New data.
	
	public Variable newVariable(String name, Value value) {
		Variable variable = new Variable(name);
		variable.write(value);
		return variable;
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
		
		scope.setVariable(this.newVariable("Boolean",   this.typeBoolean));
		scope.setVariable(this.newVariable("Class",     this.typeClass));
		scope.setVariable(this.newVariable("Function",  this.typeFunction));
		scope.setVariable(this.newVariable("Integer",   this.typeInteger));
		scope.setVariable(this.newVariable("Object",    this.typeObject));
		scope.setVariable(this.newVariable("String",    this.typeString));
		scope.setVariable(this.newVariable("Reference", this.typeReference));

		scope.setVariable(this.newVariable("assert", new PrimitiveAssert()));
		scope.setVariable(this.newVariable("error",  new PrimitiveError()));
		scope.setVariable(this.newVariable("exit",   new PrimitiveExit()));
		scope.setVariable(this.newVariable("new",    new PrimitiveNew()));
		scope.setVariable(this.newVariable("print",  new PrimitivePrint()));
		
		return scope;
	}
}
