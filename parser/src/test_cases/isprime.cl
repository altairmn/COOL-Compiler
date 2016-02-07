class Main inherits IO {
	main() : Object {
		{
			out_string("Enter a number to check if number is prime\n");
			let i : Int <- in_int() in {
				if(i <= 1) then {
					out_string("Invalid Input\n");
					abort();
				} else {
					if (isPrime(i) = 1) then
						out_string("Number is prime\n")
					else
						out_string("Number is composite\n")
					fi;
				}
				fi;
			};
		}
	};

	mod(i : Int, ) : Int {	-- Formal list must be comma separated. A comma does not terminate a list of formals.
	  i - (i/k)*k
	};

	isPrime(i : Int) : Int {
		{
			let x : Int <- 2,
				c : Int <- 1 in
			{
				while (not (x = i)) loop
				if (mod(i, x) = 0) then {
					c <- 0;
					x <- i;
				} else x <- x + 1 fi
				pool;
				c;
			};
		}
	};
};
