<%@ include file="/common/jsp/include.jsp"%>

<head>
	
	<script type="text/javascript" src="comercio/js/altaEnvio.js"/></script>
	<link href="/JLet/comercio/css/altaenvio.css" type="text/css" rel="stylesheet">

<%

	String txmensaj = "";
	String imptotal = "";
	String idemisor = "";
	Grid gridLine = null; 
	Grid gridDivi = null; 
	Grid gridColo = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			txmensaj = io.getStringValue("txmensaj");
			idemisor = io.getStringValue("idemisor");
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
	<style>

		
	</style>
	

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
	<div class="nompanta" >Alta envío</div>
		<div class="centrado" style="width:90%; margin-left:6%;">
			<form name="formpedi" method="POST" action="">
				<table border="0" width="98%" align="center" border="1">
					<tr class="fonts">
						<td align="center">&nbsp;</td>
						<td align="center" class="input-b1" style="height:22px;">Imei</td>
						<td align="center" class="input-b1" style="height:22px;">Make</td>
						<td align="center" class="input-b1" style="height:22px;">Model</td>
						<td align="center" class="input-b1" style="height:22px;">Colour</td>
						<td align="center" class="input-b1" style="height:22px;">Price CHF</td>
						<td align="center" class="input-b1" style="height:22px;">% Benef</td>
						<td align="center" class="input-b1" style="height:22px;">Imp. Benef</td>
						<td align="center" class="input-b1" style="height:22px;">Provider</td>
						<td align="center" class="input-b1" style="height:22px;">Buyer</td>
						<td align="center" class="input-b1" style="height:22px;">Funding</td>
						<td align="center" class="input-b1" style="height:22px;">Box</td>
						<td align="center" class="input-b1" style="height:22px;">USB</td>
						<td align="center" class="input-b1" style="height:22px;">Category</td>
					</tr>
					<tr>
						<td align="center"><input type="checkbox" id="cbblocked" name="cbblocked" onclick="checkBlock(this.checked)"/></td>
						<td align="center" class="input-m" style="height:22px;">&nbsp;</td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" id="fijotxmarcax" name="fijotxmarcax"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="fijotxmodelo"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><select class="input-m" style="height:22px; width:74px;border:none" name="fijoidcolorx" id="fijoidcolorx">
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
						</td>		
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="fijopricechf" size="3"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="fijomargbene" size="3" onChange="cambiaMargBenef('fijo',this.value)"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="fijoimpbenef" size="3" onChange="cambiaImpBenef('fijo',this.value)"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="fijotxprovid" size="2"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="fijotxbuyerx" size="2"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="fijotxfundin" size="2"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:10px; width:10px;border:none" type="checkbox" name="fijowithboxx" value="S"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:10px; width:10px;border:none" type="checkbox" name="fijowithusbx" value="S"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><select class="input-m" style="height:22px; width:74px;border:none" name="fijoidcatego" id="fijoidcatego">
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
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:374px;border:none" type="text" name="imeicode" onchange="validar();"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="txmarcax"/></td>          
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="txmodelo"/></td>       
						<td align="center" class="input-m" style="height:22px; width:74px;">
									<select name="idcolorx" id="colorid" class="input-m" style="height:22px; width:74px;">
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
								       
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="pricechf" size="3"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="margbene" size="3" onChange="cambiaMargBenef('valor',this.value)"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="impbenef" size="3" onChange="cambiaImpBenef('valor',this.value)"/></td>
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="txprovid" size="2"/></td>          
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="txbuyerx" size="2"/></td>          
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:22px; width:74px;border:none" type="text" name="txfundin" size="2"/></td>          
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:10px; width:10px;border:none" type="checkbox" name="withboxx"  value='S'/></td> 
						<td align="center" class="input-m" style="height:22px; width:74px;"><input class="input-m" style="height:10px; width:10px;border:none" type="checkbox" name="withusbx" value='S'/></td> 
						<td align="center" class="input-m" style="height:22px; width:74px;">
							<select name="idcatego" id="categoid" class="input-m" style="height:22px; width:74px;border:none">
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
							<a class="boton" style="font-family:arial; margin-top:35px;" onClick="validar()">Aceptar</a>
						</td>
					</tr>
				</table>
			 
			</form>
			
			<div id="lineastmp">
			
			 	<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { %> 
			 		<table width="100%" border="0" class="TablaGrande">
			  	   		<tr class="fonts">
							<td align="center" class="input-b1" style="height:22px;">&nbsp;</td>
							<td align="center" class="input-b1" style="height:22px;">Imei</td>
							<td align="center" class="input-b1" style="height:22px;">Make</td>
							<td align="center" class="input-b1" style="height:22px;">Model</td>
							<td align="center" class="input-b1" style="height:22px;">Colour</td>
							<td align="center" class="input-b1" style="height:22px;">Price CHF</td>
							<td align="center" class="input-b1" style="height:22px;">% Benef</td>
							<td align="center" class="input-b1" style="height:22px;">Imp. Benef</td>
							<td align="center" class="input-b1" style="height:22px;">Provider</td>
							<td align="center" class="input-b1" style="height:22px;">Buyer</td>
							<td align="center" class="input-b1" style="height:22px;">Funding</td>
							<td align="center" class="input-b1" style="height:22px;">Box</td>
							<td align="center" class="input-b1" style="height:22px;">USB</td>
							<td align="center" class="input-b1" style="height:22px;">Category</td>
							<td align="center">&nbsp;</td>
						</tr>
			  	  		<tr>
			  	  			<td colspan="14"><hr style="color: #E1E1E1"></td>	
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
				  					idlineax = gridLine.getStringCell(i,"idproenv");
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
							  			<td><div>&nbsp;</div></td>
									  	<td><div class="fonts6" style="height:22px; width:174px;"><%=imeicode%></div></td>
										<td><div class="fonts6" style="height:22px; width:90px;"><%=txmarcax%></div></td>
										<td><div class="fonts6" style="height:22px; width:90px;"><%=txmodelo%></div></td>
										<td><div class="fonts6" style="height:22px; width:74px;"><%=idcolorx%></div></td>
										<td><div class="fonts6" style="height:22px; width:74px;"><%=pricechf%></div></td>
										<td><div class="fonts6" style="height:22px; width:74px;"><%=margbene%></div></td>
										<td><div class="fonts6" style="height:22px; width:74px;"><%=impbenef%></div></td>
										<td><div class="fonts6" style="height:22px; width:90px;"><%=txprovid%></div></td>
										<td><div class="fonts6" style="height:22px; width:90px;"><%=txbuyerx%></div></td>
										<td><div class="fonts6" style="height:22px; width:90px;"><%=txfundin%></div></td>
										<td><div class="fonts6" style="height:22px; width:74px;"><%=withboxx%></div></td>
										<td><div class="fonts6" style="height:22px; width:74px;"><%=withusbx%></div></td>
										<td><div class="fonts6" style="height:22px; width:74px;"><%=idcatego%></div></td>
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
				
						<br/>
						<br/>
				<table width="100%" align="center">
					<tr>
						<td align="center">
							<a class="boton" onClick="validarEnvio()">Validar Envío</a>
						</td>
					</tr>
				</table>
			
					
				<% } %>
				
		
			</div>
			
				<form name="formvali" method="POST" action="/JLet/App">
						<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
						<input type="hidden" name="services"	value="ValidarEnvioSrv"/>
						<input type="hidden" name="view" 		value="comercio/validarEnvio.jsp"/>
						<input type="hidden" name="tpproduc" 		value="T"/>
						<input type="hidden" name="idemisor" 		value="<%=idemisor%>"/>
				</form>
	
		</div>
	</div>
</body>