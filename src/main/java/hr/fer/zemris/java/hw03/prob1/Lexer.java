package hr.fer.zemris.java.hw03.prob1;

/**
 * Class that represents a lexer - a tool that processes a document and applies
 * a lexical analysis. The data from the documents is stored in a character
 * field, while analyzed tokens are stored as a single Token class that
 * represents the latest analyzed token. Also the lexer uses currentindex
 * integer value that determines the index of the next character to analyze. In
 * the end, the lexer works in two states - basic and extended.
 * 
 * @author Dinz
 *
 */
public class Lexer {
	/**
	 * Character array that stores the input data.
	 */
	private char[] data; // ulazni tekst
	/**
	 * Last analyzed token.
	 */
	private Token token; // trenutni token
	/**
	 * Index of the next unanalyzed character.
	 */
	private int currentIndex; // indeks prvog neobrađenog znaka
	/**
	 * State of the lexer.
	 */
	private LexerState state = LexerState.BASIC;

	// konstruktor prima ulazni tekst koji se tokenizira
	/**
	 * Constructs a new lexer from a given text. It removes all the escapes from the
	 * input text and stores the text into a character array.
	 * 
	 * @param text
	 *            Text to analyze.
	 * @throws IllegalArgumentException
	 *             If the string is null.
	 */
	public Lexer(String text) {
		if (text == null) {
			throw new IllegalArgumentException("String must not be null");
		}
		text = text.replaceAll("(\r|\n|\t|)", "");

		this.data = text.toCharArray();
	}

	// generira i vraća sljedeći token
	// baca LexerException ako dođe do pogreške
	/**
	 * Generates the next token from the analyzed data and stores it into a class
	 * variable.
	 * 
	 * @return Analyzed token.
	 * @throws LexerException
	 *             If the data is invalid.
	 */
	public Token nextToken() {
		if (currentIndex > data.length) {
			throw new LexerException("No more tokens available.");
		}
		if (currentIndex == data.length) {
			currentIndex++;
			Token newToken = new Token(TokenType.EOF, null);
			this.token = newToken;
			return newToken;
		}

		if (state == LexerState.EXTENDED) {
			if (Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex])) {
				StringBuilder tokenBuilder = new StringBuilder();
				while (currentIndex < data.length && !Character.toString(data[currentIndex]).equals(" ")
						&& !Character.toString(data[currentIndex]).equals("#")) {
					tokenBuilder.append(data[currentIndex]);
					currentIndex++;
				}
				Token newToken = new Token(TokenType.WORD, tokenBuilder.toString());
				this.token = newToken;
				return newToken;
			} else if (Character.toString(data[currentIndex]).equals(" ")) {
				currentIndex++;
				return this.nextToken();
			} else if (Character.toString(data[currentIndex]).equals("#")) {
				Token newToken = new Token(TokenType.SYMBOL, data[currentIndex]);
				this.token = newToken;
				currentIndex++;
				this.setState(LexerState.BASIC);
				return newToken;
			} else {
				Token newToken = new Token(TokenType.SYMBOL, data[currentIndex]);
				this.token = newToken;
				currentIndex++;
				return newToken;
			}
		}

		else {

			if (Character.isDigit(data[currentIndex])) {
				StringBuilder tokenBuilder = new StringBuilder();
				while (currentIndex < data.length && Character.isDigit(data[currentIndex])) {
					tokenBuilder.append(data[currentIndex]);
					currentIndex++;
				}

				try {
					long number = Long.parseLong(tokenBuilder.toString());
					Token newToken = new Token(TokenType.NUMBER, number);
					this.token = newToken;
					return newToken;
				} catch (NumberFormatException ex) {
					throw new LexerException("Number in the input is too big.");
				}

			} else if (Character.isLetter(data[currentIndex]) || Character.toString(data[currentIndex]).equals("\\")) {
				StringBuilder tokenBuilder = new StringBuilder();
				while (currentIndex < data.length && (Character.isLetter(data[currentIndex])
						|| Character.toString(data[currentIndex]).equals("\\"))) {
					if (Character.toString(data[currentIndex]).equals("\\")) {
						currentIndex++;
						if ((currentIndex) >= data.length) {
							throw new LexerException("Invalid use of escape characters.");
						}
						if (Character.isDigit(data[currentIndex])
								|| Character.toString(data[currentIndex]).equals("\\")) {
							tokenBuilder.append(data[currentIndex]);
							currentIndex++;
						} else {
							throw new LexerException("Invalid use of escape characters.");
						}
					} else {

						tokenBuilder.append(data[currentIndex]);
						currentIndex++;
					}
				}
				Token newToken = new Token(TokenType.WORD, tokenBuilder.toString());
				this.token = newToken;
				return newToken;

			} else if (Character.toString(data[currentIndex]).equals(" ")) {
				currentIndex++;
				return this.nextToken();

			} else {
				Token newToken = new Token(TokenType.SYMBOL, data[currentIndex]);
				if (Character.toString(data[currentIndex]).equals("#")) {
					this.setState(LexerState.EXTENDED);
				}
				this.token = newToken;
				currentIndex++;
				return newToken;
			}
		}
	}

	/**
	 * Returns last generated token.
	 * 
	 * @return Last generated token.
	 */
	public Token getToken() {
		return this.token;
	}

	/**
	 * Sets the state of the lexer.
	 * 
	 * @param state
	 *            State to be set.
	 */
	public void setState(LexerState state) {
		if (state == null)
			throw new IllegalArgumentException("State must not be null.");
		this.state = state;
	}
}
