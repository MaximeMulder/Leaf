package leaf.runtime.factory;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Scope;
import leaf.runtime.primitive.*;
import leaf.runtime.value.ValueClass;
import leaf.runtime.value.ValueFunction;
import leaf.runtime.value.ValueName;

public class FactoryPrimitives {
	private FactoryTypes types;
	private Scope scope;
	
	public FactoryPrimitives(Engine engine) {
		this.types = engine.getTypes();
		this.scope = engine.getScope();
		
		this.setArray();
		this.setBoolean();
		this.setInstance();
		this.setInteger();
		this.setObject();
		this.setString();
		
		this.setScope();
	}
	
	private void setArray() {
		ValueClass type = this.types.getArray();

		this.setMethod(type, "copy",      new MethodArrayCopy());
		this.setMethod(type, "append",    new MethodArrayAppend());
		this.setMethod(type, "prepend",   new MethodArrayPrepend());
		this.setMethod(type, "insert",    new MethodArrayInsert());
		this.setMethod(type, "remove",    new MethodArrayRemove());
		this.setMethod(type, "to_string", new MethodArrayToString());
	}

	private void setBoolean() {
		ValueClass type = this.types.getBoolean();

		this.setMethod(type, "to_string", new MethodBooleanToString());
		
		this.setBinary(type, "==", new MethodBooleanComparison());
	}
	
	private void setInstance() {
		ValueClass type = this.types.getInstance();

		this.setMethod(type, "to_string", new MethodInstanceToString());
	}
	
	private void setInteger() {
		ValueClass type = this.types.getInteger();
		
		this.setMethod(type, "to_string", new MethodIntegerToString());
		
		this.setBinary(type, "+",  new MethodIntegerAddition());
		this.setBinary(type, "-",  new MethodIntegerSubtraction());
		this.setBinary(type, "*",  new MethodIntegerMultiplication());
		this.setBinary(type, "/",  new MethodIntegerDivision());
		this.setBinary(type, "<",  new MethodIntegerOrderLesser());
		this.setBinary(type, "==", new MethodIntegerComparison());
	}
	
	private void setObject() {
		ValueClass type = this.types.getObject();

		this.setBinary(type, ">",  new MethodObjectOrderGreater());
		this.setBinary(type, "<=", new MethodObjectOrderLesserEqual());
		this.setBinary(type, ">=", new MethodObjectOrderGreaterEqual());
		this.setBinary(type, "==", new MethodObjectComparison());
		this.setBinary(type, "!=", new MethodObjectDifference());
	}
		
	private void setString() {
		ValueClass type = this.types.getString();
		
		this.setMethod(type, "to_string", new MethodStringToString());
		
		this.setBinary(type, "+" , new MethodStringAddition());
		this.setBinary(type, "<",  new MethodStringOrderLesser());
		this.setBinary(type, "==", new MethodStringComparison());
	}
	
	private void setScope() {
		List<ValueName> values = new ArrayList<ValueName>();
		
		values.add(this.types.getArray());
		values.add(this.types.getBoolean());
		values.add(this.types.getType());
		values.add(this.types.getFunction());
		values.add(this.types.getInstance());
		values.add(this.types.getInteger());
		values.add(this.types.getObject());
		values.add(this.types.getReference());
		values.add(this.types.getString());
		
		values.add(this.newFunction("assert", new FunctionAssert()));
		values.add(this.newFunction("error",  new FunctionError()));
		values.add(this.newFunction("exit",   new FunctionExit()));
		values.add(this.newFunction("new",    new FunctionNew()));
		values.add(this.newFunction("print",  new FunctionPrint()));
		
		for (ValueName value : values) {
			this.scope.newVariable(value.getName(), null, value);
		}
	}
	
	private void setMethod(ValueClass type, String name, Callable callable) {
		ValueFunction function = newFunction(name, callable);
		type.setMethod(function.getName(), function);
	}
	
	private void setBinary(ValueClass type, String operator, Callable callable) {
		type.setBinary(operator, newFunction(null, callable));
	}
	
	private ValueFunction newFunction(String name, Callable callable) {
		return new ValueFunction(this.types.getFunction(), name, callable);
	}
}
