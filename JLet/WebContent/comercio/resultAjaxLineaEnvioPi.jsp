<%@ include file="/common/jsp/include_ajax.jsp"%>


<%

	String txmensaj = "";
	Grid gridLine = null;
			
	String quantity = ""; 
	String txmarcax = ""; 
	String txmodelo = ""; 
	String idcolorx = ""; 
	String pricechf = ""; 
	String priceusd = ""; 
	String txprovid = ""; 
	String txbuyerx = ""; 
	String txfundin = ""; 
	String withboxx = ""; 
	String withusbx = ""; 
	String idcatego = ""; 
	String cdestado = "";
	String idlineax = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			txmensaj = io.getStringValue("txmensaj");
			gridLine = io.getGrid("gridLine");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ajaxok factura/index.jsp");	
		}
	}
		
%>
	<div id="lineastmp">
		  
  		<table width="100%" border=0 class="TablaGrande">
  	   		<tr class="fonts">
				<td align="center">&nbsp;</td>
				<td align="center">Quantity</td>
				<td align="center">Make</td>
				<td align="center">Model</td>
				<td align="center">Colour</td>
				<td align="center">Price CHF</td>
				<td align="center">Price USD</td>
				<td align="center">Provider</td>
				<td align="center">Buyer</td>
				<td align="center">Funding</td>
				<td align="center">Box</td>
				<td align="center">USB</td>
				<td align="center">Category</td>
				<td align="center">&nbsp;</td>
			</tr>
  	  		<tr>
  	  			<td colspan="13"><hr></td>	
  	  		</tr>
  		
  		<%
  		
	  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
	  			
	  			for (int i = 0; i < gridLine.rowCount(); i++){
	  				
	  				try {
	  					idlineax = gridLine.getStringCell(i,"idproenv");
	  					quantity = gridLine.getStringCell(i,"quantity");
	  					txmarcax = gridLine.getStringCell(i,"txmarcax");
	  					txmodelo = gridLine.getStringCell(i,"txmodelo");
	  					idcolorx = gridLine.getStringCell(i,"idcolorx");	  					
	  					pricechf = gridLine.getStringCell(i,"pricechf");	  					
	  					priceusd = gridLine.getStringCell(i,"priceusd");
	  					txprovid = gridLine.getStringCell(i,"txprovid");
	  					txbuyerx = gridLine.getStringCell(i,"txbuyerx");
	  					txfundin = gridLine.getStringCell(i,"txfundin");
	  					withboxx = gridLine.getStringCell(i,"withboxx");
	  					withusbx = gridLine.getStringCell(i,"withusbx");
	  					idcatego = gridLine.getStringCell(i,"idcatego");
	
	  					//CAST A DOUBLE PARA DAR FORMATO
	  					double dpricechf = 0;
	  					double dpriceusd = 0;
	  					
	  					try {
	  						dpricechf = Double.parseDouble(pricechf);
	  					} catch (Exception e) {
	  						System.err.println(" ERROR recuperando Price CHF - "+ gridLine.getStringCell(i,"idproenv"));
	  					}
	  					
	  					try {
	  						dpriceusd = Double.parseDouble(priceusd);
	  					} catch (Exception e) {
	  						System.err.println(" ERROR recuperando Price CHF - "+ gridLine.getStringCell(i,"idproenv"));
	  					}
	  					
	  					pricechf = format2d.format(dpricechf);
	  					priceusd = format2d.format(dpriceusd);
	  					
					%>  	
				  		<tr>
				  		<td><div class="input-j">&nbsp;</div></td>
						  	<td><div class="input-j"><%=quantity%></div></td>
							<td><div class="input-j"><%=txmarcax%></div></td>
							<td><div class="input-j"><%=txmodelo%></div></td>
							<td><div class="input-j"><%=idcolorx%></div></td>
							<td><div class="input-j"><%=pricechf%></div></td>
							<td><div class="input-j"><%=priceusd%></div></td>
							<td><div class="input-j"><%=txprovid%></div></td>
							<td><div class="input-j"><%=txbuyerx%></div></td>
							<td><div class="input-j"><%=txfundin%></div></td>
							<td><div class="input-j"><%=withboxx%></div></td>
							<td><div class="input-j"><%=withusbx%></div></td>
							<td><div class="input-j"><%=idcatego%></div></td>
							<td width="5%"><img src="common/img/varios/delete.jpg" width=20 height=20 style="cursor:pointer" onclick="borraLinea(<%=idlineax%>)"></td>
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
		
		<form name="formvali" method="POST" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
			<input type="hidden" name="services"	value="ValidarEnvioSrv"/>
			<input type="hidden" name="view" 		value="comercio/validarEnvio.jsp"/>
			<input type="hidden" name="tpproduc" 		value="PI"/>								
			
			<br/>
			<br/>
			<table width="100%" align="center">
				<tr>
					<td align="center">
						<a class="boton" onClick="validarEnvio()">Validar Envío</a>
					</td>
				</tr>
			</table>
		</form>
  	</div>		
  	