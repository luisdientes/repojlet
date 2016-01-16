<%@ include file="/common/jsp/include.jsp"%>

<%

	String imptotal = "";
	Grid gridLine = null; 
	Grid gridDivi = null; 
	Grid gridColo = null;
	
	String txmarcax = ""; 
	String txmodelo = ""; 
	String numunida = ""; 
	String idemisor = "";
	String tpclient = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gridLine = io.getGrid("gridLine");		
			gridColo = io.getGrid("gridColo");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en stock/listadoStock.jsp "+ e.getMessage());	
		}
	}
	
	
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    simbolo.setDecimalSeparator(',');
    simbolo.setGroupingSeparator('.');
	
	DecimalFormat formatDivi = new DecimalFormat("###,##0.0000",simbolo);
	
%>

<head>
</head>


<body>

	<div class="fondo2">
		<div class="centrado" style="width:90%">
		
			<br>
			<br>
			<div id="lineastmp">
			
			 	<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { %> 
			 		<table width="50%" border="0" class="TablaGrande" align="center">
			  	   		<tr class="fonts">
							<td align="center"><div class="cabecera input-b1">Make</div></td>
							<td align="center"><div class="cabecera input-b1">Model</div></td>
							<td align="center"><div class="cabecera input-b1">Unidades</div></td>
						</tr>
			  	  		<tr>
			  	  			<td colspan="13"><hr style="color: #E1E1E1"></td>	
			  	  		</tr>
			  		
			  		<%
				  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
				  			
				  			for (int i = 0; i < gridLine.rowCount(); i++){
				  				
				  				try {
				  					txmarcax = gridLine.getStringCell(i,"txmarcax");
				  					txmodelo = gridLine.getStringCell(i,"txmodelo");
				  					numunida = gridLine.getStringCell(i,"quantity");
								%>  	
							  		<tr>
										<td><div class="input-j"><%=txmarcax%></div></td>
										<td><div class="input-j"><%=txmodelo%></div></td>
										<td><div class="input-j"><%=numunida%></div></td>
									</tr>
							<%	} catch (Exception e) { 
									e.printStackTrace();
									System.out.println("ERROR recuperando linea "+ i); %>
									<tr>
										<td align="center" colspan="6" style="color:#FF0000">-- ERROR --</td>
									</tr>
							<%	}
							}
						}
					%>
					</table>
					<br>
					<br>
					<br>
					
					<form name="formStock" method="POST" action="/JLet/App">
						<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
						<input type="hidden" name="services"	value=""/>
						<input type="hidden" name="view" 		value="comercio/resultCreaEnvio.jsp"/>	
						<input type="hidden" name="listimei" 	value=""/>
						<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>							
						<br/>
						<br/>
					</form>
				<% } %>
			</div>
		</div>
	</div>
</body>