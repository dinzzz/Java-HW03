package hr.fer.zemris.java.hw03;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import hr.fer.zemris.java.custom.scripting.lexer.SmartScriptLexerException;
import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;

/**
 * Class that checks if the parser is working correctly. It parses the document
 * given in the main method, prints it out, then parses the document given by
 * the parser (document body) and then compares them. If the parser works
 * correctly, appropriate message is printed at the end.
 * 
 * @author Dinz
 *
 */
public class SmartScriptTester02 {
	/**
	 * Method that executes the class.
	 * 
	 * @param args
	 *            Arguments from the console.
	 * @throws IOException
	 *             If the input stream is invalid.
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String docBody = new String(Files.readAllBytes(Paths.get("src/test/resources/doc2.txt")),
				StandardCharsets.UTF_8);

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
		System.out.println(originalDocumentBody);

		try {
			parser = new SmartScriptParser(originalDocumentBody);
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

		DocumentNode document2 = parser.getDocumentNode();
		String originalDocumentBody2 = parser.createOriginalDocumentBody(document2);
		System.out.println(originalDocumentBody2);

		if (originalDocumentBody2.equals(originalDocumentBody)) {
			System.out.println("\nDocument bodies are equal - parser works great!");
		}
	}

}
