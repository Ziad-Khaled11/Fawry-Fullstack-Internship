import java.util.ArrayList;

public class ShippingService implements Shipping {
    private double total_weight;
    private double shipment_fees;
    private ArrayList<Product> products;

    public ShippingService(ArrayList<Product> products){
        this.products = products;
        total_weight = 0;
        shipment_fees = 0;
        for (Product product : products){
            total_weight += product.getWeight() * product.getQuantity();
        }
        int FEES = 30;
        shipment_fees = (total_weight / 1000) * FEES;
        products = new  ArrayList<>();
    }

    @Override
    public double getWeight() {
        return total_weight;
    }

    @Override
    public String getName() {
        String str = "** Shipment Notice **";
        if (!products.isEmpty()) {
            for (Product product : products) {
                String temp = "\n" + product.getQuantity() + "x    " + product.getName() + "        " + product.getWeight() + "g";
                str += temp;
            }
            String temp = "\nTotal package weight " + total_weight / 1000 + "kg";
            str += temp;
            return str;
        }
        else {
            return str += "\nNo Shippable Products";
        }
    }

    public double getShipment_fees() {
        return shipment_fees;
    }
}
