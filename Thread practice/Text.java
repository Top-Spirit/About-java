package commu;

public class Text {
    public static void main(String[] args) {
        Basket basket = new Basket() ;
        Producter producter = new Producter(basket) ;
        Consumer consumer = new Consumer(basket) ;
        producter.start();
        consumer.start();
    }
}
