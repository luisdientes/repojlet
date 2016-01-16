<%@ include file="/common/jsp/include.jsp"%>


<%
	
	Grid gdRepara = null;
	String idemisor = "";
	String tpclient = "";
	String idrecibo = "";
	String cdrecibo = "";
	String txnombre = "";
	String txmodelo = "";
	String txcolorx = "";
	String txmarcax = "";
	String txdescri = "";
	String tximeixx = "";
	String fhentrad = "";
	String costordx = "";
	String costcheq = "";
	String perconta = "";
	String telefono = "";
	String txmailxx = "";
	String tiempent = "";
	String entregad = "";
	String recibido = "";
	String filereci = "";
	String diagaran = "";
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gdRepara = io.getGrid("gdRepara");	
			//grEmpres = io.getGrid("grEmpres");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Al.jsp "+ e.getMessage());	
		}
	}
	
	if ((gdRepara != null) && (gdRepara.rowCount() > 0)) { 
				cdrecibo 	= gdRepara.getStringCell(0,"cdrecibo");
				idrecibo 	= gdRepara.getStringCell(0,"idrecibo");
				txnombre 	= gdRepara.getStringCell(0,"txnombre");
				txmodelo	= gdRepara.getStringCell(0,"txmodelo");
				txcolorx	= gdRepara.getStringCell(0,"txcolorx");
				txmarcax 	= gdRepara.getStringCell(0,"txmarcax");
				txdescri	= gdRepara.getStringCell(0,"txdescri");
				tximeixx 	= gdRepara.getStringCell(0,"tximeixx");
				fhentrad	= gdRepara.getStringCell(0,"fhentrad");
				costordx 	= gdRepara.getStringCell(0,"costordx");
				costcheq	= gdRepara.getStringCell(0,"costcheq");
				perconta 	= gdRepara.getStringCell(0,"perconta");	
				telefono 	= gdRepara.getStringCell(0,"telefono");
				txmailxx 	= gdRepara.getStringCell(0,"txmailxx");
				tiempent	= gdRepara.getStringCell(0,"tiempent");
				diagaran	= gdRepara.getStringCell(0,"garantia");
				entregad 	= gdRepara.getStringCell(0,"entregad");
				recibido	= gdRepara.getStringCell(0,"recibido");
				filereci 	= gdRepara.getStringCell(0,"filereci");
 		
	}	
 	%>

<script>

function abrirRecibo(namefile){
	document.abriRepara.filename.value = namefile;
	document.abriRepara.submit();
}


function imprime(){
	document.formImprime.submit();
	
}

function validarReparacion(){
	var cadena ="";
	
	costcheq = document.formAltaRepara.costcheq.value;
	costordx = document.formAltaRepara.costordx.value;
	
	document.formAltaRepara.costcheq.value = costcheq.replace("RD$", ""); 
	document.formAltaRepara.costordx.value = costordx.replace("RD$", "");  
	
	
	if(!ver_fecha(document.formAltaRepara.fhentrad.value)){
		cadena=" Fecha no es correcta\n";
	}
	if(document.formAltaRepara.txnombre.value == ""){
		cadena+=" Debe introducir un nombre\n";
	}
	if(!document.formAltaRepara.txmailxx.value == "" && !validarEmail(document.formAltaRepara.txmailxx.value)){
		cadena+=" El email no es correcto\n";
	}
	if(isNaN(document.formAltaRepara.costordx.value) || document.formAltaRepara.costordx.value == ""){
		cadena +=" Precio de reparaci\u00f3n no v\u00e1lido\n";
	}
	if(isNaN(document.formAltaRepara.costcheq.value)  || document.formAltaRepara.costcheq.value == "" ){
		cadena +=" Precio de chequeo no v\u00e1lido\n";
	}
	if(isNaN(document.formAltaRepara.garantia.value) || document.formAltaRepara.garantia.value == ""){
		cadena +=" Dias garantia no v\u00e1lido\n";
	}
	
	
	
	if (cadena != ""){
		alert (cadena);
		document.formAltaRepara.costcheq.value = costcheq;
		document.formAltaRepara.costordx.value = costordx;
	}else{
		document.formAltaRepara.submit();
	}
}

function ponDivisa(caja, cantidad){
	
	if ((caja == "C") && (document.formAltaRepara.costcheq.value.indexOf("RD$") == -1)){
		document.formAltaRepara.costcheq.value ="";
		document.formAltaRepara.costcheq.value =cantidad + " RD$";
	}
	
	if ((caja == "R") && (document.formAltaRepara.costordx.value.indexOf("RD$") == -1)){
		document.formAltaRepara.costordx.value ="";
		document.formAltaRepara.costordx.value =cantidad + " RD$";
	}
	
}
</script>


<body>

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>

	<div class="fondo1">
    <div class="testata"><img src="/JLet/common/img/icons/title-alta-reparaciones.png"></div>
    <div class=nompanta >Detalle Reparación</div>	
		<div class="centrado4" style="width:85%; margin-left:8%; height:750px;">
		
			
			<form name="formAltaRepara" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="ReparaHttpHandler"/>
				<input type="hidden" name="services"	value="AltaReparaSrv"/>
				<input type="hidden" name="view" 		value="reparaciones/GeneraCodBarras.jsp"/>
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>"/>
				<input type="hidden" name="idrecibo" 	value="<%=idrecibo%>"/>
				<input type="hidden" name="tipoacci" 	value="UPD"/>
				<br>
				<br>
			
				<table border="0" align="center" border="1">
				<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:200px;">Recibo No.:</td>
						<td class="fonts6"><%=cdrecibo%></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:200px;">Nombre:</td>
						<td><input style=";" type="text" name="txnombre"  size="44" class="input-m" value="<%=txnombre%>"/></td>
					</tr>
					<tr>	
						<td align="center" class="cabecera300 input-b350" style="width:200px;">Marca</td>
						<td><input style=" " type="text" name="txmarcax"  size="44" class="input-m" value="<%=txmarcax%>"/></td>
						<td align="center" class="cabecera300 input-b350" style="width:200px;">Modelo</td>
						<td><input style=" " type="text" name="txmodelo"  size="44" class="input-m" value="<%=txmodelo%>"/></td>	
						
					</tr>
					<tr>	
						<td align="center" class="cabecera300 input-b350" style="width:200px;">Color</td>
						<td colspan=3><input style=" " type="text" name="txcolorx"  size="44" class="input-m" value="<%=txcolorx%>"/></td>						
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:200px;">Descripción</td>
						<td><textarea name="txdescri" class="input-m" style="text-align:justify !important;width:100%;height:150px"><%=txdescri%></textarea></td>
					</tr>
					
					<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:200px;">IMEI</td>
						<td><input  type="text" name="tximeixx"size="44" class="input-m" value="<%=tximeixx%>"/></td>
					</tr>
					
					<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:200px;">Fecha entrada: </td>
						<td><input type="text" id="fhentrad" name="fhentrad" size="39" class="input-m" value="<%=fhentrad%>"/></td>
					
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:140px;">Coste chequeo</td>
						<td ><input  type="text" name="costcheq"size="44" class="input-m" value="<%=costcheq%> RD$" onBlur="ponDivisa('C',this.value)" placeholder="RD$"/>   RD$</td>
						<td align="center" class="cabecera300 input-b350" style="width:140px;">Coste Reparación</td>
						<td><input  type="text" name="costordx" size="44" class="input-m" onBlur="ponDivisa('R',this.value)" placeholder="RD$" value="<%=costordx%>"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:140px;">Persona contacto:</td>
						<td > <input type="text" name="perconta" size="44" value="<%=perconta%>" class="input-m"/></td>	
					</tr>
					<tr>		
						<td align="center" class="cabecera300 input-b350" style="width:140px;"> Tel&eacute;fono</td>
						<td><input type="text" name="telefono" maxlength="12"  size="44" class="input-m" value="<%=telefono%>"/></td>
						<td align="center" class="cabecera300 input-b350" style="width:140px;">E-Mail</td>
						<td><input type="text" name="txmailxx"  class="input-m" size="44" value="<%=txmailxx%>"/></td>	
					</tr>	
					<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:140px;">Tiempo entrega:</td>
						<td><input type="text" name="tiempent" onChange="calculaTotal()" size="44" class="input-m" value="<%=tiempent%>"/></td>	
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:140px;">Tiempo Garantía (días):</td>
						<td><input type="text" name="garantia" size="44" class="input-m" value="<%=diagaran%>"/></td>	
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera300 input-b350" style="width:140px;">Recibido por: </td>
						<td><input type="text" name="recibido"  class="input-m" size="44" value="<%=recibido%>"/></td>	
						<td align="center" class="cabecera300 input-b350" style="width:140px;">Aceptado por: </td>
						<td><input type="text" name="entregad"  class="input-m" size="44" value="<%=entregad%>"/></td>	
					</tr>
					<tr>
					 <td>&nbsp;</td>
					</tr>
					<tr>
					<td colspan="4" align="center"><a class="boton" style="margin-left:0%; font-family:Arial, Helvetica, sans-serif; font-size:18px;" onClick="validarReparacion()">Aceptar</a></td>
				</tr>
					
				</table>
				</form>
			
					
		</div>
	</div>
	<form method="post" name="abriRepara" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="idemisor" value="<%=pre_idemisor%>"/>
			<input type="hidden" name="tipofile" value="REC"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value=""/><!-- Cambiar -->
	</form>
	
	<form name="formImprime" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="ReparaHttpHandler"/>
				<input type="hidden" name="services"	value="GenCodBarrasReciboSrv"/>
				<input type="hidden" name="view" 		value="reparaciones/GeneraCodBarras.jsp"/>
				<input type="hidden" name="filereci" 		value="<%=idrecibo%>"/>
	</form>
</body>