package warehouse;

public class PurchaseProduct {
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
            else if(query.equals("purchase"))
            {
                int currentDay = StdIn.readInt();
                int prodId = StdIn.readInt();
                int purchaseAmount = StdIn.readInt();
                test.purchaseProduct(prodId, currentDay, purchaseAmount);
            }
        }
        StdOut.println(test);
	// Use this file to test purchaseProduct
    }
}
