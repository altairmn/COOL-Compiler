package cool;

public class Error {
	public String fname;
	public int line;
	public String err;
	Error(String f, int l, String er) {
		fname = new String(f);
		line = l;
		err = new String(er);
	}
}