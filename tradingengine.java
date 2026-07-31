import java.util.ArrayList;
import java.util.Scanner;

class tradingengine{

    private market market;
    private User user;
    ArrayList<Transaction> transactionhistory;
    private int nexttransaction;


    public tradingengine(String username , double startingCash){
       this.market = new market();
       this.user = new User(username, startingCash);
       this.transactionhistory = new ArrayList<>();
       this.nexttransaction = 1001;
    } 
 
 public void executebuy(String symbol , int qty){
    Stock stock = market.getstock(symbol);

    if(stock == null){
    System.out.println("Error: Stock ticker " + symbol.toUpperCase() + " does not exist.");
        return;
    } 

    double totalcost = stock.current_price * qty;

    if(user.deductcash(totalcost)){
        user.addshare(stock.symbol, qty);


        Transaction receipt = new Transaction(  nexttransaction++, stock.symbol, "BUY", qty, stock.current_price, "2026-07-14 17:22");

        transactionhistory.add(receipt);
        receipt.printReceipt();
    } 
 }

 public void executesell(String symbol , int qty){
        Stock stock = market.getstock(symbol);

    if(stock == null){
        System.out.println("Error: Stock ticker " + symbol.toUpperCase() + " does not exist.");
            return;
    } 

    if(user.removeshare(stock.symbol, qty)){
        double totalearning = stock.current_price * qty;
        user.addcash(totalearning);


        Transaction receipt = new Transaction(
                nexttransaction++, stock.symbol, "SELL", qty, stock.current_price, "2026-07-14 17:22"
            );

            transactionhistory.add(receipt);
            receipt.printReceipt();
    }

 }
    public void displayportfolioperformance() {
       System.out.println("\n=================== USER PORTFOLIO ===================");
        System.out.println("Account Holder: " + user.username);
        System.out.printf("Available Liquid Cash: $%.2f\n", user.cashbalance);
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s\n", "TICKER", "SHARES OWNED", "CURRENT VALUE");

        double totalstockvalue = 0;

        for (String symbol : user.portfolio.keySet()) {
            int qty = user.portfolio.get(symbol);
            Stock s = market.getstock(symbol);
            double currentStockValue = qty * s.current_price;
            totalstockvalue += currentStockValue;

            System.out.printf("%-10s %-15d $%-15.2f\n", symbol, qty, currentStockValue);
        }

        double netWorth = user.cashbalance + totalstockvalue;
        System.out.println("-----------------------------------------------------");
        System.out.printf("Total Asset Value: $%.2f\n", netWorth);
        System.out.println("=====================================================");

 }     

 public void displayhistory(){
       System.out.print("\n--- All Transation Logs ----");
        
        if(transactionhistory.isEmpty()){
            System.out.println("No Traders Completed Yet.");
            return;
        } 

        for(Transaction tx : transactionhistory){
            tx.printReceipt();
        }
 }

  public void startapp(){
      Scanner sc = new Scanner(System.in);
      System.out.println(" --- Welcome To The Simulation Terminal ---");

      while(true){

        System.out.println("\n[MENU] 1:View Market | 2:Buy | 3:Sell | 4:Portfolio | 5:Trade History | 6:Exit");
        System.out.print("Select operational token: ");
        String choice = sc.nextLine().trim();

        switch(choice) {
              
           case "1" ->{
                market.displaymarket();
                break;
           }
           case "2" ->{
            System.out.println("Enter Asset Ticket: ");
            String buysymbol = sc.nextLine();
            System.out.println("Enter Quantity To Purchase: ");
            int buyqty = Integer.parseInt(sc.nextLine());
            executebuy(buysymbol, buyqty);
            market.updatemarketprice();
            break;
           }
           case "3" ->{
            System.out.print("Enter asset ticker: ");
            String sellSymbol = sc.nextLine();
            System.out.print("Enter quantity to sell: ");
            int sellQty = Integer.parseInt(sc.nextLine());
            executesell(sellSymbol, sellQty);
            market.updatemarketprice(); // Trigger price updates
            break;
           }
           case "4" ->{
            displayportfolioperformance();
            break;
           }
           case "5" ->{
            displayhistory();
            break;
           }
           case "6" ->{

           System.out.println("Closing simulation engine. Terminating session...");
            sc.close();
            return;
           }
                default->
                    System.out.println("Invalid selection parameter.");

        }
      }
  }


    public static void main(String[] args){
  tradingengine engine = new tradingengine("Apex", 10000.00);
  engine.startapp();
    }
}