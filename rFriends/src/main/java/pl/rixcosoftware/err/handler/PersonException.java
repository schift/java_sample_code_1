package pl.riscosoftware.err.handler;

public class PersonException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public PersonException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public PersonException() {
		super();
	}
}
