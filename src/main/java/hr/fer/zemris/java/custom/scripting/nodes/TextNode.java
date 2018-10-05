package hr.fer.zemris.java.custom.scripting.nodes;

/**
 * Class that represents a text node for a parser. It stores a text token
 * analyzed by a lexer.
 * 
 * @author Dinz
 *
 */
public class TextNode extends Node {
	/**
	 * Text in a TextNode.
	 */
	String text;

	/**
	 * Constructs a new TextNode from a string.
	 * 
	 * @param text
	 *            Text.
	 */
	public TextNode(String text) {
		this.text = text;
	}

	/**
	 * Returns a text from a TextNode.
	 * 
	 * @return Text from a TextNode.
	 */
	public String getText() {
		return text;
	}

}
