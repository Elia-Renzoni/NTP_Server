package NTP_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.Instant;
import java.util.concurrent.Callable;


public class TimeBestow implements Callable<Void> {
    private Socket connection;
    private Instant acceptionTimestamp;
    private Instant replyTimestamp;
    private String senderTimestamp;

    public TimeBestow(final Socket conn) {
        this.connection = conn;
    }

    private void closeConn() {
        try {
            this.connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Instant timeStamp() {
        return Instant.now(); 
    }

    private void getTimestampFromRequest() {
        try {
            var reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            this.senderTimestamp = reader.readLine();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public Void call() throws Exception {


    }
}