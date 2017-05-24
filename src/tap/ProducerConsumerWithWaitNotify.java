
package tap;

public class ProducerConsumerWithWaitNotify {
    public static void main(String args[]) throws InterruptedException{
           
           Producer prod=new Producer();
           Consumer cons=new Consumer(prod);
           
        Thread prodThread=new Thread(prod,"prodThread");
           Thread consThread=new Thread(cons,"consThread");
           
           consThread.start();     //start consumer thread.
          //Thread.sleep(100);      //This minor delay will ensure that consumer thread starts before producer thread.
           prodThread.start();     //start producer thread.
           
           
    }
 
}