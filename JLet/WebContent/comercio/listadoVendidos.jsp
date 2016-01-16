<%@ include file="/common/jsp/include.jsp"%>

<%

	String imptotal = "";
	Grid gridLine = null; 
	Grid gridDivi = null; 
	Grid gridColo = null;
	
	String imeicode = ""; 
	String txmarcax = ""; 
	String txmodelo = ""; 
	String idcolorx = ""; 
	String pricechf = ""; 
	String margbene = "";
	String impbenef = "";
	String txprovid = ""; 
	String txbuyerx = ""; 
	String txfundin = ""; 
	String withboxx = ""; 
	String withusbx = ""; 
	String idcatego = "";
	String idlineax = "";
	String idemisor = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gridLine = io.getGrid("gridLine");		
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listadoStock.jsp "+ e.getMessage());	
		}
	}
	
	
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    simbolo.setDecimalSeparator(',');
    simbolo.setGroupingSeparator('.');
	
	DecimalFormat formatDivi = new DecimalFormat("###,##0.0000",simbolo);
	
%>

<head>

<script>
	
	
</script>
	
</head>


<body>

	<div class="fondo2">
		<div class="centrado" style="width:90%">
								
			<div id="lineastmp">
			
			 	<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { %> 
			 		<table width="100%" border="0" class="TablaGrande">
			  	   		<tr class="fonts">
							<td align="center">&nbsp;</td>
							<td align="center">Cliente</td>
							<td align="center">Imei</td>
							<td align="center">Marca</td>
							<td align="center">Modelo</td>
							<td align="center">Colour</td>
							<td align="center">Precio CHF</td>
							<td align="center">Tasas</td>
							<td align="center">Total</td>
							<td align="center">Fecha Venta</td>
							<td align="center">Codigo Factura</td>							
						</tr>
			  	  		<tr>
			  	  			<td colspan="13"><hr style="color: #E1E1E1"></td>	
			  	  		</tr>
			  		
			  		<%
				  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
				  			
				  			for (int i = 0; i < gridLine.rowCount(); i++){
				  				
				  				try {
				  					idlineax = gridLine.getStringCell(i,"idstockx");
				  					imeicode = gridLine.getStringCell(i,"txclient");
				  					imeicode = gridLine.getStringCell(i,"imeicode");
				  					txmarcax = gridLine.getStringCell(i,"txmarcax");
				  					txmodelo = gridLine.getStringCell(i,"txmodelo");
				  					idcolorx = gridLine.getStringCell(i,"idcolorx");	  					
				  					pricechf = gridLine.getStringCell(i,"pricechf");	  					
				  					margbene = gridLine.getStringCell(i,"porcmarg");
				  					txprovid = gridLine.getStringCell(i,"txprovid");
				  					txbuyerx = gridLine.getStringCell(i,"txbuyerx");
				  					txfundin = gridLine.getStringCell(i,"txfundin");
				  					withboxx = gridLine.getStringCell(i,"withboxx");
				  					withusbx = gridLine.getStringCell(i,"withusbx");
				  					idcatego = gridLine.getStringCell(i,"idcatego");
				
				  					//CAST A DOUBLE PARA DAR FORMATO
				  					double dpricechf = 0;
				  					double dmargbene = 0;
				  					
				  					try {
				  						dpricechf = Double.parseDouble(pricechf);
				  					} catch (Exception e) {
				  						System.err.println(" ERROR recuperando Price CHF - "+ gridLine.getStringCell(i,"idproenv"));
				  					}
				  					
				  					try {
				  						dmargbene = Double.parseDouble(margbene);
				  					} catch (Exception e) {
				  						System.err.println(" ERROR recuperando Price USD - "+ gridLine.getStringCell(i,"idproenv"));
				  					}
				  					
				  					pricechf = format2d.format(dpricechf);
				  					margbene = format2d.format(dmargbene);
				  					
								%>  	
							  		<tr>
							  			<td><div class="input-j"><input type="checkbox" id="<%=imeicode %>" value="<%=imeicode%>"> </div></td>
									  	<td><div class="input-j"><%=imeicode%></div></td>
										<td><div class="input-j"><%=txmarcax%></div></td>
										<td><div class="input-j"><%=txmodelo%></div></td>
										<td><div class="input-j"><%=idcolorx%></div></td>
										<% if (!idemisor.equals("1") && !idemisor.equals("5")){ %>
											<td><div class="input-j" id="pricechf"><%=pricechf%></div></td>
											<td><div class="input-j" id="benefici"><%=margbene%></div></td>
											<td><div class="input-j" id="impbenef"><%=impbenef%></div></td>
											<td><div class="input-j" id="txprovid"><%=txprovid%></div></td>
											<td><div class="input-j" id="txbuyerx"><%=txbuyerx%></div></td>
											<td><div class="input-j" id="txfundin"><%=txfundin%></div></td>
										<% } %>
										<td><div class="input-j"><%=withboxx%></div></td>
										<td><div class="input-j"><%=withusbx%></div></td>
										<td><div class="input-j"><%=idcatego%></div></td>
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
					
					<table width="60%" align="center">
						<tr>
							<td align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/bot-factura.png" onClick=""></td>
							<td width=5%>&nbsp;</td>
							<td align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/bot-envio.png" onClick="creaEnvio()"></td>
							<td width=5%>&nbsp;</td>
							<td align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/bot-deposito.png" onClick=""></td>
						</tr>
						<tr>
							<td align="center" class="input-txt-b">Generar Factura</td>
							<td width=5%>&nbsp;</td>
							<td align="center" class="input-txt-b">Crear Envío</td>
							<td width=5%>&nbsp;</td>
							<td align="center" class="input-txt-b">Depósito</td>
						</tr>
					</table>
					
					
					
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