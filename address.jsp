<%@ page import="java.util.*"  %>
<%@ page import="com.server.*" %>
<%@ page import="java.sql.*"  %>
<%
Vector v = new Vector();

String ipvalue= request.getParameter("value");
  

    try {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           String dataSourceName = "AgentNM";
           String dbURL = "jdbc:odbc:" + dataSourceName;
           Connection con = DriverManager.getConnection(dbURL, "","");
           Statement s = con.createStatement();

           s.execute("SELECT faults.IP,faults.EventSource,faults.Problem FROM faults WHERE  faults.IP= '" + ipvalue+"'");
           ResultSet rs = s.getResultSet();
		   Statement ips = con.createStatement();
		   ips.execute("SELECT DISTINCT faults.IP FROM faults WHERE 1=1");
		   ResultSet ip = ips.getResultSet();
           
%>

<html>

<head>
<script>
<!--

/*
Auto Refresh Page with Time script
By JavaScript Kit (javascriptkit.com)
Over 200+ free scripts here!
*/

//enter refresh time in "minutes:seconds" Minutes should range from 0 to inifinity. Seconds should range from 0 to 59
var limit="0:30"

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
          <td width="294" height="365" rowspan="2" valign="top">
          <p align="center"><font face="lucida console"><h6>&nbsp;
          <h5><strong>Data Collection and processing Status: ( <span class="style1">Commence</span> )</strong></h5>
          <p class="style2">Connected Hosts:</p>
          <% 
		  if (ip != null)
           while ( ip.next() )
           {
		   			    String ipTemp = ip.getString(1);		  
		  
		   %>
          <ul>
            <li><strong><a href="address.jsp?value=<%= ipTemp %>" > <%= ipTemp %> <a></strong></li>
            </ul>  
            <%
			 }
			 %>        
          <h6>
            <p>          </h6></td>
          <td width="412" height="263">
  <form  method='post'>        
<table border=1 cellpadding=3 cellspacing=0 style='border-collapse: collapse' bordercolor=#C0C0C0 width=100% id=AutoNumber1>
<tr>
<td valign=top bgcolor="#9D9254">
<p align=center><strong>
<font face=Verdana size=2>IP address</font></td>
<td valign=top bgcolor="#9D9254">
<p align=center>
<font face=Verdana size=2><strong> Risk</font></td>
<td valign=top bgcolor="#9D9254">
<p align=center>
<font face=Verdana size=2><strong> Details</font></td>

</tr>
<%

if (rs != null)
           while ( rs.next() )
           {

         %>
<tr>
<td ><font face=Verdana size=1><%= rs.getString(1) %></font>&nbsp;</td>
<td ><font face=Verdana size=2><%= rs.getString(2) %></font>&nbsp;</td>
<td ><font face=Verdana size=2><%= rs.getString(3) %></font>&nbsp;</td>
<%
}
%>
<%   
           s.close();
           con.close();
       }
           catch (Exception err) {
           System.out.println("ERROR: " + err);
       }
	   finally 
	   {
	   }
 %>
</tr>

</table>
<p align=center>

  </Form>

</TD>
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