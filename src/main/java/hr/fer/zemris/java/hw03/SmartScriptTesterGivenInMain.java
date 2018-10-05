package hr.fer.zemris.java.hw03;

import hr.fer.zemris.java.custom.scripting.lexer.SmartScriptLexerException;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

/**
 * Class that check if the parser is working correctly by printing the correct
 * document body from the document given to the parser.
 * 
 * @author Dinz
 *
 */
public class SmartScriptTesterGivenInMain {
	/**
	 * Method that executes the class.
	 * 
	 * @param args
	 *            Arguments from the console.
	 */
	public static void main(String[] args) {
		String docBody = "This is sample text.\r\n" + "{$ FOR i 1 10 1 $}\r\n"
				+ " This is {$= i $}-th time this message is generated.\r\n" + "{$END$}\r\n" + "{$FOR i 0 10 2 $}\r\n"
				+ " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}\r\n" + "{$END$}";
		SmartScriptParser parser = null;
		try {
			parser = new SmartScriptParser(docBody);
		} catch (SmartScriptLexerException e) {
			System.out.println("Lexer cannot process this document!");
			System.exit(-1);
		} catch (SmartScriptParserException e) {
			System.out.println("Unable to parse document!");
			System.exit(-1);
		} catch (Exception e) {
			System.out.println("If this line ever executes, you have failed this class!");
			System.exit(-1);
		}
		DocumentNode document = parser.getDocumentNode();
		String originalDocumentBody = parser.createOriginalDocumentBody(document);
		System.out.println(originalDocumentBody); // should write something like original
		// content of docBody

	}

}
