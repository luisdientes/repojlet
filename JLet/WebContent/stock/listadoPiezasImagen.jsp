<%@ include file="/common/jsp/include.jsp"%>

<%


	String imptotal = "";
	String cdestado = "";
	Grid gridProd = null; 
	Grid grPrAgru = null;
	Grid gridDivi = null; 
	Grid gridMarc = null;
	Grid gridClie = null;
	Grid gridPhon = null;
	
	
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
	String logoemis = "";
	String tpproduc = "";
	String idgrupox = "";
	String[] piezaMar = null;
	
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			cdestado = io.getStringValue("cdestado");
			tpclirec = io.getStringValue("tpclient");
			idclient = io.getStringValue("idclient");
			logoemis = io.getStringValue("logoemis");
			txmarcax = io.getStringValue("txmarcax");
			tpproduc = io.getStringValue("tpproduc"); 
			idgrupox = io.getStringValue("idgrupox");
			piezaMar = request.getParameterValues("listpiec"); 
			gridProd = io.getGrid("gridProd"); 
			gridMarc = io.getGrid("gridMarc");
			gridPhon = io.getGrid("gridPhon");
			
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

<style>
	.marco{
	border:2px solid #000;
	background-color:#ccc
	}
	
	.nomarco{
	border:0px;
	background-color:#fff !important
	}
	
	
</style>

<script>
	var Arrimeixx	= new Array();
	var cadImeisx	= "";	
	var chmarcado 	= 0;
	var contFech	= 0;
	var fec			= 0;
	var piezas		= new Array();
	var FechasMa	= new Array();
	var idgrupox = '<%=idgrupox%>';


	<% 
	/* CARGO LAS FECHAS pulsadas RECIBIDAS EN UN ARRAY*/
	 try{
		if( piezaMar!=null || !piezaMar.equals("")){
		
		String[] piezasxx = piezaMar[0].split(",");
			for(int i=0;i<piezasxx.length;i++){
				if(!piezasxx[i].toString().equals("")){
			%>
				piezas[fec] = "<%=piezasxx[i].toString()%>";
				fec++;
			<%	
				}
			}
		}
	 }catch(Exception ex){
		 System.out.println("No recibe nada");
	 }
		
		
	%>
	
	
	function agregaPieza(idpieza){ /* agrego la fecha a un array o la borro del array dependiendo de si estaba pulsada o no*/
		
		if(!compruebaExiste(idpieza)){
				pulsada = 0;
				document.getElementById(idpieza).style.backgroundColor="#acbcd3";
				var elemento = document.getElementById(idpieza);
				
				
				for(z=0;z<piezas.length;z++){
					
					if(piezas[z] == idpieza){
						piezas.splice(z,1);
						document.getElementById(idpieza).style.backgroundColor="#d9dcdd";
						pulsada = 1;
						elemento.className = "nomarco";
					}	
				}
			
				
				if(pulsada == 0){
					piezas.push(idpieza);
					elemento.className = "marco";
		 	 		//contFech++;
				}
			
				if(piezas == ""){
					piezas.length = 0;
					contFech=0;
				}	
				
		}
 }
	
	function compruebaExiste(pieza){ /*me comprueba si existe la fecha marcada o no*/
		var ex=0;
		
			for(x=0; x < FechasMa.length; x++){
		   		if (FechasMa[x] == piezas){
		   			ex = 1;
		   		}
		    }
		 
		if (ex==1){
		 return true
		}else{
		 return false;
		}	  
	}

	
function cargarPiezas(){ /* me marca las fechas que he ido clickando para que se marquen si cambio de mes*/
		
		for(z=0;z<piezas.length;z++){
			//alert(fechas[z]);
			if(document.getElementById(piezas[z])){
				document.getElementById(piezas[z]).style.backgroundColor="#acbcd3";
			   document.getElementById(piezas[z]).checked = true;
				//elemento.className = "input1";
			}
			contFech++; //para continuar agregando elementos en el array
		}
	}
	
	
	 function leerTodosCheck(){
	        checkboxes = document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
	        for(i=0; i<checkboxes.length; i++) //recoremos todos los controles
	        {
	            if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
	            {
	                if(checkboxes[i].checked){
	                	cadImeisx += checkboxes[i].value+",";
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
	
	function filtraStock(idgrupox){
		
		
		
		//leerTodosCheck();
		//txmarcax = document.getElementById("txmarcax").value;
	//	idgrupox = document.getElementById("idgrupox").value;
		document.formMenu.services.value = "ListPiezasImgSrv";
		document.formMenu.view.value	 = "stock/listadoPiezasImagen.jsp";
		document.formMenu.idemisor.value = "<%=idemisor%>";
		document.formMenu.cdestado.value = "STOCK";
		//document.formMenu.idmarcax.value = txmarcax;
		document.formMenu.idgrupox.value = idgrupox;
		document.formMenu.listpiec.value = piezas;
		document.formMenu.submit();
	}	

	
	function Exportar(){
		
		//leerTodosCheck();
		
		if(piezas.length>0){
		
			document.formMenu.services.value = "ExcelSrv";
			document.formMenu.view.value	 = "stock/abrirListado.jsp";
			document.formMenu.idemisor.value = "<%=idemisor%>";
			document.formMenu.cdestado.value = "STOCK";
			document.formMenu.listpiec.value = piezas;
			
			//alert(document.formMenu.listpiec.value)
			document.formMenu.submit();
		}else{
			alert("No hay marcada ninguna pieza");
		}
	}
	
	
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
		
	

		<div class="centradoFac" style="width:100%">
		<div class="nompanta" style="margin-left:0" >Telefonos :</div>
		<br>
		<br>
		<br>
		 <% if ((gridPhon != null) && (gridPhon.rowCount() > 0)) { %>
		
				<table width="100%" border="0" id="tabStock" class="TablaGrande" align="center">
			  	   		<tr class="cab">
			  	   		<%
								int columnas = 6;
								int contc = 0;
								String rutaimga ="";
								    for (int h = 0; h < gridPhon.rowCount(); h++){ 
								    	
								    	contc++;
								    String grupopho = gridPhon.getStringCell(h,"idgrupox");
								        rutaimga ="http://mallproshop.com/";
							%>
						<td align="center">&nbsp;</td>
									<td width="7%" align="center" class="tablaGrandeW">
						<td align="center">&nbsp;</td>
									<td width="7%" align="center" class="tablaGrandeW">
									
									<% if(grupopho.equals(idgrupox)){%>
										<table border="0" id="<%=grupopho %>" class="marco" style="cursor:pointer" onclick="filtraStock('<%=grupopho %>')">
										<tr>
											<td align="center" class="fonts" width=25%><img height=80px; width=80px; src="<%=rutaimga%><%=gridPhon.getStringCell(h,"imagedet")%>"></td>
										</tr>
										<tr>
											<td class="boton" align="center" style="font-size:16px;font-weight:bold;height:80px"><%=gridPhon.getStringCell(h, "txnombre") %>&nbsp;&nbsp;<%=gridPhon.getStringCell(h, "txmodelo") %></td>
										</tr>
									</table>
									
									<% }else{%>
									<table border="0" id="<%=grupopho %>" style="cursor:pointer" onclick="filtraStock('<%=grupopho %>')">
										<tr>
											<td align="center" class="fonts" width=25%><img height=80px; width=80px; src="<%=rutaimga%><%=gridPhon.getStringCell(h,"imagedet")%>"></td>
										</tr>
										<tr>
											<td class="boton" align="center" style="font-size:16px;font-weight:bold;height:80px"><%=gridPhon.getStringCell(h, "txnombre") %>&nbsp;&nbsp;<%=gridPhon.getStringCell(h, "txmodelo") %></td>
										</tr>
									</table>
									<%}%>
									
									
									
									</td>	
									<td width="0.5%" align="center">&nbsp;</td>
									<% 	if (contc % columnas == 0){ %>
									</tr>
									<tr>
										<td colspan="10" height="10px">
									</tr>
									<tr>
		
										<% 
									   }
								   }%>
								    </tr>
        						</td>
							</table>
					<% } %>				
									
									
			<br>
			<br>
			<table width="50%" align="center">
			
				  <tr>
				  	<td colspan="4" align="center">&nbsp;</td>
				  </tr>
				  <tr>
				  	<td align="center"><a class="boton" onclick="Exportar()">Exportar a excel</a></td>
				  	<td align="center"><a class="boton" onclick="filtraStock('')">Mostrar todas piezas</a></td>
				  </tr>
				
			 </table>
				<br>
		
		<div class="nompanta" style="margin-left:0"  >Listado Piezas <%=disponib %></div>
			<div id="lineastmp">
				
				<%
				
				if ((gridProd != null) && (gridProd.rowCount() > 0)) { %> 
			
					<table width="100%" border="0" id="tabStock" class="TablaGrande" align="center">
			  	   		<tr class="cab">
					
								
								<%
								int itemxrow = 4;
								int cont = 0;
								String codprodu ="";
								String rutaimga ="";
								    for (int h = 0; h < gridProd.rowCount(); h++){ 
								    	
								    	cont++;
								    	codprodu = gridProd.getStringCell(h,"idpiezax");
								        rutaimga ="http://mallproshop.com/images/pieces/";
	
								    
								    		%>
									<td align="center">&nbsp;</td>
									<td width="15%" align="center" class="tablaGrandeW">
										<table border="0" id="<%=codprodu %>" style="cursor:pointer" onclick="agregaPieza('<%=codprodu %>')">
											<tr>
												<td align="center" class="fonts" width=25%><img height=180px; width=180px; src="<%=rutaimga%><%=gridProd.getStringCell(h,"imgdefau")%>"></td>
											</tr>
											<tr>
												<td class="boton" align="center" style="font-size:16px;font-weight:bold;height:80px"><%=gridProd.getStringCell(h,"txdescri")%></td>
											</tr>
										
										
										</table>
									</td>	
									<td width="0.5%" align="center">&nbsp;</td>
									<% 	if (cont % itemxrow == 0){ %>
									</tr>
									<tr>
										<td colspan="10" height="10px">
									</tr>
									<tr>
		
										<% 
									   }
								   }%>
								    </tr>
        						</td>
							</table>
						 <% } %>			
							
					
					
			</div>
		</div>
		<form method="POST" name="formMenu" action="/JLet/App">
					<input type="hidden" name="controller" 	value="StockHttpHandler"/>
					<input type="hidden" name="services" 	value=""/>
					<input type="hidden" name="view" 		value=""/>
					<input type="hidden" name="idemisor" 	value=""/>
					<input type="hidden" name="cdestado" 	value=""/>
					<input type="hidden" name="idmarcax" 	value=""/>
					<input type="hidden" name="tpproduc" 	value=""/>
					<input type="hidden" name="listpiec" 	value=""/>
					<input type="hidden" name="idgrupox" 	value=""/>
					
		</form>
	</div>
<script>
	
	if (piezas != ""){
		cargarPiezas();
	}
	
	if(idgrupox != "" && idgrupox !="null"){
		document.getElementById('lineastmp').scrollIntoView(true);
	}


</script>
</body>