package NTP_server;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Callable;

public class TimeBestow implements Callable<Void> {
    private Socket connection;

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

    @Override
    public Void call() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
}