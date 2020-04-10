package leaf.factory;

import leaf.callable.*;
import leaf.structure.Callable;
import leaf.structure.Engine;
import leaf.structure.ValueFunction;

public class FactoryPrimitives {
	private Engine engine;
	
	public FactoryPrimitives(Engine engine) {
		this.engine = engine;
	}
	
	public ValueFunction getBinaryBooleanComparison() {
		return this.get(new BinaryBooleanComparison());
	}
	
	public ValueFunction getBinaryIntegerAddition() {
		return this.get(new BinaryIntegerAddition());
	}

	public ValueFunction getBinaryIntegerSubtraction() {
		return this.get(new BinaryIntegerSubtraction());
	}

	public ValueFunction getBinaryIntegerMultiplication() {
		return this.get(new BinaryIntegerMultiplication());
	}

	public ValueFunction getBinaryIntegerDivision() {
		return this.get(new BinaryIntegerDivision());
	}

	public ValueFunction getBinaryIntegerOrderLesser() {
		return this.get(new BinaryIntegerOrderLesser());
	}

	public ValueFunction getBinaryIntegerComparison() {
		return this.get(new BinaryIntegerComparison());
	}

	public ValueFunction getBinaryObjectOrderGreater() {
		return this.get(new BinaryObjectOrderGreater());
	}

	public ValueFunction getBinaryObjectOrderLesserEqual() {
		return this.get(new BinaryObjectOrderLesserEqual());
	}

	public ValueFunction getBinaryObjectOrderGreaterEqual() {
		return this.get(new BinaryObjectOrderGreaterEqual());
	}

	public ValueFunction getBinaryObjectComparison() {
		return this.get(new BinaryObjectComparison());
	}

	public ValueFunction getBinaryObjectDifference() {
		return this.get(new BinaryObjectDifference());
	}

	public ValueFunction getBinaryStringAddition() {
		return this.get(new BinaryStringAddition());
	}

	public ValueFunction getBinaryStringOrderLesser() {
		return this.get(new BinaryStringOrderLesser());
	}

	public ValueFunction getBinaryStringComparison() {
		return this.get(new BinaryStringComparison());
	}

	public ValueFunction getMethodBooleanToString() {
		return this.get("to_string", new MethodBooleanToString());
	}
	
	public ValueFunction getMethodIntegerToString() {
		return this.get("to_string", new MethodIntegerToString());
	}
	
	public ValueFunction getPrimitiveAssert() {
		return this.get("assert", new PrimitiveAssert());
	}
	
	public ValueFunction getPrimitiveError() {
		return this.get("error", new PrimitiveError());
	}
	
	public ValueFunction getPrimitiveExit() {
		return this.get("exit", new PrimitiveExit());
	}
	
	public ValueFunction getPrimitiveNew() {
		return this.get("new", new PrimitiveNew());
	}
	
	public ValueFunction getPrimitivePrint() {
		return this.get("print", new PrimitivePrint());
	}
	
	private ValueFunction get(Callable callable) {
		return this.get(null, callable);
	}
	
	private ValueFunction get(String name, Callable callable) {
		return new ValueFunction(this.engine.getTypeFunction(), name, callable);
	}
}
