
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        
        Thread procuder = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.produce();
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
               pc.consumer();
            }
        });
        procuder.start();
        consumer.start();
        
        try {
            procuder.join();
            consumer.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
