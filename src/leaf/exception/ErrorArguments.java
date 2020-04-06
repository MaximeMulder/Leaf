package leaf.exception;

public class ErrorArguments extends Error {
	private static final long serialVersionUID = 9160215624560493353L;

	public ErrorArguments() {
		super("ARGUMENTS ERROR", "Call arguments do not match function parameters.");
	}
}
