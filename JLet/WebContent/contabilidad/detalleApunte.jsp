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
	String importex = "";
	String cddivisa = "";
	String idapunte = "";
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
		
		txnombre = gdCuentas.getStringCell(0,"txnombre");
		numeroid = gdCuentas.getStringCell(0,"numeroid");
		idcuenta = gdCuentas.getStringCell(0,"idcuenta");
		cddivisa = gdCuentas.getStringCell(0,"cddivisa");
		dbimport = Double.parseDouble(importex);
	}
			
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
    <div class="testata"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor %>.png"></div>
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
					<td><b>Cod. Documento:</b></td>
					<td><%=coddocum %></td>
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
				<input type="hidden" name="services" 	value="ListApuntesSrv"/>
				<input type="hidden" name="view" 		value="contabilidad/listApuntes.jsp"/>
				<input type="hidden" name="idemisor" value="<%=idemisor%>">
				<input type="hidden" name="idcuenta" value="<%=idcuenta%>">
			</form>
				
		</div>
	</div>

</body>