class Dash inherits Bash{
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
			if(a < a) then 
				(out_string("Whats up?".substr(1, 3)))
			else
				out_string("Whazzy up?")
			fi;

			if(a) then
				out_int(1)
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
};
