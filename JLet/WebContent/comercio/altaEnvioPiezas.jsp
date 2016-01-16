<%@ include file="/common/jsp/include.jsp"%>

<head>

	<script type="text/javascript" src="comercio/js/altaEnvio.js"/></script>

<%

	String txmensaj = "";
	String imptotal = "";
	Grid gridLine = null; 
	Grid gridDivi = null; 
	Grid gridColo = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			txmensaj = io.getStringValue("txmensaj");
			gridLine = io.getGrid("gridLine");		
			gridColo = io.getGrid("gridColo");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/altaDatosEnvio.jsp "+ e.getMessage());	
		}
	}
	
	
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    simbolo.setDecimalSeparator(',');
    simbolo.setGroupingSeparator('.');
	
	DecimalFormat formatDivi = new DecimalFormat("###,##0.0000",simbolo);
	
%>

	<script>
	
		function init(){
						
			<% for (int i = 0; i < 3; i++){ %>
			
				<%	if (i == 0){ %>
						<% try{ 
							double fxusdeur = Double.parseDouble(gridDivi.getStringCell(i,"fixingxx")); %>
							document.simulado.ctusdeur.value = '<%=formatDivi.format(fxusdeur)%>';
						<% } catch (Exception e){ 
								System.err.println("- ERROR - Recuperando Fixing USDEUR"); %>
							document.simulado.ctusdeur.value = '- ERROR -';
						<% } %>
						
				<%	} else if (i == 1){ %>
						<% try{ 
							double fxusddop = Double.parseDouble(gridDivi.getStringCell(i,"fixingxx")); %>
							document.simulado.ctusddop.value = '<%=formatDivi.format(fxusddop)%>';
						<% } catch (Exception e){ 
							System.err.println("- ERROR - Recuperando Fixing USDDOP"); %>
							document.simulado.ctusddop.value = '- ERROR -';
						<% } %>
				<%	} else if (i == 2){ %>
						<% try{ 
							double fxusdchf = Double.parseDouble(gridDivi.getStringCell(i,"fixingxx")); %>
							document.simulado.ctusdchf.value = '<%=formatDivi.format(fxusdchf)%>';
						<% } catch (Exception e){ 
							System.err.println("- ERROR - Recuperando Fixing USDCHF"); %>
							document.simulado.ctusdchf.value = '- ERROR -';
						<% } %>
				<% } %>
						
			<% } %>	
			
			document.simulado.imptotal.value = "<%=imptotal%>"
			
		}
		
	</script>
	
</head>


<body>

	<div class="fondo2">
	<div class="nompanta" >Alta Piezas</div>
		<div class="centrado" style="width:90%">
			<form name="formpedi" method="POST" action="">
				<table border="0" width="98%" align="center" border="1">
					<tr class="fonts">
						<td align="center" class="input-b1">&nbsp;</td>
						<td align="center" class="input-b1">Quantity</td>
						<td align="center" class="input-b1">Model</td>
						<td align="center" class="input-b1">Colour</td>
						<td align="center" class="input-b1">Price USD</td>
						<td align="center" class="input-b1">% Benef</td>
						<td align="center" class="input-b1">Imp. Benef</td>
						<td align="center" class="input-b1">Provider</td>
						<td align="center" class="input-b1">Buyer</td>
						<td align="center" class="input-b1">Funding</td>
						<td align="center" class="input-b1">Quality</td>
					</tr>
					<tr>
						<td align="center" class="input-m"><input type="checkbox" class="input-m" style="border:none" id="cbblocked" name="cbblocked" onclick="checkBlock(this.checked)"/></td>
						<td align="center" class="input-m">&nbsp;</td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijotxmodelo"/></td>
						<td align="center" class="input-m"><select name="fijoidcolorx" id="fijoidcolorx" class="input-m" style="border:none">
												<option value="0">&nbsp</option>
											<%
											 if ((gridColo != null) && (gridColo.rowCount() > 0)) { 
												for(int i=0;i<gridColo.rowCount();i++){
													String idcolorx = gridColo.getStringCell(i, "idcolorx");
													String txnombre = gridColo.getStringCell(i, "txnombre");
												%>
												<option value="<%=idcolorx %>"><%=txnombre %></option>
												<%}
											  }
												%>
											</select>
						</td>		
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijopriceusd" size="3"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijomargbene" size="3" onChange="cambiaMargBenef('fijo',this.value)"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijoimpbenef" size="3" onChange="cambiaImpBenef('fijo',this.value)"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijotxprovid" size="2"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijotxbuyerx" size="2"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijotxfundin" size="2"/></td>
						<td align="center" class="input-m">
							<select name="fijoidcatego" id="fijoidcatego">
								<option value="OR"> Original </option>
								<option value="HC"> High Copy </option>
								<option value="CP"> Copy </option>
							</select>
						</td>
					</tr>	
					<tr>
						<td>&nbsp;</td>
						<td colspan="12"><hr/></td>						
					</tr>
					<tr>
						<td>&nbsp;</td>                                       				        
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="quantity"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="txmodelo"/></td>       
						<td align="center" class="input-m">
									<select name="idcolorx" id="colorid" class="input-m" style="border:none">
												<option value="0">&nbsp</option>
											<%
											 if ((gridColo != null) && (gridColo.rowCount() > 0)) { 
												for(int i=0;i<gridColo.rowCount();i++){
													String idcolorx = gridColo.getStringCell(i, "idcolorx");
													String txnombre = gridColo.getStringCell(i, "txnombre");
												%>
												<option value="<%=idcolorx %>"><%=txnombre %></option>
												<%}
											  }
												%>
											</select>
								       
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="price" size="3"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="margbene" size="3" onChange="cambiaMargBenef('valor',this.value)"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="impbenef" size="3" onChange="cambiaImpBenef('valor',this.value)"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="txprovid" size="2"/></td>          
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="txbuyerx" size="2"/></td>          
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="txfundin" size="2"/></td>          
						<td align="center" class="input-m">
								<select name="idcatego" id="categoid" class="input-m" style="border:none">
									<option value="OR"> Original </option>
									<option value="HC"> High Copy </option>
									<option value="CP"> Copy </option>
								</select></td>
						</td> 
					</tr>
					
				</table>
					<table width="100%" align="center">
								<tr>
									<td align="center">
										<a class="boton" onClick="validar()">Aceptar</a>
								</td>
							</tr>
					</table>
			 
			</form>
			<div id="lineastmp">
			
			 	<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { %> 
			 		<table width="100%" border="0" class="TablaGrande">
			  	   		<tr class="fonts">
							<td align="center">&nbsp;</td>
							<td align="center">Quantity</td>
							<td align="center">Model</td>
							<td align="center">Colour</td>
							<td align="center">Price USD</td>
							<td align="center">% Benef</td>
							<td align="center">Imp. Benef</td>
							<td align="center">Provider</td>
							<td align="center">Buyer</td>
							<td align="center">Funding</td>
							<td align="center">Quality</td>
							<td align="center">&nbsp;</td>
						</tr>
			  	  		<tr>
			  	  			<td colspan="13"><hr style="color: #E1E1E1"></td>	
			  	  		</tr>
			  		
			  		<%
			  		
				  		String quantity = ""; 
				  		String txmodelo = ""; 
				  		String idcolorx = ""; 
				  		String priceusd = ""; 
				  		String margbene = "";
				  		String impbenef = "";
				  		String txprovid = ""; 
				  		String txbuyerx = ""; 
				  		String txfundin = ""; 
				  		String idcatego = "";
				  		String idlineax = ""; 
			  		
				  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
				  			
				  			for (int i = 0; i < gridLine.rowCount(); i++){
				  				
				  				try {
				  					idlineax = gridLine.getStringCell(i,"idproenv");
				  					quantity = gridLine.getStringCell(i,"quantity");
				  					txmodelo = gridLine.getStringCell(i,"txmodelo");
				  					idcolorx = gridLine.getStringCell(i,"idcolorx");	  					
				  					priceusd = gridLine.getStringCell(i,"priceusd");	  					
				  					margbene = gridLine.getStringCell(i,"porcmarg");
				  					txprovid = gridLine.getStringCell(i,"txprovid");
				  					txbuyerx = gridLine.getStringCell(i,"txbuyerx");
				  					txfundin = gridLine.getStringCell(i,"txfundin");
				  					idcatego = gridLine.getStringCell(i,"idcatego");
				
				  					//CAST A DOUBLE PARA DAR FORMATO
				  					double dpriceusd = 0;
				  					double dmargbene = 0;
				  					
				  					try {
				  						dpriceusd = Double.parseDouble(priceusd);
				  					} catch (Exception e) {
				  						System.err.println(" ERROR recuperando Price usd - "+ gridLine.getStringCell(i,"idproenv"));
				  					}
				  					
				  					try {
				  						dmargbene = Double.parseDouble(margbene);
				  					} catch (Exception e) {
				  						System.err.println(" ERROR recuperando Price USD - "+ gridLine.getStringCell(i,"idproenv"));
				  					}
				  					
				  					priceusd = format2d.format(dpriceusd);
				  					margbene = format2d.format(dmargbene);
				  					
								%>  	
							  		<tr>
							  		<td><div class="input-j">&nbsp;</div></td>
									  	<td><div class="input-j"><%=quantity%></div></td>
										<td><div class="input-j"><%=txmodelo%></div></td>
										<td><div class="input-j"><%=idcolorx%></div></td>
										<td><div class="input-j"><%=priceusd%></div></td>
										<td><div class="input-j"><%=margbene%></div></td>
										<td><div class="input-j"><%=impbenef%></div></td>
										<td><div class="input-j"><%=txprovid%></div></td>
										<td><div class="input-j"><%=txbuyerx%></div></td>
										<td><div class="input-j"><%=txfundin%></div></td>
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
							}
						}
					%>
					</table>
					<form name="formvali" method="POST" action="/JLet/App">
						<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
						<input type="hidden" name="services"	value="ValidarEnvioSrv"/>
						<input type="hidden" name="view" 		value="comercio/validarEnvioPi.jsp"/>
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
				<% } %>
			</div>
			
		</div>
	</div>
</body>