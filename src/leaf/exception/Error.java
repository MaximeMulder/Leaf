package leaf.exception;

public abstract class Error extends RuntimeException {
	private static final long serialVersionUID = -7930932364993605521L;
	
	protected Error(String type, String message) {
		super(type + ": " + message);
	}
}
