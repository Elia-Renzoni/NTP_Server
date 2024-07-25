package NTP_client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String ...args) {
        int port = 4040;
        NTPClient[] c = new NTPClient[60];
        try {
            for (int i = 0; i < c.length; i++) {
                c[i] = new NTPClient(i, new Socket("localhost", port));
                c[i].start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

       for (int i = 0; i < c.length && c[i] != null; i++) {
        try {
            c[i].join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       } 
    }
}
