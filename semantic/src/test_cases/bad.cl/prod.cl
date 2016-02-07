class Crash {
};

class Brash {
};

class Main inherits IO {
	main() : Object {
			{
					out_string("Enter number of numbers to multiply\n");
					out_int(prod(in_int()));
					out_string("\n");
			}
	};

	prod(i : Int) : Int {
		let y : Int <- 1 in {
				while (not ((new Crash) = (1)) ) loop {	-- can't compare basic type and some object
						out_string("Enter Number: ");
						y <- y * in_int();
						i <- i - 1;
				}
				pool;
				y;
		}
	};
};
