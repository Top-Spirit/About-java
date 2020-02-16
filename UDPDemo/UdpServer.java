package udpdemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {
    public static void main(String[] args) {
        DatagramSocket ds = null ;

        try {
            ds = new DatagramSocket(10000);
            //创建数据包
            byte[] bs = new byte[1024] ;
            DatagramPacket dp = new DatagramPacket(bs, bs.length) ;
            System.out.println("UDP服务端已经启动...");
            while(true){
                //接收数据
                ds.receive(dp);
                //获得客户端IP
                InetAddress inetAddress =dp.getAddress() ;
                //获得数据
                byte[] data = dp.getData() ;
                int length = dp.getLength() ;
                String str = new String(data, 0, length) ;
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ds != null){
                ds.close();
            }
        }
    }
}
