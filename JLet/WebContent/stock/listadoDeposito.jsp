<%@ include file="/common/jsp/include.jsp"%>

<%

	String imptotal = "";
	String cdestado = "";
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
	String tpclient = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			cdestado = io.getStringValue("cdestado");
			//tpclient = io.getStringValue("tpclient");
			gridLine = io.getGrid("gridLine");		
			gridColo = io.getGrid("gridColo");
			System.out.println("CDESTADO == "+ cdestado);
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
	var Arrimeixx	= new Array();
	var cadImeisx	= "";	
	var chmarcado 	= 0;
	
	function cargaImeis(){
		i=0;
		
		<%
		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
  			for (int j = 0; j < gridLine.rowCount(); j++){
	 			imeicode 	= gridLine.getStringCell(j,"imeicode");
			%>
			Arrimeixx[i] = "<%=imeicode%>";
			i++;
		<%
	 		}
		}	
	 	%>
		
	}
	
	  function leerTodosCheck(){
	        checkboxes = document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
	        for(i=0; i<checkboxes.length; i++) //recoremos todos los controles
	        {
	            if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
	            {
	                if(checkboxes[i].checked){
	                	cadImeisx += checkboxes[i].value+";";
	                	chmarcado++;
	                }; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
	            }
	        }
	    }
	  
	  function marcarTodosCheck()
	    {
	        checkboxes = document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
	        for(i=0; i<checkboxes.length; i++) //recoremos todos los controles
	        {
	            if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
	            {
	                checkboxes[i].checked = true; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
	            }
	        }
	    }
	
	function compruebaImei(tximeixx){
	
		exist =0;
		
		for(i=0;i< Arrimeixx.length;i++){
			
			if(tximeixx == Arrimeixx[i]){
				document.getElementById(tximeixx).checked = true;
				exist = 1;
			}
		}
		
		if (exist == 0){
			document.getElementById("noexiste").style.display = "block";
			document.getElementById("existe").style.display = "none";
			
		}else{
			document.getElementById("noexiste").style.display = "none";
			document.getElementById("existe").style.display = "block";
		}
	
		document.getElementById("imeicode").value = "";
		document.getElementById("imeicode").focus();
	}
	

	function creaEnvio(){
		leerTodosCheck();
		
		if(chmarcado > 0){
			document.formStock.services.value = "CreaEnvioSrv";
			document.formStock.listimei.value = cadImeisx;
			document.formStock.submit();
		}else{
			alert("No se han marcado productos");
		}	
			
	}
	
	function trasaccion(tptra){
		
		leerTodosCheck();
		
		if(chmarcado > 0){
			document.formStock.controller.value = "StockHttpHandler";
			document.formStock.services.value = "InitTransaccionSrv";
			document.formStock.view.value = "stock/altaTransaccion.jsp";
			document.formStock.listimei.value = cadImeisx;
			document.formStock.tptransa.value = tptra;
			
			document.formStock.submit();
		}else{
			alert("No se han marcado productos");
		}	
	}
	cargaImeis();	

</script>
	
</head>


<body>

	<div class="fondo2">
		<div class="centradoFac" style="width:90%">
		<br>
			<table border="0" width="100%" align="center" border="1">
				<tr>
					<td align="center" class="input-txt-b">Introduce Imei</td>                                      				        
					<td align="center" class="input-m"><input class="input-m" type="text" id="imeicode" onchange="compruebaImei(this.value);"/></td>
				</tr>
			</table>
			<br>
			<br>
			   <table width="100%" border=0 class="TablaGrande" align="center">
				   <tr algin=center">
						<td align="center"><a class="boton" onclick='marcarTodosCheck()'>Marcar todos </a></td>
					</tr>
			   </table>
			<table width="40%" align="center">
				<tr>
					<td align="center">
						<div id="noexiste" style="display:none; color:#F00;"><h3>El IMEI introducido no existe.</h3></div>
						<div id="existe" style="display:none; color:#0F0;"><h3>IMEI encontrado.</h3></div>
					</td>
				</tr>
			</table>
			<div id="lineastmp">
			
			 	<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { %> 
			 		<table width="100%" border="0" class="TablaGrande">
			  	   		<tr class="fonts">
							<td align="center"><div class="cabecera input-b1">&nbsp;</div></td>
							<td align="center" ><div class="cabecera input-b1">Imei</div></td>
							<td align="center"><div class="cabecera input-b1">Make</div></td>
							<td align="center"><div class="cabecera input-b1">Model</div></td>
							<td align="center"><div class="cabecera input-b1">Colour</div></td>
							<% if (!idemisor.equals("1") && !idemisor.equals("5")){ %>
								<td align="center" id="pricechf"><div class="cabecera input-b1">Price CHF</div></td>
								<td align="center" id="benefici"><div class="cabecera input-b1">% Benef</div></td>
								<td align="center" id="impbenef"><div class="cabecera input-b1">Imp. Benef</div></td>
								<td align="center" id="txprovid"><div class="cabecera input-b1">Provider</div></td>
								<td align="center" id="txbuyerx"><div class="cabecera input-b1">Buyer</div></td>
								<td align="center" id="txfundin"><div class="cabecera input-b1">Funding</div></td>
							<% } %>
							<td align="center"><div class="cabecera input-b1">Box</div></td>
							<td align="center"><div class="cabecera input-b1">USB</div></td>
							<td align="center"><div class="cabecera input-b1">Category</div></td>
						</tr>
			  	  		<tr>
			  	  			<td colspan="13"><hr style="color: #E1E1E1"></td>	
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
					
					<%
						if (cdestado.equals("STOCK")){
					%>
							<table width="60%" align="center">
								<tr>
									<td align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/bot-factura.png" onClick="trasaccion('F')"></td>
									<td width=5%>&nbsp;</td>
									<td align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/bot-envio.png" onClick="creaEnvio()"></td>
									<td width=5%>&nbsp;</td>
									<td align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/bot-deposito.png" onClick="trasaccion('D')"></td>
								</tr>
								<tr>
									<td align="center" class="input-txt-b">Generar Factura</td>
									<td width=5%>&nbsp;</td>
									<td align="center" class="input-txt-b">Crear Envío</td>
									<td width=5%>&nbsp;</td>
									<td align="center" class="input-txt-b">Depósito</td>
								</tr>
							</table>
					<%
						}
					%>
					
					<form name="formStock" method="POST" action="/JLet/App">
						<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
						<input type="hidden" name="services"	value=""/>
						<input type="hidden" name="view" 		value="comercio/resultCreaEnvio.jsp"/>	
						<input type="hidden" name="listimei" 	value=""/>
						<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>	
						<input type="hidden" name="tpclient" 	value=""/>	
						<input type="hidden" name="tptransa" 	value=""/>						
						<br/>
						<br/>
					</form>
				<% } %>
			</div>
		</div>
	</div>
</body>