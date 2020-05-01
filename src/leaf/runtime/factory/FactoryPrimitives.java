package leaf.runtime.factory;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Callable;
import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Scope;
import leaf.runtime.Value;
import leaf.runtime.data.DataFunction;
import leaf.runtime.primitive.*;
import leaf.runtime.value.Constant;

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
		this.setType();
		
		this.setScope();
	}
	
	private void setArray() {
		Value type = this.types.getArray();

		this.setMethod(type, "copy",      new MethodArrayCopy());
		this.setMethod(type, "append",    new MethodArrayAppend());
		this.setMethod(type, "prepend",   new MethodArrayPrepend());
		this.setMethod(type, "insert",    new MethodArrayInsert());
		this.setMethod(type, "remove",    new MethodArrayRemove());
		this.setMethod(type, "to_string", new MethodArrayToString());
		
		this.setSpecial(type, "[]", new MethodArrayAccess());
	}

	private void setBoolean() {
		Value type = this.types.getBoolean();

		this.setMethod(type, "to_string", new MethodBooleanToString());
		
		this.setBinary(type, "==", new MethodBooleanComparison());
	}
	
	private void setInstance() {
		Value type = this.types.getInstance();

		this.setMethod(type, "to_string", new MethodInstanceToString());
		
		this.setSpecial(type, ".", new MethodInstanceChain());
	}
	
	private void setInteger() {
		Value type = this.types.getInteger();
		
		this.setMethod(type, "to_string", new MethodIntegerToString());
		
		this.setBinary(type, "+",  new MethodIntegerAddition());
		this.setBinary(type, "-",  new MethodIntegerSubtraction());
		this.setBinary(type, "*",  new MethodIntegerMultiplication());
		this.setBinary(type, "/",  new MethodIntegerDivision());
		this.setBinary(type, "<",  new MethodIntegerOrderLesser());
		this.setBinary(type, "==", new MethodIntegerComparison());
	}
	
	private void setObject() {
		Value type = this.types.getObject();

		this.setBinary(type, ">",  new MethodObjectOrderGreater());
		this.setBinary(type, "<=", new MethodObjectOrderLesserEqual());
		this.setBinary(type, ">=", new MethodObjectOrderGreaterEqual());
		this.setBinary(type, "==", new MethodObjectComparison());
		this.setBinary(type, "!=", new MethodObjectDifference());
		
		this.setSpecial(type, ".", new MethodObjectChain());
	}
		
	private void setString() {
		Value type = this.types.getString();
		
		this.setMethod(type, "to_string", new MethodStringToString());
		
		this.setBinary(type, "+" , new MethodStringAddition());
		this.setBinary(type, "<",  new MethodStringOrderLesser());
		this.setBinary(type, "==", new MethodStringComparison());
	}
	
	private void setType() {
		Value type = this.types.getType();
		
		this.setSpecial(type, "[]" , new MethodTypeAccess());
	}
	
	private void setScope() {
		List<Value> values = new ArrayList<Value>();
		
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
		
		for (Value value : values) {
			this.scope.setVariable(value.getData().asName().getName(), new Constant(value));
		}
	}
	
	private void setMethod(Value type, String name, Callable callable) {
		Value function = this.newFunction(name, callable);
		type.getData().asType().setMethod(Index.name(function.getData().asFunction().getName()), function);
	}
	
	private void setBinary(Value type, String operator, Callable callable) {
		type.getData().asType().setMethod(Index.binary(operator), this.newFunction(null, callable));
	}
	
	private void setSpecial(Value type, String operator, Callable callable) {
		type.getData().asType().setMethod(Index.special(operator), this.newFunction(null, callable));
	}
	
	private Value newFunction(String name, Callable callable) {
		return new Value(this.types.getFunction(), new DataFunction(name, callable));
	}
}
