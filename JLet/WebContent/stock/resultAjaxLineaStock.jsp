<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>


<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<%

	String txmensaj = "";
	Grid gridLine = null;
		
	String idemisor = "";
	String cdimeixx = "";
	String pricecmp = "";
	String idcatego = "";
	String diviscmp = "";
	String withchar = "";
	String withusbx = "";
	String tipocone = "";
	String withboxx = "";
	String withheph = "";
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			//txmensaj = io.getStringValue("txmensaj");
			gridLine = io.getGrid("gridLine");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ajaxok factura/index.jsp");	
		}
	}
		
%>

	<div id="lineastmp">
	
  		<table id="myTable" border="0" width=90% align="center">
        		<tr>
        			<td class="input-b1">Imei</td>
        			<td class="input-b1">Clase</td>
        			<td class="input-b1">Divisa Compra</td>
        			<td class="input-b1">P. compra</td>
        			<td class="input-b1">Cargador</td>
        			<td class="input-b1">Conector</td>
        			<td class="input-b1">USB</td>
        			<td class="input-b1">Caja</td>
        			<td class="input-b1">Auriculares</td>
        		
        		</tr>
  			
  	  		<tr>
  	  			<td colspan="9"><hr></td>	
  	  		</tr>
  		
  		<%
  		
  		
  		
	  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
	  			
	  			for (int i = 0; i < gridLine.rowCount(); i++){
	  				
	  				try {
	  					cdimeixx = gridLine.getStringCell(i,"cdimeixx");
	  					idcatego = gridLine.getStringCell(i,"idcatego");
	  					diviscmp = gridLine.getStringCell(i,"diviscmp");
	  					pricecmp = gridLine.getStringCell(i,"pricecmp");
	  					withchar = gridLine.getStringCell(i,"withchar");	  					
	  					tipocone = gridLine.getStringCell(i,"tipocone");	
	  					withusbx = gridLine.getStringCell(i,"withusbx");
	  					withboxx = gridLine.getStringCell(i,"withboxx");
	  					withheph = gridLine.getStringCell(i,"withheph");
	  					
	  					
						if (i % 2 == 0) { %>
							<tr class="oddRow"  style="border:1px solid">
						<% } else { %>
							<tr class="evenRow" style="border:1px solid">
						<% }%>
				  			<td align="center"><div class="fonts6jej">--<%=cdimeixx%>--</div></td>
						  	<td align="center"><div class="fonts6jej"><%=idcatego%></div></td>
							<td align="center"><div class="fonts6jej"><%=diviscmp%></div></td>
							<td><div class="fonts6jej"><%=pricecmp%></div></td>
							<td><div class="fonts6jej"><%=withchar%></div></td>
						  	<td><div class="fonts6jej"><%=tipocone%></div></td>
							<td><div class="fonts6jej"><%=withusbx%></div></td>
							<td><div class="fonts6jej"><%=withboxx%></div></td>
							<td><div class="fonts6jej"><%=withheph%></div></td>
							<td width="5%" class="fonts6jej"><img src="common/img/varios/delete.jpg" width=20 height=20 style="cursor:pointer" onclick="borraLineaProd('<%=cdimeixx%>')"></td>
				
					</tr>
				<%	} catch (Exception e) { 
						e.printStackTrace();
						System.out.println("ERROR recuperando linea "+ i); %>
						<tr>
							<td align="center" colspan="6" style="color:#FF0000">-- ERROR --</td>
						</tr>
				<%	}
				} %>
	  		
			<% } %>			
		</table>	
		<br/>
		<br/>
  	</div>		
  	