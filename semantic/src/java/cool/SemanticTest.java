package cool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.Paths;

public class SemanticTest {

	static String[] TOKENS = {"ERROR", "TYPEID", "OBJECTID", "BOOL_CONST", "INT_CONST", "STR_CONST", "'('", "')'", "':'", "'@'", "';'", "','", "'+'", "'-'", "'*'", "'/'", "'~'", "'<'", "'='", "'{'", "'}'", "'.'", "DARROW", "LE", "ASSIGN", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "OF", "NEW", "ISVOID", "NOT"
	};

	static int VALUED_INDEX_LIMIT = 6;
	static int NAMED_TOKEN_INDEX = 23;
	static int parser_error_flag = 0;
	
	static String escapeSpecialCharacters(String text) {
		return
			text
				.replaceAll("\\\\", "\\\\\\\\")
				.replaceAll("\n", "\\\\n")
				.replaceAll("\t", "\\\\t")
				.replaceAll("\b", "\\\\b")
				.replaceAll("\f", "\\\\f")
				.replaceAll("\"", "\\\\\"")
				.replaceAll("\r", "\\\\015")
				.replaceAll("\033","\\\\033")
				.replaceAll("\001","\\\\001")
				.replaceAll("\002","\\\\002")
				.replaceAll("\003","\\\\003")
				.replaceAll("\004","\\\\004")
				.replaceAll("\022","\\\\022")
				.replaceAll("\013","\\\\013")
				.replaceAll("\000", "\\\\000")
				;
	}

	static void printAST(String filename) throws Exception{
		ANTLRInputStream inStream=null;
		try{
			inStream = new ANTLRInputStream(new FileInputStream(filename));
		}catch(Exception e){
			System.err.println("Could not read file "+filename);
			return;
		}
		
		CoolLexer lexer = new CoolLexer(inStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();		
		int lexer_flag = 0;
		for(Token t : tokens.getTokens()){
			if ( t.getType() == 1 ){
				lexer_flag = 1;
				System.err.println("Lexical error at "+t.getLine()+": "+escapeSpecialCharacters(t.getText()));	
			}
		}
		if (lexer_flag == 1)
			return;
		
		parser_error_flag = 0;
		CoolParser parser = new CoolParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ParserError(Paths.get(filename).getFileName().toString()));
		parser.setFilename(Paths.get(filename).getFileName().toString());

		CoolParser.ProgramContext prog = null;
		try{
			prog = parser.program();
		}catch(Exception e){
		//	e.printStackTrace();
		}
		if(parser_error_flag == 1){
			System.err.println("Compilation halted due to lex and parse errors");
			return;
		}
		Semantic semanticAnalyzer=new Semantic(prog.value);
		if (semanticAnalyzer.getErrorFlag()){
			System.err.println("Compilation halter due to semantic errors.");
			return;
		}
		System.out.println(prog.value.getString(""));
	}

	public static void main(String args[]) throws Exception{

		if(args.length < 1) {
			System.err.println("No files given");
			System.exit(1);
		}
		printAST(args[0]);
	}

	public static class ParserError extends BaseErrorListener {
		
		String filename;
		public ParserError(String fn) {
			super();
			filename=fn;
		}
		@Override
		public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionIntLine, String msg, RecognitionException e){
			parser_error_flag=1;
			String sourceName = recognizer.getInputStream().getSourceName();
			String errorMessage="";
			if(filename!=null){
				if(offendingSymbol instanceof CommonToken){
					errorMessage += "\""+filename+"\", line "+line+": syntax error at or near ";
					int typeid = ((CommonToken)offendingSymbol).getType();
					if (typeid == -1){
						errorMessage += "EOF";
					}
					else if (typeid <= VALUED_INDEX_LIMIT) {
						errorMessage += TOKENS[typeid-1] + " = " + ((CommonToken)offendingSymbol).getText();
					}else if(typeid >= NAMED_TOKEN_INDEX){
						errorMessage += TOKENS[typeid-1];
					}else{
						errorMessage += "\'"+escapeSpecialCharacters(((CommonToken)offendingSymbol).getText())+"\'";
					}
				}
			}
			System.err.println(errorMessage);
			throw new RuntimeException("One error found!");
		}
	}		
}

//objectid, typeid, int_const, string_const, bool_const
//	static String[] TOKENS = {"ERROR", "TYPEID", "OBJECTID", "BOOL_CONST", "INT_CONST", "STR_CONST", "'('", "')'", "':'", "'@'", "';'", "','", "'+'", "'-'", "'*'", "'/'", "'~'", "'<'", "'='", "'{'", "'}'", "'.'", "DARROW", "LE", "ASSIGN", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "OF", "NEW", "ISVOID", "NOT"
//	};
