package leaf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import leaf.language_leaf.LexerException;
import leaf.language_leaf.Node;
import leaf.language_leaf.Parser;
import leaf.language_leaf.ParserException;
import leaf.structure.Engine;

public class Interpreter {
	public static void main(String[] arguments) {
		Reader reader = null;
		if (arguments.length == 0) {
			reader = new InputStreamReader(System.in);
		} else if (arguments.length == 1) {
			try {
				reader = new FileReader(arguments[0]);
			} catch (FileNotFoundException exception) {
				System.err.println("INPUT ERROR: File not found '" + arguments[0] + "'.");
				System.exit(1);
			}
		} else {
			System.err.println("COMMAND-LINE ERROR: Too many arguments.");
			System.exit(1);
		}

		Node tree = null;
		
		try {
			tree = new Parser(reader).parse();
		} catch (IOException exception) {
			String input;
			if (arguments.length == 0) {
				input = "standard input";
			} else {
				input = arguments[0];
			}
			
			System.err.println("INPUT ERROR: " + exception.getMessage() + " while reading " + input + ".");
			System.exit(1);
		} catch (LexerException exception) {
		    System.err.println("LEXER ERROR: " + exception.getMessage() + ".");
			System.exit(1);
		} catch (ParserException exception) {
		    System.err.println("PARSER ERROR: " + exception.getMessage() + ".");
			System.exit(1);
		}
		
		Engine engine = new Engine();
		try {
			engine.visit(tree);
		} catch (Exception exception) {
			System.err.println("INTERPRETER ERROR: " + exception.getMessage() + ".");
			exception.printStackTrace();
			System.exit(1);
		}
		
		System.exit(0);
	}
}
