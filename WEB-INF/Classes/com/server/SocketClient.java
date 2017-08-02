
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.Vector;

/**
 *
 * @author Jihad
 */
public class SocketClient {

   public static void main(String argv[]) throws Exception {
      ObjectOutputStream oos = null;

while(true)
{
	Thread.sleep(10000);
      try {
        // open a socket connection
        Socket socket = new Socket("127.0.0.1", 4000);
        // open I/O streams for objects
        oos = new ObjectOutputStream(socket.getOutputStream());

        // create two serialized objects
       // EventsVect so1 = new EventsVect();


        //JEventViewer jev = new JEventViewer();
        //so1.setEventsVect();
        Diagnoser d = new Diagnoser();

        // write the objects to the server
        //System.out.println("23123");
        Vector v = d.match();
        EventsVect ev = new EventsVect();
        ev.setEventsVect(v);
        //Event er =(Event) v.get(0);
        //System.out.println(er.getProblem());
        //System.out.println("Printing Vector0");
        //System.out.println(v.toString());

        oos.writeObject(ev);


        // read an object from the server
        oos.flush();
        oos.close();

       }
      catch(Exception e) {
        System.out.println(e.getMessage());
      }
  }
   }
}


