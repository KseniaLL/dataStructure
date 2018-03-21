
public class Num {
	
	public static int recurse (int n) {

		   if (n <= 1)

		       return 1;

		   else return recurse(n-2) + n;

		}

		public static void main (String [] args) {

		     System.out.println (recurse (7));

		}
}
