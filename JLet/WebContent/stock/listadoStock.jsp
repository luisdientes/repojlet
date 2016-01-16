<%@ include file="/common/jsp/include.jsp"%>

<%

	String imptotal = "";
	String cdestado = "";
	Grid gridProd = null; 
	Grid grPrAgru = null;
	Grid gridDivi = null; 
	Grid gridColo = null;
	Grid gridClie = null;
	Grid gridMarc = null;
	
	String imeicode = ""; 
	String codprodu = ""; 
	String txmarcax = ""; 
	String txmodelo = ""; 
	String idcolorx = ""; 
	String pricechf = "";  
	String pricecmp = ""; 
	String diviscmp = ""; 
	String prusdcmp = "";
	String margbene = "";  
	String pricevnt = ""; 
	String divisvnt = ""; 
	String prusdvnt = "";
	String impbenef = "";
	String txprovid = ""; 
	String txbuyerx = ""; 
	String txfundin = ""; 
	String withboxx = ""; 
	String withusbx = ""; 
	String idcatego = "";
	String idlineax = "";
	String idemisor = "";
	String tpclirec = "";
	String tpclient = "";
	String idclient = "";
	String valSelec = "";
	String logoemis = "";
	String tpproduc = "";
	String txmarfil = "";
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			cdestado = io.getStringValue("cdestado");
			tpclirec = io.getStringValue("tpclient");
			idclient = io.getStringValue("idclient");
			logoemis = io.getStringValue("logoemis");
			txmarfil = io.getStringValue("txmarcax");
			tpproduc = io.getStringValue("tpproduc");
			gridProd = io.getGrid("gridProd");
			grPrAgru = io.getGrid("grPrAgru");
			gridColo = io.getGrid("gridColo");
			gridClie = io.getGrid("gridClie"); 
			gridMarc = io.getGrid("gridMarc");
			
			System.out.println("CDESTADO == "+ cdestado);
			
			try{
				idclient = io.getStringValue("idclient");
				if(idclient !=null || idclient.equals("null") || idclient.equals("")){
					valSelec = tpclirec +"#"+idclient;
				}else{
					
				}
				
				System.out.println("sadsadsad "+valSelec);
			
			}catch(Exception ex) {
				idclient = "";
			}
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listadoStock.jsp "+ e.getMessage());	
		}
	}
	
	
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    simbolo.setDecimalSeparator(',');
    simbolo.setGroupingSeparator('.');
	
	DecimalFormat formatDivi = new DecimalFormat("###,##0.0000",simbolo);
	
	String disponib ="";
	if(cdestado.equals("STOCK")){
		
		disponib ="diponibles";
	}else if(cdestado.equals("DEPOSITO")){
		
		disponib =" en depósito";
	}else if(cdestado.equals("VENDIDO")){
		disponib =" vendidas";
	}
	
%>

<head>

<script>
	var Arrimeixx	= new Array();
	var ArrProduc	= new Array();
	var cadImeisx	= "";
	var chmarcado 	= 0;
	
	function cargaImeis(){
		i=0;
		
		<%
		if ((gridProd != null) && (gridProd.rowCount() > 0)) { 
  			for (int j = 0; j < gridProd.rowCount(); j++){
	 			imeicode 	= gridProd.getStringCell(j,"imeicode");
	 			codprodu 	= gridProd.getStringCell(j,"codprodu");
			%>
			Arrimeixx[i] = "<%=imeicode%>";
			ArrProduc[i] = "<%=codprodu%>";
			i++;
		<%
	 		}
		}	
	 	%>
		
	}
	
	function filtraStock(idemisor){
		
		var cadena = "";
		if(document.getElementById("selidcli")){
			arclient = document.getElementById("selidcli").value.split("#");
		}else{
			arclient = "";
		}
		
		txmarcax = document.getElementById("txmarcax").value;
		tpproduc = document.getElementById("tpproduc").value;
		
		if (arclient != ""){
			tpclient = arclient[0];
			idclient = arclient[1];
		}else{
			tpclient = "";
			idclient = "";
		}
		
		document.formMenu.services.value = "ListStockSrv";
		document.formMenu.view.value	 = "stock/listadoStock.jsp";
		document.formMenu.idemisor.value = "<%=idemisor%>";
		document.formMenu.cdestado.value = "<%=cdestado%>";
		document.formMenu.idclient.value = idclient;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.txmarcax.value = txmarcax;
		document.formMenu.tpproduc.value = tpproduc;
		document.formMenu.submit();
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
		codprodu = "";
		
		for(i=0;i< Arrimeixx.length;i++){
			
			if(tximeixx == Arrimeixx[i]){
				document.getElementById(tximeixx).checked = true;
				codprodu = ArrProduc[i];
				exist = 1;
			}
		}
		
		if (exist == 0){
			document.getElementById("noexiste").style.display = "block";
			document.getElementById("existe").style.display = "none";
			
		}else{
			document.getElementById("noexiste").style.display = "none";
			document.getElementById("existe").style.display = "block";
			clickAgrupada(codprodu);
			document.location.href="#"+tximeixx;
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
	
	
	function clickAgrupada(cdagrupa){
		for (i = 0; i < document.getElementById("tabStock").rows.length; i++){
		
			if (document.getElementById("tabStock").rows[i].id == cdagrupa) {
				if (document.getElementById("tabStock").rows[i].style.display == "none"){
					document.getElementById("tabStock").rows[i].style.display = "";
				} else {
					document.getElementById("tabStock").rows[i].style.display = "none";
				}
			}
		}
			
	}
	
	function altaCostes(idunicox){
		
		document.formStock.controller.value = "DesgCostesHttpHandler";
		document.formStock.services.value 	= "InitAltaDesgloseSrv";
		document.formStock.view.value 		= "desgcostes/altaDesglose.jsp";
		document.formStock.idunicox.value 	= idunicox;
				
		document.formStock.submit();
			
	}
	
	
	function generaPegatinas(){
		leerTodosCheck();
		document.genpegatinas.listimei.value = cadImeisx;
		document.genpegatinas.submit();
	}
	
	function detalleProd(idunicox){
		document.formStock.controller.value = "StockHttpHandler";
		document.formStock.services.value 	= "DetalleProducSrv";
		document.formStock.view.value 		= "stock/detalleProducto.jsp";
		document.formStock.idunicox.value 	= idunicox;
		document.formStock.submit();
	}
	
	cargaImeis();	

</script>
	
</head>


<body>

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>

	<div class="fondo2">
	<div class="testata"></div>
	<div class=nompanta >Listado unidades <%=disponib %></div>	
		<div class="centradoFac" style="width:100%;">
		<br>
		<br>
		<br>
			<br>
			<table width="60%" align="center">
				<tbody>
					<tr>
						<td width="15%" class="input-b1">Marca :</td>
						<td class="input-m">
							<select class="input-m2" id="txmarcax">
								<option value="" selected="selected">-- Todos --</option> 
								<% 
									for (int h = 0; h < gridMarc.rowCount(); h++){ 
										if(txmarfil !=null && txmarcax.equals(gridMarc.getStringCell(h, "txnombre"))){
										
										%>
										<option selected="selected" value="<%=gridMarc.getStringCell(h, "txnombre") %>"><%=gridMarc.getStringCell(h, "txnombre")%></option>
									<% }else{%>
										<option value="<%=gridMarc.getStringCell(h, "txnombre") %>"><%=gridMarc.getStringCell(h, "txnombre")%></option>
								
									<%}
									}
									%>
							</select>
					  </td>
					  <td width="15%" class="input-b1">Tipo :</td>
					  <td class="input-m">
					    <select class="input-m2" id="tpproduc">
								<option value="" selected="selected">-- Todos --</option> 
								<option value="PH"  <% if(tpproduc !=null && tpproduc.equals("PH")){%> selected="selected"<%} %>>-- Celular --</option>
								<option value="EL"   <% if(tpproduc !=null && tpproduc.equals("EL")){%> selected="selected"<%} %>>-- Electrodomestico --</option> 
								<option value="LA" <% if(tpproduc !=null && tpproduc.equals("LA")){%> selected="selected"<%} %>>-- Laptop --</option> 
								<option value="PI" <% if(tpproduc !=null && tpproduc.equals("PI")){%> selected="selected"<%} %>>-- Pieza --</option> 
								<option value="TA" <% if(tpproduc !=null && tpproduc.equals("TA")){%> selected="selected"<%} %>>-- Tablet --</option> 
								
					
						</select>
						</td>	
					</tr>
				  </tbody>
				  <tr>
				  	<td colspan="4" align="center">&nbsp;</td>
				  </tr>
				  <tr>
				  	<td colspan="4" align="center"><a class="boton" onclick="filtraStock()">Filtrar</a></td>
				  </tr>
				  <tr>
				  <td colspan="4" align="center"><a class="boton" onclick="generaPegatinas()">Generar Pegatinas</a></td>
				  	
				  </tr>
			 </table>
				<br>
		
		
			<table border="0" width="60%" align="center" border="1">
				<tr>
			<% if(!cdestado.equals("STOCK")){ %>
				<td class="input-b1" width=30% >Cliente</td>
				<td class="input-m" >
				
						<select id="selidcli" class="input-m2" >
							<option value="" >-- Todos --</option> 
								<% 
							 	 String idclierc = "-1";
							 		for (int i = 0; i < gridClie.rowCount(); i++){
							 			tpclient = gridClie.getStringCell(i,"tpclient");
							 			idclierc = gridClie.getStringCell(i,"idclient");
										if(idclient.equals(idclierc) && tpclient.equals(tpclirec)) {%>
											<option value="<%=tpclient%>#<%=idclierc%>" selected="true" ><%= gridClie.getStringCell(i,"rzsocial") %></option>
									<% }else{%>
					  						<option value="<%=tpclient%>#<%=idclierc%>"  ><%= gridClie.getStringCell(i,"rzsocial") %></option>
									<%}
							     	}
								 	%>  
								</select>	
					
					</td>
					<td><a class="boton" onclick="filtraStock()">Buscar</a>
					<%} %>
				</tr>
				<tr>
					<td align="center" class="input-b1">Introduce Imei</td>                                      				        
					<td align="center"><input class="input-m" type="text" style="width:100%" id="imeicode" onchange="compruebaImei(this.value);"/></td>
				</tr>
			</table>
			<br>
			<br>
			   <table width="80%" border=0 class="TablaGrande" align="center">
				   <tr algin=center">
						<td align="center"><a class="boton" onclick='marcarTodosCheck()'>Marcar todos </a></td>
					</tr>
			   </table>
			<table width="40%" align="center">
				<tr>
					<td align="center">
						<div id="noexiste" style="display:none; color:#F00;"><h3>El IMEI introducido no existe.</h3></div>
						<div id="existe" style="display:none;" class="txtok"><h3>IMEI encontrado.</h3></div>
					</td>
				</tr>
			</table>
			<div id="lineastmp">
				
				<%
				
				if ((gridProd != null) && (gridProd.rowCount() > 0)) { %> 
			
					<table width="95%" border="0" id="tabStock"  align="center">
			  	   		<tr class="cab">
							<td align="center"  width="4%"><div class="cabecera input-b1">&nbsp;</div></td>
							<td align="center" width="20%"><div class="cabecera input-b1">Codigo</div></td>
							<td align="center" width="10%"><div class="cabecera input-b1">Marca</div></td>
							<td align="center" width="20%"><div class="cabecera input-b1">Modelo</div></td>
							<td align="center" width="10%"><div class="cabecera input-b1">Color</div></td>
							<td align="center" width="10%"><div class="cabecera input-b1">Precio</div></td>
							<td align="center" width="5%" ><div class="cabecera input-b1">Caja</div></td>
							<td align="center" width="5%" ><div class="cabecera input-b1">USB</div></td>
							<td align="center" width="8%" ><div class="cabecera input-b1">Clase</div></td>
							<td align="center" width="2%" ><div class="cabecera input-b1">&nbsp;</div></td>
						</tr>
								
								<%
								    for (int h = 0; h < grPrAgru.rowCount(); h++){ %>
									<tr class="usuario" onclick="clickAgrupada('<%=grPrAgru.getStringCell(h,"codprodu")%>')" style="cursor:pointer">
										<td align="center">&nbsp;</td>
										<td align="center" class="fonts6"><%=grPrAgru.getStringCell(h,"codprodu")%></td>
										<td align="center" class="fonts6"><%=grPrAgru.getStringCell(h,"txmarcax")%></td>
										<td align="center" class="fonts6"><%=grPrAgru.getStringCell(h,"txmodelo")%></td>
										<td align="center" colspan="6" align="center" class="fonts6"><%=grPrAgru.getStringCell(h,"unistock")%> unidades <%=disponib %></td>
									</tr>
								  	<%
								  		System.out.println(h +" vs "+ grPrAgru.getStringCell(h,"codprodu") +" - "+ grPrAgru.getStringCell(h,"txmarcax") +" - "+ grPrAgru.getStringCell(h,"txmodelo"));
								  		String oldcodex = grPrAgru.getStringCell(h,"codprodu");
							  			for (int i = 0; i < gridProd.rowCount(); i++){
							  				if (oldcodex.equals(gridProd.getStringCell(i,"codprodu"))){
								  				
								  				try {
								  					imeicode = gridProd.getStringCell(i,"imeicode");
								  					txmarcax = gridProd.getStringCell(i,"txmarcax");
								  					txmodelo = gridProd.getStringCell(i,"txmodelo");
								  					idcolorx = gridProd.getStringCell(i,"idcolorx");	  					
								  					pricevnt = gridProd.getStringCell(i,"pricevnt");	  					
								  					divisvnt = gridProd.getStringCell(i,"divisvnt");	  					
								  					prusdvnt = gridProd.getStringCell(i,"prusdvnt");
								  					withboxx = gridProd.getStringCell(i,"withboxx");
								  					withusbx = gridProd.getStringCell(i,"withusbx");
								  					idcatego = gridProd.getStringCell(i,"idcatego");
								  					
								  					String imageche = "";
								
								  					if (pricevnt.equals("")) {
								  						pricevnt = "0";
								  					}
								  					
								  					if (withboxx.equals("N")) {
								  						withboxx = "-";
								  						withboxx="<img src ='/JLet/common/img/icons/cancel.png' style='width:32px'";
								  					}else{
								  						
								  						withboxx="<img src ='/JLet/common/img/icons/check.png' style='width:32px'";
								  					}
								  					
								  					if (withusbx.equals("N")) {
								  						withusbx = "-";
								  						withusbx ="<img src ='/JLet/common/img/icons/cancel.png' style='width:32px'";
								  					}else{
								  						withusbx="<img src ='/JLet/common/img/icons/check.png' style='width:32px'";
								  					}
								  					
								  					
								  					
								  					if(idcolorx.equals("AM")){
								  						idcolorx  ="Amarillo";
								  					}else if(idcolorx.equals("AZ")){
								  						idcolorx  ="Azul";
								  					}else if(idcolorx.equals("BL")){
								  						idcolorx  ="Blanco";
								  					}else if(idcolorx.equals("GR")){
								  						idcolorx  ="Granate";
								  					}else if(idcolorx.equals("GS")){
								  						idcolorx  ="Gris";
								  					}else if(idcolorx.equals("MA")){
								  						idcolorx  ="Marron";
								  					}else if(idcolorx.equals("NA")){
								  						idcolorx  ="Naranja";
								  					}else if(idcolorx.equals("NE")){
								  						idcolorx  ="Negro";
								  					}else if(idcolorx.equals("RO")){
								  						idcolorx  ="Rojo";
								  					}else if(idcolorx.equals("Ve")){
								  						idcolorx  ="Verde";
								  					}else if(idcolorx.equals("")){
								  						idcolorx ="-";
								  					}
								  					
								  					
								  					if(idcatego.equals("N")){
								  						idcatego  ="Nuevo";
								  					}else if(idcatego.equals("N7")){
								  						idcatego  ="Nuevo 7 dias";
								  					}else if(idcatego.equals("R")){
								  						idcatego  ="Refurbished";
								  					}else if(idcatego.equals("A")){
								  						idcatego  ="Usado Clase A";
								  					}else if(idcatego.equals("B")){
								  						idcatego  ="Usado Clase B";
								  					}else if(idcatego.equals("C")){
								  						idcatego  ="Usado Clase C";
								  					}
								  				
								  					//CAST A DOUBLE PARA DAR FORMATO
								  					double dpricevnt = 0;
								  					
								  					try {
								  						dpricevnt = Double.parseDouble(pricevnt);
								  					} catch (Exception e) {
								  						System.err.println(this.getClass().getName() +" ERROR recuperando Price - "+ gridProd.getStringCell(i,"pricevnt") +". (e) -> "+ e.getMessage());
								  					}
								  					
								  					pricevnt = format2d.format(dpricevnt);
								  					
								  					if (i % 2 == 0) { %>
											  		<tr class="oddRow" id="<%=oldcodex%>"  style="border:1px solid;display:none">
												<% } else { %>
													<tr class="evenRow" id="<%=oldcodex%>" style="border:1px solid;display:none">
												<% }
								  				
												%>  	
											  	
											  			<td class="fonts6jej" ><a name="<%=imeicode %>"></a><input type="checkbox" id="<%=imeicode %>" value="<%=imeicode%>"> </div></td>
													  	<td class="fonts6jej" style="cursor:pointer" onclick='detalleProd("<%=imeicode%>")'><%=imeicode%></td>
														<td class="fonts6jej" style=""><%=txmarcax%></td>
														<td class="fonts6jej" ><%=txmodelo%></td>
														<td class="fonts6jej" ><%=idcolorx%></td>
													<%	if (pricevnt.equals("0,00")) { %>
															<td class="highlightedColumn" style="font-size:12px" ><div style="color:#FB0000"><b><%=pricevnt%> <%=divisvnt%></b></div></td>
													<%	} else { %>
															<td class="highlightedColumn" style="font-size:12px"><%=pricevnt%> <%=divisvnt%></div></td>
													<%	} %>
														<td class="fonts6jej" ><div><%=withboxx%></td>
														<td class="fonts6jej"><div><%=withusbx%></td>
														<td class="fonts6jej"><div><%=idcatego%></td>
														<td class="fonts6jej"  onclick="altaCostes('<%=imeicode%>');"><img src="/JLet/common/img/icons/altacoste.png" width="16px" height="16px"  style="cursor:pointer"/></td>
													</tr>
											<%	} catch (Exception e) { 
													e.printStackTrace();
													System.out.println(this.getClass().getName() +" -- ERROR -- recuperando linea "+ i); %>
													<tr>
														<td align="center" colspan="10" style="color:#FF0000">-- ERROR --</td>
													</tr>
											<%	}
											}
							  			}
										%>
								<% } %>
					
					<%
						if (cdestado.equals("STOCK")){
					%>
					
					<br><br>
							<table width="95%" align="center">
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="5">&nbsp;</td>
								</tr>
								<tr>
									<td align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/bot-factura.png" onClick="trasaccion('F')"></td>
									<td width=5%>&nbsp;</td>
									<td align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/bot-envio.png" onClick="creaEnvio()"></td>
									<td width=5%>&nbsp;</td>
									<td align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/bot-deposito.png" onClick="trasaccion('D')"></td>
								</tr>
								<tr>
									<td align="center" class="input-b1">Generar Factura</td>
									<td width=5%>&nbsp;</td>
									<td align="center" class="input-b1">Crear Envío</td>
									<td width=5%>&nbsp;</td>
									<td align="center" class="input-b1">Depósito</td>
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
						<input type="hidden" name="idunicox" 	value=""/>
						<br/>
						<br/>
					</form>
				<% }else{ %>
				<table width="95%" align="center">
					<tr>
						<td align="center"><h3>La b&uacute;squeda no ha obtenido resultados</h3></td>
					</tr>
				</table>
				<% } %>
				
			    <form method="POST" name="genpegatinas" action="/JLet/App">
					<input type="hidden" name="controller" 	value="StockHttpHandler"/>
					<input type="hidden" name="services" 	value="GenPegCodBarrasSrv"/>
					<input type="hidden" name="view" 		value="stock/abrirListado.jsp"/>
					<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
					<input type="hidden" name="tpclient" 	value=""/>
					<input type="hidden" name="listimei" 	value=""/>
				</form>
				
				<form method="POST" name="formMenu" action="/JLet/App">
					<input type="hidden" name="controller" 	value="StockHttpHandler"/>
					<input type="hidden" name="services" 	value=""/>
					<input type="hidden" name="view" 		value=""/>
					<input type="hidden" name="idemisor" 	value=""/>
					<input type="hidden" name="tpclient" 	value=""/>
					<input type="hidden" name="idclient" 	value=""/>
					<input type="hidden" name="cdestado" 	value=""/>
					<input type="hidden" name="tipocons" 	value=""/>
					<input type="hidden" name="cdpantal" 	value=""/>
					<input type="hidden" name="txmarcax" 	value=""/>
					<input type="hidden" name="tpproduc" 	value=""/>
				</form>
			</div>
		</div>
	</div>
</body>