package leaf.runtime.factory;

import leaf.runtime.value.ValueType;

public class FactoryTypes {
	private ValueType array;
	private ValueType binary;
	private ValueType function;
	private ValueType instance;
	private ValueType integer;
	private ValueType object;
	private ValueType reference;
	private ValueType string;
	private ValueType type;

	public FactoryTypes() {
		this.type      = this.newType("Type");
		this.object    = this.newType("Object");
		this.array     = this.newType("Array");
		this.binary    = this.newType("Boolean");
		this.function  = this.newType("Function");
		this.instance  = this.newType("Instance");
		this.integer   = this.newType("Integer");
		this.reference = this.newType("Refernece");
		this.string    = this.newType("String");

		this.type.setParent(this.getObject());
		this.type.setType(this.getType());
	}

	public ValueType getArray() {
		return this.array;
	}

	public ValueType getBoolean() {
		return this.binary;
	}

	public ValueType getFunction() {
		return this.function;
	}

	public ValueType getInstance() {
		return this.instance;
	}

	public ValueType getInteger() {
		return this.integer;
	}

	public ValueType getNull() {
		return null;
	}

	public ValueType getObject() {
		return this.object;
	}

	public ValueType getReference() {
		return this.reference;
	}

	public ValueType getString() {
		return this.string;
	}

	public ValueType getType() {
		return this.type;
	}

	private ValueType newType(String name) {
		return new ValueType(this.getType(), name, this.getObject());
	}
}
