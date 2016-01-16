<%@ include file="/common/jsp/include.jsp"%>

<%

	String imptotal = "";
	String cdestado = "";
	String horacomi = "";
	String logoemis = "";
	Grid gridProd = null; 
	Grid grPrAgru = null;
	Grid gridDivi = null; 
	Grid gridColo = null;
	Grid gridClie	= null;
	Grid gridLine = null;
	
	
	String imeicode = ""; 
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
	String claseant = "";
	String imeiprod = "";
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			imeiprod = io.getStringValue("imeicode");
			claseant = io.getStringValue("claseant");
			horacomi = io.getStringValue("horacomi");
			tpclient = io.getStringValue("tpclient");
			gridProd = io.getGrid("gridProd");
			grPrAgru = io.getGrid("grPrAgru"); 
			gridLine = io.getGrid("gridLine");
			
			System.out.println("CDESTADO == "+ cdestado);
		
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listadoStock.jsp "+ e.getMessage());	
		}
	}
	
	
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    simbolo.setDecimalSeparator(',');
    simbolo.setGroupingSeparator('.');
	
	DecimalFormat formatDivi = new DecimalFormat("###,##0.0000",simbolo);
	
	if(idemisor.equals("1")){
		logoemis = idemisor+"_"+tpclient;
	}else{
		logoemis = idemisor;
	}

	
%>

<head>

<script>
	var Arrimeixx	= new Array();
	var cadImeisx	= "";	
	var chmarcado 	= 0;
	
	function cargaImeis(){
		i=0;
		
		<%
		if ((gridProd != null) && (gridProd.rowCount() > 0)) { 
  			for (int j = 0; j < gridProd.rowCount(); j++){
	 			imeicode 	= gridProd.getStringCell(j,"imeicode");
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
	

	function insPiezas(){
		leerTodosCheck();
		
		if(chmarcado > 0){
			document.formStock.listimei.value = cadImeisx;
			document.formStock.submit();
		}else{
			alert("No se han marcado Piezas");
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
		
			if (document.getElementById("tabStock").rows[i].className == cdagrupa) {
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
	
	function marcaColor(imeicod,cdagrupa){
		
		imeiColor = "I"+imeicod;
		
		if(document.getElementById(imeicod).checked){
			document.getElementById(imeiColor).style.background="#266A2E";
			//document.getElementById(cdagrupa).style.background="#266A2E";
			
		}else{
			document.getElementById(imeiColor).style.background="#cccccc";
			//document.getElementById(cdagrupa).style.background="#cccccc";
		}
	}
	
	cargaImeis();
	
	function foco(){
		document.getElementById('imeicode').focus();
	}
	
	

</script>
	
</head>


<body onload="foco();">

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=logoemis%>.png"></td>						
		</tr>
	</table>

	<div class="fondo2">
	<div class="testata"></div>
	<div class=nompanta >Listado unidades Disponibles </div>	
		<div class="centrado" style="width:100%">
		<br>
		
		
		<%
			String txmarpro = "";
			String txmodpro = "";
			String codprodu = "";
			String imagewpt = "";
			String rutaimag = "";
			
			codprodu = gridLine.getStringCell(0,"codprodu");
			txmarpro = gridLine.getStringCell(0,"txmarcax");
			txmodpro = gridLine.getStringCell(0,"txmodelo");
			imagewpt = gridLine.getStringCell(0,"imagewpt");
			
			
			if(codprodu.substring(0,2).equals("PI")){
	    		rutaimag ="http://mallproshop.com/images/pieces/";
	    	}else{
	    		rutaimag ="http://mallproshop.com/";
	    	}
		
		%>
		
		<table  border="0" width="40%" align="center">
			<tr>
				<td align="center"><img src="<%=rutaimag%><%=imagewpt%>"></td>
			</tr>
			<tr>
				<td align="center"><%=txmarpro %> <%=txmodpro %></td>
			</tr>
		</table>
		
		
			<table border="0" width="40%" align="center" border="1">
			<tr>
				</tr>
				<tr>
					<td align="center" class="input-b1">Introducir codigo pieza</td>                                      				        
					<td align="center"><input class="input-m" type="text" style="width:100%" id="imeicode" onchange="compruebaImei(this.value);"/></td>
				</tr>
			</table>
			<br>
			<br>
			   <table width="80%" border=0 class="TablaGrande" align="center">
					<tr algin=center">
						<td align="center"><a class="boton" onclick='insPiezas()'>Aceptar </a></td>
					</tr>
			   </table>
			<table width="40%" align="center">
				<tr>
					<td align="center">
						<div id="noexiste" style="display:none; color:#F00;"><h3>El ID PIEZA introducido no existe.</h3></div>
						<div id="existe" style="display:none;" class="txtok"><h3>Pieza encontrada.</h3></div>
					</td>
				</tr>
			</table>
			<div id="lineastmp">
				
				<%
				
				if ((gridProd != null) && (gridProd.rowCount() > 0)) { %> 
			
					<table width="95%" border="0" id="tabStock"  align="center">
			  	   		<tr class="cab">
			  	   			<td align="center"  width="4%"><div>&nbsp;</div></td>
							<td align="center"  width="4%"><div>&nbsp;</div></td>
							<td align="center" width="20%"><div class="cabecera input-b1">Codigo</div></td>
							<td align="center" width="10%"><div class="cabecera input-b1">Marca</div></td>
							<td align="center" width="20%"><div class="cabecera input-b1">Modelo</div></td>
							<td align="center" width="10%"><div class="cabecera input-b1">Color</div></td>
							<td align="center" width="10%"><div class="cabecera input-b1">Precio</div></td>
							<td align="center" width="8%" ><div class="cabecera input-b1">Clase</div></td>
							
						</tr>
								
								<%
								    for (int h = 0; h < grPrAgru.rowCount(); h++){ %>
									<tr class="usuario" onclick="clickAgrupada('<%=grPrAgru.getStringCell(h,"codprodu")%>')"  style="cursor:pointer">
										<td align="center" id="<%=grPrAgru.getStringCell(h,"codprodu")%>">&nbsp;</td>
										<td align="center">&nbsp;</td>
										<td align="center" class="fonts6"><%=grPrAgru.getStringCell(h,"codprodu")%></td>
										<td align="center" class="fonts6"><%=grPrAgru.getStringCell(h,"txmarcax")%></td>
										<td align="center" class="fonts6"><%=grPrAgru.getStringCell(h,"txmodelo")%></td>
										<td align="center" colspan="6" align="center" class="fonts6"><%=grPrAgru.getStringCell(h,"unistock")%> unidades Disponibles</td>
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
								  					}else{
								  						withboxx="<img src ='/JLet/common/img/varios/checked3.png' style='width:32px'";
								  					}
								  					
								  					if (withusbx.equals("N")) {
								  						withusbx = "-";
								  					}else{
								  						withusbx="<img src ='/JLet/common/img/varios/checked3.png' style='width:32px'";
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
								  					
												%>  	
											  		<tr class="<%=oldcodex%>" style="display:none">
											  			<td id="I<%=imeicode %>" class="fonts6">&nbsp;</td>
											  			<td class="fonts6" style="background-color:ccc;"><input type="checkbox" id="<%=imeicode %>" value="<%=imeicode%>" onclick="marcaColor('<%=imeicode %>','<%=grPrAgru.getStringCell(h,"codprodu")%>')"> </div></td>
													  	<td class="fonts6" style="background-color:ccc;"><%=imeicode%></td>
														<td class="fonts6" style="background-color:ccc;"><%=txmarcax%></td>
														<td class="fonts6" style="background-color:ccc;"><%=txmodelo%></td>
														<td class="fonts6" style="background-color:ccc;"><%=idcolorx%></td>
													<%	if (pricevnt.equals("0,00")) { %>
															<td class="fonts6" style="background-color:ccc;"><div style="color:#FB0000"><b><%=pricevnt%> <%=divisvnt%></b></div></td>
													<%	} else { %>
															<td class="fonts6" style="background-color:ccc;"><%=pricevnt%> <%=divisvnt%></div></td>
													<%	} %>
												
														<td class="fonts6" style="background-color:ccc;"><div><%=idcatego%></td>
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
					
					</table>
					<form name="formStock" method="POST" action="/JLet/App">
						<input type="hidden" name="controller" 	value="UpgradeHttpHandler"/>
						<input type="hidden" name="services"	value="InsPiezasProdSrv"/>
						<input type="hidden" name="view" 		value="upgrade/ListNuevasPiezas.jsp"/>	
						<input type="hidden" name="listimei" 	value=""/>
						<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>	
						<input type="hidden" name="tpclient" 	value="<%=tpclient%>"/>	
						<input type="hidden" name="tptransa" 	value=""/>
						<input type="hidden" name="idunicox" 	value=""/>
						<input type="hidden" name="claseant" 	value="<%=claseant%>"/>
						<input type="hidden" name="horacomi" 	value="<%=horacomi%>"/>
						<input type="hidden" name="imeiprod" 	value="<%=imeiprod%>"/>
						
						<br/>
						<br/>
					</form>
				<% } %>
		
			</div>
		</div>
	</div>

</body>