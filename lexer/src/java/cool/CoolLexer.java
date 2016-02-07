// Generated from CoolLexer.g4 by ANTLR 4.5
package cool;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, TYPEID=2, OBJECTID=3, BOOL_CONST=4, INT_CONST=5, STR_CONST=6, 
		LPAREN=7, RPAREN=8, COLON=9, ATSYM=10, SEMICOLON=11, COMMA=12, PLUS=13, 
		MINUS=14, STAR=15, SLASH=16, TILDE=17, LT=18, EQUALS=19, LBRACE=20, RBRACE=21, 
		DOT=22, DARROW=23, LE=24, ASSIGN=25, CLASS=26, ELSE=27, FI=28, IF=29, 
		IN=30, INHERITS=31, LET=32, LOOP=33, POOL=34, THEN=35, WHILE=36, CASE=37, 
		ESAC=38, OF=39, NEW=40, ISVOID=41, NOT=42, SELF=43, SELF_TYPE=44, WS=45, 
		LINE_COMMENT=46, END_COMMENT=47, UN_COMMENT=48, COMMENT=49, NOTFOUND=50, 
		ERR=51, OCOMMENT=52, CCOMMENT=53, INCOMMENT_T=54, ERR3=55, OCOM=56, ERR4=57, 
		CCOM=58, INCOM_TEXT=59;
	public static final int INCOMMENT = 1;
	public static final int ININCOM = 2;
	public static String[] modeNames = {
		"DEFAULT_MODE", "INCOMMENT", "ININCOM"
	};

	public static final String[] ruleNames = {
		"ERROR", "SEMICOLON", "DARROW", "INT_CONST", "STR_CONST", "ESC", "SELF", 
		"SELF_TYPE", "TYPEID", "OBJECTID", "BOOL_CONST", "LPAREN", "RPAREN", "COLON", 
		"ATSYM", "COMMA", "PLUS", "MINUS", "STAR", "SLASH", "TILDE", "LT", "EQUALS", 
		"LBRACE", "RBRACE", "DOT", "LE", "ASSIGN", "CLASS", "ELSE", "FI", "IF", 
		"IN", "INHERITS", "LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", 
		"OF", "NEW", "ISVOID", "NOT", "WS", "LINE_COMMENT", "END_COMMENT", "UN_COMMENT", 
		"COMMENT", "NOTFOUND", "ERR", "OCOMMENT", "CCOMMENT", "INCOMMENT_T", "ERR3", 
		"OCOM", "ERR4", "CCOM", "INCOM_TEXT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'('", "')'", "':'", "'@'", 
		"';'", "','", "'+'", "'-'", "'*'", "'/'", "'~'", "'<'", "'='", "'{'", 
		"'}'", "'.'", "'=>'", "'<='", "'<-'", null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "'self'", 
		"'SELF_TYPE'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ERROR", "TYPEID", "OBJECTID", "BOOL_CONST", "INT_CONST", "STR_CONST", 
		"LPAREN", "RPAREN", "COLON", "ATSYM", "SEMICOLON", "COMMA", "PLUS", "MINUS", 
		"STAR", "SLASH", "TILDE", "LT", "EQUALS", "LBRACE", "RBRACE", "DOT", "DARROW", 
		"LE", "ASSIGN", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "LET", 
		"LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "OF", "NEW", "ISVOID", 
		"NOT", "SELF", "SELF_TYPE", "WS", "LINE_COMMENT", "END_COMMENT", "UN_COMMENT", 
		"COMMENT", "NOTFOUND", "ERR", "OCOMMENT", "CCOMMENT", "INCOMMENT_T", "ERR3", 
		"OCOM", "ERR4", "CCOM", "INCOM_TEXT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}



		/*
			YOU CAN ADD YOUR MEMBER VARIABLES AND METHODS HERE
		*/

		/**
		* Function to report errors.
		* Use this function whenever your lexer encounters any erroneous input
		* DO NOT EDIT THIS FUNCTION
		*/
		public void reportError(String errorString) {
			setText(errorString);
			setType(ERROR);
		}

		public void notFound() {
			Token t = _factory.create(_tokenFactorySourcePair, _type, _text, _channel, _tokenStartCharIndex, getCharIndex()-1, _tokenStartLine, _tokenStartCharPositionInLine);
			String text = t.getText();
			reportError(text);
		}

		public void processString() {
			Token t = _factory.create(_tokenFactorySourcePair, _type, _text, _channel, _tokenStartCharIndex, getCharIndex()-1, _tokenStartLine, _tokenStartCharPositionInLine);
			String text = t.getText();

			StringBuilder buf = new StringBuilder(0);

			//write your code to test strings here
			for(int i = 0; i < text.length(); ++i) {
				if(text.charAt(i) == '\\') {
					if(text.charAt(i+1) == 'n')
						buf.append('\n');
					else if(text.charAt(i+1) == 'f')
						buf.append('\f');
					else if(text.charAt(i+1) == 't')
						buf.append('\t');
					else if(text.charAt(i+1) == 'b')
						buf.append('\t');
					else if(text.charAt(i+1) == '\"')
						buf.append('\"');
					else if(text.charAt(i+1) == '\\')
						buf.append('\\');
					else
						buf.append(text.charAt(i+1));
					i++;
				}
				else {
					buf.append(text.charAt(i));
				}
			}
			String ntext = buf.toString();
			if(ntext.length() > 1024) {
				reportError("String constant too long");
				return;
			}
			setText(ntext);
			return;
		}


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0:
			ERROR_action((RuleContext)_localctx, actionIndex);
			break;
		case 4:
			STR_CONST_action((RuleContext)_localctx, actionIndex);
			break;
		case 47:
			END_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 48:
			UN_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 50:
			NOTFOUND_action((RuleContext)_localctx, actionIndex);
			break;
		case 51:
			ERR_action((RuleContext)_localctx, actionIndex);
			break;
		case 55:
			ERR3_action((RuleContext)_localctx, actionIndex);
			break;
		case 57:
			ERR4_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ERROR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 reportError("String contains null character"); 
			break;
		case 1:
			 reportError("EOF in string literal"); 
			break;
		case 2:
			 reportError("Unterminated string constant"); 
			break;
		}
	}
	private void STR_CONST_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 processString() ;
			break;
		}
	}
	private void END_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			 reportError("Unmatched *)"); 
			break;
		}
	}
	private void UN_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			 reportError("Unmatched *)"); 
			break;
		}
	}
	private void NOTFOUND_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			 notFound(); 
			break;
		}
	}
	private void ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:
			 reportError("EOF in comment"); 
			break;
		}
	}
	private void ERR3_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:
			 reportError("EOF in comment"); 
			break;
		}
	}
	private void ERR4_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9:
			 reportError("EOF in comment"); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2=\u01b9\b\1\b\1\b"+
		"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"+
		"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"+
		"\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30"+
		"\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37"+
		"\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t"+
		"*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63"+
		"\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t"+
		"<\4=\t=\3\2\3\2\7\2\u0080\n\2\f\2\16\2\u0083\13\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\6\2\u008b\n\2\r\2\16\2\u008c\3\2\7\2\u0090\n\2\f\2\16\2\u0093\13"+
		"\2\3\2\3\2\3\2\3\2\7\2\u0099\n\2\f\2\16\2\u009c\13\2\3\2\3\2\3\2\3\2\7"+
		"\2\u00a2\n\2\f\2\16\2\u00a5\13\2\3\2\3\2\5\2\u00a9\n\2\3\3\3\3\3\4\3\4"+
		"\3\4\3\5\6\5\u00b1\n\5\r\5\16\5\u00b2\3\6\3\6\3\6\7\6\u00b8\n\6\f\6\16"+
		"\6\u00bb\13\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7\u00c4\n\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\7\n\u00d7\n\n\f"+
		"\n\16\n\u00da\13\n\3\13\3\13\7\13\u00de\n\13\f\13\16\13\u00e1\13\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00ec\n\f\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37"+
		"\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$"+
		"\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3"+
		")\3)\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3"+
		"-\3.\3.\3.\3.\3/\6/\u0165\n/\r/\16/\u0166\3/\3/\3\60\3\60\3\60\3\60\7"+
		"\60\u016f\n\60\f\60\16\60\u0172\13\60\3\60\3\60\3\60\3\60\3\61\3\61\3"+
		"\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3"+
		"\63\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3"+
		"\67\3\67\3\67\3\67\3\67\3\67\38\38\38\38\39\39\39\39\3:\3:\3:\3:\3:\3"+
		":\3;\3;\3;\3;\3;\3;\3<\3<\3<\3<\3<\3<\3=\3=\3=\3=\4\u00b9\u0170\2>\5\3"+
		"\7\r\t\31\13\7\r\b\17\2\21-\23.\25\4\27\5\31\6\33\t\35\n\37\13!\f#\16"+
		"%\17\'\20)\21+\22-\23/\24\61\25\63\26\65\27\67\309\32;\33=\34?\35A\36"+
		"C\37E G!I\"K#M$O%Q&S\'U(W)Y*[+],_/a\60c\61e\62g\63i\64k\65m\66o\67q8s"+
		"9u:w;y<{=\5\2\3\4\34\3\2\2\2\6\2\f\f$$GHQQ\4\2\f\f$$\3\2\62;\3\2C\\\6"+
		"\2\62;C\\aac|\3\2c|\4\2\62;C|\4\2TTtt\4\2WWww\4\2GGgg\4\2CCcc\4\2NNnn"+
		"\4\2UUuu\4\2EEee\4\2HHhh\4\2KKkk\4\2PPpp\4\2JJjj\4\2VVvv\4\2QQqq\4\2R"+
		"Rrr\4\2YYyy\4\2XXxx\4\2FFff\7\2\13\f\16\17\"\"^^xx\u01c5\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O"+
		"\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2"+
		"\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2"+
		"\2i\3\2\2\2\3k\3\2\2\2\3m\3\2\2\2\3o\3\2\2\2\3q\3\2\2\2\4s\3\2\2\2\4u"+
		"\3\2\2\2\4w\3\2\2\2\4y\3\2\2\2\4{\3\2\2\2\5\u00a8\3\2\2\2\7\u00aa\3\2"+
		"\2\2\t\u00ac\3\2\2\2\13\u00b0\3\2\2\2\r\u00b4\3\2\2\2\17\u00c3\3\2\2\2"+
		"\21\u00c5\3\2\2\2\23\u00ca\3\2\2\2\25\u00d4\3\2\2\2\27\u00db\3\2\2\2\31"+
		"\u00eb\3\2\2\2\33\u00ed\3\2\2\2\35\u00ef\3\2\2\2\37\u00f1\3\2\2\2!\u00f3"+
		"\3\2\2\2#\u00f5\3\2\2\2%\u00f7\3\2\2\2\'\u00f9\3\2\2\2)\u00fb\3\2\2\2"+
		"+\u00fd\3\2\2\2-\u00ff\3\2\2\2/\u0101\3\2\2\2\61\u0103\3\2\2\2\63\u0105"+
		"\3\2\2\2\65\u0107\3\2\2\2\67\u0109\3\2\2\29\u010b\3\2\2\2;\u010e\3\2\2"+
		"\2=\u0111\3\2\2\2?\u0117\3\2\2\2A\u011c\3\2\2\2C\u011f\3\2\2\2E\u0122"+
		"\3\2\2\2G\u0125\3\2\2\2I\u012e\3\2\2\2K\u0132\3\2\2\2M\u0137\3\2\2\2O"+
		"\u013c\3\2\2\2Q\u0141\3\2\2\2S\u0147\3\2\2\2U\u014c\3\2\2\2W\u0151\3\2"+
		"\2\2Y\u0154\3\2\2\2[\u0158\3\2\2\2]\u015f\3\2\2\2_\u0164\3\2\2\2a\u016a"+
		"\3\2\2\2c\u0177\3\2\2\2e\u017d\3\2\2\2g\u0182\3\2\2\2i\u0188\3\2\2\2k"+
		"\u018b\3\2\2\2m\u018f\3\2\2\2o\u0195\3\2\2\2q\u019b\3\2\2\2s\u019f\3\2"+
		"\2\2u\u01a3\3\2\2\2w\u01a9\3\2\2\2y\u01af\3\2\2\2{\u01b5\3\2\2\2}\u008a"+
		"\7$\2\2~\u0080\n\2\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2"+
		"\2\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084"+
		"\u0085\7^\2\2\u0085\u0086\7w\2\2\u0086\u0087\7\62\2\2\u0087\u0088\7\62"+
		"\2\2\u0088\u0089\7\62\2\2\u0089\u008b\7\62\2\2\u008a\u0081\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0091\3\2"+
		"\2\2\u008e\u0090\n\3\2\2\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0091\3\2"+
		"\2\2\u0094\u0095\t\3\2\2\u0095\u00a9\b\2\2\2\u0096\u009a\7$\2\2\u0097"+
		"\u0099\n\4\2\2\u0098\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2"+
		"\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d"+
		"\u009e\7\2\2\3\u009e\u00a9\b\2\3\2\u009f\u00a3\7$\2\2\u00a0\u00a2\n\3"+
		"\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7\f"+
		"\2\2\u00a7\u00a9\b\2\4\2\u00a8}\3\2\2\2\u00a8\u0096\3\2\2\2\u00a8\u009f"+
		"\3\2\2\2\u00a9\6\3\2\2\2\u00aa\u00ab\7=\2\2\u00ab\b\3\2\2\2\u00ac\u00ad"+
		"\7?\2\2\u00ad\u00ae\7@\2\2\u00ae\n\3\2\2\2\u00af\u00b1\t\5\2\2\u00b0\u00af"+
		"\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3"+
		"\f\3\2\2\2\u00b4\u00b9\7$\2\2\u00b5\u00b8\5\17\7\2\u00b6\u00b8\13\2\2"+
		"\2\u00b7\u00b5\3\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00ba"+
		"\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc"+
		"\u00bd\7$\2\2\u00bd\u00be\b\6\5\2\u00be\16\3\2\2\2\u00bf\u00c0\7^\2\2"+
		"\u00c0\u00c4\7$\2\2\u00c1\u00c2\7^\2\2\u00c2\u00c4\7^\2\2\u00c3\u00bf"+
		"\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\20\3\2\2\2\u00c5\u00c6\7u\2\2\u00c6"+
		"\u00c7\7g\2\2\u00c7\u00c8\7n\2\2\u00c8\u00c9\7h\2\2\u00c9\22\3\2\2\2\u00ca"+
		"\u00cb\7U\2\2\u00cb\u00cc\7G\2\2\u00cc\u00cd\7N\2\2\u00cd\u00ce\7H\2\2"+
		"\u00ce\u00cf\7a\2\2\u00cf\u00d0\7V\2\2\u00d0\u00d1\7[\2\2\u00d1\u00d2"+
		"\7R\2\2\u00d2\u00d3\7G\2\2\u00d3\24\3\2\2\2\u00d4\u00d8\t\6\2\2\u00d5"+
		"\u00d7\t\7\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2"+
		"\2\2\u00d8\u00d9\3\2\2\2\u00d9\26\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00df"+
		"\t\b\2\2\u00dc\u00de\t\t\2\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\30\3\2\2\2\u00e1\u00df\3\2\2"+
		"\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\t\n\2\2\u00e4\u00e5\t\13\2\2\u00e5\u00ec"+
		"\t\f\2\2\u00e6\u00e7\7h\2\2\u00e7\u00e8\t\r\2\2\u00e8\u00e9\t\16\2\2\u00e9"+
		"\u00ea\t\17\2\2\u00ea\u00ec\t\f\2\2\u00eb\u00e2\3\2\2\2\u00eb\u00e6\3"+
		"\2\2\2\u00ec\32\3\2\2\2\u00ed\u00ee\7*\2\2\u00ee\34\3\2\2\2\u00ef\u00f0"+
		"\7+\2\2\u00f0\36\3\2\2\2\u00f1\u00f2\7<\2\2\u00f2 \3\2\2\2\u00f3\u00f4"+
		"\7B\2\2\u00f4\"\3\2\2\2\u00f5\u00f6\7.\2\2\u00f6$\3\2\2\2\u00f7\u00f8"+
		"\7-\2\2\u00f8&\3\2\2\2\u00f9\u00fa\7/\2\2\u00fa(\3\2\2\2\u00fb\u00fc\7"+
		",\2\2\u00fc*\3\2\2\2\u00fd\u00fe\7\61\2\2\u00fe,\3\2\2\2\u00ff\u0100\7"+
		"\u0080\2\2\u0100.\3\2\2\2\u0101\u0102\7>\2\2\u0102\60\3\2\2\2\u0103\u0104"+
		"\7?\2\2\u0104\62\3\2\2\2\u0105\u0106\7}\2\2\u0106\64\3\2\2\2\u0107\u0108"+
		"\7\177\2\2\u0108\66\3\2\2\2\u0109\u010a\7\60\2\2\u010a8\3\2\2\2\u010b"+
		"\u010c\7>\2\2\u010c\u010d\7?\2\2\u010d:\3\2\2\2\u010e\u010f\7>\2\2\u010f"+
		"\u0110\7/\2\2\u0110<\3\2\2\2\u0111\u0112\t\20\2\2\u0112\u0113\t\16\2\2"+
		"\u0113\u0114\t\r\2\2\u0114\u0115\t\17\2\2\u0115\u0116\t\17\2\2\u0116>"+
		"\3\2\2\2\u0117\u0118\t\f\2\2\u0118\u0119\t\16\2\2\u0119\u011a\t\17\2\2"+
		"\u011a\u011b\t\f\2\2\u011b@\3\2\2\2\u011c\u011d\t\21\2\2\u011d\u011e\t"+
		"\22\2\2\u011eB\3\2\2\2\u011f\u0120\t\22\2\2\u0120\u0121\t\21\2\2\u0121"+
		"D\3\2\2\2\u0122\u0123\t\22\2\2\u0123\u0124\t\23\2\2\u0124F\3\2\2\2\u0125"+
		"\u0126\t\22\2\2\u0126\u0127\t\23\2\2\u0127\u0128\t\24\2\2\u0128\u0129"+
		"\t\f\2\2\u0129\u012a\t\n\2\2\u012a\u012b\t\22\2\2\u012b\u012c\t\25\2\2"+
		"\u012c\u012d\t\17\2\2\u012dH\3\2\2\2\u012e\u012f\t\16\2\2\u012f\u0130"+
		"\t\f\2\2\u0130\u0131\t\25\2\2\u0131J\3\2\2\2\u0132\u0133\t\16\2\2\u0133"+
		"\u0134\t\26\2\2\u0134\u0135\t\26\2\2\u0135\u0136\t\27\2\2\u0136L\3\2\2"+
		"\2\u0137\u0138\t\27\2\2\u0138\u0139\t\26\2\2\u0139\u013a\t\26\2\2\u013a"+
		"\u013b\t\16\2\2\u013bN\3\2\2\2\u013c\u013d\t\25\2\2\u013d\u013e\t\24\2"+
		"\2\u013e\u013f\t\f\2\2\u013f\u0140\t\23\2\2\u0140P\3\2\2\2\u0141\u0142"+
		"\t\30\2\2\u0142\u0143\t\24\2\2\u0143\u0144\t\22\2\2\u0144\u0145\t\16\2"+
		"\2\u0145\u0146\t\f\2\2\u0146R\3\2\2\2\u0147\u0148\t\20\2\2\u0148\u0149"+
		"\t\r\2\2\u0149\u014a\t\17\2\2\u014a\u014b\t\f\2\2\u014bT\3\2\2\2\u014c"+
		"\u014d\t\f\2\2\u014d\u014e\t\17\2\2\u014e\u014f\t\r\2\2\u014f\u0150\t"+
		"\20\2\2\u0150V\3\2\2\2\u0151\u0152\t\26\2\2\u0152\u0153\t\21\2\2\u0153"+
		"X\3\2\2\2\u0154\u0155\t\23\2\2\u0155\u0156\t\f\2\2\u0156\u0157\t\30\2"+
		"\2\u0157Z\3\2\2\2\u0158\u0159\t\22\2\2\u0159\u015a\t\17\2\2\u015a\u015b"+
		"\t\31\2\2\u015b\u015c\t\26\2\2\u015c\u015d\t\22\2\2\u015d\u015e\t\32\2"+
		"\2\u015e\\\3\2\2\2\u015f\u0160\t\23\2\2\u0160\u0161\t\26\2\2\u0161\u0162"+
		"\t\25\2\2\u0162^\3\2\2\2\u0163\u0165\t\33\2\2\u0164\u0163\3\2\2\2\u0165"+
		"\u0166\3\2\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0168\3\2"+
		"\2\2\u0168\u0169\b/\6\2\u0169`\3\2\2\2\u016a\u016b\7/\2\2\u016b\u016c"+
		"\7/\2\2\u016c\u0170\3\2\2\2\u016d\u016f\13\2\2\2\u016e\u016d\3\2\2\2\u016f"+
		"\u0172\3\2\2\2\u0170\u0171\3\2\2\2\u0170\u016e\3\2\2\2\u0171\u0173\3\2"+
		"\2\2\u0172\u0170\3\2\2\2\u0173\u0174\7\f\2\2\u0174\u0175\3\2\2\2\u0175"+
		"\u0176\b\60\6\2\u0176b\3\2\2\2\u0177\u0178\7,\2\2\u0178\u0179\7+\2\2\u0179"+
		"\u017a\3\2\2\2\u017a\u017b\7\2\2\3\u017b\u017c\b\61\7\2\u017cd\3\2\2\2"+
		"\u017d\u017e\7,\2\2\u017e\u017f\7+\2\2\u017f\u0180\3\2\2\2\u0180\u0181"+
		"\b\62\b\2\u0181f\3\2\2\2\u0182\u0183\7*\2\2\u0183\u0184\7,\2\2\u0184\u0185"+
		"\3\2\2\2\u0185\u0186\b\63\t\2\u0186\u0187\b\63\6\2\u0187h\3\2\2\2\u0188"+
		"\u0189\13\2\2\2\u0189\u018a\b\64\n\2\u018aj\3\2\2\2\u018b\u018c\13\2\2"+
		"\2\u018c\u018d\7\2\2\3\u018d\u018e\b\65\13\2\u018el\3\2\2\2\u018f\u0190"+
		"\7*\2\2\u0190\u0191\7,\2\2\u0191\u0192\3\2\2\2\u0192\u0193\b\66\f\2\u0193"+
		"\u0194\b\66\6\2\u0194n\3\2\2\2\u0195\u0196\7,\2\2\u0196\u0197\7+\2\2\u0197"+
		"\u0198\3\2\2\2\u0198\u0199\b\67\r\2\u0199\u019a\b\67\6\2\u019ap\3\2\2"+
		"\2\u019b\u019c\13\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\b8\6\2\u019er"+
		"\3\2\2\2\u019f\u01a0\13\2\2\2\u01a0\u01a1\7\2\2\3\u01a1\u01a2\b9\16\2"+
		"\u01a2t\3\2\2\2\u01a3\u01a4\7*\2\2\u01a4\u01a5\7,\2\2\u01a5\u01a6\3\2"+
		"\2\2\u01a6\u01a7\b:\f\2\u01a7\u01a8\b:\6\2\u01a8v\3\2\2\2\u01a9\u01aa"+
		"\7,\2\2\u01aa\u01ab\7+\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01ad\7\2\2\3\u01ad"+
		"\u01ae\b;\17\2\u01aex\3\2\2\2\u01af\u01b0\7,\2\2\u01b0\u01b1\7+\2\2\u01b1"+
		"\u01b2\3\2\2\2\u01b2\u01b3\b<\r\2\u01b3\u01b4\b<\6\2\u01b4z\3\2\2\2\u01b5"+
		"\u01b6\13\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01b8\b=\6\2\u01b8|\3\2\2\2"+
		"\24\2\3\4\u0081\u008c\u0091\u009a\u00a3\u00a8\u00b2\u00b7\u00b9\u00c3"+
		"\u00d8\u00df\u00eb\u0166\u0170\20\3\2\2\3\2\3\3\2\4\3\6\5\b\2\2\3\61\6"+
		"\3\62\7\7\3\2\3\64\b\3\65\t\7\4\2\6\2\2\39\n\3;\13";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}