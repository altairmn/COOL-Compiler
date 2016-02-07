class Main {
		main(): Object {
				(new alpha).print()
		};

};

(* Class names must begin with uppercase letters *)
class alpha inherits IO {
	print() : Object {
			out_string("reached!!\n");
	};
};

