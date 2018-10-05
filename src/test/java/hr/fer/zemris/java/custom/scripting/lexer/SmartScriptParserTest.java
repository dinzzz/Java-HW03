package hr.fer.zemris.java.custom.scripting.lexer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;
import junit.framework.Assert;

@SuppressWarnings({ "deprecation", "unused" })
public class SmartScriptParserTest {


	@Test
	public void TestParser() throws IOException {
		String docBody = new String(Files.readAllBytes(Paths.get("src/test/resources/doc1.txt")),
				StandardCharsets.UTF_8);
		SmartScriptParser parser = new SmartScriptParser(docBody);

		String expected = new String("This is sample text.\r\n" + "{$ FOR i 1 10 1 $}\r\n"
				+ " This is {$= i $}-th time this message is generated.\r\n" + "{$END$}\r\n" + "{$ FOR i 0 10 2 $}\r\n"
				+ " sin({$= i $}^2) = {$= i i * @sin \"0.000\" @decfmt $}\r\n" + "{$END$}" + "");

		String actual = parser.createOriginalDocumentBody(parser.getDocumentNode());

		Assert.assertEquals(expected, actual);

	}
	

	@Test
	public void TestNumberOfChildren() throws IOException {
		String docBody = new String(Files.readAllBytes(Paths.get("src/test/resources/doc1.txt")),
				StandardCharsets.UTF_8);
		SmartScriptParser parser = new SmartScriptParser(docBody);
		int actual = parser.getDocumentNode().numberOfChildren();
		int expected = 4;
		
		Assert.assertEquals(expected, actual);
	}
	

	@Test
	public void TestNumberOfChildrenInside() throws IOException {
		String docBody = new String(Files.readAllBytes(Paths.get("src/test/resources/doc1.txt")),
				StandardCharsets.UTF_8);
		SmartScriptParser parser = new SmartScriptParser(docBody);
		int actual = parser.getDocumentNode().getChild(1).numberOfChildren();
		int expected = 3;
		
		Assert.assertEquals(expected, actual);
	}
	

	@Test
	public void TestFirstChild() throws IOException {
		String docBody = new String(Files.readAllBytes(Paths.get("src/test/resources/doc1.txt")),
				StandardCharsets.UTF_8);
		SmartScriptParser parser = new SmartScriptParser(docBody);
		int actual = 0;
		if(parser.getDocumentNode().getChild(0) instanceof TextNode) {
			actual = 1;
		}
		int expected = 1;
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void TestSecondChild() throws IOException {
		String docBody = new String(Files.readAllBytes(Paths.get("src/test/resources/doc1.txt")),
				StandardCharsets.UTF_8);
		SmartScriptParser parser = new SmartScriptParser(docBody);
		int actual = 0;
		if(parser.getDocumentNode().getChild(1) instanceof ForLoopNode) {
			actual = 1;
		}
		int expected = 1;
		
		Assert.assertEquals(expected, actual);
	}
	

	@Test
	public void TestChildInside() throws IOException {
		String docBody = new String(Files.readAllBytes(Paths.get("src/test/resources/doc1.txt")),
				StandardCharsets.UTF_8);
		SmartScriptParser parser = new SmartScriptParser(docBody);
		int actual = 0;
		if(parser.getDocumentNode().getChild(1).getChild(1) instanceof EchoNode) {
			actual = 1;
		}
		int expected = 1;
		
		Assert.assertEquals(expected, actual);
	}
	@Test(expected=SmartScriptParserException.class)
	public void ParseExceptionTest() throws IOException {
		String docBody = new String(Files.readAllBytes(Paths.get("src/test/resources/doc3.txt")),
				StandardCharsets.UTF_8);
		SmartScriptParser parser = new SmartScriptParser(docBody);
	}
	
	@Test(expected=SmartScriptLexerException.class)
	public void LexerExceptionTest() throws IOException{
		String docBody = new String(Files.readAllBytes(Paths.get("src/test/resources/doc4.txt")),
				StandardCharsets.UTF_8);
		SmartScriptParser parser = new SmartScriptParser(docBody);
	}
	
	@Test(expected=NullPointerException.class)
	public void NullPointerExceptionTest() throws IOException{
		SmartScriptParser parser = new SmartScriptParser(null);
	}

}
