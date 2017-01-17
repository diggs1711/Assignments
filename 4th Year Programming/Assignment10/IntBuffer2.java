//David Higgins
//11428382


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class IntBuffer2 {
// queue

private ArrayList<String> items = new ArrayList<String>();

// create lock and conditions
private ReentrantLock lock = new ReentrantLock();
private Condition notFull = lock.newCondition();
private Condition notEmpty = lock.newCondition();

Random rand = new Random();
public int x;

// Consumer
public String get() throws InterruptedException {
    lock.lock();
    try {
        while (items.isEmpty()) {
            try {
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
            Thread.sleep(100);
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
        
        items.add(value);
        System.out.println("Producer put "  + value + " on the queue ");
        
        int size = items.size();
        //print size of list
        System.out.println("Size : " + size);
        
        // signal to consumer that item is on list
        notEmpty.signal();
        Thread.sleep(100);
    } finally {
        lock.unlock();
    }
}

public static void main(String[] args) {

    new IntBuffer2().startThreads(args);

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
                
                Thread.sleep(100);
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
                
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }             
        }
    }

}

}

