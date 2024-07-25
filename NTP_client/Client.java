package NTP_client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String ...args) {
        NTPClient[] c = new NTPClient[60];

        try {
            Socket conn = new Socket("localhost", 4040);

            for (int i = 0; i < c.length; i++) {
                c[i] = new NTPClient(i, conn);
                c[i].start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < c.length; i++) {
            try {
                c[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
