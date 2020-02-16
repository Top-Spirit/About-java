package udpdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

    public static void main(String[] args) {
        DatagramSocket ds = null ;
        BufferedReader reader = null ;

        try {
            reader = new BufferedReader(new InputStreamReader(System.in)) ;
            ds = new DatagramSocket() ;
//            String str = "你好" ;
            String line = null ;
            while ((line = reader.readLine()) != null) {
                byte[] bs = line.getBytes() ;
                DatagramPacket dp = new DatagramPacket(bs, bs.length, InetAddress.getByName("127.0.0.1"),10000) ;
                ds.send(dp);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ds != null){
                ds.close();
            }
        }
    }
}
