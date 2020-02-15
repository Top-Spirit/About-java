package commu;

public class Producter extends Thread{
    private Basket basket ;
    public Producter (Basket basket){
        super();
        this.basket = basket ;
    }

    @Override
    public void run() {
        while(true){
            synchronized (basket){
                try {
                    if (!basket.isEmpty()){
                        basket.wait() ;
                    }
                    System.out.println("生成水果");
                    basket.setEmpty(false);
                    basket.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
