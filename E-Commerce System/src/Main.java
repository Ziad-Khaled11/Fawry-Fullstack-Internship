import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    public static void main(String[] args) {
        Storage.dummyStorage();
        System.out.println("Welcome to Fawry Ecommerce System");
        Scanner scanner = new Scanner(System.in);
        int num;
        String choice;
        while(true){
            System.out.println("For Employees press 1\nFor Customers press 2 \nTo Exit press 3");
            num = scanner.nextInt();
            if (num == 1){
                System.out.println("Please enter your name");
                String name = scanner.next();
                String email;
                while (true){
                    System.out.println("Please enter your email");
                    email = scanner.next();
                    Matcher matcher = pattern.matcher(email);
                    if(!matcher.matches()){
                        System.err.println("Invalid email address");
                    }
                    else break;
                }
                Employee emp = new Employee(name, email);
                System.out.println("Welcome " + emp.getName());
                while(true){
                    System.out.println("Do you want to add a product");
                    System.out.println("Enter Y or N to continue");
                    choice = scanner.next();
                    if(choice.equalsIgnoreCase("y")) {
                        emp.addProduct();
                    }
                    else {
                        System.out.println("Thanks for visiting our market");
                        break;
                    }
                }
            }
            else if (num == 2) {
                System.out.println("Please enter your name");
                String name = scanner.next();
                String email;
                while (true) {
                    System.out.println("Please enter your email");
                    email = scanner.next();
                    Matcher matcher = pattern.matcher(email);
                    if (!matcher.matches()) {
                        System.err.println("Invalid email address");
                    } else break;
                }
                Customer cust = new Customer(500, name, email);
                System.out.println("Welcome " + cust.getName() + " we added an initial balance 500LE as a gift");
                while (true) {
                    System.out.println("For purchasing press 1\nFor deposit press 2\nTo exit press 3");
                    int number = scanner.nextInt();
                    if (number == 1) {
                        System.out.println("Enter the product name you want to add");
                        String product_name = scanner.next();
                        Product returned = Storage.shop_products.get(product_name.toLowerCase());
                        if (returned != null) {
                            long quantity = -1;
                            while (quantity < 0) {
                                System.out.println("Please enter the quantity");
                                quantity = scanner.nextInt();
                                if (quantity < 0){
                                    System.err.println("Invalid quantity value");
                                }
                                else
                                    cust.getMy_cart().addToCart(product_name, quantity);
                            }
                        }
                        System.out.println("Do you want to continue");
                        System.out.println("Enter Y or N to continue");
                        choice = scanner.next();
                        if (!choice.equalsIgnoreCase("y")) {
                            System.out.println("Do you want to checkout?");
                            System.out.println("Enter Y or N to continue");
                            choice = scanner.next();
                            if (choice.equalsIgnoreCase("y")) {
                                cust.checkout();
                                System.out.println("Your current balance " + cust.getBalance());
                            } else {
                                System.out.println("Do you want to remove an item?");
                                System.out.println("Enter Y or N to continue");
                                choice = scanner.next();
                                if (choice.equalsIgnoreCase("y")) {
                                    System.out.println("Enter the product name you want to remove");
                                    String name_to_remove = scanner.next();
                                    cust.getMy_cart().removeProductFromCart(name_to_remove);
                                } else {
                                    break;
                                }
                            }
                        }
                    } else if (number == 2) {
                        System.out.println("Please enter the amount you want to deposit");
                        double deposit = scanner.nextDouble();
                        if (deposit > 0) {
                            double new_balance = cust.getBalance() + deposit;
                            cust.setBalance(new_balance);
                        } else {
                            System.err.println("Wrong amount to be deposited");
                        }
                    } else if (number == 3) {
                        System.out.println("Thanks for visiting our market");
                        break;
                    }
                    else {
                        System.err.println("Wrong value entered");
                    }
                }
            } else if (num == 3) {
                System.out.println("Thanks for visiting our market");
                break;
            }
            else{
                System.err.println("Wrong value entered");
            }
        }
    }
}
