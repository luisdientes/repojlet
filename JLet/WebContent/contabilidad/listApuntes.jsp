<%@ include file="/common/jsp/include.jsp"%>
<link rel="stylesheet" href="common/css/calendario.css" type="text/css"/>
<script type="text/javascript" src="common/js/calendar.js"></script>
<script type="text/javascript" src="common/js/calendar-es.js"/></script>
<script type="text/javascript" src="common/js/calendar-setup.js"/></script>

<%
	String idemisor = "";
	String tpclient = "";
	String idcuenta = "";
	String txnombre = "";
	String cdpaisxx = "";
	String tipocuen = "";
	String numeroid = "";
	String debhaber = "";
	String concepto = "";
	String fhapunte = "";
	String coddocum = "";
	String tpapunte = "";
	String ultsaldo = "";
	String cantidad = "";
	String cddivisa = "";
	String idapunte = "";
	String sumaapun = "";
	String fhdesdex = "";
	String fhhastax = "";
	String filename = "";
	String documint = "";
	String filedocu = "";

	double dbimport = 0;
	double dbsaldox = 0;
	
	
	Grid gdCuentas   = null;
	Grid gdApuntes   = null;
	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			ultsaldo = io.getStringValue("ultsaldo");
			fhdesdex = io.getStringValue("fhdesdex");
			fhhastax = io.getStringValue("fhhastax");
			filename = io.getStringValue("filename");
			gdCuentas = io.getGrid("gdCuentas");
			gdApuntes = io.getGrid("gdApuntes");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ALTA APUNTES "+ e.getMessage());	
		}
	}
	idcuenta = gdCuentas.getStringCell(0, "idcuenta");
	txnombre = gdCuentas.getStringCell(0, "txnombre");
	numeroid = gdCuentas.getStringCell(0, "numeroid");
	cddivisa = gdCuentas.getStringCell(0, "cddivisa");
	dbsaldox = Double.parseDouble(ultsaldo);
	
	
	
	
	
%>

<script>
function modificar (idapunte){
		
		document.formAlta.view.value = "contabilidad/altaApunte.jsp";
		document.formAlta.idapunte.value = idapunte;
		document.formAlta.submit();	
	}
	
function detalle (idapunte){
		
		document.formAlta.idapunte.value = idapunte;
		document.formAlta.view.value = "contabilidad/detalleApunte.jsp";
		document.formAlta.submit();	
	}
	
	function filtra(){
		document.formBusca.fhdesdex.value = document.getElementById("fhdesdex").value;
		document.formBusca.fhhastax.value = document.getElementById("fhhastax").value;
		document.formBusca.submit();
	}
	
	function descargarExcel(){
		document.abriXlsx.submit();
	}
	/*
	function verFactu(coddocu){
		document.buscaDoc.tpdocume.value='G';
		document.buscaDoc.iddocume.value = coddocu;
		document.buscaDoc.submit();
	}
	*/
	function verFactu(namefile){
		document.abriFactu.filename.value = namefile;
		document.abriFactu.submit();
	}
	
	function verCloud(coddocu){
		document.buscaDoc.tpdocume.value='S';
		document.buscaDoc.iddocume.value = coddocu;
		document.buscaDoc.submit();
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


<body onload="calendarSetup();">


<div class="fondo2">	
	
	<table width="100%" align="center" style="margin-left:13%">
		<tr>
			<td width="20%">&nbsp;</td>
			<td width="60%" align="center"><img title="Usuario" src="<%=pathimgx%>icons/emisores/logo_<%=pre_idemisor%>.png"></td>
			<td width="20%">
			 	<div class="nompanta" >Listado Apuntes Contables</div>
			</td>						
		</tr>
	</table>
	
	<br/>
	<br/>
	
	<table  style="margin: auto;" width="60%">
		<tr>
			<td width="20%" style="color:#000000"><b>Nombre</b></td>
			<td width="80%"><%=txnombre %></td>
		</tr>
		<tr>
			<td style="color:#000000"><b>Numero ID</b></td>
			<td><%=numeroid %></td>
		</tr>
		<tr>
			<td style="color:#000000"><b>Detalle</b></td>
			<td></td>
		</tr>
		<tr>
			<td style="color:#000000"><b>Saldo</b></td>
			<td style="font-size:20px; font-weight: bold">
			
			<% if (dbsaldox > 0){ %>
				<font style="color: rgb(102, 204, 51);">
			<% } else { %>
				<font style="color: rgb(255, 0, 0);">
			<% } %>
			
			<%=formateador.format(dbsaldox) %> <%=cddivisa %></font></td>
		</tr>
		
	</table>
	
	<br>
	<hr>
	<br>
	
	<table align="center" width=60% >
		<tr style="vertical-align:middle">
			<td width="15%" style="margin: 0 10 0 0; text-align:right"><b>Fecha Desde :</b></td>
			<td width="25%" nowrap><input type="text" name="fhdesdex" style="width:110px" id="fhdesdex" value="<%=fhdesdex %>" class="input-m">&nbsp;&nbsp;<img src="common/img/varios/fechas.png" style="cursor:pointer;" width="24" height="24" border="0" title="Fecha Factura" id="lanzador"></td>
			<td width="15%" style="margin: 0 10 0 0; text-align:right"><b>Fecha Hasta :</b></td>
			<td width="25%" nowrap><input type="text" name="fhhastax" style="width:110px" id="fhhastax" value="<%=fhhastax %>" class="input-m">&nbsp;&nbsp;<img src="common/img/varios/fechas.png" style="cursor:pointer;" width="24" height="24" border="0" title="Fecha Factura" id="lanzador2"></td>
			<td width="20%" style="text-align:center"><a class="boton" onclick="filtra()">Buscar</a></td>
		</tr>
	</table>
	
	<br>
	
	<% if (gdApuntes.rowCount() > 0) { %>
		
		<table width="100%" style="margin-left: 10%;" >
			<tr>
				<td width="20%">
					<span style="cursor:pointer" onclick="descargarExcel('<%=filename%>','XLSX')"><img src="/JLet/common/img/varios/iconExcel.png" width="32px"></span>
				</td>
				<td width="80%">&nbsp;</td>
			<tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			<tr>
		</table>
		
		<table width="100%" class="table table-stripped" style="margin-left: 10%;" >
			<tr style="text-align: center">
				<td width="10%" style="background-color: #000000; border-left: 1px solid black;">Fecha</td>
				<td width="4%"  style="background-color: #000000">Doc.</td>
				<td width="60%" style="background-color: #000000">Concepto</td>
				<td width="9%"  style="background-color: #000000">+</td>
				<td width="9%"  style="background-color: #000000">-</td>
				<td width="4%"  style="background-color: #FFFFFF; border: none">&nbsp;</td>
				<td width="4%"  style="background-color: #FFFFFF; border: none">&nbsp;</td>
			</tr>
		
			<% for (int i = 0; i < gdApuntes.rowCount(); i++) { 
				
				idapunte = gdApuntes.getStringCell(i, "idapunte");
				fhapunte = gdApuntes.getStringCell(i, "fhapunte");
				coddocum = gdApuntes.getStringCell(i, "coddocum");
				concepto = gdApuntes.getStringCell(i, "concepto");
				debhaber = gdApuntes.getStringCell(i, "debhaber");
				cantidad = gdApuntes.getStringCell(i, "cantidad");
				documint = gdApuntes.getStringCell(i, "documint");
				filedocu = gdApuntes.getStringCell(i, "filedocu");
				dbimport = Double.parseDouble(cantidad);
		
			%>
			
			<% if (i + 1 == gdApuntes.rowCount()) {%>
				<tr style="border-bottom: 1px solid black;">
			<% } else { %>
				<tr>
			<% } %>
			
				<td style="text-align:center; border-left: 1px solid black;"><%=fhapunte%></td>
				
				<% if (documint != null && documint.equals("G")) {%>
					<td onclick="verFactu('<%=filedocu%>')" align="center" style="cursor:pointer"><img height=16px width=16px; src="/JLet/cloud/img/pdf.png"></td>
				<% } else if (documint != null && documint.equals("S")) {%>
					<td onclick="verCloud('<%=coddocum%>')" align="center" style="cursor:pointer"><img height=16px width=16px; src="/JLet/cloud/img/pdf.png" title="<%=filedocu %>"></td>
				<% } else {%>
					<td><%=coddocum %></td>
				<% } %>
				
				<td style="text-align: left"><%=concepto %></td>
				
				<%if (debhaber.equals("D")) {%>
					<td style="background-color:#EFEFEF; border-left: 1px solid black; color: #000000; font-weight: bold; text-align: right" nowrap><%= formateador.format(dbimport) %> <%=cddivisa %></td>
					<td style="background-color:#EFEFEF; border-left: 1px solid black; border-right: 1px solid black" align="center">-</td>
				<%}else{%>
					<td style="background-color:#EFEFEF; border-left: 1px solid black" align="center">-</td>
					<td style="background-color:#EFEFEF; border-left: 1px solid black; border-right: 1px solid black; color: #FB0000; font-weight: bold; text-align: right" nowrap>- <%=formateador.format(dbimport) %> <%=cddivisa %></td>
				<% } %>
				
				<td align="center" style="cursor:pointer; background-color:#FFFFFF; border: none" onclick="detalle(<%=idapunte%>);"><img src="/JLet/common/img/icons/ojo_ver.png" width="16px" title="Ver Apunte"/></td>
				<td align="center" style="cursor:pointer; background-color:#FFFFFF; border: none" onclick="modificar(<%=idapunte%>);"><img src="/JLet/common/img/icons/modificar.png" width="16px" title="Modificar Apunte"/></td>
				
			</tr>
			<% }%>
		</table>	
		<% } else { %>
		<h3 style="text-align:center">No se han obtenido resultados</h3>
		<% } %>
	
	<br/>
	<br/>
	<br/>
	
	<form method="POST" name="formAlta" action="/JLet/App">
		<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
		<input type="hidden" name="services" 	value="ListApuntesSrv"/>
		<input type="hidden" name="view" 		value="contabilidad/altaApunte.jsp"/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>">
		<input type="hidden" name="idcuenta" value="<%=idcuenta%>">
		<input type="hidden" name="idapunte">
	</form>
		
	<form method="POST" name="formBusca" action="/JLet/App">
		<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
		<input type="hidden" name="services" 	value="ListApuntesSrv"/>
		<input type="hidden" name="view" 		value="contabilidad/listApuntes.jsp"/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>">
		<input type="hidden" name="idcuenta" value="<%=idcuenta%>">
		<input type="hidden" name="fhdesdex">
		<input type="hidden" name="fhhastax">
	</form>
	
	<form method="post" name="buscaDoc" action="/JLet/App" target="_blank">
		<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
		<input type="hidden" name="services" 	value="BuscaDocumentoSrv"/>
		<input type="hidden" name="view" 		value="contabilidad/abrirDocumento.jsp"/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>">
		<input type="hidden" name="tpdocume" value="">
		<input type="hidden" name="iddocume" value="">
	</form>
	
	<form method="post" name="abriXlsx" action="/JLet/JLetDownload" target="_blank">
				<input type="hidden" name="idusuari" value="1"/>
				<input type="hidden" name="tipofile" value="XLSX"/>
				<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
				<input type="hidden" name="filename" value="<%=filename%>"/><!-- Cambiar -->
	</form>
	
	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
		<input type="hidden" name="idusuari" value="1"/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
		
		<input type="hidden" name="tipofile" value="FRA"/> <!-- Cambiar -->
		<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
		<input type="hidden" name="filename" value=""/><!-- Cambiar -->
	</form>
</div>

</body>