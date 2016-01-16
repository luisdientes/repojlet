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
	
	
	Grid gdCuentas   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gdCuentas = io.getGrid("gdCuentas");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ALTA APUNTES "+ e.getMessage());	
		}
	}
	
	
	
	
	
%>

<script>
var arrIdcue    = new Array();
var arrNombr    = new Array();
var arrTipox    = new Array();
var arrnumId    = new Array();
var debehabe = "H";


	function cargaArrays(){
		<%
		if(gdCuentas!=null && gdCuentas.rowCount() > 0) {
			for (int i=0; i < gdCuentas.rowCount(); i++){
				idcuenta = gdCuentas.getStringCell(i,"idcuenta");
				txnombre = gdCuentas.getStringCell(i,"txnombre");
				tipocuen = gdCuentas.getStringCell(i,"tipocuen");
				numeroid = gdCuentas.getStringCell(i,"numeroid");
			%>

			arrNombr[<%=idcuenta%>] = "<%=txnombre%>";
			arrTipox[<%=idcuenta%>] = "<%=tipocuen%>";
			arrnumId[<%=idcuenta%>] = "<%=numeroid%>";
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
	}
	


function enviaForm(){
	document.formAlta.submit();
}

</script>


<head>

	
</head>


<body onload="cargaArrays();">


	<div class="fondo2">
    <div class="testata"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></div>
    <div class="nompanta" >Seleccione cuenta</div>
		<div class="centrado" style="width:85%;">
		<form method="POST" name="formAlta" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
			<input type="hidden" name="services" 	value="ListApuntesSrv"/>
			<input type="hidden" name="view" 		value="contabilidad/listApuntes.jsp"/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>">
			<table align="center" width=70% >
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td><b>Cuenta :</b></td>
					<td>
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
					</td>
				
				</tr>
				<tr>
					<td colspan="2" align="center"><p id="numid">&nbsp;</p></td></tr>
				<tr>
					<td align="center" colspan="2"><a class="boton" onclick="enviaForm()">Aceptar</a></td>
				</tr>
				
			</table>
			
			</form>
				
		</div>
	</div>

</body>