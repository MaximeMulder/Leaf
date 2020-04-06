package leaf.exception;

public class ErrorAssertion extends Error {
	private static final long serialVersionUID = -3211861961957504012L;

	public ErrorAssertion() {
		super("ASSERTION ERROR", "Assertion failed.");
	}
}
