package hr.fer.zemris.java.custom.scripting.parser;

/**
 * Non-obligatory class that checks the execution of the parser by implementing
 * the string document directly into the constructor.
 * 
 * @author Dinz
 *
 */
public class Testererer {
	/**
	 * Method that executes the class.
	 * 
	 * @param args
	 *            Argument from the command line.
	 */
	public static void main(String[] args) {
		SmartScriptParser parser = new SmartScriptParser("This is sample text.\r\n" + "{$ FOR i 1 10 1 $}\r\n"
				+ " This is {$= i $}-th time this message is generated.\r\n" + "{$END$}\r\n" + "{$FOR i 0 10 2 $}\r\n"
				+ " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}\r\n" + "{$END$}");

		System.out.println(parser.createOriginalDocumentBody(parser.getDocumentNode()));

	}

}
