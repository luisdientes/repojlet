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
		
		<% if ((cdpantal != null) && (cdpantal.equals("altapers"))){ %>
			altaPersonal(idemisor);
		<% } else if ((cdpantal != null) && (cdpantal.equals("altaproy"))){ %>
			altaProy(idemisor);
		<% } else if ((cdpantal != null) && (cdpantal.equals("altaempr"))){ %>
			altaEmpr(idemisor);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listproy"))){ %>
			listProy(idemisor);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listempr"))){ %>
			listEmpr(idemisor);
		<% } else if ((cdpantal != null) && (cdpantal.equals("listpers"))){ %>
			listPers(idemisor);
	<% } %>
	
		
	}

	function altaPersonal(idemisor){
		
		document.formMenu.services.value = "InitTrabajadorSrv";
		document.formMenu.view.value	 = "personal/administracion/altaTrabajador.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.submit();
		
	}

	function altaProy(idemisor){
		document.formMenu.services.value = "InitProyectosSrv";
		document.formMenu.view.value	 = "personal/administracion/altaProyectos.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.submit();
	}
	
	function altaEmpr(idemisor){
		document.formMenu.services.value = "InitEmpresasSrv";
		document.formMenu.view.value	 = "personal/administracion/altaEmpresa.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.submit();
		
	}

	function listProy(idemisor){
		document.formMenu.services.value = "ListProyectosSrv";
		document.formMenu.view.value	 = "personal/administracion/listadoProyectos.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.submit();
		
	}
	
	function listPers(idemisor){
		document.formMenu.services.value = "ListPersonalSrv";
		document.formMenu.view.value	 = "personal/administracion/listadoEmpleados.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.submit();
		
	}
	
	function listEmpr(idemisor){
		document.formMenu.services.value = "ListEmpresaSrv";
		document.formMenu.view.value	 = "personal/administracion/listadoEmpresas.jsp";		
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.submit();
		
	}
	
		
</script>
		

<body>

	<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="PersonalHttpHandler"/>
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
   <div class="table-responsive">	
	<div class="fondo2">      
		<div class="testata"><img src="/JLet/common/img/icons/icon-clientes.png"></div>
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
	
		
	</div>
</body>
