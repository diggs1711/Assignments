import java.util.ArrayList;
/* david higgins 
 * 11428382
 */


public class StockRoom implements Runnable {
    
    private ArrayList<Stock> stockRoom;


    public StockRoom()
    {
        ArrayList<Stock> stockRoom = new ArrayList<Stock>();
    }

    
    @Override
    public void run() {
        
    }
    
    
    public synchronized void addToStock(Stock st){
       
        stockRoom.add(st);
    }
    
    public synchronized void removeStock(Stock st){
        stockRoom.remove(st);
    }
    
    public  synchronized int checkStock(){
        return stockRoom.size();
    }
    
    
    

}
