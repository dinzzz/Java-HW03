package hr.fer.zemris.java.custom.scripting.lexer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class SmartScriptLexerTest {

	@Test
	public void Test2() {
		SmartScriptLexer lexer = new SmartScriptLexer("{$ FOR i -1 10 1 $}");

		SmartScriptToken correctData[] = {

				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.FOR, "FOR"),
				new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
				new SmartScriptToken(SmartScriptTokenType.INTEGER, -1),
				new SmartScriptToken(SmartScriptTokenType.INTEGER, 10),
				new SmartScriptToken(SmartScriptTokenType.INTEGER, 1),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}") };

		checkTokenStream(lexer, correctData);

	}

	@Test
	public void Test3() {
		SmartScriptLexer lexer = new SmartScriptLexer("{$ FOR sco_re \"-1\"10.2 \"1\" $}");

		SmartScriptToken correctData[] = {

				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.FOR, "FOR"),
				new SmartScriptToken(SmartScriptTokenType.VARIABLE, "sco_re"),
				new SmartScriptToken(SmartScriptTokenType.STRING, "\"-1\""),
				new SmartScriptToken(SmartScriptTokenType.DOUBLE, 10.2),
				new SmartScriptToken(SmartScriptTokenType.STRING, "\"1\""),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}") };

		checkTokenStream(lexer, correctData);
	}

	//
	@Test
	public void Test4() {
		SmartScriptLexer lexer = new SmartScriptLexer("{$ FOR year 1  \r\n    last_year $}\r\n");

		SmartScriptToken correctData[] = {

				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.FOR, "FOR"),
				new SmartScriptToken(SmartScriptTokenType.VARIABLE, "year"),
				new SmartScriptToken(SmartScriptTokenType.INTEGER, 1),
				new SmartScriptToken(SmartScriptTokenType.VARIABLE, "last_year"),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}") };
		Assert.assertEquals(SmartScriptLexerState.TEXT, lexer.getState());

		checkTokenStream(lexer, correctData);
	}

	@Test
	public void Test5() {
		SmartScriptLexer lexer = new SmartScriptLexer(
				"{$FOR i 0 10 2 $}\r\n" + " sin({$=i$}^2) = {$= i i * @sin \"0.000\" @decfmt $}    \r\n" + "");

		SmartScriptToken correctData[] = {

				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.FOR, "FOR"),
				new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
				new SmartScriptToken(SmartScriptTokenType.INTEGER, 0),
				new SmartScriptToken(SmartScriptTokenType.INTEGER, 10),
				new SmartScriptToken(SmartScriptTokenType.INTEGER, 2),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}"),
				new SmartScriptToken(SmartScriptTokenType.TEXT, "\r\n sin("),
				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.TAGNAME, "="),
				new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}"),
				new SmartScriptToken(SmartScriptTokenType.TEXT, "^2) = "),
				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.TAGNAME, "="),
				new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
				new SmartScriptToken(SmartScriptTokenType.VARIABLE, "i"),
				new SmartScriptToken(SmartScriptTokenType.OPERATOR, "*"),
				new SmartScriptToken(SmartScriptTokenType.FUNCTION, "@sin"),
				new SmartScriptToken(SmartScriptTokenType.STRING, "\"0.000\""),
				new SmartScriptToken(SmartScriptTokenType.FUNCTION, "@decfmt"),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}"),
				new SmartScriptToken(SmartScriptTokenType.TEXT, "    \r\n")
				
				};

		checkTokenStream(lexer, correctData);
	}
	
	@Test
	public void Test6() {
		SmartScriptLexer lexer = new SmartScriptLexer("{$END$}");

		SmartScriptToken correctData[] = {

				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.END, "END"),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}") };

		checkTokenStream(lexer, correctData);
	}
	
	@Test
	public void Test7() {
		SmartScriptLexer lexer = new SmartScriptLexer("{$     END    $}");

		SmartScriptToken correctData[] = {

				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.END, "END"),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}") };

		checkTokenStream(lexer, correctData);
	}
	
	@Test
	public void Test8() {
		SmartScriptLexer lexer = new SmartScriptLexer("Example \\{$=1$}. Now actually write one {$=1$}");

		SmartScriptToken correctData[] = {

				new SmartScriptToken(SmartScriptTokenType.TEXT, "Example \\{$=1$}. Now actually write one "),
				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.TAGNAME, "="),
				new SmartScriptToken(SmartScriptTokenType.INTEGER, 1),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}") };

		checkTokenStream(lexer, correctData);
	}
	
	
	@Test
	public void Test9() {
		SmartScriptLexer lexer = new SmartScriptLexer("{$=1$}Dinzov test");

		SmartScriptToken correctData[] = {

				new SmartScriptToken(SmartScriptTokenType.STARTTAG, "{$"),
				new SmartScriptToken(SmartScriptTokenType.TAGNAME, "="),
				new SmartScriptToken(SmartScriptTokenType.INTEGER, 1),
				new SmartScriptToken(SmartScriptTokenType.ENDTAG, "$}"),
				new SmartScriptToken(SmartScriptTokenType.TEXT, "Dinzov test")};

		checkTokenStream(lexer, correctData);
	}
	
		
	private void checkTokenStream(SmartScriptLexer lexer, SmartScriptToken[] correctData) {
		int counter = 0;
		for (SmartScriptToken expected : correctData) {
			SmartScriptToken actual = lexer.nextToken();
			String msg = "Checking token " + counter + ":";
			assertEquals(msg, expected.getType(), actual.getType());
			assertEquals(msg, expected.getValue(), actual.getValue());
			counter++;
		}
	}
}
