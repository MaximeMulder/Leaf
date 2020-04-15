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
		
		type.setOperator("==", this.primitives.getBinaryBooleanComparison());
		
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
		
		type.setOperator("+",  this.primitives.getBinaryIntegerAddition());
		type.setOperator("-",  this.primitives.getBinaryIntegerSubtraction());
		type.setOperator("*",  this.primitives.getBinaryIntegerMultiplication());
		type.setOperator("/",  this.primitives.getBinaryIntegerDivision());
		type.setOperator("<",  this.primitives.getBinaryIntegerOrderLesser());
		type.setOperator("==", this.primitives.getBinaryIntegerComparison());
		
		type.setMethod("to_string", this.primitives.getMethodIntegerToString());
		
		return type;
	}
	
	public ValueClass getObject() {
		ValueClass type = this.get("Object", null);

		type.setOperator(">",  this.primitives.getBinaryObjectOrderGreater());
		type.setOperator("<=", this.primitives.getBinaryObjectOrderLesserEqual());
		type.setOperator(">=", this.primitives.getBinaryObjectOrderGreaterEqual());
		type.setOperator("==", this.primitives.getBinaryObjectComparison());
		type.setOperator("!=", this.primitives.getBinaryObjectDifference());
		
		return type;
	}
	
	public ValueClass getReference() {
		return this.get("Reference");
	}
	
	public ValueClass getString() {
		ValueClass type = this.get("String");
		
		type.setOperator("+",  this.primitives.getBinaryStringAddition());
		type.setOperator("<",  this.primitives.getBinaryStringOrderLesser());
		type.setOperator("==", this.primitives.getBinaryStringComparison());
		
		return type;
	}
	
	private ValueClass get(String name) {
		return this.get(name, this.engine.getTypeObject());
	}
	
	private ValueClass get(String name, ValueClass parent) {
		return new ValueClass(this.engine.getTypeClass(), name, parent);
	}
}
