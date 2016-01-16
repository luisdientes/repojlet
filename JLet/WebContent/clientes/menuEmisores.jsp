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
		
		<% if ((cdpantal != null) && (cdpantal.equals("altaclie"))){ %>
			altaClientes(idemisor,tpclient);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listclie"))){ %>
			listClientes(idemisor,tpclient);
		<% } %>
		
	}

	function altaClientes(idemisor,tpclient){
		
		document.formMenu.services.value = "InitClienteSrv";
		document.formMenu.view.value	 = "clientes/altaCliente.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.submit();
		
	}

	function listClientes(idemisor,tpclient){
		document.formMenu.services.value = "ListClientesSrv";
		document.formMenu.view.value	 = "clientes/listClientes.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.submit();
		
	}
	
		
</script>

<body>	
<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ClientesHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value=""/>
			<input type="hidden" name="tpclient" 	value=""/>
			<input type="hidden" name="aniofact" 	value=""/>
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
		<div class="testata"><img src="/JLet/common/img/icons/icon-clientes.png"/></div>	
			<div class="centrado">
			<table width=90% align="center" class="tab-login">
				<% for (int i = 0; i < gridEmis.rowCount(); i++){ %>
					<tr onclick='enviaPantalla(<%=gridEmis.getStringCell(i,"idemisor")%>,<%=gridEmis.getStringCell(i,"tpclient")%>)' style="cursor:pointer">
						<td align="center"><img src='<%=pathimgx%>icons/emisores/<%=gridEmis.getStringCell(i,"logoimgx")%>' title="Usuario"/></td>
						<td align="center"class="usuario"><%=gridEmis.getStringCell(i,"rzsocial")%></td>
					</tr>
				<% } %>
			 </table>
			</div>	
		</div>	
	
		
	</div>
</body>