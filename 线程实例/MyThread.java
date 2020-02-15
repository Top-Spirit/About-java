package cn.tx.demo;

public class MyThread extends Thread {

    private String name ;

    public MyThread(String name) {
        this.name = name ;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(name + "下载了" + i + "%");
        }
    }

}
