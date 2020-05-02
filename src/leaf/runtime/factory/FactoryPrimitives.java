package leaf.runtime.factory;

import java.util.ArrayList;
import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.Index;
import leaf.runtime.Scope;
import leaf.runtime.Value;
import leaf.runtime.callable.Callable;
import leaf.runtime.data.DataFunction;
import leaf.runtime.primitive.*;
import leaf.runtime.reference.Constant;

public class FactoryPrimitives {
	private FactoryTypes types;
	private Scope scope;

	public FactoryPrimitives(Engine engine) {
		this.types = engine.getTypes();
		this.scope = engine.getScope();

		this.setArray();
		this.setBoolean();
		this.setFunction();
		this.setInstance();
		this.setInteger();
		this.setObject();
		this.setReference();
		this.setString();
		this.setType();

		this.setScope();
	}

	private void setArray() {
		Value type = this.types.getArray();

		this.setMethod(type, "copy",      new PrimitiveArrayCopy());
		this.setMethod(type, "append",    new PrimitiveArrayAppend());
		this.setMethod(type, "prepend",   new PrimitiveArrayPrepend());
		this.setMethod(type, "insert",    new PrimitiveArrayInsert());
		this.setMethod(type, "remove",    new PrimitiveArrayRemove());
		this.setMethod(type, "to_string", new PrimitiveArrayToString());

		this.setSpecial(type, "[]", new PrimitiveArrayAccess());
	}

	private void setBoolean() {
		Value type = this.types.getBoolean();

		this.setMethod(type, "to_string", new PrimitiveBooleanToString());

		this.setBinary(type, "==", new PrimitiveBooleanComparison());
	}

	private void setFunction() {
		Value type = this.types.getFunction();

		this.setSpecial(type, "()", new PrimitiveFunctionCall());
	}

	private void setInstance() {
		Value type = this.types.getInstance();

		this.setMethod(type, "to_string", new PrimitiveInstanceToString());

		this.setSpecial(type, ".", new PrimitiveInstanceChain());
	}

	private void setInteger() {
		Value type = this.types.getInteger();

		this.setMethod(type, "to_string", new PrimitiveIntegerToString());

		this.setBinary(type, "+",  new PrimitiveIntegerAddition());
		this.setBinary(type, "-",  new PrimitiveIntegerSubtraction());
		this.setBinary(type, "*",  new PrimitiveIntegerMultiplication());
		this.setBinary(type, "/",  new PrimitiveIntegerDivision());
		this.setBinary(type, "<",  new PrimitiveIntegerOrderLesser());
		this.setBinary(type, "==", new PrimitiveIntegerComparison());
	}

	private void setObject() {
		Value type = this.types.getObject();

		this.setBinary(type, ">",  new PrimitiveObjectOrderGreater());
		this.setBinary(type, "<=", new PrimitiveObjectOrderLesserEqual());
		this.setBinary(type, ">=", new PrimitiveObjectOrderGreaterEqual());
		this.setBinary(type, "==", new PrimitiveObjectComparison());
		this.setBinary(type, "!=", new PrimitiveObjectDifference());

		this.setPre(type, "&" , new PrimitiveObjectReference());
		
		this.setSpecial(type, ".", new PrimitiveObjectChain());
	}

	private void setReference() {
		Value type = this.types.getReference();

		this.setPre(type, "*" , new PrimitiveReferenceGet());
	}

	private void setString() {
		Value type = this.types.getString();

		this.setMethod(type, "to_string", new PrimitiveStringToString());

		this.setBinary(type, "+" , new PrimitiveStringAddition());
		this.setBinary(type, "<",  new PrimitiveStringOrderLesser());
		this.setBinary(type, "==", new PrimitiveStringComparison());
	}

	private void setType() {
		Value type = this.types.getType();

		this.setPost(type, "&" , new PrimitiveTypeReference());
		this.setSpecial(type, "[]" , new PrimitiveTypeAccess());
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

		values.add(this.newFunction("assert", new PrimitiveAssert()));
		values.add(this.newFunction("error",  new PrimitiveError()));
		values.add(this.newFunction("exit",   new PrimitiveExit()));
		values.add(this.newFunction("new",    new PrimitiveNew()));
		values.add(this.newFunction("print",  new PrimitivePrint()));

		for (Value value : values) {
			this.scope.setVariable(value.getData().asName().getName(), new Constant(value));
		}
	}

	private void setMethod(Value type, String name, Callable callable) {
		Value function = this.newFunction(name, callable);
		type.getData().asType().setMethod(Index.name(function.getData().asFunction().getName()), function);
	}

	private void setPre(Value type, String operator, Callable callable) {
		type.getData().asType().setMethod(Index.pre(operator), this.newFunction(null, callable));
	}

	private void setPost(Value type, String operator, Callable callable) {
		type.getData().asType().setMethod(Index.post(operator), this.newFunction(null, callable));
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
