// Generated from CoolLexer.g4 by ANTLR 4.5.1
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
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, TYPEID=2, OBJECTID=3, BOOL_CONST=4, INT_CONST=5, STR_CONST=6, 
		LPAREN=7, RPAREN=8, COLON=9, ATSYM=10, SEMICOLON=11, COMMA=12, PLUS=13, 
		MINUS=14, STAR=15, SLASH=16, TILDE=17, LT=18, EQUALS=19, LBRACE=20, RBRACE=21, 
		DOT=22, DARROW=23, LE=24, ASSIGN=25, CLASS=26, ELSE=27, FI=28, IF=29, 
		IN=30, INHERITS=31, LET=32, LOOP=33, POOL=34, THEN=35, WHILE=36, CASE=37, 
		ESAC=38, OF=39, NEW=40, ISVOID=41, NOT=42, WS=43, THEEND=44, SINGLE_COMMENT=45, 
		COMMENT_CLOSE=46, CLOSED=47, COM_EOF=48, NEWLINE=49, ESC=50, ESC_NULL=51, 
		STR_EOF=52, ERR1=53, ERR2=54, ERR3=55, LQUOTE=56, NL=57, TAB=58, BACKSPAC=59, 
		LINEFEED=60, SLASHN=61, ESC_NL=62;
	public static final int COMMENT = 1;
	public static final int STR = 2;
	public static final int ERR = 3;
	public static String[] modeNames = {
		"DEFAULT_MODE", "COMMENT", "STR", "ERR"
	};

	public static final String[] ruleNames = {
		"LPAREN", "RPAREN", "COLON", "ATSYM", "SEMICOLON", "COMMA", "PLUS", "MINUS", 
		"STAR", "SLASH", "TILDE", "LT", "EQUALS", "LBRACE", "RBRACE", "DOT", "DARROW", 
		"LE", "ASSIGN", "CLASS", "ELSE", "BOOL_CONST", "FI", "IF", "IN", "INHERITS", 
		"ISVOID", "LET", "LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", 
		"NOT", "OF", "TYPEID", "OBJECTID", "INT_CONST", "WS", "THEEND", "LQUOTE", 
		"SINGLE_COMMENT", "COMMENT_OPEN", "COMMENT_CLOSE", "ERROR", "OPEN", "CLOSED", 
		"COM_EOF", "NL", "ANY", "STR_CONST", "NEWLINE", "ESC", "ESC_NULL", "STR_EOF", 
		"TAB", "BACKSPAC", "LINEFEED", "SLASHN", "ESC_NL", "ESC_CHAR", "TEXT", 
		"ERR1", "ERR2", "ERR3"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'('", "')'", "':'", "'@'", 
		"';'", "','", "'+'", "'-'", "'*'", "'/'", "'~'", "'<'", "'='", "'{'", 
		"'}'", "'.'", "'=>'", "'<='", "'<-'", null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "'*)'", null, null, null, null, null, null, null, null, null, 
		null, null, "'\\t'", "'\\b'", "'\\f'", "'\\n'", "'\\\n'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ERROR", "TYPEID", "OBJECTID", "BOOL_CONST", "INT_CONST", "STR_CONST", 
		"LPAREN", "RPAREN", "COLON", "ATSYM", "SEMICOLON", "COMMA", "PLUS", "MINUS", 
		"STAR", "SLASH", "TILDE", "LT", "EQUALS", "LBRACE", "RBRACE", "DOT", "DARROW", 
		"LE", "ASSIGN", "CLASS", "ELSE", "FI", "IF", "IN", "INHERITS", "LET", 
		"LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "OF", "NEW", "ISVOID", 
		"NOT", "WS", "THEEND", "SINGLE_COMMENT", "COMMENT_CLOSE", "CLOSED", "COM_EOF", 
		"NEWLINE", "ESC", "ESC_NULL", "STR_EOF", "ERR1", "ERR2", "ERR3", "LQUOTE", 
		"NL", "TAB", "BACKSPAC", "LINEFEED", "SLASHN", "ESC_NL"
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


	  public static int maxStringLength = 1024;
	  public int nesting = 0;
	  public static String fixString(String inp){
	    String ret = new String();
	    int length = inp.length();
	    //inp = inp.substring(1,length-1);
	    char[] x = inp.toCharArray();
	    for(int i=1;i<x.length-1;i++){
	        if(x[i]=='\\'){
	            switch(x[i+1]){
	                case 'b':
	                    ret = ret +'\b';
	                    break;
	                case 't':
	                    ret = ret +'\t';
	                    break;
	                case 'n':
	                    ret = ret +'\n';
	                    break;
	                case 'f':
	                    ret = ret +'\f';
	                    break;
	                default:
	                    ret = ret + x[i+1];
	                    break;
	            }
	            i++;
	        }else{
	            ret = ret + x[i];
	        }
	    }
	    return ret;
	  }
	  public void valid(){
	    Token t = _factory.create(_tokenFactorySourcePair, _type, _text, _channel, _tokenStartCharIndex, getCharIndex()-1,
	    _tokenStartLine, _tokenStartCharPositionInLine);
	    String fixed = fixString(t.getText());
	    setText(fixed);
	    mode(0);
	    if(fixed.length()>maxStringLength){
	      reportError("String constant too long");
	    }
	  }

	  public void reportError(String errorString){
	    setText(errorString);
	    setType(ERROR);
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
		case 44:
			COMMENT_OPEN_action((RuleContext)_localctx, actionIndex);
			break;
		case 45:
			COMMENT_CLOSE_action((RuleContext)_localctx, actionIndex);
			break;
		case 47:
			OPEN_action((RuleContext)_localctx, actionIndex);
			break;
		case 48:
			CLOSED_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			COM_EOF_action((RuleContext)_localctx, actionIndex);
			break;
		case 52:
			STR_CONST_action((RuleContext)_localctx, actionIndex);
			break;
		case 53:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 54:
			ESC_action((RuleContext)_localctx, actionIndex);
			break;
		case 55:
			ESC_NULL_action((RuleContext)_localctx, actionIndex);
			break;
		case 56:
			STR_EOF_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void COMMENT_OPEN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			nesting++;
			break;
		}
	}
	private void COMMENT_CLOSE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			reportError("Unmatched *)");
			break;
		}
	}
	private void OPEN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			nesting++;
			break;
		}
	}
	private void CLOSED_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:

						nesting--;
						if(nesting == 0)
			 				skip();
						else
							more();
					
			break;
		}
	}
	private void COM_EOF_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			 reportError("EOF in comment");
			break;
		}
	}
	private void STR_CONST_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			valid();
			break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			 reportError("Unterminated string constant"); 
			break;
		}
	}
	private void ESC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:
			 reportError("String contains null character.");
			break;
		}
	}
	private void ESC_NULL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:
			 reportError("String contains escaped null character.");
			break;
		}
	}
	private void STR_EOF_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9:
			 reportError("EOF in string constant");
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2@\u01b7\b\1\b\1\b"+
		"\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t"+
		"\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21"+
		"\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30"+
		"\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37"+
		"\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)"+
		"\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63"+
		"\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;"+
		"\4<\t<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00ca"+
		"\n\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3"+
		"$\3%\3%\3%\3%\3&\3&\3&\3\'\3\'\7\'\u0115\n\'\f\'\16\'\u0118\13\'\3(\3"+
		"(\7(\u011c\n(\f(\16(\u011f\13(\3)\6)\u0122\n)\r)\16)\u0123\3*\6*\u0127"+
		"\n*\r*\16*\u0128\3*\3*\3+\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3-\7-\u013a"+
		"\n-\f-\16-\u013d\13-\3-\5-\u0140\n-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3"+
		"/\3/\3/\3/\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64"+
		"\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\38\38\38"+
		"\38\38\39\39\39\39\39\39\3:\3:\3:\3;\3;\3;\3;\3;\3<\3<\3<\3<\3<\3=\3="+
		"\3=\3=\3=\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3A\6A\u01a4\nA"+
		"\rA\16A\u01a5\3A\3A\3B\3B\3B\3B\3B\3C\3C\3C\3C\3C\3D\3D\3D\3D\3\u013b"+
		"\2E\6\t\b\n\n\13\f\f\16\r\20\16\22\17\24\20\26\21\30\22\32\23\34\24\36"+
		"\25 \26\"\27$\30&\31(\32*\33,\34.\35\60\6\62\36\64\37\66 8!:+<\">#@$B"+
		"%D&F\'H(J*L,N)P\4R\5T\7V-X.Z:\\/^\2`\60b\3d\2f\61h\62j;l\2n\bp\63r\64"+
		"t\65v\66x<z=|>~?\u0080@\u0082\2\u0084\2\u0086\67\u00888\u008a9\6\2\3\4"+
		"\5\31\4\2EEee\4\2NNnn\4\2CCcc\4\2UUuu\4\2GGgg\3\2hh\3\2vv\4\2TTtt\4\2"+
		"WWww\4\2HHhh\4\2KKkk\4\2PPpp\4\2JJjj\4\2VVvv\4\2XXxx\4\2QQqq\4\2FFff\4"+
		"\2RRrr\4\2YYyy\6\2\62;C\\aac|\4\2\13\17\"\"\3\3\f\f\6\2\2\2\f\f$$^^\u01ba"+
		"\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2"+
		"\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2"+
		"\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2"+
		"\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2"+
		"\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2>\3\2\2"+
		"\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2"+
		"L\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2R\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3"+
		"\2\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3\2\2\2\2`\3\2\2\2\2b\3\2\2\2\3d\3\2"+
		"\2\2\3f\3\2\2\2\3h\3\2\2\2\3j\3\2\2\2\3l\3\2\2\2\4n\3\2\2\2\4p\3\2\2\2"+
		"\4r\3\2\2\2\4t\3\2\2\2\4v\3\2\2\2\4x\3\2\2\2\4z\3\2\2\2\4|\3\2\2\2\4~"+
		"\3\2\2\2\4\u0080\3\2\2\2\4\u0082\3\2\2\2\4\u0084\3\2\2\2\5\u0086\3\2\2"+
		"\2\5\u0088\3\2\2\2\5\u008a\3\2\2\2\6\u008c\3\2\2\2\b\u008e\3\2\2\2\n\u0090"+
		"\3\2\2\2\f\u0092\3\2\2\2\16\u0094\3\2\2\2\20\u0096\3\2\2\2\22\u0098\3"+
		"\2\2\2\24\u009a\3\2\2\2\26\u009c\3\2\2\2\30\u009e\3\2\2\2\32\u00a0\3\2"+
		"\2\2\34\u00a2\3\2\2\2\36\u00a4\3\2\2\2 \u00a6\3\2\2\2\"\u00a8\3\2\2\2"+
		"$\u00aa\3\2\2\2&\u00ac\3\2\2\2(\u00af\3\2\2\2*\u00b2\3\2\2\2,\u00b5\3"+
		"\2\2\2.\u00bb\3\2\2\2\60\u00c9\3\2\2\2\62\u00cb\3\2\2\2\64\u00ce\3\2\2"+
		"\2\66\u00d1\3\2\2\28\u00d4\3\2\2\2:\u00dd\3\2\2\2<\u00e4\3\2\2\2>\u00e8"+
		"\3\2\2\2@\u00ed\3\2\2\2B\u00f2\3\2\2\2D\u00f7\3\2\2\2F\u00fd\3\2\2\2H"+
		"\u0102\3\2\2\2J\u0107\3\2\2\2L\u010b\3\2\2\2N\u010f\3\2\2\2P\u0112\3\2"+
		"\2\2R\u0119\3\2\2\2T\u0121\3\2\2\2V\u0126\3\2\2\2X\u012c\3\2\2\2Z\u0130"+
		"\3\2\2\2\\\u0135\3\2\2\2^\u0143\3\2\2\2`\u014b\3\2\2\2b\u0150\3\2\2\2"+
		"d\u0152\3\2\2\2f\u015a\3\2\2\2h\u0161\3\2\2\2j\u0166\3\2\2\2l\u016a\3"+
		"\2\2\2n\u016e\3\2\2\2p\u0171\3\2\2\2r\u0176\3\2\2\2t\u017b\3\2\2\2v\u0181"+
		"\3\2\2\2x\u0184\3\2\2\2z\u0189\3\2\2\2|\u018e\3\2\2\2~\u0193\3\2\2\2\u0080"+
		"\u0198\3\2\2\2\u0082\u019d\3\2\2\2\u0084\u01a3\3\2\2\2\u0086\u01a9\3\2"+
		"\2\2\u0088\u01ae\3\2\2\2\u008a\u01b3\3\2\2\2\u008c\u008d\7*\2\2\u008d"+
		"\7\3\2\2\2\u008e\u008f\7+\2\2\u008f\t\3\2\2\2\u0090\u0091\7<\2\2\u0091"+
		"\13\3\2\2\2\u0092\u0093\7B\2\2\u0093\r\3\2\2\2\u0094\u0095\7=\2\2\u0095"+
		"\17\3\2\2\2\u0096\u0097\7.\2\2\u0097\21\3\2\2\2\u0098\u0099\7-\2\2\u0099"+
		"\23\3\2\2\2\u009a\u009b\7/\2\2\u009b\25\3\2\2\2\u009c\u009d\7,\2\2\u009d"+
		"\27\3\2\2\2\u009e\u009f\7\61\2\2\u009f\31\3\2\2\2\u00a0\u00a1\7\u0080"+
		"\2\2\u00a1\33\3\2\2\2\u00a2\u00a3\7>\2\2\u00a3\35\3\2\2\2\u00a4\u00a5"+
		"\7?\2\2\u00a5\37\3\2\2\2\u00a6\u00a7\7}\2\2\u00a7!\3\2\2\2\u00a8\u00a9"+
		"\7\177\2\2\u00a9#\3\2\2\2\u00aa\u00ab\7\60\2\2\u00ab%\3\2\2\2\u00ac\u00ad"+
		"\7?\2\2\u00ad\u00ae\7@\2\2\u00ae\'\3\2\2\2\u00af\u00b0\7>\2\2\u00b0\u00b1"+
		"\7?\2\2\u00b1)\3\2\2\2\u00b2\u00b3\7>\2\2\u00b3\u00b4\7/\2\2\u00b4+\3"+
		"\2\2\2\u00b5\u00b6\t\2\2\2\u00b6\u00b7\t\3\2\2\u00b7\u00b8\t\4\2\2\u00b8"+
		"\u00b9\t\5\2\2\u00b9\u00ba\t\5\2\2\u00ba-\3\2\2\2\u00bb\u00bc\t\6\2\2"+
		"\u00bc\u00bd\t\3\2\2\u00bd\u00be\t\5\2\2\u00be\u00bf\t\6\2\2\u00bf/\3"+
		"\2\2\2\u00c0\u00c1\t\7\2\2\u00c1\u00c2\t\4\2\2\u00c2\u00c3\t\3\2\2\u00c3"+
		"\u00c4\t\5\2\2\u00c4\u00ca\t\6\2\2\u00c5\u00c6\t\b\2\2\u00c6\u00c7\t\t"+
		"\2\2\u00c7\u00c8\t\n\2\2\u00c8\u00ca\t\6\2\2\u00c9\u00c0\3\2\2\2\u00c9"+
		"\u00c5\3\2\2\2\u00ca\61\3\2\2\2\u00cb\u00cc\t\13\2\2\u00cc\u00cd\t\f\2"+
		"\2\u00cd\63\3\2\2\2\u00ce\u00cf\t\f\2\2\u00cf\u00d0\t\13\2\2\u00d0\65"+
		"\3\2\2\2\u00d1\u00d2\t\f\2\2\u00d2\u00d3\t\r\2\2\u00d3\67\3\2\2\2\u00d4"+
		"\u00d5\t\f\2\2\u00d5\u00d6\t\r\2\2\u00d6\u00d7\t\16\2\2\u00d7\u00d8\t"+
		"\6\2\2\u00d8\u00d9\t\t\2\2\u00d9\u00da\t\f\2\2\u00da\u00db\t\17\2\2\u00db"+
		"\u00dc\t\5\2\2\u00dc9\3\2\2\2\u00dd\u00de\t\f\2\2\u00de\u00df\t\5\2\2"+
		"\u00df\u00e0\t\20\2\2\u00e0\u00e1\t\21\2\2\u00e1\u00e2\t\f\2\2\u00e2\u00e3"+
		"\t\22\2\2\u00e3;\3\2\2\2\u00e4\u00e5\t\3\2\2\u00e5\u00e6\t\6\2\2\u00e6"+
		"\u00e7\t\17\2\2\u00e7=\3\2\2\2\u00e8\u00e9\t\3\2\2\u00e9\u00ea\t\21\2"+
		"\2\u00ea\u00eb\t\21\2\2\u00eb\u00ec\t\23\2\2\u00ec?\3\2\2\2\u00ed\u00ee"+
		"\t\23\2\2\u00ee\u00ef\t\21\2\2\u00ef\u00f0\t\21\2\2\u00f0\u00f1\t\3\2"+
		"\2\u00f1A\3\2\2\2\u00f2\u00f3\t\17\2\2\u00f3\u00f4\t\16\2\2\u00f4\u00f5"+
		"\t\6\2\2\u00f5\u00f6\t\r\2\2\u00f6C\3\2\2\2\u00f7\u00f8\t\24\2\2\u00f8"+
		"\u00f9\t\16\2\2\u00f9\u00fa\t\f\2\2\u00fa\u00fb\t\3\2\2\u00fb\u00fc\t"+
		"\6\2\2\u00fcE\3\2\2\2\u00fd\u00fe\t\2\2\2\u00fe\u00ff\t\4\2\2\u00ff\u0100"+
		"\t\5\2\2\u0100\u0101\t\6\2\2\u0101G\3\2\2\2\u0102\u0103\t\6\2\2\u0103"+
		"\u0104\t\5\2\2\u0104\u0105\t\4\2\2\u0105\u0106\t\2\2\2\u0106I\3\2\2\2"+
		"\u0107\u0108\t\r\2\2\u0108\u0109\t\6\2\2\u0109\u010a\t\24\2\2\u010aK\3"+
		"\2\2\2\u010b\u010c\t\r\2\2\u010c\u010d\t\21\2\2\u010d\u010e\t\17\2\2\u010e"+
		"M\3\2\2\2\u010f\u0110\t\21\2\2\u0110\u0111\t\13\2\2\u0111O\3\2\2\2\u0112"+
		"\u0116\4C\\\2\u0113\u0115\t\25\2\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2"+
		"\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117Q\3\2\2\2\u0118\u0116"+
		"\3\2\2\2\u0119\u011d\4c|\2\u011a\u011c\t\25\2\2\u011b\u011a\3\2\2\2\u011c"+
		"\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011eS\3\2\2\2"+
		"\u011f\u011d\3\2\2\2\u0120\u0122\4\62;\2\u0121\u0120\3\2\2\2\u0122\u0123"+
		"\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124U\3\2\2\2\u0125"+
		"\u0127\t\26\2\2\u0126\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0126\3"+
		"\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012b\b*\2\2\u012b"+
		"W\3\2\2\2\u012c\u012d\7\2\2\3\u012d\u012e\3\2\2\2\u012e\u012f\b+\2\2\u012f"+
		"Y\3\2\2\2\u0130\u0131\7$\2\2\u0131\u0132\3\2\2\2\u0132\u0133\b,\3\2\u0133"+
		"\u0134\b,\4\2\u0134[\3\2\2\2\u0135\u0136\7/\2\2\u0136\u0137\7/\2\2\u0137"+
		"\u013b\3\2\2\2\u0138\u013a\13\2\2\2\u0139\u0138\3\2\2\2\u013a\u013d\3"+
		"\2\2\2\u013b\u013c\3\2\2\2\u013b\u0139\3\2\2\2\u013c\u013f\3\2\2\2\u013d"+
		"\u013b\3\2\2\2\u013e\u0140\t\27\2\2\u013f\u013e\3\2\2\2\u0140\u0141\3"+
		"\2\2\2\u0141\u0142\b-\2\2\u0142]\3\2\2\2\u0143\u0144\7*\2\2\u0144\u0145"+
		"\7,\2\2\u0145\u0146\3\2\2\2\u0146\u0147\b.\5\2\u0147\u0148\3\2\2\2\u0148"+
		"\u0149\b.\6\2\u0149\u014a\b.\4\2\u014a_\3\2\2\2\u014b\u014c\7,\2\2\u014c"+
		"\u014d\7+\2\2\u014d\u014e\3\2\2\2\u014e\u014f\b/\7\2\u014fa\3\2\2\2\u0150"+
		"\u0151\13\2\2\2\u0151c\3\2\2\2\u0152\u0153\7*\2\2\u0153\u0154\7,\2\2\u0154"+
		"\u0155\3\2\2\2\u0155\u0156\b\61\b\2\u0156\u0157\3\2\2\2\u0157\u0158\b"+
		"\61\4\2\u0158\u0159\b\61\6\2\u0159e\3\2\2\2\u015a\u015b\7,\2\2\u015b\u015c"+
		"\7+\2\2\u015c\u015d\3\2\2\2\u015d\u015e\b\62\t\2\u015e\u015f\3\2\2\2\u015f"+
		"\u0160\b\62\n\2\u0160g\3\2\2\2\u0161\u0162\7\2\2\3\u0162\u0163\b\63\13"+
		"\2\u0163\u0164\3\2\2\2\u0164\u0165\b\63\n\2\u0165i\3\2\2\2\u0166\u0167"+
		"\7\f\2\2\u0167\u0168\3\2\2\2\u0168\u0169\b\64\4\2\u0169k\3\2\2\2\u016a"+
		"\u016b\13\2\2\2\u016b\u016c\3\2\2\2\u016c\u016d\b\65\4\2\u016dm\3\2\2"+
		"\2\u016e\u016f\7$\2\2\u016f\u0170\b\66\f\2\u0170o\3\2\2\2\u0171\u0172"+
		"\7\f\2\2\u0172\u0173\b\67\r\2\u0173\u0174\3\2\2\2\u0174\u0175\b\67\16"+
		"\2\u0175q\3\2\2\2\u0176\u0177\7\2\2\2\u0177\u0178\b8\17\2\u0178\u0179"+
		"\3\2\2\2\u0179\u017a\b8\20\2\u017as\3\2\2\2\u017b\u017c\7^\2\2\u017c\u017d"+
		"\7\2\2\2\u017d\u017e\b9\21\2\u017e\u017f\3\2\2\2\u017f\u0180\b9\20\2\u0180"+
		"u\3\2\2\2\u0181\u0182\7\2\2\3\u0182\u0183\b:\22\2\u0183w\3\2\2\2\u0184"+
		"\u0185\7^\2\2\u0185\u0186\7v\2\2\u0186\u0187\3\2\2\2\u0187\u0188\b;\4"+
		"\2\u0188y\3\2\2\2\u0189\u018a\7^\2\2\u018a\u018b\7d\2\2\u018b\u018c\3"+
		"\2\2\2\u018c\u018d\b<\4\2\u018d{\3\2\2\2\u018e\u018f\7^\2\2\u018f\u0190"+
		"\7h\2\2\u0190\u0191\3\2\2\2\u0191\u0192\b=\4\2\u0192}\3\2\2\2\u0193\u0194"+
		"\7^\2\2\u0194\u0195\7p\2\2\u0195\u0196\3\2\2\2\u0196\u0197\b>\4\2\u0197"+
		"\177\3\2\2\2\u0198\u0199\7^\2\2\u0199\u019a\7\f\2\2\u019a\u019b\3\2\2"+
		"\2\u019b\u019c\b?\4\2\u019c\u0081\3\2\2\2\u019d\u019e\7^\2\2\u019e\u019f"+
		"\13\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u01a1\b@\4\2\u01a1\u0083\3\2\2\2\u01a2"+
		"\u01a4\n\30\2\2\u01a3\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a3\3"+
		"\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\bA\4\2\u01a8"+
		"\u0085\3\2\2\2\u01a9\u01aa\7$\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ac\bB\2"+
		"\2\u01ac\u01ad\bB\16\2\u01ad\u0087\3\2\2\2\u01ae\u01af\7\f\2\2\u01af\u01b0"+
		"\3\2\2\2\u01b0\u01b1\bC\2\2\u01b1\u01b2\bC\16\2\u01b2\u0089\3\2\2\2\u01b3"+
		"\u01b4\13\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b6\bD\2\2\u01b6\u008b\3\2"+
		"\2\2\16\2\3\4\5\u00c9\u0116\u011d\u0123\u0128\u013b\u013f\u01a5\23\b\2"+
		"\2\4\4\2\5\2\2\3.\2\7\3\2\3/\3\3\61\4\3\62\5\6\2\2\3\63\6\3\66\7\3\67"+
		"\b\4\2\2\38\t\4\5\2\39\n\3:\13";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}