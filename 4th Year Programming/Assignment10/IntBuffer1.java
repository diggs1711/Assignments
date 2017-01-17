//David Higgins
//11428382

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class IntBuffer1 {

    // queue
    //define max size of array
    public static final int MAX_CAPACITY = 10;
    private ArrayList<String> items = new ArrayList<String>(MAX_CAPACITY);

    // create lock and conditions
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    //creating random to put on queue
    Random rand = new Random();
    public int x;

    // Consumer
    public String get() throws InterruptedException {
        lock.lock();
        try {
            while (items.isEmpty()) {
                try {
                    //wait for producer to put item on array
                    notEmpty.await();                 
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            String item = null;
            try {
                // take item from top of list
                item = items.remove(0);
                
                System.out.println("Consumer has taken " + item);
                
                int size = items.size();
                //print out size of the list
                System.out.println("Size : " + size);
                
                // signal to producer that item was taken
                notFull.signal();
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            return item;
        } finally {
            lock.unlock();
        }
    }

    // producer
    public void put(String value) throws InterruptedException {
        lock.lock();
        try {
                // check if list is full
            while (items.size() >= MAX_CAPACITY) {
                try {
                    // wait for list to have space
                    notFull.await();
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            items.add(value);
            System.out.println("Producer put "  + value + " on the queue ");
            
            int size = items.size();
            //print size of list
            System.out.println("Size : " + size);
            
            // signal to consumer that item is on list
            notEmpty.signal();
            //System.err.println("Signalled to producer");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        new IntBuffer1().startThreads(args);

    }

    private void startThreads(String[] args) {

        for (int i = 0; i < rand.nextInt(6); i++) {
            //create random number of consumer threads
            new Thread(new Consumer()).start();
            //System.out.println("Consumer thread started");
        }

        for (int i = 0; i < rand.nextInt(6); i++) {
          //create random number of producer threads
            new Thread(new Producer()).start();
            //System.out.println("Producer thread started");
        }
        
    }

    private class Consumer implements Runnable {
        public void run() {
            while (true) {
                try {
                    //run get method indefinitely
                    get();
                    
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    private class Producer implements Runnable {
        public void run() {
            while (true) {
                x = rand.nextInt(20);
                String y = Integer.toString(x);
                try {
                  //run put method indefinitely
                    put(y);
                    
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }             
            }
        }

    }

}
