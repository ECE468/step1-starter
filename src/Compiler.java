import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class Compiler {

	public Compiler() {
		
	}

	public static void main(String args[]) {
		
		
		try {
			MicroCLexer lexer = new MicroCLexer(CharStreams.fromFileName(args[0]));
			
			MicroCParser parser = new MicroCParser(new CommonTokenStream(lexer));
			
			parser.setErrorHandler(new MyErrorStrategy());
			
			ParseTree pt = parser.program();
			
			System.out.println("Accepted");

		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(1);
		}		
	}

	static private class MyErrorStrategy extends DefaultErrorStrategy {

		@Override
		public void reportError(Parser recognizer, RecognitionException e) {
			System.out.println("Not Accepted");
			System.exit(1);
		}

		@Override
		public Token recoverInline(Parser recognizer) throws RecognitionException {
			System.out.println("Not Accepted");
			System.exit(1);
			return null;
		}
	}
	
}