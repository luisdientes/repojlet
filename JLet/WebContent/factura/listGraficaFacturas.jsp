<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>

<%

	String aniofact = null;
	String idemisor = null;
	String fhdesdex = null;
	String fhhastax = null;
	String predesde = null;
	String prehasta = null;
	String idclient = null;
	Grid grfactur   = null; 
	Grid gridClie	= null; 
	
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
	}
	
	if ((idemisor.equals("1")) || (idemisor.equals("5"))){
		txcdfact = "NCF";
		
	} else {
		txcdfact = "NCF";
	}
%>

<script>

function abrirFactura(namefile){
	document.abriFactu.filename.value = namefile;
	document.abriFactu.submit();
}

function filtrafactura(){
	
	var cadena = "";
	idclie = document.getElementById("selidcli").value;
	fhdesd = document.getElementById("fhdesd").value;
	fhasta = document.getElementById("fhasta").value;
	impdesde = document.getElementById("impdesde").value;
	imphasta = document.getElementById("imphasta").value;
	
	document.formMenu.services.value = "ListFacturasEmisorSrv";
	document.formMenu.view.value = "factura/listadoFacturasEmisor.jsp";		
	document.formMenu.idcliere.value = idclie;
	document.formMenu.aniofact.value = "2014";
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
		document.formMenu.view.value = "ListGraficaFacturas.jsp";
		document.formMenu.submit();
	}

</script>

	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
		<input type="hidden" name="idusuari" value="1"/>
		<input type="hidden" name="tipofile" value="FRA"/>
		<input type="hidden" name="pathfile" value=""/>
		<input type="hidden" name="filename" value=""/>
	</form>
	
	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value=""/>
		<input type="hidden" name="view" value=""/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
		<input type="hidden" name="aniofact" value=""/>
		<input type="hidden" name="idcliere" value=""/>
		<input type="hidden" name="fhdesdex" value=""/>
		<input type="hidden" name="fhhastax" value=""/>
		<input type="hidden" name="predesde" value=""/>
		<input type="hidden" name="prehasta" value=""/>
	</form>
	

<div class="fondo1">
<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="centradoFac" width="80%">
	
		<!-- INICIO FORMULARIO FILTRO -->
		<table width="80%" align="center" >
			<tr>
				<td class="input-txt-b" >Cliente :</td>
				<td class="input-m" >
					<select id="selidcli" class="input-m" >
						<option value="" >-- Todos --</option> 
					<% 
				 	 String idclierc ="-1";
				 		for (int i = 0; i < gridClie.rowCount(); i++){
					 	 	idclierc = gridClie.getStringCell(i,"idclient");
							if(idclient.equals(idclierc)) {%>
								<option value="<%=idclierc%>" selected="true" ><%= gridClie.getStringCell(i,"rzsocial") %></option>
						<% }else{%>
		  						<option value="<%=idclierc%>"  ><%= gridClie.getStringCell(i,"rzsocial") %></option>
						<%}
				     	}
					 	%>  
					</select>	
				</td>
				<td class="input-txt-b" >Fecha desde</td>
				<td class="input-m"><input type="text" size=12 id="fhdesd" class="input-m"  value="<%=fhdesdex%>"> <img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Factura" id="lanzador"></td>
				<td class="input-txt-b" >Fecha Hasta</td>
				<td class="input-m"><input type="text" size=12 id="fhasta" class="input-m"  value="<%=fhhastax%>"><img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Factura" id="lanzador2"></td>
			</tr>
            <tr>
				<td colspan=6>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" >&nbsp;</td>
				<td class="input-txt-b" >Importe desde</td>
				<td class="input-m" ><input type="text" size=12 id="impdesde" class="input-m"   value="<%=predesde%>"></td>
				<td class="input-txt-b" >Importe Hasta</td>
				<td class="input-m" ><input type="text" size=12 id="imphasta" class="input-m"  value="<%=prehasta%>"></td>
			</tr>
			<tr>
				<td colspan=6>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=6>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=6 align="center" ><a class="boton" style="font-family:Arial, Helvetica, sans-serif" onclick="filtrafactura()">Buscar</a></td>
			</tr>
		</table>
	
		<!-- FIN FORMULARIO FILTRO -->
	
		<table width=80% align="center" class="tab-login">
			<% 
			  String cddivisa = ""; 
			  String antTpFac = "";
			  double totBasIm = 0;
			  double totImpue = 0;
			  double totTotal = 0;
			  
			  for (int i = 0; i < grfactur.rowCount(); i++){ 
				  
				  	cddivisa = grfactur.getStringCell(i,"cddivisa");
				  
			  		if (!antTpFac.equals(grfactur.getStringCell(i,"tipofact"))){ 

			  			%>
			  			
			  			
			  			<% if (i > 0){ %>
			  			
				  			<tr class="usuario">
								<td colspan="3">&nbsp;</td>
								<td class="fonts5" style="font-size:12px" align="center"><b>TOTAL</b></td>
								<td align="right" class="fonts5" style="font-size:12px"><b> <%= formateador.format(totBasIm) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
								<td align="right" class="fonts5" style="font-size:12px"><b> <%= formateador.format(totImpue) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
								<td align="right" class="fonts5" style="font-size:12px"><b> <%= formateador.format(totTotal) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
							</tr>
							
						<% } %>
			  			
			  			
				  		<tr>
				  			<td colspan="7"><div class="cabecera input-b"><%= grfactur.getStringCell(i,"txtpfact") %></div></td>
				  		</tr>
						<tr>
							<td><div class="cabecera input-b1">&nbsp;</div></td>
							<td><div class="cabecera input-b1"> <%=txcdfact%> </div></td>
							<td><div class="cabecera input-b1"> Fecha</div></td>
							<td><div class="cabecera input-b1"> Cliente </div></td>
							<td><div class="cabecera input-b1"> Base Imp.</div></td>
							<td><div class="cabecera input-b1"> Impuesto</div></td>
							<td><div class="cabecera input-b1"> Total </div></td>
						</tr>
				  		
				 <%	
				 		totBasIm = 0;
						totImpue = 0;
						totTotal = 0;
			  		
			  		}
			  		antTpFac = grfactur.getStringCell(i,"tipofact");
			  		
			  	%>
			  	
				<tr class="usuario">
					<td align="center" class="fonts5" style="font-size:12px" onclick="abrirFactura('<%=grfactur.getStringCell(i,"filecrea") %>');" style="cursor:pointer">
						<img src="/JLet/common/img/varios/factura.png" height="24px" title="Factura" style="cursor:pointer"/>
					</td>
					<td class="fonts5" style="font-size:12px"><%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %></td>
					<td align="center" class="fonts5" style="font-size:12px"><%= grfactur.getStringCell(i,"fhfactur") %></td>
					<td class="fonts5" style="font-size:12px"><%= grfactur.getStringCell(i,"rzsocial") %></td>
					<td align="right" class="fonts5" style="font-size:12px"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"baseimpo"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<td align="right" class="fonts5" style="font-size:12px"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptaxes"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<td align="right" class="fonts5" style="font-size:12px"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptotal"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					
					<%
						totBasIm += Double.parseDouble(grfactur.getStringCell(i,"baseimpo"));
						totImpue += Double.parseDouble(grfactur.getStringCell(i,"imptaxes"));
						totTotal += Double.parseDouble(grfactur.getStringCell(i,"imptotal"));
					
					%>
					
				</tr>
			<% } %>
				
			<% if (grfactur.rowCount() > 0){ %>
  			
	  			<tr class="usuario">
					<td colspan="3">&nbsp;</td>
					<td class="fonts5" style="font-size:12px" align="center"><b>TOTAL</b></td>
					<td align="right" class="fonts5" style="font-size:12px"><b> <%= formateador.format(totBasIm) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					<td align="right" class="fonts5" style="font-size:12px"><b> <%= formateador.format(totImpue) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					<td align="right" class="fonts5" style="font-size:12px"><b> <%= formateador.format(totTotal) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
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
	

