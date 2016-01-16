<%@ include file="/common/jsp/include.jsp"%>

<%
	String  idemisor = "";
	String txmensaj = "";
	Grid gdTienda = null;
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gdTienda = io.getGrid("gdTienda");		
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
	 
 function verTiendaDetalle(idtienda){
		 
		 document.formenvi.idtienda.value = idtienda;
		 document.formenvi.submit();
		 
	 }
	 
		
	</script>
</head>


<body>
	<div class="centrado-all">
	   <table width="98%" align="center">
			<tr>
				<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
			</tr>
			<tr>
				<td width=100% align="center"><div class="nompanta" style="margin-left:0%" >Listado Tiendas</div></td>						
			</tr>
		</table>
		<br>
		<br>
			
			<form name="formenvi" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="TiendasHttpHandler"/>
				<input type="hidden" name="services"	value="ListTiendasSrv"/>
				<input type="hidden" name="view" 		value="tiendas/altaTiendas.jsp"/>
				<input type="hidden" name="idtienda" 	value=""/>
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
						
			<% if(gdTienda.rowCount() > 0){	%>	
			
				<table width="90%" align="center">
					<tr class="fonts">
					
						<td width="20%" align="center"><div class="input-b1">Nombre</div></td>
						<td width="15%" align="center"><div class="input-b1">Ciudad</div></td>
						<td width="15%" align="center"><div class="input-b1">Telefono </div></td>
						<td width="20%" align="center"><div class="input-b1">Nombre Responsable</div></td>
						<td width="15%" align="center"><div class="input-b1">Telefono Resp.</div></td>
						<td width="15%" align="center"><div class="input-b1">Mail</div></td>
					</tr>
					
					
				<% 
						for (int i = 0; i < gdTienda.rowCount(); i++) { 
							
							String idclient = "";
							String txnombre = "";
							String txciudad = "";
							String telefono = "";
							String nomrespo = "";
							String tefrespo = "";
							String mailresp = "";
							String idtienda = "";
							
							try {
								idtienda = gdTienda.getStringCell(i,"idtienda");
								idemisor = gdTienda.getStringCell(i,"idemisor");
								txnombre = gdTienda.getStringCell(i,"txnombre");
								txciudad = gdTienda.getStringCell(i,"txciudad");
								telefono = gdTienda.getStringCell(i,"telefono");
								nomrespo = gdTienda.getStringCell(i,"txnomres");
								tefrespo = gdTienda.getStringCell(i,"tfnomovi");
								mailresp = gdTienda.getStringCell(i,"mailresp");
								
		
							if (i % 2 == 0) { %>
			  					<tr class="oddRow" style="cursor:pointer;border:1px solid" onclick="verTiendaDetalle('<%=idtienda%>')">
						 <% } else { %>
						 		<tr class="evenRow" style="cursor:pointer;border:1px solid" onclick="verTiendaDetalle('<%=idtienda%>')">
						 <% } %>	
									<td align="center" class="fonts6jej" style="font-size:12px"><%=txnombre%></td>
									<td align="center" class="fonts6jej" style="font-size:12px"><%=txciudad%></td>
									<td align="center" class="fonts6jej" style="font-size:12px"><%=telefono%></td>
									<td align="center" class="fonts6jej" style="font-size:12px"><%=nomrespo%></td>
									<td align="center" class="fonts6jej" style="font-size:12px"><%=tefrespo%></td>
									<td align="center" class="fonts6jej" style="font-size:12px"><%=mailresp%></td>					
								</tr>
							<% } catch (Exception e) { 							
								System.out.println(" ERROR - Recuperando linea TIENDA: "+ i +" - "+ e.getMessage());
							   }
							
						}
				%>
				</table>
				<%	}else{ %>
				<table width="90%" align="center">
					<tr class="fonts">
						<td align="center"><h2>No se han encontrado resultados</h2></td>
					</tr>
				</table>
					<% }%>																
				
			</form>
			 <table align="center"  width="20%" border="0" >
		 <tr>
		 	<td align="center" onclick="javascript:document.location.href='/JLet/common/jsp/menuPrinc.jsp'" style="cursor:pointer"><b>Ir a Menú</b></td>
		 </tr>
		
		 </table>
		 <br>
		 <br>
		</div>
</body>
