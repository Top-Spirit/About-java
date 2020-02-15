package cn.tx.demo;

public class ThreadTest {


    public static void main(String[] args) {
        MyThread thread = new MyThread("当幸福来敲门");
        MyThread thread1 = new MyThread("肖申克的救赎");
        thread.start(); ;
        thread1.start();

    }
}
