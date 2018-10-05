package hr.fer.zemris.java.hw03.prob1;

/**
 * Class that defines a lexer exception - if the document/text given is not able
 * to be lexicaly analyzed for the language given, it throws a LexerException.
 * 
 * @author Dinz
 *
 */
public class LexerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new LexerException.
	 */
	public LexerException() {
		super();
	}

	/**
	 * Constructs a new LexerException with a given message.
	 * 
	 * @param message
	 *            Message about the exception.
	 */
	public LexerException(String message) {
		super(message);
	}
}
