package warehouse;

/*
 * Use this class to put it all together.
 */ 
public class Everything {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse test = new Warehouse();
        int totalAmount = StdIn.readInt();
        for(int i = 0; i < totalAmount; i++)
        {
            //System.out.print("loop");
            String query = StdIn.readString();
            if(query.equals("add"))
            {
                int currentDay = StdIn.readInt();
                int prodId = StdIn.readInt();
                String prodName = StdIn.readString();
                int itemStock = StdIn.readInt();
                int itemDemand = StdIn.readInt();
                test.addProduct(prodId, prodName, itemStock, currentDay, itemDemand);
                //System.out.print("yes");
            }
            else if(query.equals("delete"))
            {
                int prodId = StdIn.readInt();
                test.deleteProduct(prodId);
                //System.out.print("no");
            }
            else if(query.equals("restock"))
            {
                int prodId = StdIn.readInt();
                int restockAmount = StdIn.readInt();
                test.restockProduct(prodId, restockAmount);
                //System.out.print("why");
            }
            else if(query.equals("purchase"))
            {
                int currentDay = StdIn.readInt();
                int prodId = StdIn.readInt();
                int purchaseAmount = StdIn.readInt();
                test.purchaseProduct(prodId, currentDay, purchaseAmount);
                //System.out.print("ok");
            }
        }
        StdOut.println(test);
	// Use this file to test all methods
    }
}
