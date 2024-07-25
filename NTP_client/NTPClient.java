package NTP_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NTPClient extends Thread {
    private Socket conn;
    private String beforeSendinTimeStamp;
    private String acceptedServerConnTimeStamp;
    private String whenSendingServerTimeStamp;
    private String whenRecevingClientTimeStamp;

    public NTPClient(final int i, final Socket connection) {
        super("Client Number " + i);
        this.conn = connection;
    }

    private String getTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()); 
    }
    
    public void run() { 
        try {
            var writer = new OutputStreamWriter(this.conn.getOutputStream());
            writer.write(this.getTimeStamp() + "\n");
            writer.flush();

            // now wait for the response...
            var reader = new InputStreamReader(this.conn.getInputStream());
            BufferedReader r = new BufferedReader(reader);

            this.beforeSendinTimeStamp = r.readLine();
            this.acceptedServerConnTimeStamp = r.readLine();
            this.whenSendingServerTimeStamp = r.readLine();

            this.whenRecevingClientTimeStamp = this.getTimeStamp();

            System.out.println(super.getName() + " received server response!!");

            
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
