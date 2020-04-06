package leaf.exception;

public class ErrorWrite extends Error {
	private static final long serialVersionUID = 7559199151647774479L;

	public ErrorWrite() {
		super("WRITE ERROR", "Value is not writable.");
	}
}
