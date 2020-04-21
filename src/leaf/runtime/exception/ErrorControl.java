package leaf.runtime.exception;

public class ErrorControl extends Error {
	private static final long serialVersionUID = -1649844820174103621L;

	public ErrorControl() {
		super("CONTROL ERROR", "Invalid use of control instruction.");
	}
}
