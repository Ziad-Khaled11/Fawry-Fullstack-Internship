import java.time.LocalDate;

public class Product {

    private String name;
    private double price;
    private long quantity;
    private boolean has_expiration;
    private LocalDate expiration_date;
    private boolean is_shippable;
    private double weight;


    public Product(String name, double price, long quantity, boolean has_expiration, boolean is_shippable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.has_expiration = has_expiration;
        this.is_shippable = is_shippable;
    }
    public Product(String name, double price, long quantity, boolean has_expiration, boolean is_shippable, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.has_expiration = has_expiration;
        this.is_shippable = is_shippable;
        this.weight = weight;
    }
    public Product(String name, double price, long quantity, boolean has_expiration, boolean is_shippable, String date) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.has_expiration = has_expiration;
        this.is_shippable = is_shippable;
        try {
            this.expiration_date = LocalDate.parse(date);
        }
        catch (Exception e){
            System.err.println("Invalid Date");
        }
    }
    public Product(String name, double price, long quantity, boolean has_expiration, boolean is_shippable, double weight, String date) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.has_expiration = has_expiration;
        this.is_shippable = is_shippable;
        this.weight = weight;
        try {
            this.expiration_date = LocalDate.parse(date);
        }
        catch (Exception e){
            System.err.println("Invalid Date");
        }
    }

    public Product(Product original, long quantity){
        this.name = original.getName();
        this.price = original.getPrice();
        this.quantity = quantity;
        this.has_expiration = original.isHas_expiration();
        this.is_shippable = original.isIs_shippable();
        this.weight = original.getWeight();
        this.expiration_date = original.getExpiration_date();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isHas_expiration() {
        return has_expiration;
    }


    public boolean isIs_shippable() {
        return is_shippable;
    }


    public double getWeight() {
        return weight;
    }


    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = LocalDate.parse(expiration_date);
    }
}
