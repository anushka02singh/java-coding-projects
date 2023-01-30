/*************************************************************************
 *  Compilation:  javac PolygonTransform.java
 *  Execution:    java PolygonTransform
 *
 *  @author: Anushka Singh, as3711@scarletmail.rutgers.edu, as3711
 *
 *************************************************************************/

public class PolygonTransform 
{


    // Returns a new array that is an exact copy of the given array. 
    // The given array is not mutated. 
    public static double[] copy(double[] array) 
    {

	    double[] a = new double [array.length];
        for (int i = 0; i < array.length; i++)
            {
                a[i] = array[i];
            }
        return a;
    }
    
    // Scales the given polygon by the factor alpha. 
    public static void scale(double[] x, double[] y, double alpha) 
    {
    
	    for (int i = 0; i < x.length; i++)
            {
                x[i] *= alpha;
                y[i] *= alpha;
            }
    }
    
    // Translates the given polygon by (dx, dy). 
    public static void translate(double[] x, double[] y, double dx, double dy) 
    {

        for (int i = 0; i < x.length; i++)
        {
            x[i] += dx;
            y[i] += dy;
        }
    }
    
    // Rotates the given polygon theta degrees counterclockwise, about the origin. 
    public static void rotate(double[] x, double[] y, double theta) 
    {
        double[] ax = copy(x);
        double[] ay = copy(y);
	    double r = Math.toRadians(theta);
        for (int i = 0; i < x.length; i++)
        {
            x[i] = ax[i]*Math.cos(r) - ay[i]*Math.sin(r);
            y[i] = ay[i]*Math.cos(r) + ax[i]*Math.sin(r);
        }
    }

    // Tests each of the API methods by directly calling them. 
    public static void main(String[] args) 
    {

	StdDraw.setScale (-5.0, +5.0);
    double[] x = {0,1,1,0};
    double[] y = {0,0,2,1};
    
    //draw the polygon
    StdDraw.setPenColor (StdDraw.RED);
    StdDraw.polygon (x,y);

    /*copy the array object
    double[] ax = copy(x);
    double[] ay = copy(y);

    //scales the polygon by the factor 2
    double alpha = 2.0;
    scale (x,y, alpha);
    StdDraw.setPenColor (StdDraw.BLUE);
    StdDraw.polygon (x,y);

    //copy the array object
    ax = copy(x);
    ay = copy(y);

    *///translates the polygon by (2, 1)
    double dx = 2.0, dy = 1.0;
    translate(x, y, dx, dy);
    StdDraw.setPenColor(StdDraw.GREEN);
    StdDraw.polygon(x, y);

    /*copy the array object
    ax = copy(x);
    ay = copy(y);

    //rotates the polygon 45 degrees
    double theta = 45.0;
    rotate(x, y, theta);
    StdDraw.setPenColor(StdDraw.MAGENTA);
    StdDraw.polygon(x,y);
    */
    }
}