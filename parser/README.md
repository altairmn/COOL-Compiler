# COOL Compiler #

The parser "CoolParser.g4" conforms to the language specification. The description of the implementation and design decisions
is split across the following categories:

  - Precedence of operators
  - Handling lists.
  - Handling optional entities.
  - LET expression
  - Typecasting Constants

### Precedence of Operators

The operators rules are listed in the order of correct precedence. According to the "COOL" language specification the precedence rules are as follows:

	.
	@
	~
	isvoid
	* /
	+ -
	<= < =
	not
	<-

All the parser rules are in keeping with this order.


### Handling lists

The grammar specification makes it apparent that several kinds of lists (of language constructs) need to be handled separately. For example, we encounter two kinds of expression lists. One of the lists occurs in a block. Each list item is delimeted by a `;`. Note that each expression is **terminated** by a semicolon.
On the other hand, the other expression list is comma separated. We also note that the first list, wherever it can make an appearance, is not optional. While the second kind is.
In short,

	expr, [[, expr]]* 	// is optional in some instances
	[[expr;]]+ 			// is not optional wherver it occurs

Other kinds are of lists are :

  - `class_list` : The whole program is one giant class list having at least one element.
  - `feature_list`: A class is populated with features (method and attributes)
  - `formal_list`: Each method invocation accepts a formal list.
  - `expression_list`
  - `block_expr_list`
  - `branch_list`: All the **Switch case** cases comprise a branch list.
  - `let_list`: The let construct accepts a list of attributes.


### Handling Optional Entities

In the grammar we observe, in multiple places, that some constructs are optional. To handle this construct we have rules which check for presence/absence of these constructs. In each case, a returns a tree node. In case the optional construct is omitted, the tree node is returned with an empty child.

In the feature rule, we observer that assigment is optional. This is handled by using two rules ; one with assignment and the other without.

### LET expression

Let expressions are recursive beasts. They accept attribute lists. Ofcourse, we resort to a looping method to cycle through all the attributes to the list. Furthermore, all the attributes are concatenated together.

### Typecasting Constants

We get a string stream for tokens. To get the integer value of the `INT_CONST` or boolean value of the `BOOL_CONST` we have to do appropriate typecasting. The implementation is fairly self explanatory in this regard. Note that in the case of `BOOL_CONST`, we are not really type casting. We are analyzing the string input and returning a boolean value as a result of the analysis.

* * *

### Working Programs

  - `fib.cl` : Demonstrates nesting of expressions. Essentially using the `(expr)` RHS of the expression rule. It also demonstrates a feature list present within a class. The program is succesfully parsed.
  - `atoi.cl` : A long and complex cool program. Comments have been added to the relevant sections. A featureless class is also added to the code.
  - `print-cool.cl` : Very adequately presents the dispatch constructs.

### Erroneous Programs

  - `isprime.cl` : The lexical analyzer does not throw an error. The parser throws an error because of the incorrect format of listing formals.
  - `err2.cl` : In this case the lexer throws an error. Note that if the lexer did approve the invalid class name as valid then the parser wouldn't be able to spot the error. This means that both are essential components in the compilation process. Also, the lexers role is not only to tokenize the input character stream.
  - `prod.cl` : We use a TypeID in place of an ObjectID in a method invocation.