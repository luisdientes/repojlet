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
	Grid grfactur   = null; 
	Grid gridClie	= null;
	Grid gdAniosx   = null; 
	
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
			System.out.println("Error - al recibir fecha desde");
		}
		
		try{
			fhdesdex = io.getStringValue("fhdesdex");
			if(fhdesdex.equals("null")){
				fhdesdex = "";
			}
		
		}catch(Exception ex) {
			fhdesdex = "";
			System.out.println("Error - al recibir fecha desde");
		}
		try{
			fhhastax = io.getStringValue("fhhastax");
			if(fhhastax.equals("null")){
				fhhastax = "";
			}
		
		}catch(Exception ex) {
			fhhastax ="";
			System.out.println("Error - al recibir fecha desde");
		}
		
		try{
			predesde = io.getStringValue("predesde");
			if(predesde.equals("null")){
				predesde = "";
			}
		
		}catch(Exception ex) {
			predesde = "";
			System.out.println("Error - al recibir precio desde");
		}
		
		try{
			prehasta = io.getStringValue("prehasta");
			if(prehasta.equals("null")){
				prehasta = "";
			}
		
		}catch(Exception ex) {
			prehasta ="";
			System.out.println("Error - al recibir precio hasta");
		}
		
		try{
			tpclirec = io.getStringValue("tpclient");
		}catch(Exception ex) {
			tpclirec ="0";
			System.out.println("Error - al recibir tpclient ");
		}
		
	}
	
	if ((idemisor.equals("1")) || (idemisor.equals("5"))){
		txcdfact = "NCF";
		
	} else {
		txcdfact = "NCF";
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
	
	function marcapag(numfactu, totalfac, idfactur, divisa){
		
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
	
	

</script>

	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
		<input type="hidden" name="idusuari" value="1"/>
		<input type="hidden" name="tipofile" value="FRA"/>
		<input type="hidden" name="pathfile" value=""/>
		<input type="hidden" name="filename" value=""/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
	</form>
	
	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value=""/>
		<input type="hidden" name="view" value=""/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
		<input type="hidden" name="aniofact" value=""/>
		<input type="hidden" name="tpclient" value=""/>
		<input type="hidden" name="idcliere" value=""/>
		<input type="hidden" name="fhdesdex" value=""/>
		<input type="hidden" name="fhhastax" value=""/>
		<input type="hidden" name="predesde" value=""/>
		<input type="hidden" name="prehasta" value=""/>
		<input type="hidden" name="idfactur" value=""/>
		<input type="hidden" name="mcpagado" value=""/>
	</form>
	
	<form method="POST" name="formRegenera" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value="RegeneraFacturaSrv"/>
		<input type="hidden" name="view" 		value="factura/abrirFactura.jsp"/>
		<input type="hidden" name="idfactur" 	value="">

	</form>
	
	
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	

<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="nompanta" >Listado Facturas</div>
	<div class="centradoFac" width="100%">
	<br>
	
		<!-- INICIO FORMULARIO FILTRO -->
		<table width="100%" align="center" >
			<tr>
				<td class="input-b1" width=15% >Cliente :</td>
				<td class="input-m" >
					<select id="selidcli" class="input-m2" >
						<option value="" >-- Todos --</option> 
					<% 
				 	 String idclierc = "-1";
					 String tpclient = "0";
				 		for (int i = 0; i < gridClie.rowCount(); i++){
				 			tpclient = gridClie.getStringCell(i,"tpclient");
				 			idclierc = gridClie.getStringCell(i,"idclient");
							if(idclient.equals(idclierc) && tpclirec.equals(tpclient)) {%>
								<option value="<%=tpclient%>#<%=idclierc%>" selected="true" ><%= gridClie.getStringCell(i,"rzsocial") %></option>
						<% }else{%>
		  						<option value="<%=tpclient%>#<%=idclierc%>"  ><%= gridClie.getStringCell(i,"rzsocial") %></option>
						<%}
				     	}
					 	%>  
					</select>	
				</td>
				<td class="input-b1" >Fecha desde</td>
				<td class="input-m"><input type="text" size=12 id="fhdesd" class="input-m2" style="position:relative;top:-5px"  value="<%=fhdesdex%>"> <img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Factura" id="lanzador"></td>
				<td class="input-b1" >Fecha hasta</td>
				<td class="input-m"><input type="text" size=12 id="fhasta" class="input-m2" style="position:relative;top:-5px"   value="<%=fhhastax%>"><img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Factura" id="lanzador2"></td>
			
			</tr>
            <tr>
				<td colspan=6>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
				<td class="input-b1" >Importe desde</td>
				<td class="input-m" ><input type="text" size=12 id="impdesde" class="input-m2"   value="<%=predesde%>"></td>
				<td class="input-b1" >Importe hasta</td>
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
	
		<table align="center" class="tab-listado" width="100%" >
			<% 
			  String cddivisa = ""; 
			  String antTpFac = "";
			  String tipoClie = "";
			  String logotpcl = "";
			  String mcpagado = "";
			  double totBasIm = 0;
			  double totImpue = 0;
			  double totTotal = 0;
			  
			  for (int i = 0; i < grfactur.rowCount(); i++){ 
				  
				  	cddivisa = grfactur.getStringCell(i,"cddivisa");
				  	tipoClie = grfactur.getStringCell(i,"tpclient"); 
				  	
				  	if(idemisor.equals("1")){
				  		if(tipoClie.equals("1")){
				  			logotpcl = "icomall.jpg";
				  		}else{
				  			logotpcl = "iconizum.jpg";
				  		}
				  	}
				  	
				  	if (cddivisa.equals("€")){
				  	
				  	}
					//cddivisa = "&euro;";
			  		if (!antTpFac.equals(grfactur.getStringCell(i,"tipofact"))){ 

			  			%>
			  			
			  			
			  			<% if (i > 0){ %>
			  			
				  			<tr class="usuario">
								<td colspan="4">&nbsp;</td>
								<td class="fonts6" style="font-size:12px" align="center"><b>TOTAL</b></td>
								<td align="right" class="fonts6" style="font-size:12px;padding:5px"><b> <%= formateador.format(totBasIm) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
								<td align="right" class="fonts6" style="font-size:12px;padding:5px"><b> <%= formateador.format(totImpue) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
								<td align="right" class="fonts6" style="font-size:12px;padding:5px"><b> <%= formateador.format(totTotal) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
							</tr>
							
						<% } %>
			  			
			  			
				  		<tr>
				  			<td colspan="8" ><div class="input-b1"><%= grfactur.getStringCell(i,"txtpfact") %></div></td>
				  		</tr>
						<tr>
							<td><div class="input-b1">&nbsp;</div></td>
							<td><div class="input-b1">&nbsp;</div></td>
							<td><div class="input-b1"> <%=txcdfact%> </div></td>
							<td><div class="input-b1"> Fecha</div></td>
							<td><div class="input-b1"> Cliente </div></td>
							<td><div class="input-b1"> Base Imp.</div></td>
							<td><div class="input-b1"> Impuesto</div></td>
							<td><div class="input-b1"> Total </div></td>
							<td><div class="input-b1">&nbsp;</div></td>
						</tr>
				  		
				 <%	
				 		totBasIm = 0;
						totImpue = 0;
						totTotal = 0;
			  		
			  		}
			  		antTpFac = grfactur.getStringCell(i,"tipofact");
			  		
			  	%>
			  	
				<tr class="usuario">
					<% if(!logotpcl.equals("")){ %>
								<td><div><img title="Logo" src="/JLet/common/img/icons/<%=logotpcl%>"></div></td>
							<%}else{ %>
							<td><div >&nbsp;</div></td>
							<%} %>
					<td align="center" class="fonts5" style="font-size:12px" onclick="abrirFactura('<%=grfactur.getStringCell(i,"filecrea") %>');" style="cursor:pointer">
						<img src="/JLet/common/img/varios/factura.png" height="24px" title="Factura" style="cursor:pointer"/>
					</td>
					<td class="fonts6" style="font-size:12px"><%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %></td>
					<td align="center" class="fonts6" style="font-size:12px"><%= grfactur.getStringCell(i,"fhfactur") %></td>
					<td class="fonts6" style="font-size:12px"><%= grfactur.getStringCell(i,"rzsocial") %></td>
					<td align="right" class="fonts6" style="font-size:12px"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"baseimpo"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<td align="right" class="fonts6" style="font-size:12px"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptaxes"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<td align="right" class="fonts6" style="font-size:12px"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptotal"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					
					<%
					
						totBasIm += Double.parseDouble(grfactur.getStringCell(i,"baseimpo"));
						totImpue += Double.parseDouble(grfactur.getStringCell(i,"imptaxes"));
						totTotal += Double.parseDouble(grfactur.getStringCell(i,"imptotal"));
						mcpagado = grfactur.getStringCell(i,"mcpagado");
					%>
					
					
					<% if (permEmis.containsKey("REGEFACT")){ %>					
						<td style="cursor:pointer" onclick="regenera('<%= grfactur.getStringCell(i,"idfactur") %>')"><img width=20px src="/JLet/common/img/icons/barita.png"/></td>
					<% }  %>
					
					<% if (mcpagado.equals("S")){ %>					
						<td><img width=20px src="/JLet/common/img/icons/money-v.png"/></td>
					<% } else{ %>
					<td style="cursor:pointer" onclick="marcapag('<%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %>','<%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptotal"))) %>','<%= grfactur.getStringCell(i,"idfactur") %>','<%=cddivisa%>')"><img width=20px src="/JLet/common/img/icons/money-r.png"/></td>
					<% }  %>
					
				</tr>
			<% } %>
				
			<% if (grfactur.rowCount() > 0){ %>
  			
	  			<tr>
					<td colspan="4">&nbsp;</td>
					<td class="fonts6" style="font-size:12px" align="center"><b>TOTAL</b></td>
					<td align="right" class="fonts6" style="font-size:12px;padding:5px"><b> <%= formateador.format(totBasIm) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					<td align="right" class="fonts6" style="font-size:12px;padding:5px"><b> <%= formateador.format(totImpue) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					<td align="right" class="fonts6" style="font-size:12px;padding:5px"><b> <%= formateador.format(totTotal) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
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
	

