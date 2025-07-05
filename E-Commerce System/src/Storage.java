import java.util.HashMap;

public class Storage {
    public static HashMap<String, Product> shop_products = new HashMap<>();
    public static void dummyStorage(){
        Product tv = new Product("TV", 1200.00, 1, false, true, 2000);
        Product cheese = new Product("Cheese", 3.50, 2, true, true, 500, "2025-09-10");
        Product scratchcard = new Product("ScratchCard", 45.99, 1, false, false);
        Product biscuits = new Product("Biscuits", 2.99, 5, true, true, 250, "2025-06-26");
        Product chair = new Product("Chair", 250.00, 0, false, true, 3000);
        shop_products.put(tv.getName().toLowerCase(),tv);
        shop_products.put(cheese.getName().toLowerCase(), cheese);
        shop_products.put(scratchcard.getName().toLowerCase(), scratchcard);
        shop_products.put(biscuits.getName().toLowerCase(), biscuits);
        shop_products.put(chair.getName().toLowerCase(), chair);
    }
}
