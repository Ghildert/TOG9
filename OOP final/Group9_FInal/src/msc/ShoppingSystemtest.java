package msc;
import java.io.*;
import java.util.*;

// Interface
interface Buyable {
    double getPrice();

    String getName();
}

// Base class (superclass)
class Product implements Buyable, Serializable {
    private String name;
    private double price;
    private int quantity;
    public static int totalProducts = 0; // static variable

    public Product(String name, double price, int qty) { // constructor
        this.name = name;
        this.price = price;
        this.quantity = qty;
        totalProducts++;
    }

    // Getters & setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q) {
        this.quantity = q;
    }

    @Override
    public String toString() {
        return name + " - ₱" + price + " (" + quantity + " in stock)";
    }
}

// Subclass (shows inheritance and overriding)
class DiscountedProduct extends Product {
    private double discount;

    public DiscountedProduct(String name, double price, int qty, double discount) {
        super(name, price, qty);
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        return super.getPrice() * (1 - discount);
    }

    @Override
    public String toString() {
        return super.getName() + " - ₱" + getPrice() + " (discounted)";
    }
}

// Custom exception
class OutOfStockException extends Exception {
    public OutOfStockException(String msg) {
        super(msg);
    }
}

// Main class
public class ShoppingSystemtest {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Load items from file
        try (BufferedReader br = new BufferedReader(new FileReader("shopping.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length == 4) { // with discount
                    products.add(new DiscountedProduct(p[0], Double.parseDouble(p[1]), Integer.parseInt(p[2]),
                            Double.parseDouble(p[3])));
                } else { // regular
                    products.add(new Product(p[0], Double.parseDouble(p[1]), Integer.parseInt(p[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("No shopping.txt found — starting with defaults.");
            products.add(new Product("Apple", 10, 10));
            products.add(new Product("Banana", 5, 20));
            products.add(new DiscountedProduct("Milk", 25, 15, 0.2));
        }

        // Display menu
        System.out.println("Welcome to the Shopping System!");
        double total = 0;

        try {
            while (true) {
                System.out.println("\nItems:");
                for (int i = 0; i < products.size(); i++) {
                    System.out.println((i + 1) + ". " + products.get(i));
                }
                System.out.print("Select item (0 to exit): ");
                int choice = sc.nextInt();
                if (choice == 0)
                    break;

                Product item = products.get(choice - 1);
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                if (qty > item.getQuantity())
                    throw new OutOfStockException("Not enough stock!");

                total += item.getPrice() * qty;
                item.setQuantity(item.getQuantity() - qty);
                System.out.printf("Added ₱%.2f. Total: ₱%.2f%n", item.getPrice() * qty, total);
            }
        } catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Save updated stock
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("shopping.txt"))) {
            for (Product p : products) {
                if (p instanceof DiscountedProduct dp) {
                    bw.write(p.getName() + "," + dp.getPrice() / (1 - 0.2) + "," + p.getQuantity() + ",0.2");
                } else {
                    bw.write(p.getName() + "," + p.getPrice() + "," + p.getQuantity());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }

        System.out.printf("Thank you! Final total: ₱%.2f%n", total);
        System.out.println("Total products tracked: " + Product.totalProducts);
        sc.close();
    }
}
