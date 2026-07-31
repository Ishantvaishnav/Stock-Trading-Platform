class Transaction{

int Transactionid = 1001;
String stocksymbol = "GOOG";
String type = "";
int quantity = 1;
double pricepershare = 2850.50;
String timestamp = "";


public Transaction(int Transactionid , String stocksymbol, String type , int quantity , double pricepershare , String timestamp  ){
 this.timestamp = timestamp;
 this.quantity = quantity;
 this.stocksymbol = stocksymbol;
 this.pricepershare = pricepershare;
 this.Transactionid = Transactionid;
 this.type = type;
}

public void printReceipt() {
        System.out.println("====== TRANSACTION RECEIPT ======");
        System.out.println("ID: " + this.Transactionid);
        System.out.println("Time: " + this.timestamp);
        System.out.println("Action: " + this.type);
        System.out.println("Stock: " + this.stocksymbol);
        System.out.println("Quantity: " + this.quantity + " shares");
        System.out.println("Price per share: $" + this.pricepershare);
        System.out.println("Total Cost: $" + (this.quantity * this.pricepershare));
        System.out.println("=================================");
    }
public static void main(String args[]){
   Transaction text1 = new Transaction(1001, "GOOG", "BUY", 5, 2850.50, "2026-07-11 17:15");

   text1.printReceipt();
}
}