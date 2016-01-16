<%@ include file="/common/jsp/include.jsp"%>
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>
<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script type="text/javascript" src="comercio/js/altaEnvio.js"/></script>

<%
 // variable para rellenar arrays
	String codeenvi = "";
	String idempres = "";
	String txrazons = "";
	String tfnocon = "";

	Grid grEmpres   = null;
	Grid gridDeta   = null;
	
	//variables para recoger datos del grid
	String idtradet = "";
	String empresid = "";
	String medenvio = "";
	String txidenti = "";
	String perconta = "";
	String telefono = "";
	String txmailxx = "";
	String precioen = "";
	String impuesto = "";
	String txdivisa = "";
	String pagenvio = "";
	String totalenv = "";
	String fhrecogi = "";
	String horareco = "";
	String fhentreg = "";
	String horaentr = "";
	String cdestado = "";
	String tfnocont = "";

	
	
	
	
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			codeenvi = io.getStringValue("codeenvi"); 
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Al.jsp "+ e.getMessage());	
		}
		try {
			grEmpres = io.getGrid("grEmpres");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo grEmpres en detalleEnvio "+ e.getMessage());	
		}
		try {
			gridDeta = io.getGrid("gridDeta");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo  gridDeta en detalleEnvio "+ e.getMessage());	
		}
		
			
			
		
	}
	
	
	if( gridDeta!=null && gridDeta.rowCount()>0){
		
		idtradet = gridDeta.getStringCell(0, "idtradet");
		codeenvi = gridDeta.getStringCell(0, "idenviox");
		empresid = gridDeta.getStringCell(0, "idempres");
		medenvio = gridDeta.getStringCell(0, "medenvio");
		txidenti = gridDeta.getStringCell(0, "txidenti");
		perconta = gridDeta.getStringCell(0, "perconta");
		tfnocont = gridDeta.getStringCell(0, "tfnocont");
		txmailxx = gridDeta.getStringCell(0, "txmailxx");
		precioen = gridDeta.getStringCell(0, "precioen");
		impuesto = gridDeta.getStringCell(0, "impuesto");
		txdivisa = gridDeta.getStringCell(0, "txdivisa");
		pagenvio = gridDeta.getStringCell(0, "pagenvio");
		totalenv = gridDeta.getStringCell(0, "totalenv");
		fhrecogi = gridDeta.getStringCell(0, "fhrecogi");
		horareco = gridDeta.getStringCell(0, "horareco");
		fhentreg = gridDeta.getStringCell(0, "fhentreg");
		horaentr = gridDeta.getStringCell(0, "horaentr");
		cdestado = gridDeta.getStringCell(0, "cdestado");
		
		
		if(fhrecogi==null || fhrecogi.equals("00/00/0000")){
			fhrecogi = "";
		}
		
		if(fhentreg==null || fhentreg.equals("00/00/0000")){
			fhentreg = "";
		}
		
	}
	

%>


<head>

	<script>
	
	var arrIdEmpr = new Array();
	var arrTipoEm = new Array();
	var arrTelefo = new Array();
	
	i=0;
	
		
		<%
		if( grEmpres!=null && grEmpres.rowCount()>0){
		for( int i=0;i<grEmpres.rowCount();i++ ){
			idempres = grEmpres.getStringCell(i, "idempres");
			txrazons = grEmpres.getStringCell(i, "txrazons");
			tfnocon = grEmpres.getStringCell(i, "telefono");
		%>
		
		arrIdEmpr[i] = "<%=idempres%>";
		arrTelefo[i] = "<%=tfnocon%>";
		i++;
	
		<%	}
		}
		%>

	</script>
	
</head>


<body>

	<div class="fondo1">
    <div class="testata"><img src="/JLet/common/img/icons/icon-title-altaenvio.png"></div>
		<div class="centrado4" style="width:85%; margin-left:8%; height:750px;">
			<form name="formAltaDetEnvio" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
				<input type="hidden" name="services"	value="AltaDetalleEnvioSrv"/>
				<input type="hidden" name="view" 		value="comercio/resultCreaEnvio.jsp"/>
				<input type="hidden" name="codeenvi" 	value="<%=codeenvi%>"/>
				<input type="hidden" name="idtradet" 	value="<%=idtradet%>"/>
				
				<br>
				<br>
			
				<table border="0" width="70%" align="center" border="1">
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:340px;">Empresa</td>
						<td class="input-m">
							<select name="idempres" class="input-m" onchange="buscaEmpr(this.value)">
							<option value="0">Seleccione Empresa</option>
							<%
							
							if( grEmpres!=null && grEmpres.rowCount()>0){
								for( int i=0;i<grEmpres.rowCount();i++ ){
									idempres = grEmpres.getStringCell(i, "idempres");
									txrazons = grEmpres.getStringCell(i, "txrazons");
									if(empresid.equals(idempres) ){	
							%>		
									<option value="<%=idempres %>"  selected="true"><%=txrazons %></option>
								<%	}else{%>
									<option value="<%=idempres %>"><%=txrazons %></option>
								<% 	}
								}
							}
							%>
							
							</select>
						</td>
					</tr>
					<tr>	
						<td align="center" class="input-txt-b" style="width:270px;">Medio envío</td>
						<td class="input-m">
						
						<select name="medenvio" class="input-m" onchange="buscaEmpr(this.value)">
							<option value="0">Seleccione medio envío</option>
				
							<option value="A" <% if(medenvio.equals("A") ){%> selected="true" <%} %> >Aérea</option>
							<option value="M" <% if(medenvio.equals("M") ){%> selected="true" <%} %>>Marítima</option>
							<option value="T" <% if(medenvio.equals("T") ){%> selected="true" <%} %>>Terrestre</option>
						</select>	
						</td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:340px;">Identificador</td>
						<td class="input-m"><input style="width:300px;" type="text" value="<%=txidenti%>" name="txidenti"  size="44" class="input-m"/></td>
						<td >&nbsp;</td>
					</tr>
					<tr class="fonts">
						<td align="center">&nbsp;</td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:140px;">Persona contacto</td>
						<td class="input-m"><input  type="text" name="perconta" value="<%=perconta%>" size="44" class="input-m"/></td>
					</tr>
					<tr>		
						<td align="center" class="input-txt-b" style="width:140px;"> Tel&eacute;fono de contacto</td>
						<td class="input-m" ><input type="text" name="tfnocont" value="<%=tfnocont%>" maxlength="12"  size="44" class="input-m"/></td>
					</tr>
					<tr>
						<td align="center" class="input-txt-b" style="width:140px;">E-Mail</td>
						<td class="input-m"><input type="text" name="txmailxx" value="<%=txmailxx%>"  class="input-m" size="44"/></td>	
					</tr>	
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:140px;">Precio</td>
						<td class="input-m" ><input type="text" name="precioen" value="<%=precioen%>"  onchange="calculaTotal()" size="44" class="input-m"/></td>
						<td align="center">&nbsp;</td>
						<td class="input-txt-b" style="width:190px;">Impuestos</td>
						<td class="input-m" ><input style="width:150px" type="text" name="impuesto" value="<%=impuesto%>"  onchange="calculaTotal()"  size="44" class="input-m"/></td>	
						<td align="center" class="input-txt-b" style="width:140px;">Divisa </td>
						<td class="input-m">
							<select name="txdivisa" class="input-m">
								<option value="RD"  <% if(txdivisa.equals("RD") ){%> selected="true" <%} %> >RD$</option>
								<option value="CH"  <% if(txdivisa.equals("CH") ){%> selected="true" <%} %>>CH</option>
								<option value="EUR" <% if(txdivisa.equals("EUR") ){%> selected="true" <%} %>>EUR</option>
								<option value="USD" <% if(txdivisa.equals("USD") ){%> selected="true" <%} %>>USD</option>
							</select>
						</td>
							
					</tr>
					<tr class="fonts">
						<td align="center">&nbsp;</td>
					</tr>
						<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:140px;">Pago del envío en: </td>
						<td class="input-m" >
							<select name="pagenvio" class="input-m" style="width:240px;">
								<option value='O' <% if(pagenvio.equals("O") ){%> selected="true" <%} %>>Origen</option>
								<option value='D' <% if(pagenvio.equals("D") ){%> selected="true" <%} %>>Destino</option>
							</select>
						
						</td>
						<td align="center">&nbsp;</td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:140px;">Total: </td>
						<td class="input-m" ><input type="text" name="totalenv" value="<%=totalenv%>"  size="44" class="input-m"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:140px;">Fecha recogida: </td>
						<td class="input-m"><input type="text" id="fhrecogi" name="fhrecogi" value="<%=fhrecogi%>" size="20" class="input-m"/><img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha recogida" id="lanzador"></td>
						<td>&nbsp;</td>
						<td class="input-txt-b">Hora recogida</td>
						<td class="input-m">
							<select name="horareco" class="input-m">	
									<%
								String cadminuto = "";
								String cadhoraxx = "";
								String horarecog = "";
								for (int hora=0; hora < 24;hora++){
									
									cadhoraxx = String.valueOf(hora);
									if(cadhoraxx.length()< 2 ){
										cadhoraxx ="0"+cadhoraxx;
									}
									
									for( int minuto =0;minuto<60; minuto++){
										
										cadminuto = String.valueOf(minuto);
										if(cadminuto.length()< 2 ){
											cadminuto ="0"+cadminuto;
										}
										horarecog = cadhoraxx+":"+cadminuto;
										%>
										<option value ="<%=horarecog %>" <% if(horareco.equals(horarecog)) {%> selected="true" <%} %>><%=cadhoraxx %>:<%=cadminuto %></option>
										<% 
									}
								
								}  %>
							
							</select>
						
						
						<td>&nbsp;</td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:140px;">Fecha entrega: </td>
						<td class="input-m"><input type="text" id="fhentreg" name="fhentreg" value="<%=fhentreg%>"   size="20" class="input-m"/><img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha entrega" id="lanzador2"></td>
						<td>&nbsp;</td>
						<td class="input-txt-b">Hora entrada</td>
						<td class="input-m">
							<select name="horaentr" class="input-m">
							<%
								String horentra = "";
								for (int hora=0; hora < 24;hora++){
									
									cadhoraxx = String.valueOf(hora);
									if(cadhoraxx.length()< 2 ){
										cadhoraxx ="0"+cadhoraxx;
									}
									
									for( int minuto =0;minuto<60; minuto++){
										
										cadminuto = String.valueOf(minuto);
										if(cadminuto.length()< 2 ){
											cadminuto ="0"+cadminuto;
										}
										horentra = cadhoraxx+":"+cadminuto;
										%>
										<option value ="<%= horentra %>" <% if(horaentr.equals(horentra)) {%> selected="true" <%} %>><%=cadhoraxx %>:<%=cadminuto %></option>
										<% 
									}
								
								}  %>
							
							</select>
						<td>&nbsp;</td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:270px;">Estado: </td>
						<td class="input-m">
							<select name="cdestado" class="input-m">
								<option value="SO" <% if(cdestado.equals("SO") ){%> selected="true" <%} %>>Solicitado</option>
								<option value="RE" <% if(cdestado.equals("RE") ){%> selected="true" <%} %>>Recogido / en tránsito</option>
								<option value="EN" <% if(cdestado.equals("EN") ){%> selected="true" <%} %>>Entregado</option>
							</select>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>		
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>						
					</tr>
				</table>
	
			</form>
			
			<table width="100%" align="center">
				<tr>
					<td align="center">
						<a class="boton button-envia" style="margin-left:0%; font-family:Arial, Helvetica, sans-serif; font-size:18px;" onClick="validarDetalle()">Aceptar</a>
					</td>
				</tr>
					<tr>
						<td>&nbsp;</td>						
					</tr>
						<tr>
						<td>&nbsp;</td>						
					</tr>
			</table>
					
		</div>
	</div>
	
	<script>
		Calendar.setup({ 
	    	inputField     :"fhrecogi",     // id del campo de texto 
	    	ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
	    	button     :    "lanzador"     // el id del botón que lanzará el calendario 
		});
		
		Calendar.setup({ 
		    inputField     :"fhentreg",     // id del campo de texto 
		    ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
		    button     :    "lanzador2"     // el id del botón que lanzará el calendario 
		});
	
	</script>
</body>