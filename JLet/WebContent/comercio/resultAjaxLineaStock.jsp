<%@ include file="/common/jsp/include_ajax.jsp"%>


<%

	String txmensaj = "";
	Grid gridLine = null;
			
	String imeicode = ""; 
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
			//txmensaj = io.getStringValue("txmensaj");
			gridLine = io.getGrid("gridLine");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ajaxok factura/index.jsp");	
		}
	}
		
%>
	<div id="lineastmp">
		  
  		<table width="100%" border=0 class="">
  	   		<tr class="fonts">
				<td align="center" class="input-b1">>&nbsp;</td>
				<td align="center" class="input-b1">Imei</td>
				<td align="center" class="input-b1">Make</td>
				<td align="center" class="input-b1">Model</td>
				<td align="center" class="input-b1">Colour</td>
				<td align="center" class="input-b1">Price CHF</td>
				<td align="center" class="input-b1">Price USD</td>
				<td align="center" class="input-b1">Provider</td>
				<td align="center" class="input-b1">Buyer</td>
				<td align="center" class="input-b1">Funding</td>
				<td align="center" class="input-b1">Box</td>
				<td align="center" class="input-b1">USB</td>
				<td align="center" class="input-b1">Category</td>
				<td align="center">&nbsp;</td>
			</tr>
  	  		<tr>
  	  			<td colspan="13"><hr></td>	
  	  		</tr>
  		
  		<%
  		
	  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
	  			
	  			for (int i = 0; i < gridLine.rowCount(); i++){
	  				
	  				try {
	  					idlineax = gridLine.getStringCell(i,"idstockx");
	  					imeicode = gridLine.getStringCell(i,"imeicode");
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
				  			<td><div class="fonts6"><%=idlineax%></div></td>
						  	<td><div class="fonts6"><%=imeicode%></div></td>
							<td><div class="fonts6"><%=txmarcax%></div></td>
							<td><div class="fonts6"><%=txmodelo%></div></td>
							<td><div class="fonts6"><%=idcolorx%></div></td>
							<td><div class="fonts6"><%=pricechf%></div></td>
							<td><div class="fonts6"><%=priceusd%></div></td>
							<td><div class="fonts6"><%=txprovid%></div></td>
							<td><div class="fonts6"><%=txbuyerx%></div></td>
							<td><div class="fonts6"><%=txfundin%></div></td>
							<td><div class="fonts6"><%=withboxx%></div></td>
							<td><div class="fonts6"><%=withusbx%></div></td>
							<td><div class="fonts6"><%=idcatego%></div></td>
							<td width="5%"><img src="common/img/varios/delete.jpg" width=20 height=20 style="cursor:pointer" onclick="borraLinea('<%=idlineax%>')"></td>
						</tr>
				<%	} catch (Exception e) { 
						e.printStackTrace();
						System.out.println("ERROR recuperando linea "+ i); %>
						<tr>
							<td align="center" colspan="6" style="color:#FF0000">-- ERROR --</td>
						</tr>
				<%	}
				} %>
	  			
  				<table width="100%" align="center">
						<tr>
							<td align="center">
								<a class="boton" onClick="validarEnvio()">Validar Stock</a>
							</td>
						</tr>
				</table>
			<% } %>			
		</table>	
		<br/>
		<br/>
  	</div>		
  	