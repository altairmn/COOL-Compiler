lexer grammar CoolLexer;

tokens{
	ERROR,
	TYPEID,
	OBJECTID,
	BOOL_CONST,
	INT_CONST,
	STR_CONST,
	LPAREN,
	RPAREN,
	COLON,
	ATSYM,
	SEMICOLON,
	COMMA,
	PLUS,
	MINUS,
	STAR,
	SLASH,
	TILDE,
	LT,
	EQUALS,
	LBRACE,
	RBRACE,
	DOT,
	DARROW,
	LE,
	ASSIGN,
	CLASS,
	ELSE,
	FI,
	IF,
	IN,
	INHERITS,
	LET,
	LOOP,
	POOL,
	THEN,
	WHILE,
	CASE,
	ESAC,
	OF,
	NEW,
	ISVOID,
	NOT
}

/*
  DO NOT EDIT CODE ABOVE THIS LINE
*/
@members{

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
}


/*
	WRITE ALL LEXER RULES BELOW
*/

// Error String1
ERROR		: '"' ( ~[\u0000]* ('\\u0000') )+ ~["\nEOF]* ["\nEOF] { reportError("String contains null character"); }
			| '"' ~[\n"]* (EOF) { reportError("EOF in string literal"); }
			| '"' ~["\nEOF]* ('\n') { reportError("Unterminated string constant"); }
			;

SEMICOLON   : ';' ;
DARROW      : '=>' ;
INT_CONST	: [0-9]+ ;											// integer constants

/** String Recognition and Error:
 * Unescaped New line
 * String too long (Handled in processString())
 * String containing NULL character
 */

STR_CONST	: '"' (ESC|.)*? '"' { processString() ;} ;
fragment ESC: '\\"' | '\\\\' ;


SELF		: 'self' ;
SELF_TYPE	: 'SELF_TYPE' ;

TYPEID		: [A-Z] [a-zA-Z0-9_]* ;
OBJECTID	: [a-z] [0-9_a-zA-z]* ;

BOOL_CONST	: 't' ('r'|'R') ('u'|'U') ('e'|'E')
			| 'f' ('a'|'A') ('l'|'L') ('s'|'S') ('e'|'E') ;

LPAREN		: '(' ;
RPAREN		: ')' ;
COLON		: ':' ;
ATSYM		: '@' ;
COMMA		: ',' ;
PLUS		: '+' ;
MINUS		: '-' ;
STAR		: '*' ;
SLASH		: '/' ;
TILDE		: '~' ;
LT			: '<' ;
EQUALS		: '=' ;
LBRACE		: '{' ;
RBRACE		: '}' ;
DOT			: '.' ;
LE			: '<=';
ASSIGN		: '<-';

/**
 * Keywords are not case sensitive
 */
CLASS		: ('c'|'C') ('l'|'L') ('a'|'A') ('s'|'S') ('s'|'S') ;
ELSE		: ('e'|'E') ('l'|'L') ('s'|'S') ('e'|'E') ;
FI			: ('f'|'F') ('i'|'I') ;
IF			: ('i'|'I') ('f'|'F') ;
IN			: ('i'|'I') ('n'|'N') ;
INHERITS	: ('i'|'I') ('n'|'N') ('h'|'H') ('e'|'E') ('r'|'R') ('i'|'I') ('t'|'T') ('s'|'S') ;
LET			: ('l'|'L') ('e'|'E') ('t'|'T') ;
LOOP		: ('l'|'L') ('o'|'O') ('o'|'O') ('p'|'P') ;
POOL		: ('p'|'P') ('o'|'O') ('o'|'O') ('l'|'L') ;
THEN		: ('t'|'T') ('h'|'H') ('e'|'E') ('n'|'N') ;
WHILE		: ('w'|'W') ('h'|'H') ('i'|'I') ('l'|'L') ('e'|'E') ;
CASE		: ('c'|'C') ('a'|'A') ('s'|'S') ('e'|'E') ;
ESAC		: ('e'|'E') ('s'|'S') ('a'|'A') ('c'|'C') ;
OF			: ('o'|'O') ('f'|'F') ;
NEW			: ('n'|'N') ('e'|'E') ('w'|'W') ;
ISVOID		: ('i'|'I') ('s'|'S') ('v'|'V') ('o'|'O') ('i'|'I') ('d'|'D') ;
NOT			: ('n'|'N') ('o'|'O') ('t'|'T') ;

/**
 * The following haven't been declared in
 * the list above.
 */

WS			: [ \t\r\n\f\v]+ -> skip ;	// skip spaces, tabs, newlines

/* COMMENT */

LINE_COMMENT: '--' .*? '\n' -> skip ;

END_COMMENT	: '*)' EOF { reportError("Unmatched *)"); } ;
UN_COMMENT 	: '*)' { reportError("Unmatched *)"); } ;

COMMENT		: '(*'-> pushMode(INCOMMENT), skip;

NOTFOUND	: . { notFound(); } ;

mode INCOMMENT;
ERR     	: .(EOF) { reportError("EOF in comment"); } ;
OCOMMENT	: '(*' -> pushMode(ININCOM), skip ;
CCOMMENT	: '*)' -> popMode, skip ;
INCOMMENT_T : . -> skip ;

mode ININCOM;
ERR3		: .(EOF) { reportError("EOF in comment"); } ;
OCOM		: '(*' -> pushMode(ININCOM), skip ;
ERR4		: '*)' EOF { reportError("EOF in comment"); } ; 
CCOM		: '*)' -> popMode, skip ;
INCOM_TEXT	: . -> skip ;
