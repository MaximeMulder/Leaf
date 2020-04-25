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
		
		this.setBinary(type, "==", new BinaryBooleanComparison());
	}
	
	private void setInstance() {
		ValueClass type = this.types.getInstance();

		this.setMethod(type, "to_string", new MethodInstanceToString());
	}
	
	private void setInteger() {
		ValueClass type = this.types.getInteger();
		
		this.setMethod(type, "to_string", new MethodIntegerToString());
		
		this.setBinary(type, "+",  new BinaryIntegerAddition());
		this.setBinary(type, "-",  new BinaryIntegerSubtraction());
		this.setBinary(type, "*",  new BinaryIntegerMultiplication());
		this.setBinary(type, "/",  new BinaryIntegerDivision());
		this.setBinary(type, "<",  new BinaryIntegerOrderLesser());
		this.setBinary(type, "==", new BinaryIntegerComparison());
	}
	
	private void setObject() {
		ValueClass type = this.types.getObject();

		this.setBinary(type, ">",  new BinaryObjectOrderGreater());
		this.setBinary(type, "<=", new BinaryObjectOrderLesserEqual());
		this.setBinary(type, ">=", new BinaryObjectOrderGreaterEqual());
		this.setBinary(type, "==", new BinaryObjectComparison());
		this.setBinary(type, "!=", new BinaryObjectDifference());
	}
		
	private void setString() {
		ValueClass type = this.types.getString();
		
		this.setMethod(type, "to_string", new MethodStringToString());
		
		this.setBinary(type, "+" , new BinaryStringAddition());
		this.setBinary(type, "<",  new BinaryStringOrderLesser());
		this.setBinary(type, "==", new BinaryStringComparison());
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
		
		values.add(this.newFunction("assert", new PrimitiveAssert()));
		values.add(this.newFunction("error",  new PrimitiveError()));
		values.add(this.newFunction("exit",   new PrimitiveExit()));
		values.add(this.newFunction("new",    new PrimitiveNew()));
		values.add(this.newFunction("print",  new PrimitivePrint()));
		
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
