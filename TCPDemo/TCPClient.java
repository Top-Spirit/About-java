package tcpdemo;

import java.io.*;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) {
        Socket s = null ;
        BufferedWriter writer = null;
        BufferedReader reader = null;

        try {
            s = new Socket("127.0.0.1", 9999) ;
            OutputStream out = s.getOutputStream() ;
            writer = new BufferedWriter(new OutputStreamWriter(out)) ;
            reader = new BufferedReader(new InputStreamReader(System.in)) ;
            String line = null;
            while (true){
                line = reader.readLine() ;
                writer.write(line);
                writer.newLine();
                writer.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (s != null){
                    s.close();
                }
                if (writer != null){
                    writer.close();
                }
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
