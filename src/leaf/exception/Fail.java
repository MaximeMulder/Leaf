package leaf.exception;

public class Fail extends RuntimeException {
	private static final long serialVersionUID = -7930932364993605521L;
	
	public Fail(String message) {
		super(message);
	}
}
