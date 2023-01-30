/*************************************************************************
 *  Compilation:  javac RURottenTomatoes.java
 *  Execution:    java RURottenTomatoes
 *
 *  @author: Anushka Singh, as3711, as3711@scartletmail.rutgers.edu
 *
 * RURottenTomatoes creates a 2 dimensional array of movie ratings 
 * from the command line arguments and displays the index of the movie 
 * that has the highest sum of ratings.
 *
 *  java RURottenTomatoes 3 2 5 2 3 3 4 1
 *  0
 *************************************************************************/

public class RURottenTomatoes {

    public static void main(String[] args) 
	{
		int[][] arr2d = new int [Integer.parseInt(args[0])] [Integer.parseInt(args[1])];
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int prod = a * b;
		int i=0;
		while (i<prod)
		{
			for (int row = 0; row < arr2d.length; row++) 
			{
				for (int col = 0; col < arr2d[row].length; col++)
				{
						arr2d[row][col] = Integer.parseInt(args[2+i]);
						i++;
				}
			}
		}
		int [] arr = new int [b];
		for (int row = 0; row < arr2d.length; row++)
		{
			int sum = 0;
			for (int col = 0; col < arr2d[row].length; col++)
			{
				arr[col] += arr2d[row][col];
				//System.out.println("row:" +row +"col:" +col  +"sum:" +arr[col]);
			}
		}
		int max = arr[0];
		int k = 0;
		for (int j = 1; j < arr.length; j++)
		{
             if (arr[j] > max)
			 {
				max = arr[j];
				k = j;
			 }
		}
		System.out.println (k);	
	}
}
