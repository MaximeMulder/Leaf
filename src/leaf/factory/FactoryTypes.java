package leaf.factory;

import leaf.primitive.*;
import leaf.structure.ValueClass;

public class FactoryTypes {
	public ValueClass getBoolean() {
		ValueClass type = new ValueClass();
		
		type.addOperator("==", new BinaryBooleanComparison());
		
		type.addMethod("to_string", new MethodBooleanToString());
		
		return type;
	}
	
	public ValueClass getType() {
		return new ValueClass();
	}
	
	public ValueClass getFunction() {
		return new ValueClass();
	}
	
	public ValueClass getInteger() {
		ValueClass type = new ValueClass();
		
		type.addOperator("+",  new BinaryIntegerAddition());
		type.addOperator("-",  new BinaryIntegerSubtraction());
		type.addOperator("*",  new BinaryIntegerMultiplication());
		type.addOperator("/",  new BinaryIntegerDivision());
		type.addOperator("<",  new BinaryIntegerOrderLesser());
		type.addOperator("==", new BinaryIntegerComparison());
		
		type.addMethod("to_string", new MethodIntegerToString());
		
		return type;
	}
	
	public ValueClass getObject() {
		ValueClass type = new ValueClass();

		type.addOperator(">",  new BinaryObjectOrderGreater());
		type.addOperator("<=", new BinaryObjectOrderLesserEqual());
		type.addOperator(">=", new BinaryObjectOrderGreaterEqual());
		type.addOperator("==", new BinaryObjectComparison());
		type.addOperator("!=", new BinaryObjectDifference());
		
		return type;
	}
	
	public ValueClass getReference() {
		return new ValueClass();
	}
	
	public ValueClass getString() {
		ValueClass type = new ValueClass();
		
		type.addOperator("+",  new BinaryStringAddition());
		type.addOperator("<",  new BinaryStringOrderLesser());
		type.addOperator("==", new BinaryStringComparison());
		
		return type;
	}
}
