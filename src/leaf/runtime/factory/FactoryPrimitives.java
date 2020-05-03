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
	private FactoryValues values;
	private Scope scope;

	public FactoryPrimitives(Engine engine) {
		this.types  = engine.getTypes();
		this.values = engine.getValues();
		this.scope  = engine.getScope();

		this.setArray();
		this.setBoolean();
		this.setFunction();
		this.setInstance();
		this.setInteger();
		this.setObject();
		this.setOption();
		this.setReference();
		this.setString();
		this.setType();

		this.setScope();
	}

	private void setArray() {
		Value type = this.types.getArray();
		this.setMethod(type, Index.name("copy"),      new PrimitiveArrayCopy());
		this.setMethod(type, Index.name("append"),    new PrimitiveArrayAppend());
		this.setMethod(type, Index.name("prepend"),   new PrimitiveArrayPrepend());
		this.setMethod(type, Index.name("insert"),    new PrimitiveArrayInsert());
		this.setMethod(type, Index.name("remove"),    new PrimitiveArrayRemove());
		this.setMethod(type, Index.name("to_string"), new PrimitiveArrayToString());
		this.setMethod(type, Index.post("[]"),        new PrimitiveArrayAccess());
	}

	private void setBoolean() {
		Value type = this.types.getBoolean();
		this.setStatic(type, Index.name("True"),      this.values.getBooleanTrue());
		this.setStatic(type, Index.name("False"),     this.values.getBooleanFalse());
		this.setMethod(type, Index.name("to_string"), new PrimitiveBooleanToString());
		this.setMethod(type, Index.binary("=="),      new PrimitiveBooleanComparison());
	}

	private void setFunction() {
		Value type = this.types.getFunction();
		this.setMethod(type, Index.name("to_string"), new PrimitiveFunctionToString());
		this.setMethod(type, Index.post("()"),        new PrimitiveFunctionCall());
	}

	private void setInstance() {
		Value type = this.types.getInstance();
		this.setMethod(type, Index.name("to_string"), new PrimitiveInstanceToString());
		this.setMethod(type, Index.post("."),         new PrimitiveInstanceChain());
	}

	private void setInteger() {
		Value type = this.types.getInteger();
		this.setMethod(type, Index.name("to_string"), new PrimitiveIntegerToString());
		this.setMethod(type, Index.binary("+"),       new PrimitiveIntegerAddition());
		this.setMethod(type, Index.binary("-"),       new PrimitiveIntegerSubtraction());
		this.setMethod(type, Index.binary("*"),       new PrimitiveIntegerMultiplication());
		this.setMethod(type, Index.binary("/"),       new PrimitiveIntegerDivision());
		this.setMethod(type, Index.binary("<"),       new PrimitiveIntegerOrderLesser());
		this.setMethod(type, Index.binary("=="),      new PrimitiveIntegerComparison());
	}

	private void setObject() {
		Value type = this.types.getObject();
		this.setMethod(type, Index.pre("&"),     new PrimitiveObjectReference());
		this.setMethod(type, Index.pre("?"),     new PrimitiveObjectOption());
		this.setMethod(type, Index.post("."),    new PrimitiveObjectChain());
		this.setMethod(type, Index.binary(">"),  new PrimitiveObjectOrderGreater());
		this.setMethod(type, Index.binary("<="), new PrimitiveObjectOrderLesserEqual());
		this.setMethod(type, Index.binary(">="), new PrimitiveObjectOrderGreaterEqual());
		this.setMethod(type, Index.binary("=="), new PrimitiveObjectComparison());
		this.setMethod(type, Index.binary("!="), new PrimitiveObjectDifference());
	}

	private void setOption() {
		Value type = this.types.getOption();

		this.setStatic(type, Index.name("Null"),      this.values.getOptionNull());
		this.setStatic(type, Index.post("()"),        this.newFunction(null, new PrimitiveObjectOption()));
		this.setMethod(type, Index.name("to_string"), new PrimitiveOptionToString());
		this.setMethod(type, Index.pre("*"),          new PrimitiveOptionGet());
		this.setMethod(type, Index.binary("=="),      new PrimitiveOptionComparison());
	}

	private void setReference() {
		Value type = this.types.getReference();
		this.setStatic(type, Index.post("()"),        this.newFunction(null, new PrimitiveObjectReference()));
		this.setMethod(type, Index.name("to_string"), new PrimitiveReferenceToString());
		this.setMethod(type, Index.pre("*"),          new PrimitiveReferenceGet());
		this.setMethod(type, Index.binary("=="),      new PrimitiveReferenceComparison());
	}

	private void setString() {
		Value type = this.types.getString();
		this.setMethod(type, Index.name("to_string"), new PrimitiveStringToString());
		this.setMethod(type, Index.binary("+"),       new PrimitiveStringAddition());
		this.setMethod(type, Index.binary("<"),       new PrimitiveStringOrderLesser());
		this.setMethod(type, Index.binary("=="),      new PrimitiveStringComparison());
	}

	private void setType() {
		Value type = this.types.getType();
		this.setMethod(type, Index.name("to_string"), new PrimitiveTypeToString());
		this.setMethod(type, Index.post("&"),         new PrimitiveTypeReference());
		this.setMethod(type, Index.post("?"),         new PrimitiveTypeOption());
		this.setMethod(type, Index.post("[]"),        new PrimitiveTypeAccess());
		this.setMethod(type, Index.post("."),         new PrimitiveTypeChain());
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
		values.add(this.types.getOption());
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

	private void setStatic(Value type, Index index, Value value) {
		type.getData().asType().setStatic(index, new Constant(value));
	}

	private void setMethod(Value type, Index index, Callable callable) {
		type.getData().asType().setMethod(index, this.newFunction(null, callable));
	}

	private Value newFunction(String name, Callable callable) {
		return new Value(this.types.getFunction(), new DataFunction(name, callable));
	}
}
