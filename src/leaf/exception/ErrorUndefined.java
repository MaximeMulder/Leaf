package leaf.exception;

public class ErrorUndefined extends Error {
	private static final long serialVersionUID = 713419933136601517L;

	public ErrorUndefined() {
		super("UNDEFINED ERROR", "Value is undefined.");
	}
}
