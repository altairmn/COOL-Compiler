class Dash {
	x : Bash <- new Bash;
	f(x : Int, z : String) : Int {
		1
	};
	y : Int;
};

class Bash inherits Dash {
	f(x : Int, z : String) : String {
		y + 2
	};
	y : Bool;
	y : Int;
};

class Hash inherits Bash {
	d(a : Bool) : Bool {
		y + 1
	};
	y : String;
};

class Main inherits IO {
	-- the class has features. Only methods in this case.


	main(): Object {
		{
			if(a < "a") then 
				(out_string("Whats up?".substr(1, 3)))
			else
				out_string("Whazzy up?")
			fi;

			if(a) then
				out_int(as@Bash.f("heya", 12))
			else
				out_string("Hey browski")
			fi;

			if(isvoid(d)) then
				out_string(123)
			else
				out_string("ding dong")
			fi;
		}

	};

	main() : Int {
		1
	};


	fa : Int;

	fib(i : Int, i : Int) : Int {	-- list of formals. And the return type of the method.
			let a : Int, 
					b : Int <- (new Crash),
					c : Int <- 0,
					qw : Bash
			in
			{
			fa <- (while (not i) loop	-- expressions are nested.
			{
				c <- "String";
				i <- i - 1;
				b <- "ljasd" + (~"lka");
				a <- (new Bash) + (100 + kic);
				b <- ~(new Bash);
				a = "alj";
				b = (new Object);
				(new Object) = a;
				(new Bash) = (new Object);
			}
			pool);
			c;
			}
	};

	a : Int <- 120;
	b : Int <- 200 + c;
	c : Int <- 100;
	d : Int <- new Int;
	qx : Bool <- (a < "a");
	as: Bash <- new Bash;

};
