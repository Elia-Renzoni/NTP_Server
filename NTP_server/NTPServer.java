package NTP_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NTPServer {
    private int listenPort;
    private int nConnections;

    public NTPServer(final int listenPort, final int threads) {
        this.listenPort = listenPort;
        this.nConnections = threads;
    }

    public void start() {
        ExecutorService threadPool = Executors.newFixedThreadPool(this.nConnections);

        try {
            ServerSocket NTPconn = new ServerSocket(this.listenPort);
            System.out.println("Server Listening...");
            while (true) {
                Socket conn = NTPconn.accept();
                System.out.println("Accepted Request!");
                Callable<Void> thread = new TimeBestow(conn);
                threadPool.submit(thread); 
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
