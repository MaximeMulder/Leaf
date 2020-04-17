package leaf.runtime.factory;

import leaf.runtime.Engine;
import leaf.runtime.ValueClass;

public class FactoryTypes {
	private Engine engine;
	private FactoryPrimitives primitives;
	
	public FactoryTypes(Engine engine) {
		this.engine = engine;
		this.primitives = this.engine.getPrimitives();
	}
	
	public ValueClass getBoolean() {
		ValueClass type = this.get("Boolean");
		
		type.setBinary("==", this.primitives.getBinaryBooleanComparison());
		
		type.setMethod("to_string", this.primitives.getMethodBooleanToString());
		
		return type;
	}
	
	public ValueClass getType() {
		return this.get("Class");
	}
	
	public ValueClass getFunction() {
		return this.get("Function");
	}
	
	public ValueClass getInteger() {
		ValueClass type = this.get("Integer");
		
		type.setBinary("+",  this.primitives.getBinaryIntegerAddition());
		type.setBinary("-",  this.primitives.getBinaryIntegerSubtraction());
		type.setBinary("*",  this.primitives.getBinaryIntegerMultiplication());
		type.setBinary("/",  this.primitives.getBinaryIntegerDivision());
		type.setBinary("<",  this.primitives.getBinaryIntegerOrderLesser());
		type.setBinary("==", this.primitives.getBinaryIntegerComparison());
		
		type.setMethod("to_string", this.primitives.getMethodIntegerToString());
		
		return type;
	}
	
	public ValueClass getObject() {
		ValueClass type = this.get("Object", null);

		type.setBinary(">",  this.primitives.getBinaryObjectOrderGreater());
		type.setBinary("<=", this.primitives.getBinaryObjectOrderLesserEqual());
		type.setBinary(">=", this.primitives.getBinaryObjectOrderGreaterEqual());
		type.setBinary("==", this.primitives.getBinaryObjectComparison());
		type.setBinary("!=", this.primitives.getBinaryObjectDifference());
		
		return type;
	}
	
	public ValueClass getReference() {
		return this.get("Reference");
	}
	
	public ValueClass getString() {
		ValueClass type = this.get("String");
		
		type.setBinary("+",  this.primitives.getBinaryStringAddition());
		type.setBinary("<",  this.primitives.getBinaryStringOrderLesser());
		type.setBinary("==", this.primitives.getBinaryStringComparison());
		
		return type;
	}
	
	private ValueClass get(String name) {
		return this.get(name, this.engine.getTypeObject());
	}
	
	private ValueClass get(String name, ValueClass parent) {
		return new ValueClass(this.engine.getTypeClass(), name, parent);
	}
}
