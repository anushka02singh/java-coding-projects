/*************************************************************************
 *  Compilation:  javac Sierpinski.java
 *  Execution:    java Sierpinski
 *
 *  @author: Anushka Singh, as3711, as3711@scarletmail.rugters.edu
 *
 *************************************************************************/

public class Sierpinski {

    // Height of an equilateral triangle whose sides are of the specified length. 
    public static double height(double length) 
    {
        return ((Math.sqrt(3.0)*length)/2.0);
    }

    // Draws a filled equilateral triangle whose bottom vertex is (x, y) 
    // of the specified side length. 
    public static void filledTriangle(double x, double y, double length) 
    {
        double[] xCoor = {x,x+(0.5*length),x-(0.5*length)};
        double[] yCoor = {y,y+height(length),y+height(length)};
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledPolygon(xCoor, yCoor);
    }

    // Draws a Sierpinski triangle of order n, such that the largest filled 
    // triangle has bottom vertex (x, y) and sides of the specified length. 
    public static void sierpinski(int n, double x, double y, double length) 
    {
        filledTriangle(x, y, length);
        if (n == 1)
        {
            return;
        }
        else
        {
            sierpinski(n-1, x-(0.5*length), y, length*0.5);
            sierpinski(n-1, x+(0.5*length), y, length*0.5);
            sierpinski(n-1, x, y+height(length), length*0.5);
        }
    }

    // Takes an integer command-line argument n; 
    // draws the outline of an equilateral triangle (pointed upwards) of length 1; 
    // whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and 
    // draws a Sierpinski triangle of order n that fits snugly inside the outline. 
    public static void main(String[] args) 
    {
        double[] xCoor = {0.0,0.5,1};
        double[] yCoor = {0,height(1),0};
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.polygon(xCoor, yCoor);
        int n = Integer.parseInt(args[0]);
        sierpinski(n, 0.5, 0.0, 0.5);
    }
}
