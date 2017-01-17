/* david higgins 
 * 11428382
 */



public class Main implements Runnable{
    
    static StockRoom stockRoom;
    
    
    
    public static void main (String[] args0){
        
        stockRoom = new StockRoom();
        Thread t = new Thread();
        t.start();

    }

    @Override
    public void run() {

        for(int i=0 ; i < 1000; i++){
            Stock st = new Stock(i);
            
            stockRoom.addToStock(st);
        }
    }
        
    }


