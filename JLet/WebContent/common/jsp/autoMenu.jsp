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

%>

<script>

	function autoLaunch(idemisor, logoemis){
		
		idemisor = 8;
		logoemis = 8;
		
		document.formMenu.services.value = "ListStockSrv";
		document.formMenu.view.value	 = "stock/listadoStock.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.cdestado.value = "VENDIDO";
		document.formMenu.submit();
		
	}
	
	
</script>

<body onload="autoLaunch()">	

	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" 	value="StockHttpHandler"/>
		<input type="hidden" name="services" 	value=""/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idemisor" 	value=""/>
		<input type="hidden" name="tpclient" 	value=""/>
		<input type="hidden" name="cdestado" 	value=""/>
		<input type="hidden" name="tipocons" 	value=""/>
		<input type="hidden" name="cdpantal" 	value=""/>
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
				
				System.out.println("El emisor que envia es "+sesemiso);
				
			%>
			enviaPantalla('<%=idemisor%>',<%=tpclient%>);
		<% } %>
	</script>

</body>
