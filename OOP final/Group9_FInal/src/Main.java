/*
 *  Program:
 * 
 *  Customer experience only. Products are hard coded.
 *  Customers are only allowed to buy and view products.
 *   
 *  Program will save and log all the transactions and accessible by the customer.
 * 
 *  Defined Rules:
 *
 *      Buy()
 *      Name()
 *      Price()
 *      Description()
 *      Quantity()
 *  
 *      I/O
 * 
 *  Goal:
 *  Module 1 = Auto done :/ (Java)
 *  Module 2 = Auto done (foundation of the program (identifiers,keywords,comments,data types))
 *  Module 3 = 
 *  Module 4 = 
 *  Module 5 = Done (Access modifers (public & private), Classes, .this, getters & setters)
 *  Module 6 = Done (Class Discounted inherits Class Product)
 *  Module 7 = Done (Interface Actions)
 *  Module 8 =
 *  Module 9 =
 *  Module 10 =
 * 
*/

/*
 *  INTERFACE - Product only
*/
interface Actions {
    public void Name();
    public Double Price();
    public String Description();
    public Integer Quantity();
}

/*
 *  SUPERCLASS + GETTERS AND SETTERS
*/
class Product implements Actions {
    private String name;
    private Double price;
    private String description;
    private Integer quantity;

    public Product(String name, double price, String description, Integer quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public void Name() {
        System.out.println("Product: " + name);
    }

    public Double Price() {
        return price;
    }

    public String Description() {
        return description;
    }

    public Integer Quantity() {
        return quantity;
    }
}

/*
 *  SUBCLASS
*/
class Discounted extends Product {
    private double discountRate;

    public Discounted(String name, double price, String description, Integer quantity, double discountRate) {
        super(name, price, description, quantity);
        this.discountRate = discountRate;
    }

    @Override
    public Double Price() {
        return super.Price() * (1 - discountRate);
    }

    @Override
    public String Description() {
        return super.Description() + " (Discounted: " + (discountRate * 100) + "% off)";
    }
}

/*
 *   MAIN CLASS
*/
public class Main {
    public static void main(String[] args) {

    }
}
