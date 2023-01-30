package conwaygame;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) 
    {

        StdIn.setFile(file);
        int row = StdIn.readInt();
        int col = StdIn.readInt();
        totalAliveCells = 0;
        grid = new boolean[row][col];

        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = StdIn.readBoolean();
               
                if(grid[i][j])
                {
                    totalAliveCells++;
                }
            }

        }
    
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) 
    {
        if(grid[row][col] == true) return true;

        return false;
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () 
    {

        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j]) return true;
            }
        }
        return false;
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     * 0,0  |   0,1
     * 1,0  |   1,1
     * 
     * row,col,row+1,col
     * row,col,row-1,col
     * row,col,row+1,col+1
     * row,col,row-1,col-1
     * row,col,row,col+1
     * row,col,row,col-1
     * row,col,row+1,col-1
     * row,col,row-1,col+1
     */
    public int numOfAliveNeighbors (int row, int col) 
    {
        int count = 0;
        int ptrRow = 0;
        int ptrCol = 0;

        for(int i = 1; i > -2; i--)
        {
            for(int j = 1; j > -2; j--)
            {
                ptrCol = col-j;
                ptrRow = row-i;

                if(i == 0 && j == 0)
                {
                    continue;
                }
                if(ptrRow > grid.length-1)
                {
                    ptrRow = 0;
                }

                if(ptrCol > grid[0].length-1)
                {
                    ptrCol = 0;
                }

                if(ptrRow < 0)
                {
                    ptrRow = grid.length-1;
                }

                if(ptrCol < 0)
                {
                    ptrCol = grid[0].length-1;
                }

                if(getGrid()[ptrRow][ptrCol])
                {
                    //System.out.println(ptrCol + " , " + ptrRow);
                    count++;
                }

            }
        }
        return count;
    }

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () 
    {

        boolean[][] newGrid = new boolean[grid.length][grid[0].length];
        //totalAliveCells = 0;

        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[0].length; col++)
            {
                boolean currentCellState = grid[row][col];

                boolean newCellState = currentCellState;

                int numOfNeighbors = numOfAliveNeighbors(row, col);
               // System.out.println(row + "," + col + " has " + numOfNeighbors);

                if (numOfNeighbors <= 1 && currentCellState == true) //rule 1 : alive 0 or 1 = die
                {
                    newCellState = false;

                   // System.out.println(row + " ," + col + " rule 1 ");
                }
                else if (numOfNeighbors == 3 && currentCellState == false) //rule 2 : dead exactly 3 = reproduce
                {
                    newCellState = true;
                   // System.out.println(row + " ," + col + " rule 2 ");
                }
                else if((numOfNeighbors == 2 || numOfNeighbors == 3) && currentCellState == true) //rule 3 : alive 2 or 3 = alive
                {
                    newCellState = true;
                   // System.out.println(row + " ," + col + " rule 3 ");
                }
                else if(numOfNeighbors >=4 && currentCellState == true) //rule 4 : 4 + = die
                {
                    newCellState = false;
                 //   System.out.println(row + " ," + col + " rule 4 ");
                }

                newGrid[row][col] = newCellState;
            }
        } 
        
        return newGrid;
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () 
    {
        grid = computeNewGrid();

        totalAliveCells = 0;

        for(int i =0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j])
                {
                    totalAliveCells++;
                }
            }
        }

        //System.out.println(totalAliveCells);
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) 
    {

        for(int i = 0; i < n; i++)
        {
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() 
    {
        WeightedQuickUnionUF uF = new WeightedQuickUnionUF(grid.length, grid[0].length);
      //  System.out.println(totalAliveCells);
        for(int row = 0; row < grid.length; row++)
        {
            for(int col = 0; col < grid[0].length; col++)
            {
                boolean cellState = grid[row][col];
                int ptrRow = 0;
                int ptrCol = 0;
                if (cellState == true)
                {
                    for(int i = 1; i > -2; i--)
                    {
                        for(int j = 1; j > -2; j--)
                        {
                            ptrCol = col-j;
                            ptrRow = row-i;
            
                            if(i == 0 && j == 0)
                            {
                                continue;
                            }
                            if(ptrRow > grid.length-1)
                            {
                                ptrRow = 0;
                            }
            
                            if(ptrCol > grid[0].length-1)
                            {
                                ptrCol = 0;
                            }
            
                            if(ptrRow < 0)
                            {
                                ptrRow = grid.length-1;
                            }
            
                            if(ptrCol < 0)
                            {
                                ptrCol = grid[0].length-1;
                            }
            
                            if(getGrid()[ptrRow][ptrCol])
                            {
                                uF.union(row,col,ptrRow,ptrCol);
                            }
            
                        }
                    }
                }
            }
        }

        //System.out.println(totalAliveCells);

        int[] listOfAllSets = new int[totalAliveCells];
        int count = 0;
        for(int i =0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j])
                {
                    listOfAllSets[count] = uF.find(i,j);
                    count++;
                }
            }
        }
        for(int i = 0; i < listOfAllSets.length; i++)
        {
            //System.out.print(listOfAllSets[i] + " ");
        }
        int unqiue = 1;

        for(int i = 1; i < listOfAllSets.length; i++)
        {
            int j = 0;
            for(j = 0; j < i; j++)
            {
                if(listOfAllSets[i] == listOfAllSets[j])
                {
                    break;
                }

            }
            
            //System.out.print(listOfAllSets[i] + " ");
            
            if (i == j)
            {
                unqiue++;
            }
        }
        //System.out.println(totalAliveCells);
        if(totalAliveCells == 0)
        {
            unqiue = 0;
        }

        return unqiue;
    }
}
