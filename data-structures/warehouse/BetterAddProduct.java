package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        Warehouse test = new Warehouse();
        int totalAmount = StdIn.readInt();
        for(int i = 0; i < totalAmount; i++)
        {
            //System.out.println(i);
            int currentDay = StdIn.readInt();
            int prodId = StdIn.readInt();
            String prodName = StdIn.readString();
            int itemStock = StdIn.readInt();
            int itemDemand = StdIn.readInt();
            test.betterAddProduct(prodId, prodName, itemStock, currentDay, itemDemand);
        }
        StdOut.println(test);
        // Use this file to test betterAddProduct
    }
}
