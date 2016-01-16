<%@ include file="/common/jsp/include.jsp"%>

<%

	String cdpantal = ""; 
	String txpantal = "";
	String sesemiso = "";
	String tpclient = "";
	Grid gridEmis = null; 
	

	try {
		sesemiso = (String) request.getSession().getAttribute("idemisor");
		tpclient = (String) request.getSession().getAttribute("tpclient");
		System.out.println("Este es el emisor de sesion: "+ sesemiso);
	} catch (Exception e) {
		
	}
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			cdpantal = io.getStringValue("cdpantal");
			gridEmis = io.getGrid("gridEmis");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/menuEmisores.jsp "+ e.getMessage());	
		}
	}

	System.out.println("ESTA ES LA PANTALLA. "+ cdpantal);
	
	
	if ((cdpantal != null) && (cdpantal.equals("altarepa"))){
		txpantal = "Alta Reparación";
 	} else if ((cdpantal != null) && (cdpantal.equals("listrepa"))){ 
	 	txpantal = "Listado Reparación";
	} else if ((cdpantal != null) && (cdpantal.equals("busqrepa"))){ 
		txpantal = "Busqueda Reparación";
	}
	
	
%>

<script>

	function enviaPantalla(idemisor,tpclient,logoemis){
		
		<% if ((cdpantal != null) && (cdpantal.equals("altarepa"))){ %>
			altaReparacion(idemisor,tpclient,logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listrepa"))){ %>
			listRepara(idemisor,tpclient,logoemis);
		<% }  else if ((cdpantal != null) && (cdpantal.equals("busqrepa"))){ %>
			busqrepa(idemisor,tpclient,logoemis);
		<%	}%>
	}	

	function altaReparacion(idemisor,tpclient,logoemis){
		
		document.formMenu.services.value = "InitReparaSrv";
		document.formMenu.view.value	 = "reparaciones/altaReparacion.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.tpclient.value = tpclient;
		
		
		document.formMenu.submit();
		
	}

	function listRepara(idemisor, tpclient, logoemis){
		document.formMenu.services.value = "ListReparaSrv";
		document.formMenu.view.value	 = "reparaciones/listReparaciones.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.submit();
		
	}
	
	function busqrepa(idemisor,tpclient, logoemis){
		document.formMenu.services.value = "ListReparaSrv";
		document.formMenu.view.value	 = "reparaciones/busquedaReparacion.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.submit();
		
	}
		
</script>

<body>
	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" 	value="ReparaHttpHandler"/>
		<input type="hidden" name="services" 	value=""/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idemisor" 	value=""/>
		<input type="hidden" name="tpclient" 	value=""/>
		<input type="hidden" name="logoemis" 	value=""/>
	</form>
	
	<script>
			<% 
				String idemisor = "";
				
				if ((gridEmis.rowCount() == 1) || ((sesemiso != null) && (!sesemiso.equals("")) && (!sesemiso.equals("0")))){ 
					if (gridEmis.rowCount() == 1) {
						idemisor = gridEmis.getStringCell(0,"idemisor");
					} else {
						idemisor = sesemiso;
					}
					System.out.println("El emisor que envia es"+sesemiso);
				%>
				enviaPantalla('<%=idemisor%>',<%=tpclient%>);
			<% } %>
		</script>
	


    <div class="table-responsive">	
	<div class="fondo2">
	<div class="testata"><img src="/JLet/common/img/icons/title-reparaciones.png"/></div>	
	<div class=nompanta ><%=txpantal %></div>	
	
		<div class="centrado">
		<table width=90% align="center" class="tab-login">
			<%
				String logoimgx ="";
				for (int i = 0; i < gridEmis.rowCount(); i++){
					logoimgx = gridEmis.getStringCell(i,"logoimgx");
					
					if(logoimgx.equals("")){
						logoimgx ="not_found.png";
					}
				 %>
				<tr onclick='enviaPantalla(<%=gridEmis.getStringCell(i,"idemisor")%>,<%=gridEmis.getStringCell(i,"tpclient")%>,"<%=gridEmis.getStringCell(i,"logoimgx")%>")' style="cursor:pointer">
					<td align="center"><img src='<%=pathimgx%>icons/emisores/<%=logoimgx%>' title="Usuario"/></td>
					<td align="center"class="usuario"><%=gridEmis.getStringCell(i,"rzsocial")%></td>
				</tr>
			<% } %>
		 </table>
		</div>	
	</div>
    </div>	

	
</body>