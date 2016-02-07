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
				while (not (i = 0) ) loop {
						out_string("Enter Number: ");
						y <- y * in_int(Main : Int);	-- the parser correctly catches the error here
						i <- i - 1;
				}
					pool;
				y;
		}
	};
};
