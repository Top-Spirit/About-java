package cn.tx.demo;

public class RunnableTest {

    public static void main(String[] args) {
        Thread m1;
        m1 = new Thread(new cn.tx.demo.MyRunnable("当幸福来敲门"));
        Thread m2 = new Thread(new cn.tx.demo.MyRunnable("肖申克的救赎"));
        m1.start();
        m2.start();
    }
}
