package NTP_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NTP {
    private static int LISTEN_PORT = 4040;
    private static int THREADS = 200;
    public static void main(String ...args) {
        NTPServer server = new NTPServer(NTP.LISTEN_PORT, NTP.THREADS);
        server.start();
    }
}