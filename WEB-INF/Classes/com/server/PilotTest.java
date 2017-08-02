

import java.util.*;
import java.io.*;
import java.sql.*;

public class PilotTest extends Thread {
	boolean newcon = false;



    private static SocketServer ss=null;




	 //   public st1atic void main(String argv[]) throws Exception
	    //{
	    //    new SocketServer();
	   // }


    public   static void main(String argv[]) throws Exception
    {
        ss=new SocketServer();
        while(true)
        {

							//System.out.println("New Con Value: from Pilot Test " + ss.getNewCon());



			//int i=0;
			if(ss.getNewCon())
			{
				EventsVect ev1 = ss.getEventsVect();
				Vector v = new Vector();
				v=ev1.geEventsVect();
				System.out.println(v.size());
				for(int i =0;i<v.size();i++)
				{
				Event s = (Event)v.get(i);
				PilotTest pt = new PilotTest();

				//System.out.println(s.getProblem() + s.getSource() + s.getId());
				pt.setContents(s,ss.getSourceIP());
			}
				ss.setNewCon(false);


}
			//i++;

		}
    }

	  public void setContents(Event event, String ip) {

		         try {
		         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		         /* the next 3 lines are Step 2 method 2 from above - you could use the direct
		         access method (Step 2 method 1) istead if you wanted */
		         String dataSourceName = "AgentNM";
		         String dbURL = "jdbc:odbc:" + dataSourceName;
		         Connection con = DriverManager.getConnection(dbURL, "","");
		         // try and create a java.sql.Statement so we can run queries
		         PreparedStatement s = null;
				 s = con.prepareStatement("INSERT INTO faults (EventID, EventSource, Problem, IP) VALUES(?, ?, ?, ?)");
				 s.setString(1,event.getId());
				 s.setString(2,event.getSource());
				 s.setString(3,event.getProblem());
				 s.setString(4,ip);
				 s.executeUpdate();

   	   	}



		         catch (Exception err) {
		         System.out.println("ERROR: " + err);
		     }

}
}