/*************************************************************************
 *  Compilation:  javac RandomWalker.java
 *  Execution:    java RandomWalker 10
 *
 *  @author: Anushka Singh, as3711@scarletmail.rutgers.edu, as3711
 *
 * The program RandomWalker that takes an int command-line argument n
 * and simulates the motion of a random walk for n steps. Print the
 * location at each step (including the starting point), treating the
 * starting point as the origin (0, 0). Also, print the square of the
 * final Euclidean distance from the origin.
 *
 *  % java RandomWalker 10
 * (0,0)
 * (-1,0)
 * (-1,-1)
 * (-1,-2)
 * (-1,-3)
 * (-1,-4)
 * (-1,-5)
 * (0,-5)
 * (-1,-5)
 * (-2,-5)
 * (-2,-4)
 * Squared distance = 20.0
 *
 *************************************************************************/

public class RandomWalker 
{

    public static void main(String[] args) 
    {
        //read n from command-line and print location at each step staringwith origin
        int n = Integer.parseInt(args[0]);
        int x = 0;
        int y = 0;
        System.out.println("(" + x + "," + y + ")");
        
        //compute operation while i < n and increment by 1
        for (int i = 0; i < n; i++)
        {
            //generate random numbers and increment based on 
            int random = (int) (Math.random() * 4);
            if (random == 0)
            {
                ++x;
            }
            else if (random == 1)
            {
                --y;
            }
            else if (random == 2)
            {
                --x;
            }
            else if (random == 3)
            {
                ++y;
            }

            System.out.println("(" + x + "," + y + ")");
        }

        //print the square of the final sqaured Euclidean distance from the origin as doub,e
        double distance = ((x*x) + (y*y));
        System.out.println("Squared distance = " + distance);

    }
}
