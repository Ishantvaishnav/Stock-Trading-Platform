import java.util.HashMap;

class User{
    String username = "";
    double cashbalance = 1;
    HashMap<String , Integer> portfolio = new HashMap();
    


public User(String username, double startingCash) {
        this.username = username;
        this.cashbalance = startingCash;
        this.portfolio = new HashMap<>();
}
    public void  addcash(double amount){
     if(amount > 0){
        this.cashbalance += amount;
     }
  }
    public boolean deductcash(double amount){
        if(amount > 0 && this.cashbalance >= amount){
            this.cashbalance -= amount;
            return true;
        }
        System.out.println("Error: Insufficient cash balance.");
        return false; // Not enough money
    }

    public void addshare(String symbol , int qty){
        int currentshare = this.portfolio.getOrDefault(symbol , 0);
        this.portfolio.put(symbol, currentshare+qty);
    }
    public boolean removeshare(String symbol , int qty){
                int currentShares = this.portfolio.getOrDefault(symbol, 0);
                 if(currentShares >= qty){
                     if(currentShares == qty){
                        this.portfolio.remove(symbol);
                     }else{
                        this.portfolio.put(symbol, currentShares-qty);
                     }
                     return true;
                 }
        System.out.println("Error: You do not own enough shares of " + symbol);
        return false; // Sale denied
    }

    // public void updateprice(){
    //     double percentchage = ((random.nextDouble() * 5.0) - 2.5);
    //     this.cashbalance += this.cashbalance * (percentchage / 100.0);
    // }
    public static void main(String []args){
        User trader = new User("Alex", 10000.00);
        
        // Simulating some movements
        trader.deductcash(2850.50);
        trader.addshare("GOOG", 1);
        
        System.out.println("User: " + trader.username);
        System.out.println("Cash: $" + trader.cashbalance);
        System.out.println("Portfolio holdings: " + trader.portfolio);
    }
}