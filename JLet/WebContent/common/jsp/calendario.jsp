<%@page import="java.util.*,java.text.*" %>
 
<% int mes,anio, mesAnt, mesAct;
int primerdia=1;
int ultimodia=2;
String [] meses = {"ENERO","FEBRERO","MARZO","ABRIL",
"MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE",
"NOVIEMBRE","DICIEMBRE"};
mes=6; //pon el mes que quieras
anio=2007; //pon el año que quieras
GregorianCalendar fecha=new GregorianCalendar(2006,12,primerdia);
GregorianCalendar fecha2=new GregorianCalendar(anio,mes,ultimodia); %>

<table border="1">
<TR>
<TH COLSPAN="8" ALIGN="CENTER"><%= meses[fecha.get(Calendar.MONTH)] %></TH>
</TR>
<tr>
<th>LUN</th>
<th>MAR</th>
<th>MIE</th>
<th>JUE</th>
<th>VIE</th>
<th>SAB</th>
<th>DOM</th>
<th>SEMANA</th>
</tr>
<TR>
<%
while(fecha.equals(fecha2)== false){%>

<TD><%= fecha.get(fecha.DATE)%> </TD>

<% if(fecha.get(GregorianCalendar.DAY_OF_WEEK)==GregorianCalendar.SUNDAY) { %>
<TD><%= fecha.get(Calendar.WEEK_OF_YEAR) %>
<%} %>

<% mesAnt = fecha.get(Calendar.MONTH) ;
fecha.add(fecha.DATE,1);
mesAct = fecha.get(Calendar.MONTH) ;
primerdia=fecha.get(fecha.DAY_OF_WEEK); %>
<% if( mesAnt != mesAct ) { %>
</tr>

<TR>
<TH COLSPAN="8" ALIGN="CENTER"><%= meses[fecha.get(Calendar.MONTH)] %></TH>
</TR>
<tr>
<th>LUN</th>
<th>MAR</th>
<th>MIE</th>
<th>JUE</th>
<th>VIE</th>
<th>SAB</th>
<th>DOM</th>
<th>SEMANA</th>
</tr>
<tr>
<% System.out.println("PRIMER DIA" + primerdia ); %>
<%if(primerdia == 1) {%>
<td colspan="6" ></td> <%}%>
<%if(primerdia == 3) {%>
<td>&nbsp;</td> <%}%>
<%if(primerdia == 4) {%>
<td colspan="2" ></td> <%}%>
<%if(primerdia == 5){%>
<td colspan="3" ></td> <%}%>
<%if(primerdia == 6){%>
<td colspan="4" ></td> <%}%>
<%if(primerdia == 7){%>
<td colspan="5" ></td> <%}%>
<%} %>
<%}%>
</TR>
</table>
</TD>
</TR>