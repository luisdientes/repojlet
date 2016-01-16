<%@ include file="/common/jsp/include.jsp"%>

<%
	Grid grClient   = null; 

	String idclient = "";
	String tpclient = "";
	String cdintern = "";
	String rzsocial = "";
	String idfiscal = "";
	String txdirecc = "";
	String txciudad = "";
	String tfnofijo = "";
	String fhaltaxx = "";
	String idemisor = "";
	String asignafa = "";
	String txmailxx = "";
	String clientea = "";
	String filename = "";

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			tpclient = io.getStringValue("tpclient");
			asignafa = io.getStringValue("asignafa");
			filename = io.getStringValue("filename");
			clientea = io.getStringValue("clientea");
			grClient = io.getGrid("gdClient");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en clientes/listadoClientes.jsp "+ e.getMessage());	
		}
	}
System.out.println("Cliente antigua "+clientea);
%>

<script>

	function abrirCliente(idcli,tpclient){
		document.abriClie.idclient.value = idcli;
		document.abriClie.tpclient.value = tpclient;
		document.abriClie.view.value	 = "clientes/altaCliente.jsp";		
		document.abriClie.submit();
	}
	
	function buscaCli(){
		document.abriClie.view.value	 = "clientes/listClientes.jsp";	
		document.abriClie.txrazons.value = document.getElementById("txrazons").value;
		document.abriClie.submit();
		
	}
	
	function asignar(idclient,tpclient,clientea){
		
		document.asignacli.idclient.value = idclient
		document.asignacli.tpclient.value = tpclient;
		document.asignacli.view.value	  = "clientes/resultAltaCliente.jsp";	
		document.asignacli.clientea.value = clientea;
		if(confirm("asignar las facturas al cliente "+idclient)){
			document.asignacli.submit();	
		}
		
	}
	
	
	function descargarExcel(){
		
		document.abriXlsx.submit();
	}

</script>

<form method="post" name="asignacli" action="/JLet/App" >
		<input type="hidden" name="controller" 	value="ClientesHttpHandler"/>
		<input type="hidden" name="services"	value="BorraClientesSrv"/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idclient" 	value=""/>
		<input type="hidden" name="tpclient" 	value=""/>
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
		<input type="hidden" name="txrazons" 	value=""/>
		<input type="hidden" name="clientea" 	value=""/>	
</form>

<form method="post" name="abriClie" action="/JLet/App" >
		<input type="hidden" name="controller" 	value="ClientesHttpHandler"/>
		<input type="hidden" name="services"	value="ListClientesSrv"/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idclient" 	value=""/>
		<input type="hidden" name="tpclient" 	value="<%=tpclient%>"/>
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
		<input type="hidden" name="txrazons" 	value=""/>
</form>

<form method="post" name="abriXlsx" action="/JLet/JLetDownload" target="_blank">
		<input type="hidden" name="idusuari" value="1"/>
		<input type="hidden" name="tipofile" value="XLSX"/>
		<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
		<input type="hidden" name="filename" value="<%=filename%>"/><!-- Cambiar -->
</form>

<div class="fondo2">
	<div class="testata">
		<img src="/JLet/common/img/icons/clientes.png"/>
	</div>	
	
	<table width="100%" align="center">
				<tbody>
						<tr>
							<td align="center" style="cursor:pointer"><span onclick="descargarExcel()">Exportar a Excel. Aquí.</span></td>
						</tr>
				</tbody>
			</table>
	
	<table align="center" valing="center" width=40% style="margin-left:43%;">
		<tr>
			<td class="cabecera input-b1">Introduce Nombre cliente:</td>
		</tr>
		<tr>
			<td align="center" class="fonts6" style="padding-left:0px !important">
				<input class="input-m" type="text" size=45 id="txrazons"/>
			</td>
		</tr>
		<tr>
			<td align="center" style="padding-left:0px !important">
				<a class="boton" onclick ="buscaCli();">Aceptar</a>
			</td>
		</tr>
	</table>
	
	<div class="centradoFac" width="95%">
		<table align="center" width="100%">
			<tr>
				<td>&nbsp;</td>
		  		<td colspan="6"><div class="input-b1">Listado Clientes</div></td>
		  	</tr>
			<tr>
				<td>&nbsp;</td>
				<td><div class="input-b1" width="5%" >Código</div></td>
				<td><div class="input-b1" width="25%">Razón Social</div></td>
				<td><div class="input-b1" width="10%">ID. Fiscal</div></td>				
				<td><div class="input-b1" width="35%">Mail</div></td>
				<td><div class="input-b1" width="10%">Teléfono</div></td>
			</tr>
			
			<% for(int i=0; i< grClient.rowCount();i++ ){

				idclient = grClient.getStringCell(i,"idclient");
				rzsocial = grClient.getStringCell(i,"rzsocial");
				cdintern = grClient.getStringCell(i,"cdintern");
				tpclient = grClient.getStringCell(i,"tpclient");
				idfiscal = grClient.getStringCell(i,"idfiscal");
				txciudad = grClient.getStringCell(i,"txciudad");
				tfnofijo = grClient.getStringCell(i,"tfnofijo");
				txmailxx = grClient.getStringCell(i,"txmailxx");
				idemisor = grClient.getStringCell(i,"idemisor");
				
				if(rzsocial.equals("")){
					rzsocial  ="-";
				}
				if(idfiscal.equals("")){
					idfiscal  ="-";
				}
				if(txciudad.equals("")){
					txciudad  ="-";
				}
				if(tfnofijo.equals("")){
					tfnofijo  ="-";
				}
				if(txmailxx.equals("")){
					txmailxx  ="-";
				}
				
				if(clientea == null){
					clientea ="";
				}
				
			if(!clientea.equals(idclient)){
				if (i % 2 == 0) { %>
				<tr class="oddRow"  style="border:1px solid">
				<% } else { %>
				<tr class="evenRow" style="border:1px solid">
				<% }%>
					<td align="center" onclick="abrirCliente('<%=idclient %>','<%=tpclient %>')" style="cursor:pointer">
						<img src="/JLet/common/img/icons/clientes.png" width="24px" title="Factura"/>
					</td>
					
					<td align="right"  class="fonts6jej"><%=cdintern%>&nbsp;&nbsp;</td>
					<td align="left"   class="fonts6jej"><%=rzsocial%></td>
					<td align="center" class="fonts6jej"><%=idfiscal%></td>
					<td align="left"   class="fonts6jej"><%=txmailxx%></td>
					<td align="left"   class="fonts6jej"><%=tfnofijo%></td>
					<% 
					if(asignafa !=null && asignafa.equals("S")){%>		
					<td align="left" style="cursor:pointer"  class="fonts6" onclick="asignar('<%=idclient %>','<%=tpclient %>','<%=clientea%>')">Asignar</td>			
					<% }%>
				</tr>
		 <% } 
		}%>
	 </table>
	</div>	
</div>	
	

