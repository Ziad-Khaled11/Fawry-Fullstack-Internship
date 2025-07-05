public class Customer extends Person {
    private double balance;
    private Cart my_cart;

    public Customer(double balance, String name, String email) {
        super(name, email);
        this.balance = balance;
        my_cart = new Cart();
    }

    public Cart getMy_cart() {
        return this.my_cart;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void checkout() {
        if (!my_cart.getProducts().isEmpty()) {
            my_cart.calculateReceipt();
            ShippingService shipment = new ShippingService(my_cart.getShipped_products());
            double total = my_cart.getSubtotal() + shipment.getShipment_fees();
            System.out.println(shipment.getName());
            System.out.println(my_cart.getCheckout_receipt());
            System.out.println("\n----------------------------------");
            System.out.println("\nSubtotal     " + my_cart.getSubtotal());
            System.out.println("\nShipping     " + shipment.getShipment_fees());
            System.out.println("\nAmount       " + total);
            my_cart = new Cart();
            if (balance - total >= 0)
                balance -= total;
            else
                System.err.println("Insufficient Balance\nplease deposit some money");
        }
        else {
            System.err.println("Empty Cart\nPlease add some products");
        }
    }
}
