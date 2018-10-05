package hr.fer.zemris.java.hw03.prob1;

/**
 * Class that represents a token - a tool to store data in lexical analysis. The
 * token is defined by its type and its value.
 * 
 * @author Dinz
 *
 */
public class Token {
	/**
	 * Type of the token.
	 */
	private TokenType type;
	/**
	 * Value of the token.
	 */
	private Object value;

	/**
	 * Constructs a token with a given type and a given value.
	 * 
	 * @param type
	 *            Type of the token.
	 * @param value
	 *            Value of the token.
	 */
	public Token(TokenType type, Object value) {
		this.type = type;
		this.value = value;
	}

	/**
	 * Returns the value of the token.
	 * 
	 * @return Value of the token.
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Returns the type of the token.
	 * 
	 * @return Type of the token.
	 */
	public TokenType getType() {
		return this.type;
	}
}
