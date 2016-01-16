<%@ include file="/common/jsp/include.jsp"%>

<%

	String imptotal = "";
	String cddivisa = "";
	String divsimbo = "";
	String fixingxx = "";
	String fhfixing = "";
	String idemisor = null; 
	Grid gridLine = null; 
	Grid gridColo = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			cddivisa = io.getStringValue("cddivisa");
			fixingxx = io.getStringValue("fixingxx");
			fhfixing = io.getStringValue("fhfixing");
			divsimbo = io.getStringValue("divsimbo");
			gridLine = io.getGrid("gridLine");
			gridColo = io.getGrid("gridColo");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/altaStock.jsp "+ e.getMessage());	
		}
	}
	
	
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    simbolo.setDecimalSeparator(',');
    simbolo.setGroupingSeparator('.');
	
	DecimalFormat formatDivi = new DecimalFormat("###,##0.0000",simbolo);
	
%>

<head>
	
	<script type="text/javascript" src="comercio/js/altaStock.js"/></script>

</head>


<body>

	<div class="fondo2">
	<div class="nompanta" >Alta Stock</div>
		<div class="centrado" style="width:100%; margin-left:13%">
			<form name="formpedi" method="POST" action="">
				<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
				<input type="hidden" name="ctusdeur"/>
				<input type="hidden" name="ctusddop"/>	
				<input type="cddivisa" name="<%=cddivisa%>"/>
				
				<table border="0" width="95%" align="right" width="50%">
					<tr class="fonts">	
						<td width="30%" class="input-b1">Fixing 1 USD = <input class="fonts6" type="text" name="fixingxx" value='<%=formatDivi.format(Double.parseDouble(fixingxx))%>'/> &nbsp;<%=divsimbo%></td>
						<td width="70%">&nbsp;</td>
					</tr>
				</table>
				
				<br/>
				<br/>
				
				<table border="0" width="98%" align="center" border="1">
					<tr class="fonts">
						<td align="center" class="input-b1">&nbsp;</td>
						<td align="center" class="input-b1">Imei</td>
						<td align="center" class="input-b1">Make</td>
						<td align="center" class="input-b1">Model</td>
						<td align="center" class="input-b1">Colour</td>
						<td align="center" class="input-b1">Price <%=divsimbo%></td>
						<td align="center" class="input-b1">% Benef</td>
						<td align="center" class="input-b1">Imp. Benef</td>
						<td align="center" class="input-b1">Provider</td>
						<td align="center" class="input-b1">Buyer</td>
						<td align="center" class="input-b1">Funding</td>
						<td align="center" class="input-b1">Box</td>
						<td align="center" class="input-b1">USB</td>
						<td align="center" class="input-b1">Category</td>
					</tr>
					<tr>
						<td align="center" class="input-m"><input type="checkbox" class="input-m" style="border:none"  id="cbblocked" name="cbblocked" onclick="checkBlock(this.checked)"/></td>
						<td align="center" class="input-m">&nbsp;</td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none"  id="fijotxmarcax" name="fijotxmarcax"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none"  name="fijotxmodelo"/></td>
						<td align="center" class="input-m">
							<select name="fijoidcolorx" id="fijoidcolorx" class="input-m" style="border:none" >
								<option value="0">&nbsp</option>
								<%
									if ((gridColo != null) && (gridColo.rowCount() > 0)) { 
										for (int i=0;i<gridColo.rowCount();i++){
											String idcolorx = gridColo.getStringCell(i, "idcolorx");
											String txnombre = gridColo.getStringCell(i, "txnombre");
										%>
											<option value="<%=idcolorx %>"><%=txnombre %></option>
										<% }
										}	%>
							</select>
						</td>		
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijopricechf" size="3"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijomargbene" size="3" onChange="cambiaMargBenef('fijo',this.value)"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijoimpbenef" size="3" onChange="cambiaImpBenef('fijo',this.value)"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijotxprovid" size="2"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijotxbuyerx" size="2"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="fijotxfundin" size="2"/></td>
						<td align="center" class="input-m"><input type="checkbox" name="fijowithboxx" value="S"/></td>
						<td align="center" class="input-m"><input type="checkbox" name="fijowithusbx" value="S"/></td>
						<td align="center" class="input-m" >
							<select name="fijoidcatego" id="fijoidcatego" class="input-m" style="border:none">
								<option value="N"> Nuevo </option>
								<option value="N7"> Nuevo 7 dias </option>
								<option value="R"> Refurbished </option>
								<option value="A"> Usado Clase A </option>
								<option value="B"> Usado Clase B </option>
								<option value="C"> Usado Clase C </option>
							</select></td>
					</tr>	
					<tr>
						<td>&nbsp;</td>
						<td colspan="12"><hr/></td>						
					</tr>
					<tr>
						<td>&nbsp;</td>                                       				        
						<td align="center" class="input-m"><input type="text" name="imeicode" style="border:none" onchange="validar();"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="txmarcax"/></td>          
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="txmodelo"/></td>       
						<td align="center" class="input-m">
									<select name="idcolorx" id="colorid" class="input-m" style="border:none" >
										<option value="0">&nbsp</option>
										<%
										if ((gridColo != null) && (gridColo.rowCount() > 0)) { 
											for(int i=0;i<gridColo.rowCount();i++){
												String idcolorx = gridColo.getStringCell(i, "idcolorx");
												String txnombre = gridColo.getStringCell(i, "txnombre");
										%>
											<option value="<%=idcolorx %>"><%=txnombre %></option>
										<% }
										}
										%>
									</select>
						       
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="pricechf" size="3"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="margbene" size="3" onChange="cambiaMargBenef('valor',this.value)"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="impbenef" size="3" onChange="cambiaImpBenef('valor',this.value)"/></td>
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="txprovid" size="2"/></td>          
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="txbuyerx" size="2"/></td>          
						<td align="center" class="input-m"><input type="text" class="input-m" style="border:none" name="txfundin" size="2"/></td>          
						<td align="center" class="input-m"><input type="checkbox" class="input-m" style="border:none" name="withboxx"  value='S'/></td> 
						<td align="center" class="input-m"><input type="checkbox" class="input-m" style="border:none" name="withusbx" value='S'/></td> 
						<td align="center" class="input-m">
							<select name="idcatego" id="categoid" class="input-m" style="border:none">
								<option value="N"> Nuevo </option>
								<option value="N7"> Nuevo 7 dias </option>
								<option value="R"> Refurbished </option>
								<option value="A"> Usado Clase A </option>
								<option value="B"> Usado Clase B </option>
								<option value="C"> Usado Clase C </option>
							</select>
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
							<td align="center" class="input-b1">&nbsp;</td>
							<td align="center" class="input-b1">Imei</td>
							<td align="center" class="input-b1">Make</td>
							<td align="center" class="input-b1">Model</td>
							<td align="center" class="input-b1">Colour</td>
							<td align="center" class="input-b1">Price CHF</td>
							<td align="center" class="input-b1">% Benef</td>
							<td align="center" class="input-b1">Imp. Benef</td>
							<td align="center" class="input-b1">Provider</td>
							<td align="center" class="input-b1">Buyer</td>
							<td align="center" class="input-b1">Funding</td>
							<td align="center" class="input-b1">Box</td>
							<td align="center" class="input-b1">USB</td>
							<td align="center" class="input-b1">Category</td>
							<td align="center" class="input-b1">&nbsp;</td>
						</tr>
			  	  		<tr>
			  	  			<td colspan="13"><hr style="color: #E1E1E1"></td>	
			  	  		</tr>
			  		
			  		<%
			  		
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
			  		
				  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
				  			
				  			for (int i = 0; i < gridLine.rowCount(); i++){
				  				
				  				try {
				  					idlineax = gridLine.getStringCell(i,"idstockx");
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
							  			<td><div class="fonts6">--<%=idlineax%>--</div></td>
									  	<td><div class="fonts6"><%=imeicode%></div></td>
										<td><div class="fonts6"><%=txmarcax%></div></td>
										<td><div class="fonts6"><%=txmodelo%></div></td>
										<td><div class="fonts6"><%=idcolorx%></div></td>
										<td><div class="fonts6"><%=pricechf%></div></td>
										<td><div class="fonts6"><%=margbene%></div></td>
										<td><div class="fonts6"><%=impbenef%></div></td>
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
							}
						}
					%>
					</table>
						<table width="100%" align="center">
							<tr>
								<td align="center">
									<a class="boton" onClick="validarEnvio()">Validar Stock</a>
								</td>
							</tr>
						</table>
					
				<% } %>
			</div>
		</div>
		 <form name="formvali" method="POST" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
			<input type="hidden" name="services"	value="ValidarStockLSrv"/>
			<input type="hidden" name="view" 		value="comercio/resultValidaStock.jsp"/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
			
			<br/>
			<br/>
	   </form>
	</div>
</body>