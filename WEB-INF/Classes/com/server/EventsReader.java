
import java.io.*;
import java.util.*;
import java.sql.*;

public class EventsReader{

	static public Vector getContents() {
    			Vector v = new Vector();

    try {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           String dataSourceName = "AgentNM";
           String dbURL = "jdbc:odbc:" + dataSourceName;
           Connection con = DriverManager.getConnection(dbURL, "","");
           Statement s = con.createStatement();

           s.execute("SELECT faults.IP,faults.EventSource,faults.Problem FROM faults WHERE 1=1");
           ResultSet rs = s.getResultSet();
           if (rs != null)
           while ( rs.next() )
           {
			   Event event = new Event();
			   event.setIp(rs.getString(1));
			   event.setSource(rs.getString(2));
			   event.setProblem(rs.getString(3));
   	   	   	   v.add(event);
   	   	   	   System.out.println(event.getIp());
   	   	   	   System.out.println(event.getSource());
   	   	   	   System.out.println(event.getProblem());



           }
           s.close();
           con.close();
       }



           catch (Exception err) {
           System.out.println("ERROR: " + err);
       }




			return v;

				}


}