<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>

<%
	
	HttpSession sesion = request.getSession();
	HashMap<String,String> permEmis = new HashMap<String,String>();
	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");

	String aniofact = null;
	String idemisor = null;
	String fhdesdex = null;
	String fhhastax = null;
	String predesde = null;
	String prehasta = null;
	String idclient = null;
	String logoemis = null;
	String tpclirec = null;
	String tipofact = null;
	Grid grfactur   = null; 
	Grid gridClie	= null;
	Grid gridMaxF   = null;
	Grid gdAniosx   = null; 
	
	int cambia = 0;
	
	//Texto paises
	String txcdfact = "";

	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			aniofact = io.getStringValue("aniofact");
			grfactur = io.getGrid("grfactur");	
			gridClie = io.getGrid("gridClie");
			idemisor = io.getStringValue("idemisor");
			
			logoemis = io.getStringValue("logoemis");
			gridMaxF = io.getGrid("gridMaxF");
			gdAniosx = io.getGrid("gdAniosx");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/listadoFacturasEmisor.jsp "+ e.getMessage());	
		}
	
	
		try{
			idclient = io.getStringValue("idcliere");
			if(idclient.equals("null")){
				idclient = "";
			}
		
		}catch(Exception ex) {
			idclient = "";
			
		}
		
		try{
			fhdesdex = io.getStringValue("fhdesdex");
			if(fhdesdex.equals("null")){
				fhdesdex = "";
			}
		
		}catch(Exception ex) {
			fhdesdex = "";			
		}
		try{
			fhhastax = io.getStringValue("fhhastax");
			if(fhhastax.equals("null")){
				fhhastax = "";
			}
		
		}catch(Exception ex) {
			fhhastax ="";			
		}
		
		try{
			predesde = io.getStringValue("predesde");
			if(predesde.equals("null")){
				predesde = "";
			}
		
		}catch(Exception ex) {
			predesde = "";			
		}
		
		try{
			prehasta = io.getStringValue("prehasta");
			if(prehasta.equals("null")){
				prehasta = "";
			}
		
		}catch(Exception ex) {
			prehasta ="";			
		}
		
		try{
			tpclirec = io.getStringValue("tpclient");
		}catch(Exception ex) {
			tpclirec ="0";		
		}
		
	}
	
	if ((idemisor.equals("1")) || (idemisor.equals("5"))){
		txcdfact = "NCF";
	} else {
		txcdfact = "Cod.";
	}
	
	if ((fhdesdex == null) || (fhdesdex.equals(""))) {
		fhdesdex = "01/01/"+ aniofact;
	}
	
	
	
%>

<script>

function abrirFactura(namefile){
	document.abriFactu.filename.value = namefile;
	document.abriFactu.submit();
}

function filtrafactura(){
	
	var cadena = "";
	arclient = document.getElementById("selidcli").value.split("#");
	
	if (arclient != ""){
		tpclient = arclient[0];
		idclient = arclient[1];
	}else{
		tpclient = "";
		idclient = "";
	}
	
	fhdesd = document.getElementById("fhdesd").value;
	fhasta = document.getElementById("fhasta").value;
	impdesde = document.getElementById("impdesde").value;
	imphasta = document.getElementById("imphasta").value;
	
	document.formMenu.services.value = "ListFacturasEmisorSrv";
	document.formMenu.view.value = "factura/listadoFacturasEmisor.jsp";	
	document.formMenu.tpclient.value = tpclient;	
	document.formMenu.idcliere.value = idclient;
	
	document.formMenu.fhdesdex.value = fhdesd;
	document.formMenu.fhhastax.value = fhasta;
	document.formMenu.predesde.value = impdesde;
	document.formMenu.prehasta.value = imphasta;
	
	if(fhdesd != ""){
	 	if(!ver_fecha(fhdesd) || !fechaCorrecta(fhdesd)){
		 cadena ="Fecha desde incorrecta\n";
	 	}
	} 	
	 
	if(fhasta != ""){
	 	if(!ver_fecha(fhasta) || !fechaCorrecta(fhasta)){
		 	cadena +="Fecha hasta incorrecta";
	 	}
	}
	
	if(impdesde != ""){
		if(isNaN(impdesde)){
			cadena+="Precio de desde no valido\n";
		}
	}	
	
	if(imphasta != ""){ 
		if(isNaN(imphasta)){
			cadena+="Precio de hasta no valido\n";
		}
	}		 
	 if(cadena !=""){
		 alert(cadena)
	 }else{
		document.formMenu.submit();
	 }
}

	function abrirFactura(namefile){
		document.abriFactu.filename.value = namefile;
		document.abriFactu.submit();
	}
	
	function verGraficaFacturacion(){
		document.formMenu.services.value = "ListGraficaFacturasSrv";
		document.formMenu.view.value = "factura/listGraficaFacturas.jsp";
		document.formMenu.submit();
	}
	
	function regenera(idfactur){
		document.formRegenera.idfactur.value = idfactur;
		document.formRegenera.submit();
	}
	
	function marcapag(numfactu, totalfac, idfactur, divisa, idcli, tipocli,formpag,pagadox ){
		
		
		/*para volver al listado con el filtro*/
			arclient = document.getElementById("selidcli").value.split("#");
			
			if (arclient != ""){
				tpclient = arclient[0];
				idclient = arclient[1];
			}else{
				tpclient = "";
				idclient = "";
			}
			
			fhdesd = document.getElementById("fhdesd").value;
			fhasta = document.getElementById("fhasta").value;
			impdesde = document.getElementById("impdesde").value;
			imphasta = document.getElementById("imphasta").value;
			document.formMenu.tpclient.value = tpclient;	
			document.formMenu.idcliere.value = idclient;
			
			document.formMenu.fhdesdex.value = fhdesd;
			document.formMenu.fhhastax.value = fhasta;
			document.formMenu.predesde.value = impdesde;
			document.formMenu.prehasta.value = imphasta;
		
		
		/*para volver al listado con el filtro*/
		
		
		
		if(pagadox == "R"){
			alert("No se puede pagar todo, ya que esta factura contiene algun recibo");
		}else{
		
			if(confirm("Se va a proceder a marcar como pagada la factura "+numfactu+" con importe de "+totalfac+" "+ divisa+" \u00bf est\u00e1 seguro?")){
				document.formMenu.idfactur.value = idfactur;
				fhdesd = document.getElementById("fhdesd").value;
				document.formMenu.fhdesdex.value = fhdesd;
				document.formMenu.mcpagado.value = "S";
				document.formMenu.services.value = "PagaFacturaSrv";
				document.formMenu.view.value = "factura/listadoFacturasEmisor.jsp";
				document.formMenu.submit();
			}
		}
		
		//else{
		//	if(formpag == 6 || formpag ==7){
				//listRecibos(idfactur, idcli,numfactu,totalfac,tipocli);
		//	}
		//}
	}
	
	function listRecibos(idfactu, idcli,cdfactur, total,tipocli,pagadoxx,cddivisa,tpfacrec){
		
		document.formRecibos.idfactur.value = idfactu;
		document.formRecibos.idclient.value = idcli;
		document.formRecibos.numfactu.value = cdfactur;
		document.formRecibos.totalfac.value = total;
		document.formRecibos.tpclient.value = tipocli;
		document.formRecibos.mcpagado.value = pagadoxx;
		document.formRecibos.divisaxx.value = cddivisa;
		document.formRecibos.tpfacrec.value = tpfacrec;
		
		document.formRecibos.submit();
	}
	
	function altaNotaCredito(idfactur, factasoc){
		
		document.formNotaCred.idfactur.value = idfactur;
		document.formNotaCred.factasoc.value = factasoc;
		document.formNotaCred.submit();
	}
	
	function borraFactura(idfactur, tpfactur, aniofact){
		
		document.formBorra.idfactur.value = idfactur;
		document.formBorra.tpfactur.value = tpfactur;
		document.formBorra.aniofact.value = aniofact;
		
		alertify.confirm("<p>&iquest;Seguro que desea borrar la factura", function (e) {
			if (e) {
				document.formBorra.submit();
			}
		}); 
		
		
	}
	
	function modFactura(idtmpfra, idfactur, idclient,tpclient, fhfactur,tipofact){
		
		document.formModFact.idtmpfra.value = idtmpfra;
		document.formModFact.idfactur.value = idfactur;
		document.formModFact.idclient.value = idclient;
		document.formModFact.tpclient.value = tpclient;
		document.formModFact.fhfactur.value = fhfactur;
		document.formModFact.tipofact.value = tipofact;
		document.formModFact.tipoOper.value = "LIS";
		
		document.formModFact.submit();
		
	}
	
	
	function compruebaDev(){
		
		
	}
	
	

</script>

	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
		<input type="hidden" name="idusuari" value="1"/>
		<input type="hidden" name="tipofile" value="FRA"/>
		<input type="hidden" name="pathfile" value=""/>
		<input type="hidden" name="filename" value=""/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
	</form>
	
	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" 	value="FacturaHttpHandler"/>
		<input type="hidden" name="services" 	value=""/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
		<input type="hidden" name="aniofact" 	value=""/>
		<input type="hidden" name="tpclient" 	value=""/>
		<input type="hidden" name="idcliere" 	value=""/>
		<input type="hidden" name="fhdesdex" 	value=""/>
		<input type="hidden" name="fhhastax" 	value=""/>
		<input type="hidden" name="predesde" 	value=""/>
		<input type="hidden" name="prehasta" 	value=""/>
		<input type="hidden" name="idfactur" 	value=""/>
		<input type="hidden" name="mcpagado" 	value=""/>
		<input type="hidden" name="divisaxx" 	value=""/>
		
	</form>
	
	<form method="POST" name="formRegenera" action="/JLet/App">
		<input type="hidden" name="controller" 	value="FacturaHttpHandler"/>
		<input type="hidden" name="services" 	value="RegeneraFacturaSrv"/>
		<input type="hidden" name="view" 		value="factura/abrirFactura.jsp"/>
		<input type="hidden" name="idfactur" 	value="">

	</form>
	
	
	<form method="POST" name="formRecibos" action="/JLet/App">
		<input type="hidden" name="controller" 	value="FacturaHttpHandler"/>
		<input type="hidden" name="services" 	value="ListRecibosSrv"/>
		<input type="hidden" name="view" 		value="factura/listadoRecibosFacturas.jsp"/>
		<input type="hidden" name="idfactur" 	value="">
		<input type="hidden" name="idclient" 	value="">
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
		<input type="hidden" name="numfactu" 	value="">
		<input type="hidden" name="totalfac" 	value="">
		<input type="hidden" name="tpclient" 	value="">
		<input type="hidden" name="mcpagado" 	value="">
		<input type="hidden" name="divisaxx" 	value="">
		<input type="hidden" name="tpfacrec" 	value="">
	</form>
	
	<form method="POST" name="formNotaCred" action="/JLet/App">
		<input type="hidden" name="controller"  value="FacturaHttpHandler"/>
		<input type="hidden" name="services" 	value="VistaPreviaDevFacturaSrv"/>
		<input type="hidden" name="view" 		value="factura/vistaPreviaDevFactura.jsp"/>
		<input type="hidden" name="idfactur" 	value="">
		<input type="hidden" name="factasoc" 	value="">
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>">
	</form>
	
	<form method="POST" name="formBorra" action="/JLet/App">
		<input type="hidden" name="controller"  value="FacturaHttpHandler"/>
		<input type="hidden" name="services" 	value="BorraFacturaSrv"/>
		<input type="hidden" name="view" 		value="factura/resulBorra.jsp"/>
		<input type="hidden" name="idfactur" 	value="">
		<input type="hidden" name="tpfactur" 	value="">
		<input type="hidden" name="aniofact" 	value="">
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>">
	</form>
	
	<form method="POST" name="formModFact" action="/JLet/App">
		<input type="hidden" name="controller"  value="FacturaHttpHandler"/>
		<input type="hidden" name="services" 	value="UpdTempFacturaSrv"/>
		<input type="hidden" name="view" 		value="factura/modLineasFactur.jsp"/>
		<input type="hidden" name="idfactur" 	value="">
		<input type="hidden" name="idclient" 	value="">
		<input type="hidden" name="tpclient" 	value="">
		<input type="hidden" name="idtmpfra" 	value="">
		<input type="hidden" name="tipoOper" 	value="">
		<input type="hidden" name="fhfactur" 	value="">
		<input type="hidden" name="tipofact" 	value="">
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>">
	</form>
	
	
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	

<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="nompanta" style="text-align:center">Listado Facturas</div>
	<div class="centradoFac" style="text-align:center">
	<br>
	
		<!-- INICIO FORMULARIO FILTRO -->
		<table width="100%" align="center" >
			<tr>
				<td class="input-b1" width=15% >Cliente :</td>
				<td class="input-m" >
				
				
					<!-- JEJL - InitClinte hacerlo desde un INIT-->
				
					<select id="selidcli" class="input-m2" >
						<option value="" >-- Todos --</option> 
					<% 
				 	 String idclierc = "-1";
					 String tpclient = "0";
				 		for (int i = 0; i < gridClie.rowCount(); i++){
				 			tpclient = gridClie.getStringCell(i,"tpclient");
				 			idclierc = gridClie.getStringCell(i,"idclient");
							if(idclient.equals(idclierc) && tpclirec.equals(tpclient)) {%>
								<option value="<%=tpclient%>#<%=idclierc%>" selected><%= gridClie.getStringCell(i,"rzsocial") %></option>
						<% }else{%>
		  						<option value="<%=tpclient%>#<%=idclierc%>"  ><%= gridClie.getStringCell(i,"rzsocial") %></option>
						<%}
				     	}
					 	%>  
					</select>	
				</td>
				<td class="input-b1" nowrap>Fecha desde</td>
				<td class="input-m" nowrap><input type="text" size=12 id="fhdesd" class="input-m2" style="position:relative;top:-5px"  value="<%=fhdesdex%>"> <img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Factura" id="lanzador"></td>
				<td class="input-b1" nowrap>Fecha hasta</td>
				<td class="input-m" nowrap><input type="text" size=12 id="fhasta" class="input-m2" style="position:relative;top:-5px"   value="<%=fhhastax%>"><img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Factura" id="lanzador2"></td>
			
			</tr>
            <tr>
            	
				<td colspan=6>&nbsp;</td>
			</tr>
			<tr>
				<td class="input-b1" >Devoluci&oacute;n</td>
            	<td><input type="checkbox" id="chkdev" onclick="compruebaDev()"></td>
				<td class="input-b1" nowrap>Importe desde</td>
				<td class="input-m" ><input type="text" size=12 id="impdesde" class="input-m2"   value="<%=predesde%>"></td>
				<td class="input-b1" nowrap>Importe hasta</td>
				<td class="input-m" ><input type="text" size=12 id="imphasta" class="input-m2"  value="<%=prehasta%>"></td>
			</tr>
			<tr>
				<td colspan=6>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=6 align="center" ><a class="boton" style="font-family:Arial, Helvetica, sans-serif" onclick="filtrafactura()">Buscar</a></td>
			</tr>
			<tr>
				<td colspan=6>&nbsp;</td>
			</tr>
		</table>
	
		<!-- FIN FORMULARIO FILTRO -->
	
		<table align="center" class="reportTable" width="100%" border="0" >
			<% 
			
			  String cddivisa = ""; 
			  String antTpFac = "";
			  String tipoClie = "";
			  String logotpcl = "";
			  String mcpagado = "";
			  String formpago = "";
			  String cdestado = "";
			  String admdevol = "";
			  double totBasIm = 0;
			  double totImpue = 0;
			  double totTotal = 0;
			  
			  for (int i = 0; i < grfactur.rowCount(); i++){ 
				  
				  	cddivisa = grfactur.getStringCell(i,"cddivisa");
				  	tipoClie = grfactur.getStringCell(i,"tpclient"); 
				  	cdestado = grfactur.getStringCell(i,"cdestado");
				  	tipofact = grfactur.getStringCell(i,"tipofact");
				  	admdevol = grfactur.getStringCell(i,"admdevol");
				  	
				  	
				  	// Muestro los dos tipos de logos para el emisor Izumba (MallProShop)
				  	if (idemisor.equals("1")) {
				  		if (tipoClie.equals("1")) {
				  			logotpcl = "icomall.jpg";
				  		} else {
				  			logotpcl = "iconizum.jpg";
				  		}
				  	}
				  	
			  		if (!antTpFac.equals(grfactur.getStringCell(i,"tipofact"))){ 

			  			%>
			  			
			  			
			  			<% if (i > 0){ %>
			  			
				  			<tr class="usuario">
								<td colspan="5">&nbsp;</td>
								<td class="fonts6" style="font-size:12px" align="center"><b>TOTAL</b></td>
								<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totBasIm) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
								<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totImpue) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
								<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totTotal) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
							</tr>
							
						<% } %>
			  			
				  		<tr>
				  			<td colspan="100%" ><div class="input-b1"><%= grfactur.getStringCell(i,"txtpfact") %></div></td>
				  		</tr>
				  		
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td><div class="input-b1"> <%=txcdfact%> </div></td>
							<td><div class="input-b1"> Fecha</div></td>
							<td><div class="input-b1"> Cliente </div></td>
							<td><div class="input-b1"> Base Imp.</div></td>
							<td><div class="input-b1"> Impuesto</div></td>
							<td><div class="input-b1"> Total </div></td>
							<td>&nbsp;</td>
						</tr>
				  		
				 <%	
				 		totBasIm = 0;
						totImpue = 0;
						totTotal = 0;
						
						cambia = 1;
			  		
			  		}
			  		antTpFac = grfactur.getStringCell(i,"tipofact");
			  		
			  	%>
			  	
			  	<% if (i % 2 == 0) { %>
			  		<tr class="oddRow" style="border:1px solid">
				<% } else { %>
					<tr class="evenRow" style="border:1px solid">
				<% } %>
				
					 <td align="center" style="cursor:pointer;background-color:#FFFFFF" onclick="altaNotaCredito('<%=grfactur.getStringCell(i,"idfactur")%>','<%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %>')">
					 	
					 	<!-- Facturas NO aptas de devolucion -->
					 	<% 
					 	if (!admdevol.equals("N")) { %>
					 			   
					 		    <% if (cdestado.equals("D")) {%>
							 		<img title="Logo" height="24px" src="/JLet/common/img/icons/icondev.png" style="background-color:#FFFFFF">
							 	<% } else {%>
							 		<div id="devolu" class="devo" style="display:block" style="background-color:#FFFFFF">D</div>
							 	<% } %>
							 	
					 	<% } else { %>
							&nbsp;
						<% } %>
						
					</td>
	
					<!-- Logo MallProShop / Izumbas -->
					<% if(!logotpcl.equals("")){   %>
						<td style="background-color:#FFFFFF">
							<div>
								<img title="Logo" src="/JLet/common/img/icons/<%=logotpcl%>">
							</div>
						</td>
					<% } else { %>
						<td style="background-color:#FFFFFF">
							<div>&nbsp;</div>
						</td>
					<% } %>
					
					
					<td align="center" style="font-size:12px" onclick="abrirFactura('<%=grfactur.getStringCell(i,"filecrea") %>');" style="cursor:pointer">
						<img src="/JLet/common/img/varios/factura.png" height="24px" title="Ver Factura" style="cursor:pointer"/>
					</td>
					<td class="fonts6jej" style="font-size:12px"><%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %></td>
					<td class="fonts6jej" style="font-size:12px" align="center" ><%= grfactur.getStringCell(i,"fhfactur") %></td>
					<td class="fonts6jej" style="font-size:12px"><%= grfactur.getStringCell(i,"rzsocial") %></td>
					<td class="fonts6jej" style="font-size:12px" align="right"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"baseimpo"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<td class="fonts6jej" style="font-size:12px" align="right"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptaxes"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<td class="highlightedColumn" style="font-size:12px" align="right"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptotal"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					
					<%
					
						totBasIm += Double.parseDouble(grfactur.getStringCell(i,"baseimpo"));
						totImpue += Double.parseDouble(grfactur.getStringCell(i,"imptaxes"));
						totTotal += Double.parseDouble(grfactur.getStringCell(i,"imptotal"));
						mcpagado = grfactur.getStringCell(i,"mcpagado");
						formpago = grfactur.getStringCell(i,"formpago");
						
						String imgmoney = "";
						String estadopg = "";
						String tpfacrec = "";
						
						/*DABER SI ES UNA FACTURA CON IMPUESTOS O NO PARA GENERAR DISTINTO CODIGO DE RECIBO*/
						if(totImpue >0 ){
							tpfacrec = "S";
						}else{
							tpfacrec = "N";
						}
						
					  
					%>
					

					<% 
						// REGENERACI�N DE FACTURA (Permiso Especial)
						if (permEmis.containsKey("REGEFACT")){ %>					
						<td onclick="regenera('<%= grfactur.getStringCell(i,"idfactur") %>')" style="cursor:pointer;background-color:#FFFFFF" align="center">
							<img width=20px src="/JLet/common/img/icons/barita.png"/>
						</td>
					<% }  %>
					
					
					 
					<%  // PAGADO
						if (mcpagado.equals("S")){ 
							imgmoney = "money-v.png";
							estadopg = "Pago Total";
					%>					
						<td style="background-color:#FFFFFF" align="center">
							<img width=20px src="/JLet/common/img/icons/<%=imgmoney%>"  title="<%=estadopg%>"/>
						</td>
					<% } else{
							imgmoney = "money-r.png";
							estadopg = "Sin Pagar";
							
							//if(formpago.equals("6") || formpago.equals("7")){
								if (mcpagado.equals("R")){ 
									imgmoney = "money-a.png";
									estadopg = "Pago Parcial";
								}
						//	}
						
						%>
							<td style="cursor:pointer;background-color:#FFFFFF" onclick="marcapag('<%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %>','<%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptotal"))) %>','<%= grfactur.getStringCell(i,"idfactur") %>','<%=cddivisa%>','<%=grfactur.getStringCell(i,"idclient")%>','<%=tipoClie%>','<%=formpago%>','<%=mcpagado%>')" align="center">
								<img width=20px src="/JLet/common/img/icons/<%=imgmoney%>" title="<%=estadopg%>"/>
							</td>
		
						<%}
					// definir como como mostrar los enlaces para generar los recibos
					// solo pagos a crédito??
					if (idemisor.equals("1")){
								
						if (formpago.equals("6")) { %>
							<td style="cursor:pointer;background-color:#FFFFFF" onclick="listRecibos('<%= grfactur.getStringCell(i,"idfactur") %>','<%=grfactur.getStringCell(i,"idclient")%>','<%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %>','<%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptotal"))) %>','<%=tipoClie%>','<%=mcpagado%>','<%= cddivisa %>','<%= tpfacrec %>')">30</td>
					 <% }
						
						if (formpago.equals("7")) { %>
							<td style="cursor:pointer;background-color:#FFFFFF" onclick="listRecibos('<%= grfactur.getStringCell(i,"idfactur") %>','<%=grfactur.getStringCell(i,"idclient")%>','<%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %>','<%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptotal"))) %>','<%=tipoClie%>','<%=mcpagado%>','<%= cddivisa %>','<%= tpfacrec %>')">60</td>
					  <% }
					
					} else {
						if (!mcpagado.equals("S")){ %> 
							<td style="cursor:pointer;background-color:#FFFFFF" onclick="listRecibos('<%= grfactur.getStringCell(i,"idfactur") %>','<%=grfactur.getStringCell(i,"idclient")%>','<%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %>','<%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptotal"))) %>','<%=tipoClie%>','<%=mcpagado%>','<%= cddivisa %>','<%= tpfacrec %>')"><img src="/JLet/common/img/icons/pagoParcial.png" width="20px" title="Alta de Recibos"/></td>
					 <% } else { %>
				    			<td style="cursor:pointer;background-color:#FFFFFF">&nbsp;</td>
				     <% }
						
				    } %>
						
										   
					<% 
						//MODIFICACION DE FACTURA (Permiso Especial)
					   	if (permEmis.containsKey("MODIFACT")){ %>					
							<td style="cursor:pointer;background-color:#FFFFFF" onclick="modFactura('<%= grfactur.getStringCell(i,"idtmpfra") %>', '<%= grfactur.getStringCell(i,"idfactur") %>','<%= grfactur.getStringCell(i,"idclient") %>','<%= grfactur.getStringCell(i,"tpclient") %>','<%= grfactur.getStringCell(i,"fhfactur") %>','<%= grfactur.getStringCell(i,"tipofact") %>')" align="center">
								<img src="/JLet/common/img/icons/modificar.png" width="20px" title="Modificar Factura"/>
							</td>
																				 
						<% 
							for(int x = 0; x < gridMaxF.rowCount(); x++){
								
								String tpfactur = gridMaxF.getStringCell(x, "tipofact");
								String anifactu = gridMaxF.getStringCell(x, "aniofact");
								String cdfactur = gridMaxF.getStringCell(x, "cdfactur");
								
								//System.out.println("tpfactur "+ tpfactur +" VS "+grfactur.getStringCell(i,"tipofact") );
								//System.out.println("anifactu "+ anifactu +" VS "+grfactur.getStringCell(i,"aniofact") );
							//	System.out.println("cdfactur "+ tpfactur +" VS "+ Integer.parseInt(grfactur.getStringCell(i,"cdfactur")) );
								if (tpfactur.equals(grfactur.getStringCell(i,"tipofact")) && anifactu.equals(grfactur.getStringCell(i,"aniofact")) && 
									Integer.parseInt(cdfactur) == Integer.parseInt(grfactur.getStringCell(i,"cdfactur"))) { 
									%>
									<td width="5%" class="fonts6jej" align="center" style="cursor:pointer;background-color:#FFFFFF">
										<img src="common/img/varios/delete.jpg" width=20 height=20 style="cursor:pointer" onclick="borraFactura('<%=grfactur.getStringCell(i,"idfactur")%>','<%=grfactur.getStringCell(i,"tipofact")%>','<%=grfactur.getStringCell(i,"aniofact")%>')" title="Borrar Factura">
									</td>
								<% } 									
						   }
					} %>
						
				</tr>
				<% } %>
				
			<% if (grfactur.rowCount() > 0){ %>
  				  			
				<tr class="usuario">
					<td colspan="5">&nbsp;</td>
					<td class="fonts6" style="font-size:12px" align="center"><b>TOTAL</b></td>
					<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totBasIm) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totImpue) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totTotal) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
				</tr>
				
				
			<% } %>
				
		 </table>
	</div>	
</div>

	<script>
		Calendar.setup({ 
	    	inputField     :"fhdesd",     // id del campo de texto 
	    	ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
	    	button     :    "lanzador"     // el id del botón que lanzará el calendario 
		});
		
		Calendar.setup({ 
		    inputField     :"fhasta",     // id del campo de texto 
		    ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
		    button     :    "lanzador2"     // el id del botón que lanzará el calendario 
		});
	
	</script>	
	

