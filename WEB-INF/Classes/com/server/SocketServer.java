/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jihad
 */
public class SocketServer extends Thread {
	boolean newcon = false;

    private ServerSocket arrayServer;
    public String sourceIP="";

    public void setSourceIP(String ip)
    {
		this.sourceIP=sourceIP;
	}
	public String getSourceIP()
	{
		return sourceIP;
	}

    public static void main(String argv[]) throws Exception {
        new SocketServer();
    }


    public SocketServer() throws Exception {
        arrayServer = new ServerSocket(4000);
        System.out.println("Server listening on port 4000.");
        this.start();
    }
    EventsVect ev = new EventsVect();

    public void setEventsVect(EventsVect ev)
    {
		this.ev=ev;
	}
	public EventsVect getEventsVect()
	{
		return ev;
	}
	public boolean getNewCon()
	{
		return newcon;
	}
	public void setNewCon(boolean newcon)
	{
		this.newcon=newcon;
	}

    public void run() {

        while (true) {
            try {
                System.out.println("Waiting for connections.");
                Socket client = arrayServer.accept();
				sourceIP=client.getInetAddress().toString();
                System.out.println("Accepted a connection from: " + client.getInetAddress());


                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                //EventsVect x = null;
                System.out.println("reading vector");
                EventsVect ev = new EventsVect();


                try {

                    ev = (EventsVect) ois.readObject();
                 	 setEventsVect(ev);

					newcon = true;
					Thread.sleep(500);
					//System.out.println("New Con Value: from server " + newcon);

                     //System.out.println(v.toString());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("here is the problem");
                }
                catch(InterruptedException ex)
                {

				}
                System.out.println("Reading done");


              // System.out.println(ev.geEventsVect().size());
                Vector v = ev.geEventsVect();
                for (int i = 0; i < v.size(); i++) {
                    Event evntrec = new Event();
                    evntrec = (Event) v.get(i);
                    //System.out.println("Event ID: " + evntrec.getId() + "  ");
                    //System.out.println("Event Type: " + evntrec.getSource() + "  ");
                    System.out.println("Event Source: " + evntrec.getProblem() + "  ");

                }
                System.out.println("number of reported errors : " + v.size() + "  from PC with IP address : "+ client.getInetAddress());
                ois.close();
                client.close();
            } catch (IOException ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }





        }
    }

}
