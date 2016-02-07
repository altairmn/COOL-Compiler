General Description
===================

A total of 3 passes are made over the program to check if it is semantically correct. Sometimes, a few more passes on subtrees (or parts of tree) are required to ascertain correctness.
Note that the semantic analyzer does not handle the `SELF_TYPE` keyword of `COOL` language. This means that programs having `SELF_TYPE` then the semantic analyzer may work unexpectedly.

Code Structure
===============

The code structure is expressed in a high-level manner. Low-level details can be gleaned from the comments in the code.

The following files have been added :
	- `ClassTable.java` : Stores the class hierarchy. Helps in conformance checks and getting methods of a particular class. Also does several inheritance checks.
	- `Error.java` : Since `ClassTable.java` does not have access to the `reportError` method of `Semantic.java`, a `List<Error>` is returned to `Semantic` to print the errors.
	- `ClassPlus.java` : Inspired from `AST.class_` class. Stores class `attr` and `methods` in separate `HashMap`s. Also stores class name and parent name.


The following steps happen in order:

  - Inheritance graph is built.
  - Cycle checks are done. If any cycles are found, or any instance of bad hierarchy, then the semantic analyzers terminates after reporting an appropriate error.
  - HashMaps for both attributes and methods in a class are built. These maps include inherited attributes and methods too. Wherever a clash occurs, the inherited feature is used and appropriate error
	message is emitted.
  - Expressions within the class are processed and type annotated. Appropriate erros are reported wherever found.

Design Decision
===============

Tree Traversal
--------------

### BFS-order traversal.

The tree is traversed in BFS-order. This is done, primarily, to avoid using a recursive function. But it also serves to help in better error reporting.
Whenever a class-node, which has been visited before, is visited again, an appropriate error message is reported.

### 3 passes

Three passes over the tree is made because classes maybe defined in any order without regard to the inheritance hierarchy. The second-pass is made because
within a class, the features and attributes may be defined in any order. The third pass takes all the information collected thus far to find semantic errors.
Note that in the second-pass, only the static types of attributes and methods are recorded.

Additionally, the second-pass is also done in BFS-order. Any top-down order would work though. This is done so that the attribute HashMaps and method HashMaps can be
inherited.


Inheritance
-----------

### Attribute overrides

In the second pass, the attribute HashMap and method HashMap is inherited from the parent class. Thus, if any attribute is overriden in the class being analyzed,
an error is reported. Also note that the current class retains the inherited attribute and discards the redefined attribute. This means that to any of the classes that
inherit the current class, this same inherited attribute is visible.

### Method overrides

The method HashMap is inherited from the parent class. If any method is found to be overriden, it is check for errors. Possible errors are that the method return type may not be the same as the original.
Additionally, the method may take incompatible number of arguments (incomaptible to the inherited attribute). Thirdly, the parameter types may not match.

Even in this case, the inherited attribute is retained in case of an error. The redefined attribute is discarded.

Naming and Scoping
------------------

### Multiple attribute defintions

This error is checked in the second pass itself. This means, that if any attributes are multiply defined within a class, the error is reported then. Also note that the first instance of all of the multiple defintions
of an attribute is stored in the attribute HashMap. All others are discarded.

### Multiple method definitions

This error is checked in the second pass itself. This means that if any methods are multiply defined within a class, the error is reported then. Also note that the first instance of all of the multiple definitions of a method is stored in the method HashMap. All others are discarded.

### Formal parameters of methods

Errors are check using a scopetable. Formal parameters are analyzed from left-to-right. In effect, each parameter is in the scope of all the parameters to its right. If a parameter is redefined, appropriate error is thrown. Additionally, later definitions of a formal parameter override earlier definitions with the same name.

### In case statement

Within the case statement, the branch types are checked. If any of the branch types are identical, an error is reported. Conformance (and thus the type of the `typcase`) is checked in an ordinary manner. Wherever the expression has an erroneous type, type "Object" is returned. This pattern is observed throughout the program.

One thing done differently here is that when an undefined class is used as a branch type, then `coolc` just emits that the branch type is illegal. Since the corresponding expression has this branch variable in scope, it can use it in certain operations. In our implementation we have assumed that in the case of an erroneous branch type, the branch type is `Object` for this scope. Cool compiler (seemingly), does not execute any conformance checks at the required places in the corresponding branch expression.

### In let expressions

In let expressions, the object declaration expressions are checked for conformance with object types. In the enclosed scopes, the object type is set to the declared type (not the one infered from the expression).



Type
-----

### Of Objects

Wherever object types have an associated error, the type "Object" is set for the object.

### Of Constructs with declared types

With declared types, only declared types are used for enclosed scopes.

### Wherever else

In other cases, types which are expected of an expression are used. Appropriate error messages are produced when the erroneous subexpression is being analyzed.


* * *
