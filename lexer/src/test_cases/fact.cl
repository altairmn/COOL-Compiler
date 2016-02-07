class Main inherits IO {

	main() : Object {
		{
			out_string("Enter number to find factorial!\n");
			out_int(fact(in_int()));
			out_string("\n");
		}
	};	

	fact(i : Int) : Int {
		if (i = 0) then 1 else i*fact(i-1) fi
	};
};

