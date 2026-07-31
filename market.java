import java.util.HashMap;
import java.util.Random;

// Class names should ideally be capitalized in Java
class market {

    private HashMap<String, Stock> stockexchange;
    private Random random; 

    // FIX: Removed 'void' so it behaves as a true constructor
    public market(){
        this.stockexchange = new HashMap<>();
        this.random = new Random();

        initializeDefaultStocks();
    }

    public void initializeDefaultStocks(){
        stockexchange.put("AAPL", new Stock("AAPL", "Apple Inc.", 175.50));
        stockexchange.put("SAM", new Stock("SAM", "samsung Inc", 220.00));
        stockexchange.put("GOOG", new Stock("GOOG", "Google LLC", 2800.00));
        stockexchange.put("AMZN", new Stock("AMZN", "Amazon.com Inc.", 135.25));
        stockexchange.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 320.10));
        stockexchange.put("TSLA", new Stock("TSLA", "Tesla Inc.", 240.80));   
    }

    public void displaymarket(){
        System.out.println("\n================= LIVE MARKET DATA =================");
        System.out.printf("%-10s %-20s  %-12s\n" , "TICKER" , " COMPANY NAME" , "PRICE"); // Fixed "TICKET" typo
        System.out.println("----------------------------------------------------");

        for( Stock stock : stockexchange.values()){
            System.out.printf("%-10s %-20s $%-12.2f\n", 
                stock.symbol, stock.name, stock.current_price);
        }
        System.out.println("====================================================");
    }

    public Stock getstock(String symbol){
        return stockexchange.get(symbol.toUpperCase());
    }

    public void updatemarketprice(){
        for(Stock stock : stockexchange.values()){
            double percentchage = (random.nextDouble() * 6.0) - 3.0;
            double changefactor = 1 + (percentchage  / 100.00);
            double updatedPrice = stock.current_price * changefactor;
            stock.updateprice(updatedPrice);
        }
        System.out.println("\n[System Notification: market prices have ticked and fluctuated!]");
    }

    public static void main(String args[]){
      
        market market = new market();

        System.out.println("--- Initial market Layout ---");
        market.displaymarket();

        market.updatemarketprice();

        System.out.println("--- Layout After market Movement ---");
        market.displaymarket();

        System.out.println("\nTesting retrieval functionality:");
        Stock searchedstock = market.getstock("appl");
        if(searchedstock != null){
            System.out.println("Found Stock: " + searchedstock.name + " at $" + searchedstock.current_price);
        } else {
            System.out.println("Error: Stock not found.");
        }
    }
}
