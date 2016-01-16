<%@ include file="/common/jsp/include.jsp"%>

<%

	String cdpantal = ""; 
	Grid gridEmis = null; 
	
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
		
		<% if ((cdpantal != null) && (cdpantal.equals("altareci"))){ %>
			altaRecibos(idemisor,tpclient);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listreci"))){ %>
			listRecibos(idemisor,tpclient);
		<% } else if ((cdpantal != null) && (cdpantal.equals("factalba"))){ %>
			listAlbaranes(idemisor);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listalbar"))){ %>
			listAlbaran(idemisor);
		<% } %>
		
	}

	function altaRecibos(idemisor,tpclient){
		
		document.formMenu.services.value = "InitFacturaSrv";
		document.formMenu.view.value	 = "recibos/altaRecibos.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.submit();
		
	}

	function listRecibos(idemisor,tpclient){
				
		document.formMenu.services.value = "ListRecibosSrv";
		document.formMenu.view.value	 = "recibos/listadoRecibos.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.submit();
		
	}
	
		
</script>

<body>	
	<div class="fondo2">
	<div class="testata"><img src="/JLet/common/img/icons/title-recibos.png"/></div>	
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

	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" 	value="RecibosHttpHandler"/>
		<input type="hidden" name="services" 	value=""/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idemisor" 	value=""/>
		<input type="hidden" name="tpclient" 	value=""/>
		<input type="hidden" name="aniofact" 	value=""/>
	</form>
	
	<script>
		<% if (gridEmis.rowCount() == 1){ %>
			enviaPantalla('<%=gridEmis.getStringCell(0,"idemisor")%>');
		<% } %>
	</script>
	
</body>