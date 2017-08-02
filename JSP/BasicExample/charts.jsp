
<%@ page import="java.util.*"  %>
<%@ page import="com.server.*" %>
<%@ page import="java.sql.*"  %>
<%
Vector v = new Vector();
String strXML = "<chart caption='Factory Output report' subCaption='By Quantity' pieSliceDepth='30' showBorder='1' formatNumberScale='0' numberSuffix=' Units'>";

    try {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           String dataSourceName = "AgentNM";
           String dbURL = "jdbc:odbc:" + dataSourceName;
		   
           Connection con = DriverManager.getConnection(dbURL, "","");
           

           
           
		   Statement ips = con.createStatement();
		   Statement s = con.createStatement();
		   ips.execute("SELECT DISTINCT faults.IP FROM faults WHERE 1=1");
		   ResultSet ip = ips.getResultSet();
		   if (ip != null)
           while ( ip.next() )
           {
		   		
				String hostip = ip.getString(1);
				
		   		s.execute("SELECT COUNT(faults.EventSource) FROM faults WHERE faults.IP = '"+ hostip +"'" );
		   		ResultSet rs = s.getResultSet();
				
		   if (rs != null)
           while ( rs.next() )
           {
		   
		   		int errorCount = rs.getInt(1);
		   		strXML += "<set label='" + hostip + "' value='" +errorCount+ "'/>";
		   
		   
		   }
		   }
	        s.close();
            con.close();
       }
           catch (Exception err) {
           System.out.println("ERROR: " + err);
       }
	   finally 
	   {
	   		strXML += "</chart>";
	   }
		   
		   
		   
           
%>

<html>

<head>

<title>AgentNW - Reports</title>
<SCRIPT LANGUAGE="Javascript" SRC="/FusionCharts/FusionCharts.js"></SCRIPT>
<style type="text/css">
	<!--
	body {
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
	}
	.text{
		font-family: Arial, Helvetica, sans-serif;
		font-size: 12px;
	}
	-->
	</style>

</HEAD>
<BODY>
								<jsp:include page="../Includes/FusionChartsRenderer.jsp" flush="true"> 
								<jsp:param name="chartSWF" value="../../FusionCharts/Pie3D.swf" /> 
								<jsp:param name="strURL" value="" /> 
								<jsp:param name="strXML" value="<%=strXML%>" /> 
								<jsp:param name="chartId" value="FactorySum" /> 
								<jsp:param name="chartWidth" value="600" /> 
								<jsp:param name="chartHeight" value="300" /> 
								<jsp:param name="debugMode" value="false" /> 	
								<jsp:param name="registerWithJS" value="false" /> 								
								</jsp:include>
     <BR>
<BR>
</BODY>
</HTML>
