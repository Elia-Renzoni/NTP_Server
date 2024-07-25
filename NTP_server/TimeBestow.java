package NTP_server;

import java.io.IOException;
import java.net.Socket;
import java.time.Instant;
import java.util.concurrent.Callable;

public class TimeBestow implements Callable<Void> {
    private Socket connection;
    private Instant acceptionTimestamp;
    private Instant replyTimestamp;
    private Instant senderTimestamp;

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


    @Override
    public Void call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
}