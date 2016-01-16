<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>


<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<%

	String txmensaj = "";
	Grid gridLine = null;
		
		String idemisor = "";
		String tpclient = "";
		String idinvent = ""; 
		String idunicox = ""; 
		String fhaltaxx = ""; 
		String consolid = "";
		String nameinve = "";
		String tpproduc = "";
  		String txproduc = "";
	
	
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
		  
  		<table width="100%" border=0 class="TablaGrande">
  	   		<tr class="fonts">
				<td class="input-b1" align="center">&nbsp;</td>
				<td class="input-b1" align="center">ID Unico</td>
				<td class="input-b1" align="center">Fecha alta</td>
				<td class="input-b1" align="center" width="10%">Tipo producto</td>
				<td class="input-b1" align="center">&nbsp;</td>
			</tr>
  	  		<tr>
  	  			<td colspan="8"><hr></td>	
  	  		</tr>
  		
  		<%
  		
	  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
	  			
	  			for (int i = 0; i < gridLine.rowCount(); i++){
	  				
	  				try {
	  					idinvent = gridLine.getStringCell(i,"idinvent");
	  					idunicox = gridLine.getStringCell(i,"idunicox");
	  					fhaltaxx = gridLine.getStringCell(i,"fhaltaxx");
	  					idemisor = gridLine.getStringCell(i,"idemisor");
	  					tpclient = gridLine.getStringCell(i,"tpclient");	  					
	  					consolid = gridLine.getStringCell(i,"consolid");	
	  					nameinve = gridLine.getStringCell(i,"nameinve");
	  					tpproduc = gridLine.getStringCell(i,"tpproduc");
	  					
	  					if(tpproduc.equals("TA")){
	  						txproduc ="TABLET";
	  					}else if(tpproduc.equals("PH")){
	  						txproduc ="TELEFONO";
	  					}else if(tpproduc.equals("PI")){
	  						txproduc ="PIEZA";
	  					}else if(tpproduc.equals("")){
	  						txproduc ="SIN ESPECIFICAR";
	  					}
	  					
						if (i % 2 == 0) { %>
							<tr class="oddRow"  style="border:1px solid">
						<% } else { %>
							<tr class="evenRow" style="border:1px solid">
						<% }%>
				  			<td><div class="fonts6jej">--<%=idinvent%>--</div></td>
						  	<td><div class="fonts6jej"><%=idunicox%></div></td>
							<td><div class="fonts6jej"><%=fhaltaxx%></div></td>
							<td><div class="fonts6jej"><%=txproduc%></div></td>
							<td width="5%"><img src="common/img/varios/delete.jpg" width=20 height=20 style="cursor:pointer" onclick="borraLineaInve('<%=idemisor%>','<%=idinvent%>','<%=nameinve%>')"></td>
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
  	