
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>


<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<%

String idemisor = null;
Grid gridCodi   = null; 


DecimalFormat formateador = new DecimalFormat("###,##0.00");

if (request.getAttribute("io") != null ) {
	ObjectIO io = (ObjectIO)request.getAttribute("io");
	try {
		idemisor = io.getStringValue("idemisor");
		gridCodi = io.getGrid("gridCodi");	
	} catch (Exception e) {
		System.err.println("ERROR - Recibiendo los valores en factura/Listado Recibos Facturas.jsp "+ e.getMessage());	
	}
}
		
%>
	<div id="lineastmp">
		  
  		<table align="center" class="tab-listado" width="100%" >
			<tr>
				<td width=10%><div class="input-b1">Codigo</div></td>
				<td width=20%><div class="input-b1">Marca</div></td>
				<td width=45%><div class="input-b1"> Descripcion </div></td>
				<td width=15%><div class="input-b1"> Precio</div></td>
				<td width=10%><div class="input-b1"> Cantidad </div></td>
		   </tr>
			<% 
			 	String cddivisa = ""; 
			
				String codprodu = null; 
				String txmarcax = null;
				String txdescri = null;
				String impdefve = null;
				String cantidad = null;
				String divisaxx = null;
				String filecrea = null;
				String cdfactur = null;
			 	
		
			  for (int i = 0; i < gridCodi.rowCount(); i++){ 
				  
				  codprodu = gridCodi.getStringCell(i,"codprodu");
				  txmarcax = gridCodi.getStringCell(i,"txmarcax");
				  txdescri = gridCodi.getStringCell(i,"txdescri"); 
				  impdefve = gridCodi.getStringCell(i,"impdefve");
				  cantidad = gridCodi.getStringCell(i,"cantidad");
				  divisaxx = gridCodi.getStringCell(i,"divisaxx"); 
				  System.out.println("venta "+impdefve);
				  
				  if(impdefve == null || impdefve.equals("")){
					  impdefve="0.0";
				  }
	

				  if (i % 2 == 0) { %>
					<tr class="oddRow"  style="border:1px solid;cursor:pointer" onclick="buscaGrupo('<%=codprodu%>','<%=txmarcax%>','<%=txdescri%>')">
				<% } else { %>
					<tr class="evenRow" style="border:1px solid;cursor:pointer" onclick="buscaGrupo('<%=codprodu%>','<%=txmarcax%>','<%=txdescri%>')">
				<% }%>
				<td class="fonts6jej" style="font-size:12px" align="left" align="center"><%=codprodu  %></td>
					<td align="center" class="fonts6jej" style="font-size:12px;padding:5px"><%=txmarcax  %> </td>
					<td align="center" class="fonts6jej" style="font-size:12px;padding:5px"> <%=txdescri  %>  </td>
					<td align="right" class="highlightedColumn" style="font-size:12px;padding:5px" ><%= formateador.format(Double.parseDouble(impdefve)) %> <%= divisaxx %>  </td>
					<td align="right" class="fonts6jej" style="font-size:12px;padding:5px" ><%=cantidad  %> </td>
				</tr>
							
				<% 
		
			  } 
			  
			  %>
</table>
  	</div>		
  	