/*************************************************************************
 *  Compilation:  javac RecursiveAppend.java
 *  Execution:    java RecursiveAppend
 *
 *  @author: Anushka Singh, as3711, as3711@scarletmail.rutgers.edu
 *
 *************************************************************************/

public class RecursiveAppend {

    // Returns the original string appended to the original string n times 
    public static String appendNTimes (String original, int n) 
    {
        if (n == 0) return original;
        else return original + appendNTimes(original, n-1);
	
    }

    public static void main (String[] args) 
    {
        StdOut.println(appendNTimes("cat",2));
	}
}
