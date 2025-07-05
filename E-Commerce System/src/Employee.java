import java.time.LocalDate;
import java.util.Scanner;
public class Employee extends Person{
    public Employee(String name, String email) {
        super(name, email);
    }
    public void addProduct(){
        String choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the product name");
        String product_name = scanner.next();
        Product returned = Storage.shop_products.get(product_name.toLowerCase());
        if (returned != null){
            System.out.println("The product already exists do you need to increase its quantity?");
            System.out.println("Enter Y or N to continue");
            choice = scanner.next();
            if (choice.equalsIgnoreCase("y")) {
                long quantity = -1;
                while (quantity < 0) {
                    System.out.println("Please enter the new quantity");
                    quantity = scanner.nextInt();
                    if(quantity < 0){
                        System.err.println("Invalid quantity value");
                    }
                }
                returned.setQuantity(quantity);
                if (returned.isHas_expiration()) {
                    String date;
                    while(true) {
                        System.out.println("Please enter the new expiration date");
                        System.out.println("The format is yyyy-mm-dd");
                        date = scanner.next();
                        try {
                            LocalDate current_date = LocalDate.now();
                            if (LocalDate.parse(date).isBefore(current_date)) {
                                System.err.println("Date is not valid try to add another unexpired product");
                            }
                            else {
                                returned.setExpiration_date(date);
                                break;
                            }
                        } catch (Exception e) {
                            System.err.println("Invalid Date");
                        }
                    }
                }
            }
            System.out.println("Is the price updated?");
            System.out.println("Enter Y or N to continue");
            choice = scanner.next();
            if (choice.equalsIgnoreCase("y")){
                double price = -1;
                while (price < 0) {
                    System.out.println("Please enter the new price");
                    price = scanner.nextDouble();
                    if(price < 0){
                        System.err.println("Incorrect price value");
                    }
                }
                returned.setPrice(price);
            }

        }
        else{
            //New Product
            long quantity = -1;
            while (quantity < 0) {
                System.out.println("Please enter the quantity");
                quantity = scanner.nextInt();
                if(quantity < 0){
                    System.err.println("Invalid quantity value");
                }
            }
            double price = -1;
            while (price < 0) {
                System.out.println("Please enter the price");
                price = scanner.nextDouble();
                if(price < 0){
                    System.err.println("Incorrect price value");
                }
            }
            System.out.println("Is the product shippable?");
            System.out.println("Enter Y or N to continue");
            choice = scanner.next();
            if(choice.equalsIgnoreCase("y")){
                double weight = -1;
                while (weight < 0){
                    System.out.println("Please enter the product's weight in grams");
                    weight = scanner.nextDouble();
                    if (weight < 0){
                        System.err.println("Invalid weight value");
                    }
                }
                System.out.println("Does it have an expiration date?");
                System.out.println("Enter Y or N to continue");
                choice = scanner.next();
                if(choice.equalsIgnoreCase("y")){
                    String date;
                    while(true) {
                        System.out.println("Please enter the new expiration date");
                        System.out.println("The format is yyyy-mm-dd");
                        date = scanner.next();
                        try {
                            LocalDate current_date = LocalDate.now();
                            if (LocalDate.parse(date).isBefore(current_date)) {
                                System.err.println("Date is not valid try to add another unexpired product");
                            }
                            else break;
                        } catch (Exception e) {
                            System.err.println("Invalid Date");
                        }
                    }
                    Product new_product = new Product(product_name, price, quantity, true, true, weight, date);
                    Storage.shop_products.put(product_name.toLowerCase(), new_product);
                    System.out.println("Product added successfully");
                    return;
                }
                else{
                    Product new_product = new Product(product_name, price, quantity, false, true, weight);
                    Storage.shop_products.put(product_name.toLowerCase(), new_product);
                    System.out.println("Product added successfully");
                    return;
                }
            }
            System.out.println("Does it have an expiration date?");
            System.out.println("Enter Y or N to continue");
            choice = scanner.next();
            if(choice.equalsIgnoreCase("y")){
                String date;
                while(true) {
                    System.out.println("Please enter the new expiration date");
                    System.out.println("The format is yyyy-mm-dd");
                    date = scanner.next();
                    try {
                        LocalDate current_date = LocalDate.now();
                        if (LocalDate.parse(date).isBefore(current_date)) {
                            System.err.println("Date is not valid try to add another unexpired product");
                        }
                        else break;
                    } catch (Exception e) {
                        System.err.println("Invalid Date");
                    }
                }
                Product new_product = new Product(product_name, price, quantity, true, false, date);
                Storage.shop_products.put(product_name.toLowerCase(), new_product);
                System.out.println("Product added successfully");
            }
            else{
                Product new_product = new Product(product_name, price, quantity, false, false);
                Storage.shop_products.put(product_name.toLowerCase(), new_product);
                System.out.println("Product added successfully");
            }
        }
    }
}
