package NTP_client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String ...args) {
        try {
            Socket conn = new Socket("localhost", 4040);
            NTPClient c = new NTPClient(conn);
            c.start();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
