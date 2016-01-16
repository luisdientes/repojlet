<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid gridLine = null;
	Grid gridDivi = null;
	String txmensaj = "";
	String idemisor = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			txmensaj = io.getStringValue("txmensaj");
			gridLine = io.getGrid("gridLine");
			gridDivi = io.getGrid("gridDivi");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/validarEnvio.jsp");	
		}
	}		
	
	String imfixing = "1.0739";
	String fhfixing = "01/01/2014 14:23 DEMO";
	Double dimfixing = Double.parseDouble(imfixing);
	
	double porcmarg = 0.15;
	
	double totunits = 0;
	double totprchf = 0;
	double totmgchf = 0;
	double totprusd = 0;
	double totmgusd = 0;
	
	String strprchf = "";
	String strmgchf = "";
	String strttchf = "";
	String strprusd = "";
	String strmgusd = "";
	String strttusd = "";
	
%>


<head>
	<script>
	
		function codigoCorrecto(){
			
			return true;			
			
		}
	
		function validarEnvio(){
			
			if (document.formvaen.codeenvi.value.trim() != ""){
				if (codigoCorrecto(document.formvaen.codeenvi.value)){
					document.formvaen.submit();
				} else {
					alert('ERROR - El codigo es incorrecto. Ya se ha utilizado para otro envio.');
					document.formvaen.codeenvi.focus();
				}
			} else {
				alert('ERROR - Codigo de Envio Obligatorio,');
				document.formvaen.codeenvi.focus();
			}
			
		}
		
	</script>
</head>


<body>
	<div class="fondo2">
		<div class="centrado" style="width:95%">
			<form name="formvaen" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
				<input type="hidden" name="services"	value="GeneraXlsEnvioSrv"/>
				<input type="hidden" name="view" 		value="comercio/altaDetalleEnvio.jsp"/>
				<input type="hidden" name="tpproduc" 		value="T"/>
				<input type="hidden" name="idemisor" 		value="<%=idemisor%>"/>
				
				<table width="80%" border=0 class="TablaGrande" align="center">
					<tr class="fonts">
						<td width="10%">&nbsp;</td>
						<td width="20%" align="right">Código Envío&nbsp;&nbsp;</td>
						<td width="20%"><input type="text" maxlength="10" class="input-j" name="codeenvi" focus/></td>
						<td width="5%">&nbsp;</td>
						<td width="30%" align="right"><input type="text" class="input-j" name="imfixing" value="<%=imfixing%>" readOnly/></td>
						<td width="15%"><%=fhfixing%></td>
					</tr>
				</table>
				
				<br/>
				
				<table width="80%" border=0 class="TablaGrande" align="center">
					<tr class="fonts">
						<td width="10%">PRICE CHF</td>
						<td width="20%"><input type="text" class="input-j" name="totprchf" readOnly></td>
						<td width="10%">MARGIN CHF</td>
						<td width="20%"><input type="text" class="input-j" name="totmgchf" readOnly></td>
						<td width="10%">TOTAL CHF</td>
						<td width="30%"><input type="text" class="input-j" name="totttchf" readOnly></td>						
					</tr>
					<tr class="fonts">
						<td>PRICE USD</td>
						<td><input type="text" class="input-j" name="totprusd" readOnly></td>
						<td>MARGIN USD</td>
						<td><input type="text" class="input-j" name="totmgusd" readOnly></td>
						<td>TOTAL USD</td>
						<td><input type="text" class="input-j" name="totttusd" readOnly></td>						
					</tr>
				</table>

				<br/>
				
				<table width="100%" border=0 class="TablaGrande">
		  	   		<tr class="fonts">
						<td align="center">&nbsp;</td>
						<td align="center">Imei</td>
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
					</tr>
		  	  		<tr>
		  	  			<td colspan="13"><hr></td>	
		  	  		</tr>
		  		
		  		<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
			  			
			  			String idproenv = "";
		  				String milisegu = "";
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
		  			
		  			
			  			for (int i = 0; i < gridLine.rowCount(); i++){
			  				
			  				try {
			  					idproenv = gridLine.getStringCell(i,"idproenv");
			  					milisegu = gridLine.getStringCell(i,"milisegu");
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
			  						System.out.println("ESTE ES EL priceusd "+ priceusd);
			  					} catch (Exception e) {
			  						System.err.println(" ERROR recuperando Price CHF - "+ gridLine.getStringCell(i,"idproenv"));
			  					}
			  					
			  					//CALCULOS DE TOTALES
			  					try {
				  					totprchf += dpricechf;
				  					totmgchf += dpricechf * porcmarg;
				  					totprusd += dpriceusd;
				  					totmgusd += dpriceusd * porcmarg;
				  					totunits++;
			  					} catch (Exception e) {
			  						System.err.println(" ERROR GRAVE - No se ha incluido un valor al calculo: "+ gridLine.getStringCell(i,"idproenv"));
			  					}
			  					
			  					
			  					pricechf = format2d.format(dpricechf);
			  					priceusd = format2d.format(dpriceusd);
			  					
							%>  	
						  		<tr>
						  		<td><div class="input-j">&nbsp;</div></td>
								  	<td><div class="input-j"><%=imeicode%></div></td>
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
								</tr>
						<%	} catch (Exception e) { 
								e.printStackTrace();
								System.out.println("ERROR recuperando linea "+ i); %>
								<tr>
									<td align="center" colspan="6" style="color:#FF0000">-- ERROR --</td>
								</tr>
						<%	}
						} 
			  			
			  			double totttchf = totprchf + totmgchf;
			  			double totttusd = totprusd + totmgusd;
			  			
			  			strprchf = format2d.format(totprchf);
			  			strmgchf = format2d.format(totmgchf);
			  			strttchf = format2d.format(totttchf);
			  			strttusd = format2d.format(totprusd);
			  			strmgusd = format2d.format(totmgusd);
			  			strttusd = format2d.format(totttusd);
			  			
		  			} %>			
				</table>						
		</table>
		
		<script>
			document.formvaen.totprchf.value = "<%=strprchf%>";
			document.formvaen.totmgchf.value = "<%=strmgchf%>";
			document.formvaen.totttchf.value = "<%=strttchf%>";
			document.formvaen.totprusd.value = "<%=totprusd%>";
			document.formvaen.totmgusd.value = "<%=strmgusd%>";
			document.formvaen.totttusd.value = "<%=strttusd%>";
		</script>
		<br>
		<br>
		<br>
			<table width="100%" align="center">
				<tr>
				 	<td align="center">
						<a class="boton" onClick="validarEnvio()"> Validar Y Generar Excel</a>
					</td>
				</tr>
			</table>

		
	</form>
</body>