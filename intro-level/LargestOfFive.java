/*************************************************************************
 *  Compilation:  javac LargestOfFive.java
 *  Execution:    java LargestOfFive 35 10 32 1 8
 *
 *  @author: Anushka Singh, as3711@rutgers.edu, as3711
 *
 *  Takes five distinct integers as command-line arguments and prints the 
 *  largest value
 *
 *
 *  % java LargestOfFive 35 10 32 1 8
 *  35
 *
 *  Assume the inputs are 5 distinct integers.
 *  Print only the largest value, nothing else.
 *
 *************************************************************************/

public class LargestOfFive 
{

    public static void main (String[] args) 
    {
/*
        int v = Integer.parseInt(args[0]);
        int w = Integer.parseInt(args[1]);
        int x = Integer.parseInt(args[2]);
        int y = Integer.parseInt(args[3]);
        int z = Integer.parseInt(args[4]);
        
        if (v > w && v > x && v > y && v > z ) 
        {
            System.out.println(v);
        }
        else if (w > v && w > x && w > y && w > z)
        {
            System.out.println(w);
        } 
        else if (x > v && x > w && x > y && x > z)
        {
            System.out.println(x);
        } 
        else if (y > v && y > w && y > x && y > z) 
        {
            System.out.println(y);
        }
        else if (z > v && z > w && z > x && z > y) 
        {
            System.out.println(z);
        }
*/

        int v = Integer.parseInt(args[0]);
        int w = Integer.parseInt(args[1]);
        int x = Integer.parseInt(args[2]);
        int y = Integer.parseInt(args[3]);
        int z = Integer.parseInt(args[4]);

        int largest = v;

        if (w > largest) largest = w;
        if (x > largest) largest = x;
        if (y > largest) largest = y;
        if (z > largest) largest = z;
        System.out.println(largest);
    }
}