<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid gdGimnas = null;
	Grid gdCotiza = null;
	String txmensaj = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gdGimnas = io.getGrid("gdGimnas");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listEnvio.jsp "+ e.getMessage());	
		}
	}
	
%>


<head>
	<script>
		
	 function verListEquipos(idcli){
		 
		 document.formenvi.idclient.value = idcli;
		 document.formenvi.services.value = "UpdEquipamientoSrv";
		 document.formenvi.view.value = "gimnasio/DetalleEquiposGym.jsp";
		 document.formenvi.submit();
		 document.formenvi.submit();
		 
	 }
	 
 function verGymDetalle(idcli){
		 
		 document.formenvi.idclient.value = idcli;
		 document.formenvi.submit();
		 
	 }
	 
	 function verEquiposDetalle(idcli){
		 
		 document.formenvi.idclient.value = idcli;
		 document.formenvi.services.value = "ListEquipamientoSrv";
		 document.formenvi.view.value = "gimnasio/altaEquipos.jsp";
		 document.formenvi.submit();
		 
	 }
		
	</script>
</head>


<body>
	<div class="fondo2">
	   <div class="testata"><img src="/JLet/common/img/icons/title-listado-gym.png"></div>
		<div class="centrado" style="width:95%">
			
			<form name="formenvi" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="GimnasioHttpHandler"/>
				<input type="hidden" name="services"	value="ListGimnasioSrv"/>
				<input type="hidden" name="view" 		value="gimnasio/altaGym.jsp"/>
				<input type="hidden" name="idclient" 	value=""/>
						
				
				<table width="90%" align="center">
					<tr class="fonts">
					
						<td width="28%" align="center"><div class="input-b1">Nombre</div></td>
						<td width="8%" align="center"><div class="input-b1">Ciudad</div></td>
						<td width="8%" align="center"><div class="input-b1">Telefono Gym</div></td>
						<td width="14%" align="center"><div class="input-b1">Nombre Responsable</div></td>
						<td width="7%" align="center"><div class="input-b1">Telefono Resp.</div></td>
						<td width="7%" align="center"><div class="input-b1">Mail</div></td>
						<td width="7%" align="center"><div class="input-b1">Detalle</div></td>
						<td width="7%" align="center"><div class="input-b1">Equipos</div></td>
					</tr>
					
					
					<% for (int i = 0; i < gdGimnas.rowCount(); i++) { 
						
						String idclient = "";
						String txnombre = "";
						String txciudad = "";
						String telefono = "";
						String nomrespo = "";
						String tefrespo = "";
						String mailresp = "";
						
						try {
							idclient = gdGimnas.getStringCell(i,"idclient");
							txnombre = gdGimnas.getStringCell(i,"txnombre");
							txciudad = gdGimnas.getStringCell(i,"txciudad");
							telefono = gdGimnas.getStringCell(i,"telefono");
							nomrespo = gdGimnas.getStringCell(i,"txnomres");
							tefrespo = gdGimnas.getStringCell(i,"tfnomovi");
							mailresp = gdGimnas.getStringCell(i,"mailresp");
							
	
							%>
							<tr style="cursor:pointer;font-size:12px" onclick="verGymDetalle('<%=idclient%>')">
								
								<td align="center" class="fonts6" style="font-size:12px"><%=txnombre%></td>
								<td align="center" class="fonts6" style="font-size:12px"><%=txciudad%></td>
								<td align="center" class="fonts6" style="font-size:12px"><%=telefono%></td>
								<td align="center" class="fonts6" style="font-size:12px"><%=nomrespo%></td>
								<td align="center" class="fonts6" style="font-size:12px"><%=tefrespo%></td>
								<td align="center" class="fonts6" style="font-size:12px"><%=mailresp%></td>
								<td align="center" class="fonts6" style="font-size:12px" onclick="verListEquipos('<%=idclient%>')">&nbsp; Ver</td>
								<td align="center" class="fonts6" style="font-size:12px" onclick="verEquiposDetalle('<%=idclient%>')">&nbsp; Ver</td>
								
							</tr>
						<% } catch (Exception e) { 							
							System.out.println(" ERROR - Recuperando linea subasta: "+ i +" - "+ e.getMessage());
						   }
						
					} %>						
				
				</table>											

			</form>
</body>
