package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse test = new Warehouse();
        int totalAmount = StdIn.readInt();
        for(int i = 0; i < totalAmount; i++)
        {
            String query = StdIn.readString();
            if(query.equals("add"))
            {
                int currentDay = StdIn.readInt();
                int prodId = StdIn.readInt();
                String prodName = StdIn.readString();
                int itemStock = StdIn.readInt();
                int itemDemand = StdIn.readInt();
                test.addProduct(prodId, prodName, itemStock, currentDay, itemDemand);
            }
            else if(query.equals("restock"))
            {
                int prodId = StdIn.readInt();
                int restockAmount = StdIn.readInt();
                test.restockProduct(prodId, restockAmount);
            }
            
        }
        StdOut.println(test);
	// Uset his file to test restock
    }
}
