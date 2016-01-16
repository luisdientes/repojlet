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
	String txapunte = "";
	String txdebexx = "";
	String cantidad = "";
	String cddivisa = "";
	String idapunte = "";
	String documint = "";
	String filedocu = "";
	double dbimport = 0;
	
	Grid gdCuentas   = null;
	
	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			idcuenta = io.getStringValue("idcuenta");
			txnombre = io.getStringValue("txnombre");
			concepto = io.getStringValue("concepto");
			debhaber = io.getStringValue("debhaber");
			numeroid = io.getStringValue("numeroid");
			fhapunte = io.getStringValue("fhapunte");
			coddocum = io.getStringValue("coddocum");
			tpapunte = io.getStringValue("tpapunte");
			tipocuen = io.getStringValue("tipocuen");
			cdpaisxx = io.getStringValue("cdpaisxx");
			cantidad = io.getStringValue("cantidad");
			cddivisa = io.getStringValue("cddivisa");
			idapunte = io.getStringValue("idapunte");
			documint = io.getStringValue("documint");
			filedocu = io.getStringValue("filedocu");
			
			dbimport = Double.parseDouble(cantidad);
			
			if(tpapunte.equals("AP")){
				txapunte ="Apunte";
			}else if(tpapunte.equals("FE")){
				txapunte = "F. Expedida";
			}else if(tpapunte.equals("FR")){
				txapunte = "F. Recibida";
			}else if(tpapunte.equals("BI")){
				txapunte = "B. Inversi&oacute;n";
			}else if(tpapunte.equals("PR")){
				txapunte = "Provisi&oacute;n";
			}
			
			if(debhaber.endsWith("D")){
				txdebexx ="Debe";
			}else{
				txdebexx ="Haber";
			}
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ALTA APUNTES "+ e.getMessage());	
		}
	}
	
%>

<script>
function enviaForm(){
	document.formAlta.submit();
	
}
</script>




<head>

	
</head>


<body>


	<div class="fondo2">
    <div class="testata"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></div>
    <div class="nompanta" >Resume  Apuntes</div>
		<div class="centrado" style="width:85%;">
		
			<table align="center" width=60% class="tdRound">
				<tr class="oddRow">
					<td><b>Tipo cuenta: </b></td>
					<td><%=txnombre %></td>
				</tr>
				<tr class="evenRow">
					<td><b>Numero ID:</b></td>
					<td ><%=numeroid %></td></tr>
				<tr class="oddRow">
					<td><b>Concepto :</b></td>
					<td><%=concepto %></td>
				</tr>
				<tr class="evenRow">
					<td><b>Importe :</b></td>
					<td><%=formateador.format(dbimport) %> <%=cddivisa %> &nbsp; ( <%=txdebexx %> )</td>
				</tr>
				
				<tr class="oddRow">
					<td><b>Fecha:</b></td>
					<td><%=fhapunte %></td>
				</tr>
				<tr class="evenRow">
					<td><b>Documento:</b></td>
					<td><%=filedocu %></td>
				</tr>
				<tr class="oddRow">
					<td><b>Tipo Apunte:</b></td>
					<td><%=txapunte %></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td align="center" colspan="2"><a class="boton" onclick="enviaForm()">Aceptar</a></td>
				</tr>
				
			</table>
			<form method="POST" name="formAlta" action="/JLet/App">
				<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
				<input type="hidden" name="services" 	value="AltaApunteSrv"/>
				<input type="hidden" name="view" 		value="contabilidad/resultado.jsp"/>
				<input type="hidden" name="idemisor" value="<%=idemisor%>">
				<input type="hidden" name="idcuenta" value="<%=idcuenta%>">
				<input type="hidden" name="debhaber" value="<%=debhaber%>">
				<input type="hidden" name="concepto" value="<%=concepto%>">
				<input type="hidden" name="coddocum" value="<%=coddocum%>">
				<input type="hidden" name="fhapunte" value="<%=fhapunte%>">
				<input type="hidden" name="tpapunte" value="<%=tpapunte%>">
				<input type="hidden" name="cantidad" value="<%=cantidad%>">
				<input type="hidden" name="idapunte" value="<%=idapunte%>">
				<input type="hidden" name="documint" value="<%=documint%>">
				<input type="hidden" name="filedocu" value="<%=filedocu%>">
				
				
				
			</form>
				
		</div>
	</div>

</body>