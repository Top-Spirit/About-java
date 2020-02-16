package tcpdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {
        ServerSocket ss = null ;
        Socket s  = null ;
        BufferedReader reader = null ;

        try {
            ss = new ServerSocket(9999) ;
            System.out.println("服务已经启动了...");
            s = ss.accept() ;
            InputStream in = s.getInputStream() ;
            reader = new BufferedReader(new InputStreamReader(in)) ;
            while (true){
                String line = reader.readLine() ;
                InetAddress address = s.getInetAddress() ;
                // 获得客户端的ip地址
                String ip = address.getHostAddress() ;
                System.out.println(ip + "发送了:" + line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (reader != null) {
                    reader.close();
                }
                if (s != null){
                    s.close();
                }
                if (ss != null){
                    ss.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
