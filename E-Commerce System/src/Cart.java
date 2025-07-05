import java.util.ArrayList;
import java.time.LocalDate;

public class Cart {

    private double subtotal;
    private ArrayList<Product> products;
    private ArrayList<Product> shipped_products;
    private LocalDate current_date;
    private String checkout_receipt;
    public Cart(){
        products = new ArrayList<>();
        shipped_products = new ArrayList<>();
        current_date = LocalDate.now();
        subtotal = 0;

    }
    public void addToCart(String name, long quantity){
        Product returned_product = Storage.shop_products.get(name.toLowerCase());
        if (returned_product == null){
            System.err.println("This product doesn't exist\nTry again");
        }
        else if (returned_product.getQuantity() - quantity < 0){
            System.err.println("This product is out of stock\nPlease Choose another product until it is available");
        }
        else if (returned_product.isHas_expiration() && returned_product.getExpiration_date().isBefore(current_date)) {
            System.err.println("This product is expired\nPlease Choose another product until new one is available");
        }
        else{
            returned_product.setQuantity(returned_product.getQuantity() - quantity);
            Product added_product = new Product(returned_product, quantity);
            products.add(added_product);
            System.out.println("Product added to cart successfully");
        }
    }

    public void calculateReceipt(){
        try{
            checkout_receipt = "\n\n** Checkout Receipt **";
            for (Product product : products) {
                subtotal += product.getPrice() * product.getQuantity();
                if (product.isIs_shippable()){
                    shipped_products.add(product);
                }
                String temp = "\n" + product.getQuantity() + "x    " + product.getName() + "   " + product.getPrice() + "LE";
                checkout_receipt += temp;
            }
        }
        catch (Exception e){
            System.err.println("Empty Cart\n Please add some products before checkout");
        }
    }

    public void removeProductFromCart(String name){
        for (int i = 0; i < products.size(); i++){
           Product pro =  products.get(i);
           if(pro.getName().equalsIgnoreCase(name.toLowerCase())){
               Product real = Storage.shop_products.get(pro.getName().toLowerCase());
               real.setQuantity(real.getQuantity()+ pro.getQuantity());
               if (pro.isIs_shippable()){
                   for (int j = 0; j < shipped_products.size(); j++){
                       Product prod =  shipped_products.get(i);
                       if(prod.getName().equalsIgnoreCase(name.toLowerCase())){
                           shipped_products.remove(j);
                           break;
                       }
                   }
               }
               products.remove(i);
               System.out.println("Product is removed successfully");
               return;
           }
        }
        System.err.println("Product is not found");
    }

    public ArrayList<Product> getShipped_products() {
        return shipped_products;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getCheckout_receipt() {
        return checkout_receipt;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
