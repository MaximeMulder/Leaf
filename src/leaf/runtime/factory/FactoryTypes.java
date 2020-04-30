package leaf.runtime.factory;

import leaf.runtime.Value;
import leaf.runtime.data.DataType;

public class FactoryTypes {
	private Value array;
	private Value binary;
	private Value function;
	private Value instance;
	private Value integer;
	private Value object;
	private Value reference;
	private Value string;
	private Value type;

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

		this.type.getData().asType().setParent(this.getObject());
		this.type.setType(this.getType());
	}

	public Value getArray() {
		return this.array;
	}

	public Value getBoolean() {
		return this.binary;
	}

	public Value getFunction() {
		return this.function;
	}

	public Value getInstance() {
		return this.instance;
	}

	public Value getInteger() {
		return this.integer;
	}

	public Value getNull() {
		return null;
	}

	public Value getObject() {
		return this.object;
	}

	public Value getReference() {
		return this.reference;
	}

	public Value getString() {
		return this.string;
	}

	public Value getType() {
		return this.type;
	}

	private Value newType(String name) {
		return new Value(this.getType(), new DataType(name, this.getObject()));
	}
}
