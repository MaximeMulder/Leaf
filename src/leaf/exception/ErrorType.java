package leaf.exception;

public class ErrorType extends Error {
	private static final long serialVersionUID = 2635863600444446196L;

	public ErrorType() {
		super("TYPE ERROR", "Value type does not match requirements.");
	}
}
