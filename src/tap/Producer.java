package tap;
import java.util.ArrayList;
 
/* Producer is producing, Producer will allow consumer to
 * consume only when 10 products have been produced (i.e. when production is over).
 */
class Producer implements Runnable{
 
    ArrayList<Integer> sharedQueue;
    
    Producer(){
           sharedQueue=new ArrayList<Integer>();
    }
    
    @Override
    public void run(){
           
           synchronized (this) {
                  for(int i=1;i<=10;i++){ //Producer will produce 10 products
                        sharedQueue.add(i);
                        System.out.println("Producer is still Producing, Produced : "+i);
                        
                        try{
                               Thread.sleep(1000);
                        }catch(InterruptedException e){e.printStackTrace();}
                  
                  }
                  System.out.println("Production is over, consumer can consume.");
                  this.notify();    //Production is over, notify consumer thread so that consumer can consume.
           }
    }
    
}
 
class Consumer extends Thread{
    Producer prod;
    
    Consumer(Producer obj){
     prod=obj;
    }
    
    public void run(){
           /*
            * consumer will wait till producer is producing.
            */
           synchronized (this.prod) {  
                  
                  System.out.println("Consumer waiting for production to get over.");
                     try{
                         this.prod.wait();  
                        }catch(InterruptedException e){e.printStackTrace();}
                  
           }
           
           
           /*production is over, consumer will start consuming.*/
           int productSize=this.prod.sharedQueue.size();
           for(int i=0;i<productSize;i++)
                  System.out.println("Consumed : "+ this.prod.sharedQueue.remove(0) +" ");  
           
    }
    
}