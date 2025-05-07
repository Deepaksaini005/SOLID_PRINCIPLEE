// File: Solid.java
package demo;

// 1. Single Responsibility Principle (SRP)
class Invoice {
    private String item;
    private int quantity;
    private double price;

    public Invoice(String item, int quantity, double price) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public double calculateTotal() {
        return quantity * price;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

// SRP: Separate class for printing invoice
class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println("Item: " + invoice.getItem());
        System.out.println("Quantity: " + invoice.getQuantity());
        System.out.println("Price: " + invoice.getPrice());
        System.out.println("Total: " + invoice.calculateTotal());
    }
}

// 2. Open/Closed Principle (OCP)
interface Discount {
    double applyDiscount(double total);
}

class NoDiscount implements Discount {
    public double applyDiscount(double total) {
        return total;
    }
}

class SeasonalDiscount implements Discount {
    public double applyDiscount(double total) {
        return total * 0.9; // 10% off
    }
}

// 3. Liskov Substitution Principle (LSP)
class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
}

class Sparrow extends Bird {
    public void fly() {
        System.out.println("Sparrow flies low");
    }
}

// 4. Interface Segregation Principle (ISP)
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

class AllInOnePrinter implements Printer, Scanner {
    public void print() {
        System.out.println("Printing...");
    }

    public void scan() {
        System.out.println("Scanning...");
    }
}

// 5. Dependency Inversion Principle (DIP)
interface Database {
    void save(String data);
}

class MySQLDatabase implements Database {
    public void save(String data) {
        System.out.println("Saved to MySQL: " + data);
    }
}

class InvoiceSaver {
    private Database database;

    public InvoiceSaver(Database database) {
        this.database = database;
    }

    public void saveInvoice(String invoiceData) {
        database.save(invoiceData);
    }
}

// Main class to test
public class Solid {
    public static void main(String[] args) {
        Invoice invoice = new Invoice("Book", 3, 250);
        InvoicePrinter printer = new InvoicePrinter();
        printer.print(invoice);

        Discount discount = new SeasonalDiscount();
        System.out.println("Discounted Total: " + discount.applyDiscount(invoice.calculateTotal()));

        Bird bird = new Sparrow();
        bird.fly();

        AllInOnePrinter printerMachine = new AllInOnePrinter();
        printerMachine.print();
        printerMachine.scan();

        Database db = new MySQLDatabase();
        InvoiceSaver saver = new InvoiceSaver(db);
        saver.saveInvoice("Invoice for Book");
    }
}
