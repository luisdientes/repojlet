<!DOCTYPE html>

<%@ include file="/common/jsp/include.jsp"%>
	<head>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
	</head>




<%

	HttpSession sesion = request.getSession();
	Grid gridPant = (Grid)sesion.getAttribute("gridPant");

	int pantFila = 3;
	
	String cdpadrex = "INICIO";	

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			cdpadrex = io.getStringValue("cdpadrex");
		} catch (Exception e) {
			cdpadrex = "INICIO";
		}
		
	}

%>

<script>

function redirigePantalla(pantalla,controller,services,view){
	
	document.formMenu.cdpantal.value = pantalla;
	document.formMenu.controller.value = controller;
	document.formMenu.services.value = services;
	document.formMenu.view.value = view;
	
	document.formMenu.submit();
	
}
	function entra(){
		
		alert()
	}
</script>

<body>
	<div class="fondo2">
		<div class="centrado" style="width:100%">
			<form name="formMenu" action="/JLet/App" method="GET">
				<input type="hidden" name="controller" 	value=""/>
				<input type="hidden" name="services" 	value=""/>
				<input type="hidden" name="view" 		value=""/>	
				<input type="hidden" name="cdpantal" 	value=""/>	
			
				<table class="tabmenuprin">
					
					<tr>
					
					<% 
							int contPant = 0;
							for (int i = 0; i < gridPant.rowCount(); i++){ 
								
								if (gridPant.getStringCell(i,"cdpadrex").equals(cdpadrex)){ 
									contPant++;
									String cdpantal = gridPant.getStringCell(i,"cdpantal");
									String txnombre = gridPant.getStringCell(i,"txnombre");
									String iconfile = gridPant.getStringCell(i,"iconfile");
									String controll = gridPant.getStringCell(i,"controll");
									String services = gridPant.getStringCell(i,"services");
									String viewjspx = gridPant.getStringCell(i,"viewjspx");																		
								
									%>
									
								<td width="5%">&nbsp;</td>
									<td width="30%" align="center">
										<table width="100%">
											<tr>
												<td align="center" style="border-color: #FFFFFF">
													<img src='/JLet/common/img/icons/pantallas/<%=iconfile%>' align="center" onclick="redirigePantalla('<%=cdpantal%>','<%=controll%>','<%=services%>','<%=viewjspx%>')" title="<%=txnombre%>" style="cursor:pointer"/>
												</td>
											</tr>
											<tr>
												<td align="center"><%=txnombre%></td>
											</tr>
										</table>
									</td>
									
									<% if (contPant % pantFila == 0) { %>
										</tr>
										<tr>
									<% }
								} %>
						<% } %>
						</tr>
				 </table>
					
			</form>
		</div>
	</div>
</body>