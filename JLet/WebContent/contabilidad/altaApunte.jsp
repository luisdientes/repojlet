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
	String debhaber = "D";
	String concepto = "";
	String importex = ""; 
	String fhapunte = "";
	String coddocum = "";
	String tpapunte = "";
	String cddivisa = "";
	String idapunte = "";
	String documint = "";
	String filedocu = "";
	String textocab = "Alta apunte";
	double dbimport = 0;
	
	Grid gdCuentas   = null;
	Grid gdApuntes = null;
	
	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			//cddivisa = io.getStringValue("cddivisa");
			try{
				idapunte  = io.getStringValue("idapunte");
				gdApuntes = io.getGrid("gdApuntes");
			}catch(Exception ex){
				System.out.println("No existe idapunte ¿ Es alta Nuevo?");
			}
			gdCuentas = io.getGrid("gdCuentas");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ALTA APUNTES "+ e.getMessage());	
		}
	}
	
	if(idapunte !=null && idapunte!=""){
		
		concepto = gdApuntes.getStringCell(0,"concepto");
		importex = gdApuntes.getStringCell(0,"cantidad");
		fhapunte = gdApuntes.getStringCell(0,"fhapunte");
		coddocum = gdApuntes.getStringCell(0,"coddocum");
		tpapunte = gdApuntes.getStringCell(0,"tpapunte");
		debhaber = gdApuntes.getStringCell(0,"debhaber");
		documint = gdApuntes.getStringCell(0,"documint");
		filedocu = gdApuntes.getStringCell(0,"filedocu");
		dbimport = Double.parseDouble(importex);
		
		textocab = "Modificaci&oacute;n apunte";
	}
	
	
	
	
	
%>

<script>
var arrIdcue    = new Array();
var arrNombr    = new Array();
var arrTipox    = new Array();
var arrnumId    = new Array();
var arrDivis    = new Array();
var debehabe  = "<%=debhaber%>";
var importex  = "<%=dbimport%>";


	function cargaArrays(){
		<%
		if(gdCuentas!=null && gdCuentas.rowCount() > 0) {
			for (int i=0; i < gdCuentas.rowCount(); i++){
				idcuenta = gdCuentas.getStringCell(i,"idcuenta");
				txnombre = gdCuentas.getStringCell(i,"txnombre");
				tipocuen = gdCuentas.getStringCell(i,"tipocuen");
				numeroid = gdCuentas.getStringCell(i,"numeroid");
				cddivisa = gdCuentas.getStringCell(i,"cddivisa");
			%>

			arrNombr[<%=idcuenta%>] = "<%=txnombre%>";
			arrTipox[<%=idcuenta%>] = "<%=tipocuen%>";
			arrnumId[<%=idcuenta%>] = "<%=numeroid%>";
			arrDivis[<%=idcuenta%>] = "<%=cddivisa%>";
			
			<%	
			}
		}
		%>
	}


	function muestraID(idcuenta){
		if(idcuenta !=0 && arrnumId[idcuenta] != ""){
			document.getElementById("numid").innerHTML = arrnumId[idcuenta];
			document.formAlta.numeroid.value = arrnumId[idcuenta];
			
			
		}else{
			document.getElementById("numid").innerHTML = "No tiene cuenta asociada";
			document.formAlta.numeroid.value = "No tiene cuenta asociada";
		}
		
		
		
		tipocue 	= arrTipox[idcuenta];
		txnombre 	= arrNombr[idcuenta];
		divisaxx 	= arrDivis[idcuenta];
		
		if(tipocue == "B"){
			document.getElementById("iconotip").src="/JLet/common/img/icons/iconbank.jpg";
		} 
		if(tipocue == "C"){
			document.getElementById("iconotip").src="/JLet/common/img/icons/iconcaja.jpg";
		} 
		if(tipocue == "T"){
			document.getElementById("iconotip").src="/JLet/common/img/icons/icontarjeta.jpg";
		} 
		document.formAlta.tipocuen.value = tipocue;
		document.formAlta.txnombre.value = txnombre;
		document.formAlta.cddivisa.value = divisaxx;
		document.getElementById("cddivisa").innerHTML = divisaxx;
	}
	
function cambia(debehaber){
		
		if(debehaber=='H'){
			document.getElementById("HA").className = "fonts6v2";
			document.getElementById("DE").className = "fonts6";
	
		}
		if(debehaber=='D'){
			document.getElementById("DE").className = "fonts6v2";
			document.getElementById("HA").className = "fonts6";
		}
		
		debehabe = debehaber;
}

function enviaForm(){
	
	var cadena = "";
	
	document.formAlta.debhaber.value = debehabe;
	importe = document.formAlta.cantidad.value;
	importe = importe.replace(",",".");
	document.formAlta.cantidad.value = importe;
	
	if (document.formAlta.idcuenta.value == 0){
		cadena += "Seleccione una cuenta\n";
	}
	
	if (document.formAlta.concepto.value == ""){
		cadena += "Introduce concepto\n";
	}
	
	if (isNaN(importe) || importe == ""){
		cadena += "Importe incorrecto";
	}
	
	if (cadena != ""){
		alert (cadena);
	}else{
		document.formAlta.submit();
	}
}

function listadoApunt(idemisor){

	ventana = window.open("/JLet/App?controller=ContabilidadHttpHandler&services=ListCodApunteSrv&view=contabilidad/AyudaCodApuntes.jsp&idemisor="+ idemisor,'','toolbar=no,titlebar=no,menubar=no,scrollbars=yes,resizable=yes,maximize=yes,width=950,height=600');

}

function listDire(){
	document.formListdir.submit();
}

function buscaGrupo(concepto){
	document.formAlta.concepto.value = concepto;
}

function compruebaTipo(tipodoc){
	
	if (tipodoc == "G" ){
			ventana = window.open("/JLet/App?controller=FacturaHttpHandler&services=ListFacturasEmisorSrv&view=contabilidad/AyudalistadoFacturasEmisor.jsp&idemisor=<%=idemisor%>&aniofact=2015",'','toolbar=no,titlebar=no,menubar=no,scrollbars=yes,resizable=yes,maximize=yes,width=950,height=600');
		
		//document.formFactu.submit();
	}

	if(tipodoc =="S"){
		ventana = window.open("/JLet/App?controller=ContabilidadHttpHandler&services=ListDirectoriosSrv&view=contabilidad/listDirectorio.jsp&idemisor=<%=idemisor%>",'','toolbar=no,titlebar=no,menubar=no,scrollbars=yes,resizable=yes,maximize=yes,width=950,height=600');

		//document.formListdir.submit();
	}
}


function asignaDoc(idinodox, txnombre){
	document.formAlta.coddocum.value = idinodox;
	document.formAlta.filedocu.value = txnombre;
	
}

function calendarSetup(){
	
	Calendar.setup({ 
		inputField : "fhapunte",    // id del campo de texto 
		ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
		button     : "lanzador"     // el id del botón que lanzará el calendario 
	});
}
</script>


<head>

	
</head>


<body onload="cargaArrays();calendarSetup();">


	<div class="fondo2">
    <div class="testata"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></div>
    <div class="nompanta" ><%=textocab %></div>
		<div class="centrado" style="width:85%;">
		<form method="POST" name="formAlta" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
			<input type="hidden" name="services" 	value="VistaPreviaAltaSrv"/>
			<input type="hidden" name="view" 		value="contabilidad/vistaPrevia.jsp"/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>">
			<input type="hidden" name="debhaber">
			<input type="hidden" name="numeroid" value="<%=numeroid%>">
			<input type="hidden" name="tipocuen" value="<%=tipocuen%>">
			<input type="hidden" name="txnombre" value="<%=txnombre%>">
			<input type="hidden" name="idapunte" value="<%=idapunte%>">
			<input type="hidden" name="cddivisa" value="<%=cddivisa%>">
			<input type="hidden" name="coddocum" value="<%=coddocum %>">
			<table align="center" width=70% class="tdRound">
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><b>Cuenta :</b></td>
					<td>
						
						<%if(idapunte !=null && !idapunte.equals("")){ %>
						<b><%=txnombre %></b>
						<input type="hidden" name="idcuenta" value="<%=idcuenta%>">
						<%}else{ %>
						<select onchange="muestraID(this.value)" name="idcuenta">
							<option value="0">Seleccione cuenta...</option>
							<% if(gdCuentas!=null && gdCuentas.rowCount() > 0) {
								for (int i=0; i < gdCuentas.rowCount(); i++){%>
									<option value="<%=gdCuentas.getStringCell(i,"idcuenta")%>"><%=gdCuentas.getStringCell(i,"txnombre")%></option>
							<% }
							} %>
						</select>
						&nbsp;&nbsp;&nbsp;
						<span><img id="iconotip" style="width:26px"></span>
						<%} %>
					</td>
				
				</tr>
				<tr>
					<td colspan="2" align="center"><p id="numid"><%=numeroid %></p></td></tr>
				<tr>
					<td>
						<table width="100%">
							<tr>
								<td align="left"><div style="margin-left:-10px"><b>Concepto :</b></div></td>
								<td <%if(debhaber == null || debhaber.equals("D") || debhaber.equals("")){ %> class="fonts6v2"<% }else{ %>class="fonts6"<%} %> style="cursor:pointer" align="center" id="DE" onclick="cambia('D')">+</td>
								<td <%if(debhaber != null && debhaber.equals("H")){ %> class="fonts6v2"<% }else{ %>class="fonts6"<%} %>   style="cursor:pointer"  align="center" id="HA"  onclick="cambia('H')">-</td>
							</tr>
						</table>
						
					</td>
					<td><input type="text" style="width:100%" class="input-m" name="concepto" value="<%=concepto %>"></td>
					<td onclick="listadoApunt('<%=idemisor%>')"><a style="font-size:9px;font-weight:900;font-family:Arial;min-width:25px;border-radius:100%;min-height:20px;" class="boton">+</a></td>
				</tr>
				<tr>
					<td><b>Importe :</b></td>
					<td><input type="text" style="width:30%" class="input-m" name="cantidad" value="<%=importex %>"> &nbsp;<span id="cddivisa"><%=cddivisa %></span></td>
				</tr>
				<tr>
					<td><b>Fecha :</b></td>
					<td><input type="text" name="fhapunte" style="width:150px" id="fhapunte" value="<%=fhapunte %>" class="input-m">&nbsp;&nbsp;<img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:6px" width="24" height="24" border="0" title="Fecha Factura" id="lanzador"></td>
				</tr>
				<tr>
					<td><b>Tipo documento :</b></td>
					<td>
						<select name="documint" onchange="compruebaTipo(this.value)">
							<option value="" <%if(documint.equals("")){%> selected<% }%>>Seleccione tipo documento...</option>
							<option value="G" <%if(documint.equals("G")){%> selected<% }%>> Factura Generada</option>
							<option value="S" <%if(documint.equals("S")){%> selected<% }%>> Cloud</option>
							<option value="N" <%if(documint.equals("N")){%> selected<% }%>> Sin justificante</option>
			
						</select>
					</td>			
				</tr>
				<tr>
					<td><b>Documento :</b></td>
					<td><input type="text" name="filedocu" style="width:250px"  value="<%=filedocu %>" class="input-m" 
					readonly></td>
				</tr>
				<tr>
					<td><b>Tipo Apunte :</b></td>
					<td>
						<select name="tpapunte">
							<option value="AP" <%if(tpapunte.equals("AP")){%> selected<% }%>> Apunte</option>
							<option value="FE" <%if(tpapunte.equals("FE")){%> selected<% }%>> F. Expedida</option>
							<option value="FR" <%if(tpapunte.equals("FR")){%> selected<% }%>> F. Recibida</option>
							<option value="BI" <%if(tpapunte.equals("BI")){%> selected<% }%>> B. Inversi&oacute;n</option>
							<option value="PR" <%if(tpapunte.equals("PR")){%> selected<% }%>> Provisi&oacute;n</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td align="center" colspan="2"><a class="boton" onclick="enviaForm()">Aceptar</a></td>
				</tr>
				
			</table>
			
			</form>
			
			<form method="POST" name="formListdir" action="/JLet/App" target="_blank">
				<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
				<input type="hidden" name="services" 	value="ListDirectoriosSrv"/>
				<input type="hidden" name="view" 		value="contabilidad/listDirectorio.jsp"/>
				<input type="hidden" name="idemisor" value="<%=idemisor%>">
			</form>
			
					
		<form method="POST" name="formFactu" action="/JLet/App" target="_blank">
			<input type="hidden" name="controller" 	value="FacturaHttpHandler"/>
			<input type="hidden" name="services" 	value="ListFacturasEmisorSrv"/>
			<input type="hidden" name="view" 		value="contabilidad/AyudalistadoFacturasEmisor.jsp"/>
			<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
			<input type="hidden" name="tpclient" 	value=""/>
			<input type="hidden" name="aniofact" 	value="2015"/>
			<input type="hidden" name="modofact" 	value=""/>
			<input type="hidden" name="logoemis" 	value=""/>
			<input type="hidden" name="conitbis" 	value=""/>
		</form>
			
			
			
				
		</div>
	</div>

</body>