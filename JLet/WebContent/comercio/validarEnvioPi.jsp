<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid gridLine = null;
	Grid gridDivi = null;
	String txmensaj = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gridLine = io.getGrid("gridLine");
			gridDivi = io.getGrid("gridDivi");	
			txmensaj = io.getStringValue("txmensaj");		
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/validarEnvio.jsp");	
		}
	}		
	
	String imfixing = "43.05";
	String fhfixing = "01/01/2014 14:23 DEMO";
	Double dimfixing = Double.parseDouble(imfixing);
	
	double porcmarg = 0.15;
	
	double totunits = 0;
	double totprdop = 0;
	double totmgdop = 0;
	double totprusd = 0;
	double totmgusd = 0;
	
	
	String strunits = "";
	String strprdop = "";
	String strmgdop = "";
	String strttdop = "";
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
				<input type="hidden" name="services"	value="GeneraXlsEnvioPiezasSrv"/>
				<input type="hidden" name="view" 		value="comercio/listEnvio.jsp"/>
				<input type="hidden" name="tpproduc" 		value="PI"/>
				
				<table width="80%" border=0 class="TablaGrande" align="center">
					<tr class="fonts">
						<td width="10%">&nbsp;</td>
						<td width="20%" align="right">Código Envío&nbsp;&nbsp;</td>
						<td width="20%"><input type="text" maxlength="10" class="input-j" name="codeenvi" focus/></td>
						<td width="5%">&nbsp;</td>
						<td width="30%" align="right">Cotización USD - DOP&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="input-j" name="imfixing" value="<%=imfixing%>" readOnly/></td>
						<td width="15%"><%=fhfixing%></td>
					</tr>
				</table>
				
				<br/>
				
				<table width="80%" border=0 class="TablaGrande" align="center"><tr class="fonts">
					<tr class="fonts">
						<td>Num. Unidades</td>
						<td><input type="text" class="input-j" name="numunits" readOnly></td>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr class="fonts">
						<td>PRICE USD</td>
						<td><input type="text" class="input-j" name="totprusd" readOnly></td>
						<td>MARGIN USD</td>
						<td><input type="text" class="input-j" name="totmgusd" readOnly></td>
						<td>TOTAL USD</td>
						<td><input type="text" class="input-j" name="totttusd" readOnly></td>						
					</tr>
					<tr class="fonts">
						<td width="10%">PRICE DOP</td>
						<td width="20%"><input type="text" class="input-j" name="totprdop" readOnly></td>
						<td width="10%">MARGIN DOP</td>
						<td width="20%"><input type="text" class="input-j" name="totmgdop" readOnly></td>
						<td width="10%">TOTAL DOP</td>
						<td width="30%"><input type="text" class="input-j" name="totttdop" readOnly></td>						
					</tr>
				</table>

				<br/>
				
				<table width="100%" border=0 class="TablaGrande">
		  	   		<tr class="fonts">
						<td align="center">&nbsp;</td>
						<td align="center">Quantity</td>
						<td align="center">Make</td>
						<td align="center">Model</td>
						<td align="center">Colour</td>
						<td align="center">Price USD</td>
						<td align="center">Total</td>
						<td align="center">Provider</td>
						<td align="center">Buyer</td>
						<td align="center">Funding</td>
						<td align="center">Quality</td>
					</tr>
		  	  		<tr>
		  	  			<td colspan="13"><hr></td>	
		  	  		</tr>
		  		
		  		<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
			  			
			  			String idproenv = "";
		  				String milisegu = "";
			  			String quantity = ""; 
				  		String txmarcax = ""; 
				  		String txmodelo = ""; 
				  		String idcolorx = ""; 
				  		String priceusd = ""; 
				  		String totalusd = ""; 
				  		String txprovid = ""; 
				  		String txbuyerx = ""; 
				  		String txfundin = ""; 
				  		String idcatego = "";
				  		 
				  		String pricedop = "";
		  			
		  			
			  			for (int i = 0; i < gridLine.rowCount(); i++){
			  				
			  				try {
			  					idproenv = gridLine.getStringCell(i,"idproenv");
			  					milisegu = gridLine.getStringCell(i,"milisegu");
			  					quantity = gridLine.getStringCell(i,"quantity");
			  					txmarcax = gridLine.getStringCell(i,"txmarcax");
			  					txmodelo = gridLine.getStringCell(i,"txmodelo");
			  					idcolorx = gridLine.getStringCell(i,"idcolorx");	  					
			  					priceusd = gridLine.getStringCell(i,"priceusd");
			  					txprovid = gridLine.getStringCell(i,"txprovid");
			  					txbuyerx = gridLine.getStringCell(i,"txbuyerx");
			  					txfundin = gridLine.getStringCell(i,"txfundin");
			  					idcatego = gridLine.getStringCell(i,"idcatego");
			
			  					//CAST A DOUBLE PARA DAR FORMATO
			  					String pricetot = "";
			  					double dtotalusd = 0;
			  					
			  					double dpriceusd = 0;
			  					double dquantity = 0;
			  					double dpricedop = 0;
			  					
			  					try {
			  						dquantity = Double.parseDouble(quantity);
			  					} catch (Exception e) {
			  						System.err.println(" ERROR recuperando Quantity - "+ gridLine.getStringCell(i,"idproenv"));
			  					}
			  					
			  					try {
			  						dpriceusd = Double.parseDouble(priceusd);
			  					} catch (Exception e) {
			  						System.err.println(" ERROR recuperando Price USD - "+ gridLine.getStringCell(i,"idproenv"));
			  					}
			  					
			  					//CALCULOS DE TOTALES
			  					try {
			  						dtotalusd = dquantity * dpriceusd;
				  					totprusd += dtotalusd;
				  					totmgusd += dtotalusd * porcmarg;
			  						
			  						dpricedop = (dquantity * dpriceusd * dimfixing);
			  						System.out.println("ESTE ES EL PRICE Y EL FIXING - "+ dpriceusd +" + "+ dimfixing);
				  					totprdop += dpricedop;
				  					totmgdop += dpricedop * porcmarg;
				  					totunits += dquantity;
			  					} catch (Exception e) {
			  						System.err.println(" ERROR GRAVE - No se ha incluido un valor al calculo: "+ gridLine.getStringCell(i,"idproenv"));
			  					}
			  					
			  					try {
			  						
			  					} catch (Exception e) {
			  						System.err.println(" ERROR GRAVE - No se ha incluido un valor al calculo: "+ gridLine.getStringCell(i,"idproenv"));
			  					}	
			  					
			  					
			  					pricedop = format2d.format(dpricedop);
			  					priceusd = format2d.format(dpriceusd);
			  					pricetot = format2d.format(dtotalusd);
			  					
							%>  	
						  		<tr>
						  		<td><div class="input-j">&nbsp;</div></td>
								  	<td><div class="input-j"><%=quantity%></div></td>
									<td><div class="input-j"><%=txmarcax%></div></td>
									<td><div class="input-j"><%=txmodelo%></div></td>
									<td><div class="input-j"><%=idcolorx%></div></td>
									<td><div class="input-j"><%=priceusd%></div></td>
									<td><div class="input-j"><%=pricetot%></div></td>
									<td><div class="input-j"><%=txprovid%></div></td>
									<td><div class="input-j"><%=txbuyerx%></div></td>
									<td><div class="input-j"><%=txfundin%></div></td>
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
			  			
			  			double totttdop = totprdop + totmgdop;
			  			double totttusd = totprusd + totmgusd;

			  			strunits = enterofo.format(totunits);
			  			strprusd = format2d.format(totprusd);
			  			strmgusd = format2d.format(totmgusd);
			  			strttusd = format2d.format(totttusd);
			  			strprdop = format2d.format(totprdop);
			  			strmgdop = format2d.format(totmgdop);
			  			strttdop = format2d.format(totttdop);
			  			
		  			} %>			
				</table>						
		</table>
		
		<script>
			document.formvaen.numunits.value = "<%=strunits%>";
			document.formvaen.totprusd.value = "<%=strprusd%>";
			document.formvaen.totmgusd.value = "<%=strmgusd%>";
			document.formvaen.totttusd.value = "<%=strttusd%>";
			document.formvaen.totprdop.value = "<%=strprdop%>";
			document.formvaen.totmgdop.value = "<%=strmgdop%>";
			document.formvaen.totttdop.value = "<%=strttdop%>";
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