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
	
	
 if ((cdpantal != null) && (cdpantal.equals("liststoc"))){
	 txpantal = "Listado Stock";
 } else if ((cdpantal != null) && (cdpantal.equals("listdepo"))){
	 txpantal = "Listado Déposito";
 } else if ((cdpantal != null) && (cdpantal.equals("listvend"))){
	 txpantal = "Listado Vendidos";
 } else if ((cdpantal != null) && (cdpantal.equals("listdeag"))){
	 txpantal = "Listado Desglose";
 } else if ((cdpantal != null) && (cdpantal.equals("inventar"))){
	 txpantal = "Crear Inventario";
} else if ((cdpantal != null) && (cdpantal.equals("stockimg"))){
	 txpantal = "Listado Stock";
}else if ((cdpantal != null) && (cdpantal.equals("selecpro"))){
	 txpantal = "Selecciona producto";
} 
 
 
 
%>

<script>

	function enviaPantalla(idemisor,tpclient, logoemis){
		
		<% if ((cdpantal != null) && (cdpantal.equals("liststoc"))){ %>
			listStock(idemisor,logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listdepo"))){ %>
			listDeposito(idemisor, logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listvend"))){ %>
			listVendidos(idemisor, logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listdeag"))){ %>
			listAgrupados(idemisor, logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("inventar"))){ %>
			creaInventario(idemisor, logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("stockimg"))){ %>
			listStockImg(idemisor, logoemis);
		<%}else if ((cdpantal != null) && (cdpantal.equals("lispieza"))){ %>
			listPiezasImg(idemisor, logoemis);
		<%}else if ((cdpantal != null) && (cdpantal.equals("listcodi"))){ %>
			listCodigosVentas(idemisor, logoemis);
		<%}else if ((cdpantal != null) && (cdpantal.equals("selecpro"))){ %>
			selectPro(idemisor, logoemis);
		<%}else if ((cdpantal != null) && (cdpantal.equals("listinve"))){ %>
			listInventario(idemisor, logoemis);
		<%}%>
		
		
			
	}

	function listStock(idemisor, logoemis){
		document.formMenu.services.value = "ListStockSrv";
		document.formMenu.view.value	 = "stock/listadoStock.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.cdestado.value = "STOCK";
		document.formMenu.submit();
	}
	
	function listStockImg(idemisor, logoemis){
		document.formMenu.services.value = "ListStockImgSrv";
		document.formMenu.view.value	 = "stock/listadoStockImagen.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.cdestado.value = "STOCK";
		document.formMenu.submit();
	}
	
	function listPiezasImg(idemisor, logoemis){
		document.formMenu.services.value = "ListPiezasImgSrv";
		document.formMenu.view.value	 = "stock/listadoPiezasImagen.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.cdestado.value = "STOCK";
		document.formMenu.submit();
	}
	
	
	
	function listDeposito(idemisor, logoemis){
		document.formMenu.services.value = "ListStockSrv";
		document.formMenu.view.value	 = "stock/listadoStock.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = <%=tpclient%>;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.cdestado.value = "DEPOSITO";
		document.formMenu.submit();
	}
	
	function listVendidos(idemisor, logoemis){
		document.formMenu.services.value = "ListStockSrv";
		document.formMenu.view.value	 = "stock/listadoVendido.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = <%=tpclient%>;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.cdestado.value = "VENDIDO";
		document.formMenu.submit();
	}
	
	function creaInventario(idemisor, logoemis){
		document.formMenu.services.value = "ListInventSrv";
		document.formMenu.view.value	 = "stock/introInventario.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.cdestado.value = "N";
		document.formMenu.submit();
	}
	
	function listInventario(idemisor, logoemis){
		document.formMenu.services.value = "ListInventSrv";
		document.formMenu.view.value	 = "stock/listadoInventario.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.cdestado.value = "N";
		document.formMenu.submit();
	}
	
	function listCodigosVentas(idemisor){
		document.formMenu.services.value = "ListCodVentasSrv";
		document.formMenu.view.value	 = "stock/listadoCodigosVentas.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.cdestado.value = "N";
		document.formMenu.submit();
	}
	
	function selectPro(idemisor){
		document.formMenu.services.value = "SelecProducSrv";
		document.formMenu.view.value	 = "stock/selecProduc.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.submit();
	}
	
	
	
	
	
	
</script>

<body>	

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
			System.out.println("El emisor que envia es"+sesemiso);
		%>
		enviaPantalla('<%=idemisor%>',<%=tpclient%>);
	<% } %>
</script>

	<div class="fondo2">
		<div class="testata"><img src="/JLet/common/img/icons/icon-title-altaenvio.png"></div>
		<div class="nompanta" ><%=txpantal %></div>	
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
