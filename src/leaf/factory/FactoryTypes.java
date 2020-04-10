package leaf.factory;

import leaf.structure.Engine;
import leaf.structure.ValueClass;

public class FactoryTypes {
	private Engine engine;
	private FactoryPrimitives primitives;
	
	public FactoryTypes(Engine engine) {
		this.engine = engine;
		this.primitives = this.engine.getPrimitives();
	}
	
	public ValueClass getBoolean() {
		ValueClass type = new ValueClass(this.engine.getTypeClass(), "Boolean");
		
		type.addOperator("==", this.primitives.getBinaryBooleanComparison());
		
		type.addMethod("to_string", this.primitives.getMethodBooleanToString());
		
		return type;
	}
	
	public ValueClass getType() {
		return new ValueClass(this.engine.getTypeClass(), "Class");
	}
	
	public ValueClass getFunction() {
		return new ValueClass(this.engine.getTypeClass(), "Function");
	}
	
	public ValueClass getInteger() {
		ValueClass type = new ValueClass(this.engine.getTypeClass(), "Integer");
		
		type.addOperator("+",  this.primitives.getBinaryIntegerAddition());
		type.addOperator("-",  this.primitives.getBinaryIntegerSubtraction());
		type.addOperator("*",  this.primitives.getBinaryIntegerMultiplication());
		type.addOperator("/",  this.primitives.getBinaryIntegerDivision());
		type.addOperator("<",  this.primitives.getBinaryIntegerOrderLesser());
		type.addOperator("==", this.primitives.getBinaryIntegerComparison());
		
		type.addMethod("to_string", this.primitives.getMethodIntegerToString());
		
		return type;
	}
	
	public ValueClass getObject() {
		ValueClass type = new ValueClass(this.engine.getTypeClass(), "Object");

		type.addOperator(">",  this.primitives.getBinaryObjectOrderGreater());
		type.addOperator("<=", this.primitives.getBinaryObjectOrderLesserEqual());
		type.addOperator(">=", this.primitives.getBinaryObjectOrderGreaterEqual());
		type.addOperator("==", this.primitives.getBinaryObjectComparison());
		type.addOperator("!=", this.primitives.getBinaryObjectDifference());
		
		return type;
	}
	
	public ValueClass getReference() {
		return new ValueClass(this.engine.getTypeClass(), "Reference");
	}
	
	public ValueClass getString() {
		ValueClass type = new ValueClass(this.engine.getTypeClass(), "String");
		
		type.addOperator("+",  this.primitives.getBinaryStringAddition());
		type.addOperator("<",  this.primitives.getBinaryStringOrderLesser());
		type.addOperator("==", this.primitives.getBinaryStringComparison());
		
		return type;
	}
}
