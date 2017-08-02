
<%@ page import="java.util.*"  %>
<%@ page import="com.server.*" %>
<%@ page import="java.sql.*"  %>
<%
Vector v = new Vector();
String strXML = "<chart caption='Fault Ratio' xAxisName='Host IP Address' yAxisName='Faults Reported' showValues='0' formatNumberScale='0' showBorder='1'>";

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
<title>Statistical Chart</title>
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
<script>
<!--

/*
Auto Refresh Page with Time script
By JavaScript Kit (javascriptkit.com)
Over 200+ free scripts here!
*/

//enter refresh time in "minutes:seconds" Minutes should range from 0 to inifinity. Seconds should range from 0 to 59
var limit="0:60"

if (document.images){
var parselimit=limit.split(":")
parselimit=parselimit[0]*60+parselimit[1]*1
}
function beginrefresh(){
if (!document.images)
return
if (parselimit==1)
window.location.reload()
else{ 
parselimit-=1
curmin=Math.floor(parselimit/60)
cursec=parselimit%60
if (curmin!=0)
curtime=curmin+" minutes and "+cursec+" seconds left until page refresh!"
else
curtime=cursec+" seconds left until page refresh!"
window.status=curtime
setTimeout("beginrefresh()",1000)
}
}

window.onload=beginrefresh
//-->
</script>
<title>AgentNW - Reports</title>
<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {
	font-size: 16px;
	font-weight: bold;
}
-->
</style>
</head>

<body text="#FFFFFF" bgcolor="#000000">

<div align="center">
  <center>
  <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="800" height="500">
    <tr>
      <td width="800" colspan="3" height="67">&nbsp;</td>
    </tr>
    <tr>
      <td width="94" rowspan="3" height="433">&nbsp;</td>
      <td width="146" height="35">
      <img border="0" src="images/1_03.gif" width="146" height="35"></td>
      <td width="560" height="35">&nbsp;</td>
    </tr>
    <tr>
      <td height="33" width="707" colspan="2">
      <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="100%">
        <tr>
          <td width="62" height="33">
            <a href="index.jsp"><img border="0" src="images/1_05.gif" width="62" height="33"></a></td>
          <td width="63" height="33">
            <img border="0" src="images/1_06.gif" width="63" height="33"></td>
          <td width="66" height="33">
            <a href="hosts.jsp"><img border="0" src="images/1_07.gif" width="66" height="33"></a></td>
          <td width="85" height="33">
            <a href="StatisChart.jsp"><img border="0" src="images/1_08.gif" width="85" height="33"></a></td>
          <td width="430" height="33">
          <img border="0" src="images/1_09.gif" width="430" height="33"></td>
        </tr>
      </table>
      </td>
    </tr>
    <tr>
      <td width="707" colspan="2">
      <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="100%">
        <tr>
          <td height="365" rowspan="2" valign="top">
          <p align="center"><font face="lucida console"><h6>&nbsp;
          <br>
          <br>
          <br>
          <jsp:include page="JSP/Includes/FusionChartsHTMLRenderer.jsp" flush="true"> 
					<jsp:param name="chartSWF" value="FusionCharts/Column3D.swf" /> 
					<jsp:param name="strURL" value="" /> 
					<jsp:param name="strXML" value="<%=strXML%>" /> 
					<jsp:param name="chartId" value="myFirst" /> 
					<jsp:param name="chartWidth" value="600" /> 
					<jsp:param name="chartHeight" value="300" /> 
					<jsp:param name="debugMode" value="false" /> 	
				</jsp:include>
                <h5>&nbsp;</h5>
          <h6>
            <p>          </h6></td>
          </tr>
</TABLE></td>
        </tr>
        <tr>
          <td width="50%">&nbsp;</td>
        </tr>
      </table>
      </td>
    </tr>
  </table>
  </center>
</div>


<div style="font-size: 0.8em; text-align: center; margin-top: 1.0em; margin-bottom: 1.0em;"> All Rights Reserved Mohamed Jehad Baeth 2010 
</div>
</body>

</html>