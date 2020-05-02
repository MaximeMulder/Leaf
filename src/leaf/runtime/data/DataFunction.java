package leaf.runtime.data;

import leaf.runtime.callable.Callable;

public class DataFunction extends DataName {
	private Callable callable;
	
	public DataFunction(String name, Callable callable) {
		super(name);
		this.callable = callable;
	}
	
	public Callable getCallable() {
		return this.callable;
	}
}
