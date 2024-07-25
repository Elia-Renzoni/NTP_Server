package NTP_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NTPClient {
    private Socket conn;
    private String beforeSendinTimeStamp;
    private String acceptedServerConnTimeStamp;
    private String whenSendingServerTimeStamp;
    private String whenRecevingClientTimeStamp;

    public NTPClient(final Socket connection) {
        this.conn = connection;
    }

    private String getTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()); 
    }
    
    public void start() { 
        try {
            var writer = new OutputStreamWriter(this.conn.getOutputStream());
            writer.write(this.getTimeStamp() + "\n");

            // now wait for the response...
            var reader = new InputStreamReader(this.conn.getInputStream());
            BufferedReader r = new BufferedReader(reader);

            this.beforeSendinTimeStamp = r.readLine();
            this.acceptedServerConnTimeStamp = r.readLine();
            this.whenSendingServerTimeStamp = r.readLine();

            this.whenRecevingClientTimeStamp = this.getTimeStamp();

            System.out.println(" received server response!!");
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
