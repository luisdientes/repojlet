<%@ include file="/common/jsp/include.jsp"%>


<%
	
	Grid gdRepara = null;
	String idemisor = "";
	String tpclient = "";
	String idrecibo = "";
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
			gdRepara = io.getGrid("gdRepara");	
			//grEmpres = io.getGrid("grEmpres");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Al.jsp "+ e.getMessage());	
		}
	}
	
	if ((gdRepara != null) && (gdRepara.rowCount() > 0)) { 
				idrecibo 	= gdRepara.getStringCell(0,"cdrecibo");
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

</script>


<body>

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>

	<div class="fondo1">
    <div class="testata"><img src="/JLet/common/img/icons/icon-title-altaenvio.png"></div>
    <div class=nompanta >Detalle Reparación</div>	
		<div class="centrado4" style="width:85%; margin-left:8%; height:750px;">
				<br>
				<br>
			
				<table border="0" width="70%" align="center" border="1">
				<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:200px;">Recibo No.:</td>
						<td class="fonts6"><%=idrecibo%></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:200px;">Nombre:</td>
						<td class="fonts6"><%=txnombre%></td>
					</tr>
					<tr>	
						<td align="center" class="cabecera input-b1" style="width:200px;">Marca</td>
						<td class="fonts6"><%=txmarcax%></td>
						<td align="center" class="cabecera input-b1" style="width:200px;">Modelo</td>
						<td class="fonts6"><%=txmodelo%></td>	
						
					</tr>
					<tr>	
						<td align="center" class="cabecera input-b1" style="width:200px;">Color</td>
						<td class="fonts6"><%=txcolorx%></td>						
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:200px;">Descripción</td>
						<td class="fonts6" colspan=3><%=txdescri%></textarea></td>
					</tr>
					
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:200px;">IMEI</td>
						<td class="fonts6"><%=tximeixx%></td>
					</tr>
					
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:200px;">Fecha entrada: </td>
						<td class="fonts6"><%=fhentrad%></td>
					
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Coste chequeo</td>
						<td class="fonts6"><%=costcheq%>   RD$</td>
						<td align="center" class="cabecera input-b1" style="width:140px;">Coste Reparación</td>
						<td class="fonts6"><%=costordx%>   RD$</td>
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Persona contacto:</td>
						<td class="fonts6" ><%=perconta%></td>	
					</tr>
					<tr>		
						<td align="center" class="cabecera input-b1" style="width:140px;"> Tel&eacute;fono</td>
						<td class="fonts6" ><%=telefono%></td>
						<td align="center" class="cabecera input-b1" style="width:140px;">E-Mail</td>
						<td class="fonts6"><%=txmailxx%></td>	
					</tr>	
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Tiempo entrega:</td>
						<td class="fonts6" ><%=tiempent%></td>	
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Tiempo Garantía (días):</td>
						<td  class="fonts6" ><%=diagaran%></td>	
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Recibido por: </td>
						<td class="fonts6" ><%=recibido%></td>	
						<td align="center" class="cabecera input-b1" style="width:140px;">Aceptado por: </td>
						<td class="fonts6" ><%=entregad%></td>	
					</tr>
					<tr>
					 <td>&nbsp;</td>
					</tr>
					
					<tr style="cursor:pointer">
					<td align="center" class="cabecera input-b1" style="width:140px;">Documento: </td>
					<td align="center" class="fonts6" style=";padding-left:0px" onclick="abrirRecibo('<%=filereci %>');" style="cursor:pointer">
									<img src="/JLet/common/img/varios/iconPDF.png" height="16px" title="Documento PDF" style="cursor:pointer"/>
								</td>
					</tr>
					
					<tr>
					 <td>&nbsp;</td>
					</tr>
					
					<tr style="cursor:pointer">
						<td align="center"  class="cabecera input-b1" style="width:140px;">Imprimir código: </td>
						<td align="center" class="fonts6" style=";padding-left:0px" onclick="imprime();" style="cursor:pointer">
									<img src="/JLet/common/img/icons/barcode.png" height="16px" title="Código de Barras" style="cursor:pointer"/>
								</td>
					</tr>
					
				</table>
	
			
					
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