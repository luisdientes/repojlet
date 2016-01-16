<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid gdRepara = null;

	String txmensaj = "";
	String cdrecibo = "";
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
  				cdrecibo 	= gdRepara.getStringCell(j,"cdrecibo");
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
			document.getElementById("none").style.display = "block";
		}
	
		document.getElementById("idrecibo").value = "";
		document.getElementById("idrecibo").focus();
	}
	
	
	function abrirRecibo(namefile){
		document.abriFactu.filename.value = namefile;
		document.abriFactu.submit();
	}
	
	function imprime(idrecibo){
		document.formImprime.filereci.value= idrecibo;
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
	</table>

	<div class="fondo2">
	<div class="testata"><img src="/JLet/common/img/icons/title-listado-reparaciones.png"/></div>	
	<div class=nompanta >Busqueda Reparación</div>	
		<div class="centrado1b" style="width:100%;min-height:250px;">
			
			<form name="formDetalleRepa" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ReparaHttpHandler"/>
				<input type="hidden" name="services"	value="ListReparaSrv"/>
				<input type="hidden" name="view" 		value="reparaciones/detalleReparacion.jsp"/>
				<input type="hidden" name="cdrecibo" 	value=""/>	
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>"/>	
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>	
				<br><br>
				
				<table align="center" valing="center" width=40%>
					<tr>
						<td class="cabecera input-b1">Introduce ID. Recibo</td>
					</tr>
					<tr>
						<td align="center" class="fonts6" style="padding-left:0px !important">
							<input class="input-m" type="text" size=45 id="idrecibo" onchange="compruebaRecibo(this.value);"/>
						</td>
					</tr>
				</table>
				<br>
				<br>
				
				<table width="40%" align="center">
					<tr>
						<td align="center">
							<div id="noexiste" style="display:none; color:#F00;"><h3>El Recibo introducido no existe.</h3></div>
							<div id="existe" style="display:none; color:#0F0;"><h3>Recibo encontrado.</h3>
						</div>
						</td>
					</tr>
				</table>											
			</form>
</body>