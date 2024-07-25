package NTP_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NTP {
    private static int LISTEN_PORT = 4040;
    public static void main(String ...args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(200);

        try {
            ServerSocket ntpConn = new ServerSocket(NTP.LISTEN_PORT);
            while (true) {
                Socket conn = ntpConn.accept();
                Callable<Void> thread = new TimeBestow();
                threadPool.submit(thread);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}