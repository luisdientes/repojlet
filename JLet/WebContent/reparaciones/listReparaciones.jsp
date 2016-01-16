<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid gdRepara = null;

	String txmensaj = "";
	String tpclient = "";
	String idemisor = "";
	String logoemis = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			tpclient = io.getStringValue("tpclient");
			idemisor = io.getStringValue("idemisor");
			logoemis = io.getStringValue("logoemis");
			gdRepara = io.getGrid("gdRepara");	
			
			txmensaj = io.getStringValue("txmensaj");		
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listReparaciones.jsp "+ e.getMessage());	
		}
	}
	
%>


<head>
	<script>
	var Arrcdrec	= new Array();
	function cargaRepara(){
		i=0;
		
		<%
		if ((gdRepara != null) && (gdRepara.rowCount() > 0)) { 
  			for (int j = 0; j < gdRepara.rowCount(); j++){
  				String cdrecibo 	= gdRepara.getStringCell(j,"cdrecibo");
			%>
			Arrcdrec[i] = "<%=cdrecibo%>";
			i++;
		<%
	 		}
		}	
	 	%>
		
	}
	
	function compruebaRecibo(cdrecibo){
		
		exist =0;
		for(i=0;i< Arrcdrec.length;i++){
			if(cdrecibo == Arrcdrec[i]){
				exist = 1;
			}
		}
		
		if (exist == 0){
			document.getElementById("noexiste").style.display = "block";
			document.getElementById("existe").style.display = "none";
			
		}else{
			
			
			document.formDetalleRepa.cdrecibo.value = cdrecibo;
			document.formDetalleRepa.submit();
			document.getElementById("existe").style.display = "block";
			document.getElementById("noexiste").style.display = "none";
		}
	
		document.getElementById("cdrecibo").value = "";
		document.getElementById("cdrecibo").focus();
	}
	
	function modRecibo(cdrecibo){
		
		exist =0;
		for(i=0;i< Arrcdrec.length;i++){
			if(cdrecibo == Arrcdrec[i]){
				exist = 1;
			}
		}
		
		if (exist == 0){
			document.getElementById("noexiste").style.display = "block";
			document.getElementById("existe").style.display = "none";
			
		}else{
			
			
			document.formDetalleRepa.view.value = "reparaciones/modReparacion.jsp";
			document.formDetalleRepa.cdrecibo.value = cdrecibo;
			document.formDetalleRepa.submit();
			document.getElementById("existe").style.display = "block";
			document.getElementById("noexiste").style.display = "none";
		}
		
	}
	
	
	function abrirRecibo(namefile){
		document.abriFactu.filename.value = namefile;
		document.abriFactu.submit();
	}
	
	function imprime(cdrecibo){
		document.formImprime.filereci.value= cdrecibo;
		document.formImprime.submit();
		
	}
	
	
		
	cargaRepara();	
	</script>
</head>


<body>
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
		<tr>
			<td width=100% align="center"><img src="/JLet/common/img/icons/title-listado-reparaciones.png"/></td>						
		</tr>
		<tr>
			<td width=100% align="center">Listado Reparaciones</td>						
		</tr>
	</table>

	<div class="centrado-all">	

			<form name="formDetalleRepa" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ReparaHttpHandler"/>
				<input type="hidden" name="services"	value="ListReparaSrv"/>
				<input type="hidden" name="view" 		value="reparaciones/detalleReparacion.jsp"/>
				<input type="hidden" name="cdrecibo" 	value=""/>	
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>"/>	
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>	
			
			</form>	
				<table border="0" width="40%" align="center" border="1">
					<tr>
						<td align="center" class="cabecera input-b1">Introduce ID. Recibo</td>                                      				        
						<td align="center"><input class="input-m" type="text" size=45 id="cdrecibo" onchange="compruebaRecibo(this.value);"/></td>
					</tr>
				</table>
				<br>
				<br>
				
				<table width="40%" align="center">
					<tr>
						<td align="center">
							<div id="noexiste" style="display:none; color:#F00;"><h3>El Recibo introducido no existe.</h3></div>
							<div id="existe" style="display:none; color:#228B22;"><h3>Recibo encontrado.</h3>
						</div>
						</td>
					</tr>
				</table>
				
				<table  align="center" class="listado-tab" >
					<tr class="fonts">
					    <td width="10%" align="center"><div class="input-b1" width="100%">Pdf / Cod. Barras</div></td>
					    <td width="10%" align="center"><div class="input-b1" width="100%">Cod. Reparaci&oacute;n</div></td>
						<td width="10%" align="center"><div class="input-b1" width="100%">Marca</div></td>
						<td width="10%" align="center"><div class="input-b1" width="100%">Modelo</div></td>
						<td width="10%" align="center"><div class="input-b1" width="100%">Imei</div></td>
						<td width="10%" align="center"><div class="input-b1" width="100%">Persona contacto</div></td>
						<td width="10%" align="center"><div class="input-b1" width="100%">Fecha Entrada</div></td>
						<td width="10%" align="center">&nbsp;</td>
					</tr>
					
					
					<% for (int i = 0; i < gdRepara.rowCount(); i++) { 
						
						String idrecibo = "";
						String cdrecibo = "";
						String txnombre = "";
						String txmodelo = "";
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

						
						try {
							idrecibo = gdRepara.getStringCell(i,"idrecibo");
							cdrecibo = gdRepara.getStringCell(i,"cdrecibo");
							txnombre = gdRepara.getStringCell(i,"txnombre");
							idemisor = gdRepara.getStringCell(i,"idemisor");
							txmodelo = gdRepara.getStringCell(i,"txmodelo");
							txmarcax = gdRepara.getStringCell(i,"txmarcax");
							txdescri = gdRepara.getStringCell(i,"txdescri");
							tximeixx = gdRepara.getStringCell(i,"tximeixx");
							fhentrad = gdRepara.getStringCell(i,"fhentrad");
							filereci = gdRepara.getStringCell(i,"filereci");
							costordx = gdRepara.getStringCell(i,"costordx");
							costcheq = gdRepara.getStringCell(i,"costcheq");
							perconta = gdRepara.getStringCell(i,"perconta");
							telefono = gdRepara.getStringCell(i,"telefono");
							txmailxx = gdRepara.getStringCell(i,"txmailxx");
							tiempent = gdRepara.getStringCell(i,"tiempent");
							entregad = gdRepara.getStringCell(i,"entregad");
							recibido = gdRepara.getStringCell(i,"recibido");
							
							if (i % 2 == 0) { %>
							<tr class="oddRow"  style="border:1px solid;cursor:pointer">
						<% } else { %>
							<tr class="evenRow" style="border:1px solid;cursor:pointer" >
						<% }%>
								<td align="center" class="fonts6jej" style="padding-left:0px;cursor:pointer" width="10%">
									<img src="/JLet/common/img/varios/iconPDF.png" height="16px" title="Recibo PDF" onclick="abrirRecibo('<%=filereci %>');" style="cursor:pointer"/>&nbsp;&nbsp;&nbsp;
									<img src="/JLet/common/img/icons/barcode.png" height="16px" title="Generar Código Barras" onclick="imprime('<%=cdrecibo %>');" style="cursor:pointer"/>
								</td>
								<td width="10%" onclick="compruebaRecibo('<%=cdrecibo%>')" align="center" class="fonts6jej" ><%=cdrecibo%></td>
								<td width="10%" onclick="compruebaRecibo('<%=cdrecibo%>')" align="center" class="fonts6jej" ><%=txmarcax%></td>
								<td width="10%" onclick="compruebaRecibo('<%=cdrecibo%>')" align="center" class="fonts6jej" ><%=txmodelo%></td>
								<td width="10%" onclick="compruebaRecibo('<%=cdrecibo%>')" align="center" class="fonts6jej" ><%=tximeixx%></td>
								<td width="10%" onclick="compruebaRecibo('<%=cdrecibo%>')" align="center" class="fonts6jej" ><%=perconta%></td>
								<td width="10%" onclick="compruebaRecibo('<%=cdrecibo%>')" align="center" class="fonts6jej" ><%=fhentrad%></td>
								<td width="10%" onclick="modRecibo('<%=cdrecibo%>')" align="center" class="fonts6jej" >Modificar</td>
							</tr>
						<% } catch (Exception e) { 							
							System.out.println(" ERROR - Recuperando envio linea: "+ i);
						   }
						
					} %>						
				
				</table>	
			</div>
		
				<br>
				<br>										
		
			
		<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="tipofile" value="REC"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value=""/><!-- Cambiar -->
			<input type="hidden" name="idemisor" value="<%=pre_idemisor%>"/>
		</form>
		
		
		
		<form name="formImprime" method="POST" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ReparaHttpHandler"/>
			<input type="hidden" name="services"	value="GenCodBarrasReciboSrv"/>
			<input type="hidden" name="view" 		value="reparaciones/GeneraCodBarras.jsp"/>
			<input type="hidden" name="filereci"    value=""/>
		</form>
</body>