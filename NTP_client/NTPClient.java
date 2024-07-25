package NTP_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NTPClient extends Thread {
    private Socket conn;

    private String beforeSendinTimeStamp;
    private String acceptedServerConnTimeStamp;
    private String whenSendingServerTimeStamp;
    private String whenRecevingClientTimeStamp;

    private Date t1;
    private Date t2;
    private Date t3;
    private Date t4;

    private long networkDelay;
    private long serverTimeStamp;
    private long clockSkew;
    private long syncClock;

    public NTPClient(final int i, final Socket connection) {
        super("Client Number " + i);
        this.conn = connection;
    }

    private String getTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss.SS").format(Calendar.getInstance().getTime()); 
    }

    private void calculateClockSkew() {
        // clock skew formula: ((t3 + Ndelay / 2) - t4)
        // Ndelay formula: (t4 - t1) - (t3 - t2)
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            this.t1 = sdf.parse(this.beforeSendinTimeStamp);
            this.t2 = sdf.parse(this.acceptedServerConnTimeStamp);
            this.t3 = sdf.parse(this.whenSendingServerTimeStamp);
            this.t4 = sdf.parse(this.whenRecevingClientTimeStamp);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        this.networkDelay = (t4.getTime() - t1.getTime()) - (t3.getTime() - t2.getTime());
        this.serverTimeStamp = t3.getTime() + this.networkDelay / 2;
        this.clockSkew = this.serverTimeStamp - t4.getTime();
    }

    private void clockSync() {
    }
    
    public void run() { 
        try {
            var writer = new OutputStreamWriter(this.conn.getOutputStream());
            writer.write(this.getTimeStamp() + "\n");
            writer.flush();

            // now wait for the response...
            var reader = new InputStreamReader(this.conn.getInputStream());
            BufferedReader r = new BufferedReader(reader);

            // read the server timestamps
            this.beforeSendinTimeStamp = r.readLine();
            this.acceptedServerConnTimeStamp = r.readLine();
            this.whenSendingServerTimeStamp = r.readLine();
            this.whenRecevingClientTimeStamp = this.getTimeStamp();

            // calculate the clock skew then sync. the clock
            this.calculateClockSkew();
            this.clockSync();
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
