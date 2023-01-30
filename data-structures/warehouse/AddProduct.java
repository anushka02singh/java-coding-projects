package warehouse;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse test = new Warehouse();
        int totalAmount = StdIn.readInt();
        for(int i = 0; i < totalAmount; i++)
        {
            int currentDay = StdIn.readInt();
            int prodId = StdIn.readInt();
            String prodName = StdIn.readString();
            int itemStock = StdIn.readInt();
            int itemDemand = StdIn.readInt();
            test.addProduct(prodId, prodName, itemStock, currentDay, itemDemand);
        }
        StdOut.println(test);
	// Use this file to test addProduct
    }
}
