
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProducerConsumer {
    Random random = new Random();
    //arrayblokingqueue blokingqueue şeklinde bir interface'i implemente ediyor
    BlockingQueue<Integer> qu = new ArrayBlockingQueue<Integer>(10);
    /*arrayblokingqueue içersinde maximum 10 değer olmasını istediğimiz için constructordaa bunu ifade ediyoruz.
    arrayblokingqueue içerisinde ReentLock kendi içerisinde thread operasyonları yapan final anahtar kelimesiyle tanımlanmış bir yapı barındırıyor.Bu yapı threadlerimizi senkronize hale getirmeye çalışıyor.
    arrayblokingqueue threadler ile uyumlu çalışması için pek çok yapı bulunduruyor.*/
    public void produce(){
        //burada arrayblokingqueue içerisinde değerlerimizi eklemeye çalışacağız.
        while(true){
            try {
                //yapının sonsuza kadar çalışması için whlie yapısı kurduk
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Thread kesilirken sıkıntı oluştu(Uyku Hatası...!)");
            }
                Integer val = random.nextInt(100);//0-100 arasında değer üretiyoruz.
                System.out.println("Producer Üretiyor : "+val);
                
            try {
                //val değerimizi arrayblokingqueue içerisine atamamız gerekiyor sıradaki işlemimiz bu ...
                qu.put(val);//arrayblokingqueue kendi içersinde threadleri kullandığı için put ifadesini güvenli blok içerisine yazmamız gerek.
            } catch (InterruptedException ex) {
                System.out.println("ekleme sırasında hata meydana geldi");
            }/*
            bu yapı qu nun boyutu 10 değeri geçerse içeri değer koymayacak ve producerThread bekleyecek .ConsumerThread bu alandan bir değer alana kadar bu bekleme işlemi devam edecek.
            */
          
                
            }
        }
      public void consumer(){
           Integer valget ;
          while (true) {    
              
              try {
                  if(qu.size()==10){
                      Thread.sleep(100);
                      valget = qu.take();
                    System.out.println("Consumer Tüketiyor = " +valget);
                    System.out.println("Kuyruk boyutu : "+qu.size());
                      System.out.println("Eritme hzı 100 ms");
                  }else{
                       Thread.sleep(2500);
                       valget = qu.take();
                       System.out.println("Consumer Tüketiyor = " +valget);
                       System.out.println("Kuyruk boyutu : "+qu.size());
                       System.out.println("Eritme hızı 2500 ms");
                  }
                 
                  
                 
              } catch (InterruptedException ex) {
                  System.out.println("Uyku Hatası/kuyruk işlem hatası");
              }
            
          }
      }
    }
    /*
        put ifadesi sona değer ekler.Take ifadesi baştan değer çeker queue yapısı first in first out yapısına sahiptir.
    */
    

