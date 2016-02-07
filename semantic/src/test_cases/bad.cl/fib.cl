class Bash {
	f() : Int {
		1
	};
};

class Dash inherits Bash {
	f() : Int {
		2
	};
};

class Main inherits IO {
	
	main(): Object {
		{
			out_string("Enter n to find nth fibonacci number!\n");
			out_int(fib(in_int()));
			out_string("\n");
		}
	};

	fib(i : Int) : Int {
			let a : Int <- 1,
					b : Int <- 0,
					c : Int <- 0,
					d : Dash <- (new Dash),
					f : Bash <- (new Dash)
			in
			{
			while (i) loop
			{
				c <- a + b;
				i <- i - 1;
				b <- a;
				a <- c + d@Bash.f();
			}
			pool;
			c;
			}
	};

};
