class Main inherits IO {
	str <- "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
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
				y <- y * in_int();
				i <- i - 1;
			}
			y;
		}
	};
}
