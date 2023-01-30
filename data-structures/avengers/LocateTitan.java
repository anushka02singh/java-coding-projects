package avengers;

import java.util.ArrayList;

/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }

        String LocateTitanInputFile = args[0];
        String LocateTitanOutputFile = args[1];
        StdIn.setFile(LocateTitanInputFile); 


        int numGen = StdIn.readInt();
        
        Graph graph = new Graph(numGen);

        Vertex vertex = new Vertex();
        for(int i = 0; i < numGen; i++)
        {
            vertex = new Vertex(StdIn.readInt(), StdIn.readDouble());
            graph.add(vertex, i);
        }


        for(int i = 0; i < numGen; i++)
        {
            for(int j = 0; j < numGen; j++)
            {
                //read adj matrix to give vertex edges
                graph.getVertex(i).addToEdges(StdIn.readInt(), graph.getVertex(j).getFunVal());

            }
            //System.out.println(graph.getVertex(i).toString());
        }

    	//use Dijkstra’s Algorithm to find the minimum cost bw gen0 and gen5 --> this variable number is the output

        int[] minCost = new int[numGen];
        boolean[] dijkstraSet = new boolean[numGen];

        for(int i = 0; i < numGen; i++)
        {
            if(graph.getVertex(i).getGenNum() == 0) minCost[i] = 0;
            else minCost[i] = Integer.MAX_VALUE;
        }

        int count = 0;
        int minPtr = 0;
        while(count != numGen-1)
        {
            for(int i = 0; i < numGen; i++)
            {
                if(graph.getVertex(minPtr).getEdge(i) != 0 && minCost[i] > graph.getVertex(minPtr).getEdge(i) + minCost[minPtr])
                {
                    minCost[i] = graph.getVertex(minPtr).getEdge(i) + minCost[minPtr];
                }

            }
            minPtr = getMin(minCost, dijkstraSet);
            count++;
        }
        
        StdOut.setFile(LocateTitanOutputFile);
        /*for(int i = 0; i < minCost.length; i++)
        {
            System.out.println(minCost[i]);
        }
        */
        StdOut.print(minCost[numGen-1]);
    }

    public static int getMin(int[] minCost, boolean[] dijkstraSet)
    {
        /*
        make a for loop that reads the int arr --> look for min value, variable called min which is infinity, and minPtr which is the index of the smalles edge in arr
        only time change is if min in int arr at i < min and the boolean array at i is false
        and return min at the end of the loop
        */
        int minNum = Integer.MAX_VALUE;
        int minPtr = 0;
        for(int i = 0; i < minCost.length; i++)
        {
            if(minCost[i] < minNum && dijkstraSet[i] == false)
            {
                minNum = minCost[i];
                minPtr = i;
            }
        }
        
        dijkstraSet[minPtr] = true;
        //System.out.println(minPtr);
        return minPtr;
    }
}
