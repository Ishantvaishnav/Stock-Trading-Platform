public class Stock {
    // Step 1: Define Class Attributes (Properties)
    String symbol;
    String name;
    double current_price;

    // Step 3: Create a Constructor to initialize a stock
    public Stock(String symbol, String name, double current_price) {
        this.symbol = symbol;
        this.name = name;
        this.current_price = current_price;
    }

    // Step 2: Object Method (Notice: NO 'static' keyword here)
    public void updateprice(double newprice) {
        if (newprice > 0) {
            this.current_price = newprice; // Now 'this' works perfectly
        } else {
            System.out.println("Error: Stock price must be greater than zero.");
        }
    }

    // Execution Block
    public static void main(String[] args) {
        // Create an actual instance of the Stock object
        Stock google = new Stock("GOOG", "GOOGLE", 2800.00);
        
        // Print the original price using the object
        System.out.println("Name: " + google.name + " | Price: " + google.current_price);
        
        // Call the method ON the object
        google.updateprice(2850.50);
        
        // Print the updated price
        System.out.println("Updated Price: " + google.current_price);
    }
}
