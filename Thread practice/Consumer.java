package commu;

public class Consumer extends Thread{
    private Basket basket ;
    public Consumer (Basket basket){
        super();
        this.basket = basket ;
    }

    @Override
    public void run() {
        while(true){
            synchronized (basket){
                try {
                    if (basket.isEmpty()){
                        basket.wait() ;
                    }
                    System.out.println("消费水果");
                    basket.setEmpty(true);
                    basket.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
