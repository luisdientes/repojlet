<%@ include file="/common/jsp/include.jsp"%>

<%

	String cdpantal = ""; 
	String txpantal = "";
	String modofact = "";
	String sesemiso = "";
	String tpclient = "";
	Grid gridEmis = null; 
	
	try {
		sesemiso = (String) request.getSession().getAttribute("idemisor");
		tpclient = (String) request.getSession().getAttribute("tpclient");
		System.out.println("Este es el emisor de sesion: "+ sesemiso);
	} catch (Exception e) {
		
	}
	
	
	
	modofact = request.getParameter("tpfact");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			cdpantal = io.getStringValue("cdpantal");
			gridEmis = io.getGrid("gridEmis");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/menuEmisores.jsp "+ e.getMessage());	
		}
	}

	if ((cdpantal != null) && (cdpantal.equals("altafact"))){
		 txpantal = "Alta Factura ";
	 } else if ((cdpantal != null) && (cdpantal.equals("listfact"))){
		 txpantal = "Listado Facturas";
	 } else if ((cdpantal != null) && (cdpantal.equals("factalba"))){
		 txpantal = "Anular Conduce";
	 } else if ((cdpantal != null) && (cdpantal.equals("listalbar"))){
		 txpantal = "Listado Conduces";
	 } else if ((cdpantal != null) && (cdpantal.equals("altafcre"))){
		 txpantal = "Alta Nota Crédito";
	 }else if ((cdpantal != null) && (cdpantal.equals("graffact"))){
		 txpantal = "Gráfica Facturación";
	 } else if ((cdpantal != null) && (cdpantal.equals("albadesg"))){
		 txpantal = "Desglose Conduce";
	 }else if ((cdpantal != null) && (cdpantal.equals("listdesg"))){
		 txpantal = "Listado Desglose";
	 }
	
	
%>

<script>

	function enviaPantalla(idemisor,tpclient,logoemis){
		
		if ((tpclient == "") || (tpclient == " ")){
			tpclient = "0";
		}
		
		<% if ((cdpantal != null) && (cdpantal.equals("altafact"))){ %>
			altaFacturas(idemisor,tpclient,'F',logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listfact"))){ %>
			listFacturas(idemisor,'',logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("factalba"))){ %>
			listAlbaranes(idemisor,'', logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listalbar"))){ %>
			listAlbaran(idemisor,'', logoemis);
		<% }else if ((cdpantal != null) && (cdpantal.equals("altafcre"))){ %>
			altaDevolucion(idemisor,tpclient,'D',logoemis);
		<% }else if ((cdpantal != null) && (cdpantal.equals("listalwe"))){ %>
			listalbaranesWeb(idemisor);
		<% }else if ((cdpantal != null) && (cdpantal.equals("graffact"))){ %>
			listGraficaFacturacion(idemisor);
		<% }else if ((cdpantal != null) && (cdpantal.equals("albadesg"))){ %>
			listSelecAlbaranDesglose(idemisor,tpclient, logoemis);
		<% } else if ((cdpantal != null) && (cdpantal.equals("devcondu"))){ %>
			DevolucionConduce(idemisor,tpclient);
		<% }else if ((cdpantal != null) && (cdpantal.equals("lisfaitb"))){ %>
			ListadoFacturasItbis(idemisor,tpclient);
		<% }else if ((cdpantal != null) && (cdpantal.equals("listdesg"))){ %>
			ListadoDesglose(idemisor,tpclient);
	<% } %>
		
		
		
		
	}
	// a ver si llega con esta funcion...
	
	function ListadoDesglose(idemisor,tpclient, modofac,logoemis){
		
		
		document.formMenu.controller.value = "DesgCostesHttpHandler";
		document.formMenu.services.value = "ListDesgloseSrv";
		document.formMenu.view.value	 = "desgcostes/listDesglose.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.submit();
		
	}

	function altaFacturas(idemisor,tpclient, modofac,logoemis){
		
		document.formMenu.services.value = "InitFacturaSrv";
		document.formMenu.view.value	 = "factura/altaFactura.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.modofact.value = modofac;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.submit();
		
	}
	
function DevolucionConduce(idemisor,tpclient, modofac,logoemis){
		
		document.formMenu.services.value = "InitFacturaSrv";
		document.formMenu.view.value	 = "factura/altaDevAlba.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.modofact.value = modofac;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.submit();
		
	}
	
	function altaDevolucion(idemisor,tpclient, modofac, logoemis){
		
		document.formMenu.services.value = "InitDevolucionSrv";
		document.formMenu.view.value	 = "factura/altaDevolucion.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.modofact.value = modofac;
		document.formMenu.submit();
		
	}
	
	
	

	function listFacturas(idemisor,tpclient, logoemis){
				
		document.formMenu.services.value = "ListFacturasEmisorSrv";
		document.formMenu.view.value	 = "factura/listadoFacturasEmisor.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.aniofact.value = "2015";
		document.formMenu.submit();
		
	}
	
	function ListadoFacturasItbis(idemisor,tpclient, logoemis){
		
		document.formMenu.services.value = "ListFacturasEmisorSrv";
		document.formMenu.view.value	 = "factura/listadoFacturasCronoloEmisor.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = "";
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.conitbis.value = "S";
		document.formMenu.aniofact.value = "2015";
		document.formMenu.submit();
		
	}
	

	function listAlbaranes(idemisor,tpclient, logoemis){
		
		document.formMenu.services.value = "ListAlbaranesEmisorSrv";
		document.formMenu.view.value 	 = "factura/selecAlbaran.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.aniofact.value = "";
		document.formMenu.submit();
		
	}
	
	function listAlbaran(idemisor,tpclient, logoemis){
		
		document.formMenu.services.value = "ListAlbaranesEmisorSrv";
		document.formMenu.view.value = "factura/listadoAlbaranesEmisor.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.aniofact.value = "2015";
		document.formMenu.submit();
		
	}
	
	function listalbaranesWeb(idemisor){
		
		document.formMenu.services.value = "ListAlbaranesWebEmisorSrv";
		document.formMenu.view.value = "factura/listadoAlbaranesWebEmisor.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.aniofact.value = "2015";
		document.formMenu.submit();
	}
	

	function listGraficaFacturacion(idemisor){
		document.formMenu.controller.value="InformesHttpHandler"
		document.formMenu.services.value = "ListInformeAnualSrv";
		document.formMenu.view.value = "informes/informeAnual.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.aniofact.value = "2015";
		document.formMenu.submit();
	}
	
	function listSelecAlbaranDesglose(idemisor,tpclient, logoemis){
		document.formMenu.services.value = "ListAlbaranesEmisorSrv";
		document.formMenu.view.value 	 = "factura/selecAlbaranesDesglosado.jsp";	
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.logoemis.value = logoemis;
		document.formMenu.aniofact.value = "2015";
		document.formMenu.submit();
	}
	
	
</script>

<body>	

		
		<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="FacturaHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value=""/>
			<input type="hidden" name="tpclient" 	value=""/>
			<input type="hidden" name="aniofact" 	value=""/>
			<input type="hidden" name="modofact" 	value="<%=modofact%>"/>
			<input type="hidden" name="logoemis" 	value=""/>
			<input type="hidden" name="conitbis" 	value=""/>
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

	<div>
        <div class="fondo2">
		<div class="testata"><img src="/JLet/common/img/icons/factura.png"/></div>	
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

		
		
	</div>
</body>