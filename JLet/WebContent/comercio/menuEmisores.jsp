<%@ include file="/common/jsp/include.jsp"%>

<%

	String cdpantal = ""; 
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
	
%>

<script>

	function enviaPantalla(idemisor,tpclient){
		
		<% if ((cdpantal != null) && (cdpantal.equals("liststoc"))){ %>
			listStock(idemisor,tpclient);
		<% } else if ((cdpantal != null) && (cdpantal.equals("altastoc"))){ %>
			altaStock(idemisor,tpclient);
		<% } else if ((cdpantal != null) && (cdpantal.equals("altapiez"))){ %>
			altaPieza(idemisor,tpclient);
		<% } else if ((cdpantal != null) && (cdpantal.equals("newenvio"))){ %>
			altaEnvio(idemisor,tpclient);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listenvi"))){ %>
			listEnvio(idemisor,tpclient);
	<% } %>
	
		
	}

	function altaStock(idemisor,tpclient){
		
		document.formMenu.services.value = "InitStockSrv";
		document.formMenu.view.value	 = "comercio/altaStock.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.submit();
		
	}

	function listStock(idemisor,tpclient){
		document.formMenu.services.value = "ListStockSrv";
		document.formMenu.view.value	 = "comercio/listadoStock.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value  = tpclient;
		document.formMenu.cdestado.value = "STOCK";
		document.formMenu.submit();
	}
	
	function altaPieza(idemisor,tpclient){
		document.formMenu.services.value = "InitStockSrv";
		document.formMenu.view.value	 = "comercio/altaEnvioPiezas.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value  = tpclient;
		document.formMenu.cdpantal.value = "altapiez";
		document.formMenu.submit();
		
	}

	function altaEnvio(idemisor,tpclient){
		document.formMenu.services.value = "InitEnvioSrv";
		document.formMenu.view.value	 = "comercio/altaEnvio.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value  = tpclient;
		document.formMenu.cdpantal.value = "newenvio";
		document.formMenu.submit();
		
	}
	
	function listEnvio(idemisor,tpclient){
		document.formMenu.services.value = "ListEnvioSrv";
		document.formMenu.view.value	 = "comercio/listEnvio.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value  = tpclient;
		document.formMenu.cdpantal.value = "listenvi";
		document.formMenu.submit();
		
	}
	
		
</script>

<body>	

<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value=""/>
			<input type="hidden" name="tpclient" 	value=""/>
			<input type="hidden" name="cdestado" 	value=""/>
			<input type="hidden" name="cdpantal" 	value=""/>
			
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
	<div class="fondo2">
		<div class="testata"><img src="/JLet/common/img/icons/icon-title-altaenvio.png"></div>
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
					<tr onclick='enviaPantalla(<%=gridEmis.getStringCell(i,"idemisor")%>,<%=gridEmis.getStringCell(i,"tpclient")%>)' style="cursor:pointer">
						<td align="center"><img src='<%=pathimgx%>icons/emisores/<%=logoimgx%>' title="Usuario"/></td>
						<td align="center"class="usuario"><%=gridEmis.getStringCell(i,"rzsocial")%></td>
					</tr>
				<% } %>
			 </table>
			</div>	
		</div>	
	
		
	</div>
</body>
