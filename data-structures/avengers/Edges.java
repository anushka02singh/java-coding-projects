package avengers;

public class Edges 
{
    private int weight;
    private Vertex v1;
    private Vertex v2;  

    public Edges(int weight, Vertex v1, Vertex v2)
    {
        this.weight = weight;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int getWeight()
    {
        return weight;
    }

    public int setWeight(int n)
    {
        return weight = n;
    }

    public Vertex getV1()
    {
        return v1;
    }

    public Vertex getV2()
    {
        return v2;
    }
    
}
