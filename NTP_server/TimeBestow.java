package NTP_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.concurrent.Callable;


public class TimeBestow implements Callable<Void> {
    private Socket connection;
    private String acceptionTimestamp;
    private String replyTimestamp;
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
    
    private String timeStamp() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()); 
    }

    private void unmarshaling() {
        try {
            var reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
            this.senderTimestamp = reader.readLine();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private String marhsaling(final String t1, final String t2, final String t3) {
        return t1 + "\n" + 
               t2 + "\n" + 
               t3 + "\n";
    }

    @Override
    public Void call() {
        // setting up T2
        this.acceptionTimestamp = this.timeStamp();
        
        // unmarshal the node message
        this.unmarshaling();

        // open the buffer and write the response
        try {
            var writer = new OutputStreamWriter(this.connection.getOutputStream());
            this.replyTimestamp = this.timeStamp();
            writer.write(this.marhsaling(senderTimestamp, replyTimestamp, acceptionTimestamp));
            writer.flush();
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            this.closeConn();
        }
        return null;
    }
}