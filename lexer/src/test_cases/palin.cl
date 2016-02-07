class Main inherits IO {
	main(): Object {
		{
			out_string("Enter string to check for palindrome\n");
			let str : String <- in_string() in
			if (str = reverse(str)) then
			out_string("String is a Palindrome\n")
			else
			out_string("String is not a Palindrome\n")
			fi;
		}
	};
	
	reverse(str : String): String {
		if (str.length() = 1) then str
		else (new String).concat(str.substr(str.length()-1, 1)).concat(reverse(str.substr(0, str.length()-1)))
		fi
	};
};
