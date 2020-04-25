package leaf.runtime.factory;

import leaf.runtime.value.ValueClass;

public class FactoryTypes {
	private ValueClass array;
	private ValueClass binary;
	private ValueClass type;
	private ValueClass function;
	private ValueClass instance;
	private ValueClass integer;
	private ValueClass object;
	private ValueClass reference;
	private ValueClass string;
	
	public FactoryTypes() {
		this.type      = this.newType("Class");
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
	
	public ValueClass getArray() {
		return this.array;
	}
	
	public ValueClass getBoolean() {
		return this.binary;
	}
	
	public ValueClass getType() {
		return this.type;
	}
	
	public ValueClass getFunction() {
		return this.function;
	}
	
	public ValueClass getInstance() {
		return this.instance;
	}
	
	public ValueClass getInteger() {
		return this.integer;
	}
	
	public ValueClass getNull() {
		return null;
	}
	
	public ValueClass getObject() {
		return this.object;
	}
	
	public ValueClass getReference() {
		return this.reference;
	}
	
	public ValueClass getString() {
		return this.string;
	}
	
	private ValueClass newType(String name) {
		return new ValueClass(this.getType(), name, this.getObject());
	}
}
