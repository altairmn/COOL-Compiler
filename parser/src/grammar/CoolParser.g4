parser grammar CoolParser;

options {
	tokenVocab = CoolLexer;
}

@header{
	import cool.AST;
	import java.util.List;
}

@members{
	String filename;
	public void setFilename(String f){
		filename = f;
	}

/*
	DO NOT EDIT THE FILE ABOVE THIS LINE
	Add member functions, variables below.
*/

}

/*
	Add Grammar rules and appropriate actions for building AST below.
*/

program returns [AST.program value] : 
	cl=class_list EOF 
	{
		$value = new AST.program($cl.value, $cl.value.get(0).lineNo);
	}
	;

/* [[class]]+ */
class_list returns [ArrayList<AST.class_> value]
	@init
	{
		$value = new ArrayList<AST.class_>();
	}
	:
		(c = class_ SEMICOLON {$value.add($c.value);})+
	;

class_ returns  [AST.class_ value]  :
	/* class TYPE { [[feature]]* }	*/
	st=CLASS type=TYPEID LBRACE fl=feature_list RBRACE
	{
		$value = new AST.class_($type.getText(), filename, "Object", $feature_list.value, $st.getLine());
	}
	/* class TYPE [inherits TYPE] { [[feature]]* }	*/
	| st=CLASS type=TYPEID INHERITS p_type=TYPEID LBRACE fl=feature_list RBRACE
	{
		$value = new AST.class_($type.getText(), filename, $p_type.getText(), $feature_list.value, $st.getLine());
	}
	;


feature_list returns [ArrayList<AST.feature> value]
	@init
	{
		$value = new ArrayList<AST.feature>();
	}
	:
		(c = feature SEMICOLON {$value.add($c.value);})*
   ;

feature returns [AST.feature value] :
	/* ID: TYPE */
	func = method
	{
		$value = $func.value;
	}
	| var = attr
	{
		$value = $var.value;
	}
	;

attr returns [AST.attr value]	:
	id=OBJECTID COLON type=TYPEID
	{
		$value = new AST.attr($id.getText(), $type.getText(), new AST.no_expr($id.getLine()), $id.getLine());
	}
	/* ID: TYPE [<- expr] */
	| id=OBJECTID COLON type=TYPEID ASSIGN expr=expression
	{
		$value = new AST.attr($id.getText(), $type.getText(), $expr.value, $id.getLine());
	}
	;

method returns [AST.method value] :
	id=OBJECTID LPAREN RPAREN COLON type=TYPEID LBRACE expr=expression RBRACE
	{
		$value = new AST.method($id.getText(), new ArrayList<AST.formal>(), $type.getText(), $expr.value, $id.getLine());
	}
	| id=OBJECTID LPAREN fr_list=formal_list RPAREN COLON type=TYPEID LBRACE expr=expression RBRACE
	{
		$value = new AST.method($id.getText(), $fr_list.value, $type.getText(), $expr.value, $id.getLine());
	}
	;


formal_list returns [ArrayList<AST.formal> value]
	@init
	{
		$value = new ArrayList<AST.formal>();
	}
	:
		c = formal {$value.add($c.value);} 

		(COMMA y = formal {$value.add($y.value);})*
	;


formal returns [AST.formal value]   :
	id=OBJECTID COLON type=TYPEID
	{
		$value = new AST.formal($id.getText(), $type.getText(), $id.getLine()) ;
	}
	;

/* [expr [[, expr]]* ] */
expression_list returns [ArrayList<AST.expression> value]
	@init
	{
		$value = new ArrayList<AST.expression>();
	}
	:
		( expr = expression {$value.add($expr.value);} (COMMA expr = expression {$value.add($expr.value);})* )?
	;

block_expr_list returns [ArrayList<AST.expression> value]
	@init
	{
		$value = new ArrayList<AST.expression>();
	}
	:
		(expr = expression SEMICOLON {$value.add($expr.value);})+
	;

branch_list returns [ArrayList<AST.branch> value]
	@init
	{
		$value = new ArrayList<AST.branch>();
	}
	:
		(br = branch SEMICOLON {$value.add($br.value);})+
	;

branch returns [AST.branch value] :
	id=OBJECTID COLON type=TYPEID DARROW expr=expression SEMICOLON
	{
		$value = new AST.branch($id.getText(), $type.getText(), $expr.value, $id.getLine());
	}
	;



let_list returns [ArrayList<AST.attr> value]
	@init
	{
		$value = new ArrayList<AST.attr>();
	}
	:
	at_un = attr { $value.add($at_un.value); }
	(COMMA at_deux = attr {$value.add($at_deux.value);})*
	;
						

expression returns [AST.expression value]   :
	/*expr.ID([expr [[, expr]]* ]) */
	expr=expression DOT id=OBJECTID LPAREN expr_list = expression_list RPAREN
	{
		$value = new AST.dispatch($expr.value, $id.getText(), $expr_list.value, $expr.value.lineNo);
	}
	/*expr[@TYPE].ID([expr [[, expr]]* ]) */
	| expr=expression ATSYM type=TYPEID DOT id=OBJECTID LPAREN expr_list = expression_list RPAREN
	{
		$value = new AST.static_dispatch($expr.value, $type.getText(), $id.getText(), $expr_list.value, $expr.value.lineNo);
	}
	/*ID ( [expr [[, expr]]* ]) */
	| id=OBJECTID LPAREN expr_list = expression_list RPAREN
	{
		$value = new AST.dispatch(new AST.object("self" , $id.getLine()) , $id.getText() , $expr_list.value , $id.getLine()); 
	}
	/* ~ expr */
	| st=TILDE e1=expression
		{
			$value = new AST.comp($e1.value, $st.getLine());
		}
	/* isvoid expr */
	| st=ISVOID expr=expression
		{
			$value = new AST.isvoid($expr.value, $st.getLine());
		}
	/* expr * expr */
	| e1=expression STAR e2=expression
		{
			$value = new AST.mul($e1.value, $e2.value, $e1.value.lineNo);
		}
	/* expr / expr */
	| e1=expression SLASH e2=expression
		{
			$value = new AST.divide($e1.value, $e2.value, $e1.value.lineNo);
		}
	/* expr + expr */
	| e1=expression PLUS e2=expression
		{
			$value = new AST.plus($e1.value, $e2.value, $e1.value.lineNo);
		}
	/* expr - expr */
	| e1=expression MINUS e2=expression
		{
			$value = new AST.sub($e1.value, $e2.value, $e1.value.lineNo);
		}
	/* expr < expr */
	| e1=expression LT e2=expression
		{
			$value = new AST.lt($e1.value, $e2.value, $e1.value.lineNo);
		}
	/* expr <= expr */
	| e1=expression LE e2=expression
		{
			$value = new AST.leq($e1.value, $e2.value, $e1.value.lineNo);
		}
	/* expr = expr */
	| e1=expression EQUALS e2=expression
		{
			$value = new AST.eq($e1.value, $e2.value, $e1.value.lineNo);
		}
	/* not expr */
	| st=NOT e1=expression
		{
			$value = new AST.neg($e1.value, $st.getLine());
		}
	/* ID <- expr */
	| id=OBJECTID ASSIGN expr=expression
		{
			$value = new AST.assign($id.getText(), $expr.value, $id.getLine());  
		}
	/* if expr then expr else expr fi*/
	| st=IF predicate=expression THEN ifbody=expression ELSE elsebody=expression FI 
		{
			$value = new AST.cond($predicate.value, $ifbody.value, $elsebody.value, $st.getLine());

		}
	/* while expr loop expr pool */
	| st=WHILE predicate=expression LOOP body=expression POOL
		{
			$value = new AST.loop($predicate.value, $body.value, $st.getLine());
		}
	/* { [[expr]]* } */
	| st=LBRACE el=block_expr_list RBRACE	// define
		{
			$value = new AST.block($el.value, $st.getLine());
		}
	/* let ID:TYPE [[, ID : TYPE [<- expr]]]* in expr */
	| st=LET ll=let_list IN e1=expression
	{
		$value = $e1.value; 
		AST.attr this_attr;
		for(int i = $ll.value.size() - 1; i >= 0; --i) 
		{
			this_attr = $ll.value.get(i);
			$value = new AST.let(this_attr.name, this_attr.typeid, this_attr.value, $value, $st.getLine());
		}
	}
	/* case expr of [[ID : TYPE => expr;]]+ esac */
	| st=CASE predicate=expression OF bl=branch_list ESAC
		{	// define
			$value = new AST.typcase($predicate.value, $bl.value, $st.getLine());
		}
	/* new TYPE */
	| st=NEW type=TYPEID
		{
			$value = new AST.new_($type.getText(), $st.getLine());
		}
	/* (expr)	*/
	| st=LPAREN e1=expression RPAREN
		{
			$value = $e1.value;
		}
	/* ID */	
	| id=OBJECTID
		{
			$value = new AST.object($id.getText(), $id.getLine());
		}
	/* integer */
	| v=INT_CONST
		{
			$value = new AST.int_const(Integer.parseInt($v.getText()), $v.getLine());
		}
	/* string */
	| v=STR_CONST
		{
			$value = new AST.string_const($v.getText(), $v.getLine());
		}
	/* true / false */
	| v=BOOL_CONST
		{
			$value = new AST.bool_const($v.getText().charAt(0)=='t', $v.getLine());
		}
	;
