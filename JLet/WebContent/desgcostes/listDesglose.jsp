<%@ include file="/common/jsp/include.jsp"%>
<link rel="stylesheet" href="common/css/calendario.css" type="text/css"/>
<script type="text/javascript" src="common/js/calendar.js"></script>
<script type="text/javascript" src="common/js/calendar-es.js"/></script>
<script type="text/javascript" src="common/js/calendar-setup.js"/></script>

<%
	String idemisor = "";
	String fhdesdex = "";
	String fhhastax = "";
	String tipocons = "";
	String filename = "";
	String filepdfx = "";
	String idunicox = "";
	String txmodelo = "";
	String clasexxx = "";
	String canalven = "";
	String txpaisxx = "";
	String fhventax = "";
	String catotalx = "";
	String cptotalx = "";
	String cttotalx = "";
	String vatotalx = "";
	String vptotalx = "";
	String vttotalx = "";
	String igtotalx = "";
	String cddivisa = "";

	
	double dbcatota = 0;
	double dbcptota = 0;
	double dbcttota = 0;
	double dbvatota = 0;
	double dbvptota = 0;
	double dbvttota = 0;
	
	double dbcostto = 0;
	
	double dbigtota = 0;
	double dbbenefi = 0;
	double porcbene = 0;
	
	
	Grid gdDesglo   = null;
	Grid gdApuntes   = null;
	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	DecimalFormat formatPorc = new DecimalFormat("##0.00%");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			fhdesdex = io.getStringValue("fhdesdex");
			fhhastax = io.getStringValue("fhhastax");
			tipocons = io.getStringValue("tipocons");
			filename = io.getStringValue("filename");
			filepdfx = io.getStringValue("filepdfx");
			canalven = io.getStringValue("canalven");
			txpaisxx = io.getStringValue("txpaisxx");
			cddivisa = io.getStringValue("cddivisa");
			gdDesglo = io.getGrid("gdDesglo");
			
			if(txpaisxx == null){
				txpaisxx = "";
			}
			if(canalven == null){
				canalven = "";
			}
			
			if(fhdesdex == null){
				fhdesdex = "";
			}
			if(fhhastax == null){
				fhhastax = "";
			}
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ALTA APUNTES "+ e.getMessage());	
		}
	}

%>

<script>

	
	function filtra(){
		
		document.formBusca.submit();
	}
	
	function exportarDatos(tipofile){
		
		document.formBusca.tipocons.value = tipofile;
		document.formBusca.submit();
		
	}
	
	function initFormulario() {
		
		element = document.getElementById("canalven");
				
	   	for (i = 0; i < element.length; i++) {
			if (element[i].value == '<%=canalven%>') {
	    		element[i].selected = true;
	      	}   
	   	}	

	}
	
	
	
function calendarSetup(){
	
	Calendar.setup({ 
		inputField : "fhdesdex",    // id del campo de texto 
		ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
		button     : "lanzador"     // el id del botón que lanzará el calendario 
	});
	
	Calendar.setup({ 
		inputField : "fhhastax",    // id del campo de texto 
		ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
		button     : "lanzador2"     // el id del botón que lanzará el calendario 
	});
}
</script>


<head>

	
</head>


<body onload="calendarSetup();initFormulario();">

	<div class="centrado-all">
			<table width="50%" align="center">
				<tbody>
						<tr>
							<td align="center" colspan=2><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>
						</tr>
						<tr>
							<td align="center" colspan=2 class="nompanta">Listado desglose costes</td>
						</tr>
						
						<tr>
							<td align="center" ><span style="cursor:pointer" onclick="descargarExcel('<%=filename%>','XLSX')"><img src="/JLet/common/img/varios/iconExcel.png"></span></td>
							<td align="center"><span style="cursor:pointer" onclick="descargarExcel('<%=filepdfx%>','FRA')"><img src="/JLet/common/img/varios/iconPDF.png"> </span></td>
						</tr>
						<tr>
							<td align="center" ><span style="cursor:pointer" onclick="descargarExcel('<%=filename%>','XLSX')">Exportar a Excel</span></td>
							<td align="center" ><span style="cursor:pointer" onclick="descargarExcel('<%=filepdfx%>','FRA')"> Exportar a PDF</span></td>
						</tr>
				</tbody>
			</table>
	
			<br>
			<br>
			<br>
			
			<form method="POST" name="formBusca" action="/JLet/App">
				<input type="hidden" name="controller" 	value="DesgCostesHttpHandler"/>
				<input type="hidden" name="services" 	value="ListDesgloseSrv"/>
				<input type="hidden" name="view" 		value="desgcostes/listDesglose.jsp"/>
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
				<input type="hidden" name="tipocons" 	value="WEB"/>
				
			
				<table align="center" width=60%>
					<tr>
						
						<td><b>Fecha Desde :</b></td>
						<td><input type="text" name="fhdesdex" style="width:110px" id="fhdesdex" value="<%=fhdesdex %>" class="input-m">&nbsp;&nbsp;<img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:6px" width="24" height="24" border="0" title="Fecha Factura" id="lanzador"></td>
						<td><b>Fecha Hasta :</b></td>
						<td><input type="text" name="fhhastax" style="width:110px" id="fhhastax" value="<%=fhhastax %>" class="input-m">&nbsp;&nbsp;<img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:6px" width="24" height="24" border="0" title="Fecha Factura" id="lanzador2"></td>
				
					</tr>
					<tr>
						
						<td><b>Canal Venta :</b></td>
						<td>
							<select id="canalven" name="canalven" style="width:110px;cursor:pointer" >
								<option value="">&nbsp;</option>
								<option value="AMAZON">AMAZON (Todos Marketplace)</option>
								<option value="AMAZON (ES)">AMAZON ES</option>
								<option value="AMAZON (FR)">AMAZON FR</option>
								<option value="AMAZON (DE)">AMAZON DE</option>
								<option value="AMAZON (IT)">AMAZON IT</option>
								<option value="AMAZON (UK)">AMAZON UK</option>
								<option value="EBAY">EBAY</option>
								<option value="MILANUNCIOS">MIL ANUNCIOS</option>
								<option value="TELEFONO">TELEFONO</option>
								<option value="PERSONAL">PERSONAL</option>
								<option value="OTROS">OTROS</option>
							</select>
						</td>
						<td><b>Pais :</b></td>
						<td>
							<select name="txpaisxx">
								<option value="DE" <% if(txpaisxx.equals("DE")){%>selected<%} %>>Alemania</option>
								<option value="ES" <% if(txpaisxx.equals("ES")){%>selected<%} %>>Espa&ntilde;a</option>
								<option value="FR" <% if(txpaisxx.equals("FR")){%>selected<%} %>>Francia</option>
								<option value="GB" <% if(txpaisxx.equals("GB")){%>selected<%} %>>Gran Breta&ntilde;a</option>
								<option value="IT" <% if(txpaisxx.equals("IT")){%>selected<%} %>>Italia</option>
								<option value="OT" <% if(txpaisxx.equals("OT")){%>selected<%} %>>Otros</option>
								<option value="" <% if(txpaisxx.equals("")){%>selected<%} %>>Todos</option>
							</select>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><a class="boton" onclick="filtra()">Buscar</a></td>
					</tr>
				</table>
				</form>
				
				<br>
				<br>
				
				<%if(gdDesglo.rowCount() >0){ %>
				
					<table align="center" class="listado-tab">
						<tr>
							<td colspan="6">&nbsp;</td>
							<td colspan="3" class="cabecera input-b1" align="center">Compra</td>
							<td colspan="3" class="cabecera input-b1" align="center">Venta</td>
						</tr>
						<tr>
							<td class="cabecera input-b1" style="font-size:10px">IMEI</td>
							<td class="cabecera input-b1" style="font-size:10px">Modelo</td>
							<td class="cabecera input-b1" style="font-size:10px">Clase</td>
							<td class="cabecera input-b1" style="font-size:10px">Canal Venta</td>
							<td class="cabecera input-b1" style="font-size:10px" nowrap>País dest.</td>
							<td class="cabecera input-b1" style="font-size:10px">F. venta</td>
							<td class="cabecera input-b1" style="font-size:10px">Aduana</td>
							<td class="cabecera input-b1" style="font-size:10px">Producto</td>
							<td class="cabecera input-b1" style="font-size:10px">Transporte</td>
							<td class="cabecera input-b1" style="font-size:10px">Aduana</td>
							<td class="cabecera input-b1" style="font-size:10px">Producto</td>
							<td class="cabecera input-b1" style="font-size:10px">Transporte</td>
							<td class="cabecera input-b1" style="font-size:10px">Ingreso</td>
							<td class="cabecera input-b1" style="font-size:10px">C. Total</td>
							<td class="cabecera input-b1" style="font-size:10px">Beneficio</td>
							<td class="cabecera input-b1" style="font-size:10px" nowrap>% Benef.</td>
						</tr>
					
						<% for (int i = 0; i < gdDesglo.rowCount(); i++) {%>
						
							<% if (i % 2 == 0) { %>
				  				<tr class="oddRow" style="border:1px solid">
							<% } else { %>
								<tr class="evenRow" style="border:1px solid">
							<% } 
						
					
							idunicox = gdDesglo.getStringCell(i, "idunicox");
							txmodelo = gdDesglo.getStringCell(i, "txmodelo");
							clasexxx = gdDesglo.getStringCell(i, "idcatego");
							canalven = gdDesglo.getStringCell(i, "canalven");
							txpaisxx = gdDesglo.getStringCell(i, "txpaisxx");
							fhventax = gdDesglo.getStringCell(i, "fhventax");
							
							catotalx = gdDesglo.getStringCell(i, "catotalx");
							cptotalx = gdDesglo.getStringCell(i, "cptotalx");
							cttotalx = gdDesglo.getStringCell(i, "cttotalx");
							
							vatotalx = gdDesglo.getStringCell(i, "vatotalx");
							vptotalx = gdDesglo.getStringCell(i, "vptotalx");
							vttotalx = gdDesglo.getStringCell(i, "vttotalx");
							
							igtotalx = gdDesglo.getStringCell(i, "igtotalx");
							
							
							/*SUMAMOS COSTES*/
							dbcatota =  Double.parseDouble(catotalx);
							dbcptota =  Double.parseDouble(cptotalx);
							dbcttota =  Double.parseDouble(cttotalx);
							dbvatota =  Double.parseDouble(vatotalx);
							dbvptota =  Double.parseDouble(vptotalx);
							dbvttota =  Double.parseDouble(vttotalx);
							
							dbcostto = dbcatota + dbcptota + dbcttota + dbvatota + dbvptota + dbvttota;
							dbigtota =  Double.parseDouble(igtotalx);
							dbbenefi = dbigtota - dbcostto;
							
							porcbene = dbbenefi / dbigtota;
	
						%>
					
							<td style="font-size:10px"><%=idunicox %></td>
							<td style="font-size:10px"><%=txmodelo %></td>
							<td style="font-size:10px">
								<% if (clasexxx!=null && !clasexxx.equals("R") && !clasexxx.equals("")) { %>
									Clase <%=clasexxx %> 
								<% } else if (clasexxx!=null && !clasexxx.equals("R")) {%>
									Refurbished
								<% } else {%>
									<%=clasexxx %>
								<% } %> 
							</td>
							<td style="font-size:10px"><%=canalven %></td>
							<td align="center" style="font-size:10px"><%=txpaisxx %></td>
							<td align="center" style="font-size:10px"><%=fhventax %></td>
							<td align="right" class="highlightedColumn" style="font-size:10px"><%=formateador.format(dbcatota) %> <%=cddivisa %></td>
							<td align="right" class="highlightedColumn" style="font-size:10px"><%=formateador.format(dbcptota) %> <%=cddivisa %></td>
							<td align="right" class="highlightedColumn" style="font-size:10px"><%=formateador.format(dbcttota) %> <%=cddivisa %></td>
							<td align="right" class="highlightedColumn" style="font-size:10px"><%=formateador.format(dbvatota) %> <%=cddivisa %></td>
							<td align="right" class="highlightedColumn" style="font-size:10px"><%=formateador.format(dbvptota) %> <%=cddivisa %></td>
							<td align="right" class="highlightedColumn" style="font-size:10px"><%=formateador.format(dbvttota) %> <%=cddivisa %></td>
							
							<td align="right" class="highlightedColumn" style="font-size:10px"><%=formateador.format(dbigtota) %> <%=cddivisa %></td>
							<td align="right" class="highlightedColumn" style="font-size:10px"><%=formateador.format(dbcostto) %> <%=cddivisa %></td>
							<td align="right" class="highlightedColumn" style="font-size:10px">
								<% if (dbbenefi > 0){ %>
									<span style="color:#266A2E">
								<% } else { %>
									<span style="color:#FB4040">
								<% } %>
										<%=formateador.format(dbbenefi) %> <%=cddivisa %>
									</span>
							</td>
							<td align="right" class="highlightedColumn" style="font-size:10px">
								<% if (dbbenefi > 0){ %>
									<span style="color:#266A2E">
								<% } else { %>
									<span style="color:#FB4040">
								<% } %>							
									<%=formatPorc.format(porcbene) %>
								</span>
							</td>
		
						</tr>
					<% }%>
				
				</table>
				
				<% } else { %>
					
					<h3>No se han obtenido resultados</h3>
					
				<% } %>
			
			</div>		
		
			<form method="post" name="abriXlsx" action="/JLet/JLetDownload" target="_blank">
				<input type="hidden" name="idusuari" value="1"/>
				<input type="hidden" name="tipofile" value=""/>
				<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
				<input type="hidden" name="filename" value=""/><!-- Cambiar -->
				<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
			</form>
</body>