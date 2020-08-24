import java.io.IOException;

import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.gui.TreeViewer;

public class ParseTreeViewer {

	public ParseTreeViewer() {
		
	}

	public static void main(String args[]) {
		
		
		try {
			MicroCLexer lexer = new MicroCLexer(CharStreams.fromFileName(args[0]));
			
			//runTokenizer(lexer);
			
			MicroCParser parser = new MicroCParser(new CommonTokenStream(lexer));
			
			// parser.setErrorHandler(new MyErrorStrategy());
			
			ParseTree pt = parser.program();
			
			//TestVisitor tv = new TestVisitor();
			//tv.visit(pt);
			
//			symbolTable.printErrors();
//			symbolTable.printTable();

		JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),pt);
        viewer.setScale(1.5); // Scale a little
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
			System.exit(1);
		}		
	}
	
	// @SuppressWarnings("unused") 
	// private static void runTokenizer(Lexer l) {
	// 	while (true) {
	// 		Token t = l.nextToken();
	// 		if (t.getType() == Token.EOF) {
	// 			break;
	// 		}

	// 		System.out.println(t.getType());
	// 	}		
	// }

	static private class MyErrorStrategy extends DefaultErrorStrategy {

		@Override
		public void reportError(Parser recognizer, RecognitionException e) {
			System.out.println("Not Accepted");
			System.exit(1);
		}
	}
	
}