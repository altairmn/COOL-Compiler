CS3020: Compilers
=================

Code Generator Alligator!
-------------------------

We have introduced two extra classes:
	- IRClassTable
	- IRClassPlus

The IRClassTable stores the table of classes. Each of the entries in this table
is an entry of the IRClassPlus class. The IRClassPlus objects have the 
following useful information required for generating code:

	- List of attributes (in order of declaration)
	- List of methods (in order of declaration in the virtual table)
	- Methods (inherited + within the class)
	- Attributes (inherited + within the class)
	- Offset of methods in class virtual table
	- Offset of objects in class virtual table
	- Intermediate Representation name for the method names



Design Decisions :
==================

A lot of the boilerplate code has been added as constants. This is because the methods associated with the
base classes are interfaces for system calls (like `abort()` for `exit()`, or `out_string()` for `printf("%s", ...)`)

This code is added in the initialization phase. This also includes getting the inheritance graph.

	{i32, i8*, baseClass}

The `i32` stores the class associated integer (from classGraph).
The `i8*` stores pointer to the virtual table.

### Designing Class Structure

Each of the classes is declared twice in the LLVM IR : once as a `%class.ClassName.Base` and once as `%class.ClassName`.
This is done to simplify the inheritance. Each class has a different virtual table. So, a child class inherits only data members of its parent class. So for the purposes of inheritance, the child class has a struct member of the parent base class and not the actual parent class.

Access to the base class attributes are done via this struct member. Additionally, this member is at 0 offset in the class type declaration.

### Designing and Inheriting Virtual Table

The virual table is absolutely essential. One of the use cases of the virtual tables is illustrated below:

	class A {
		// some attributes
		f() : Int {
			1
		};
	};

	class B : inherits A {
		// some attributes
		f() : Int {
			2
		};
	};

	class C {

		func(x : A) : Int{
			// some procedure
			x.f()
		};
	};


Now, the `func` method in class C takes an object of class A. It could just as well take any other object of a class that conforms to class A. Thus, an object of class B can also be taken. This is one situation where a virtual table comes very handy. Here, the static type of the of the variable x is A. But, if we pass an object of type B, then we should not encounter an error.

So, how do we handle this situation? Virtual tables come to rescue!

Basically, the functions within a class are stored in a virtual table associated with the class. The virtual table stores pointers to the functions and not the functions themselves.

So, in this example, whenever we invoke the function `f()` on the object, it looks for the function pointer at a fixed offset in the virtual table, say offset 3. Thus, we need to make sure that the overriden function in the child class is placed at the same offset in the child classes virtual table.


Thus, to implement this scheme, we initialize the virtual table of the `Object` and other basic classes manually. Then, the virtual table of all the derived classes is inherited from the base class. Whenever a method of the base class is overriden, we change the function pointed to in the corresponding virtual table entry of the derived class. Elsewise, we append the new function pointer to the end of the virtual table.


