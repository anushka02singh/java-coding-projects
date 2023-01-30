/*************************************************************************
 *  Compilation:  javac FindDuplicate.java
 *  Execution:    java FindDuplicate
 *
 *  @author:
 *
 * FindDuplicate that reads n integer arguments from the command line 
 * into an integer array of length n, where each value is between is 1 and n, 
 * and displays true if there are any duplicate values, false otherwise.
 *
 *  % java FindDuplicate 10 8 5 4 1 3 6 7 9
 *  false
 *
 *  % java FindDuplicate 4 5 2 1 
 *  true
 *************************************************************************/

public class FindDuplicate {

    public static void main(String[] args) 
	{

		int n = args.length;
		int [] arr = new int [n];
		boolean dup = false;

		for (int i = 0; i < arr.length; i++)
		{
			arr [i] = Integer.parseInt(args[i]);
			//System.out.println(i);
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++){
				if (i != j) 
				{
					if (arr[i] == arr[j])
					{
						dup = true;
					}
				}
			}
		}
		System.out.println(dup);
	}
}
