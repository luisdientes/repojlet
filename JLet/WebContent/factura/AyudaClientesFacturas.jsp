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
			grClient = io.getGrid("gdClient");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en clientes/listadoClientes.jsp "+ e.getMessage());	
		}
	}

%>

<script>

document.getElementById("dl-menu").style.display	= "none";
document.getElementById("menu-izq1").style.display	= "none";
document.getElementById("logo-izq").style.display	= "none";

	function selecCliente(idclient, idemisor){
		 alertify.confirm("<p>&iquest;Desea a&ntilde;adir el cliente", function (e) {
				if (e) {
					 opener.buscaClien(idclient,idemisor);
					 window.close();
				}
			}); 
			return false	
	}
	
	function buscaCli(){
		document.abriClie.view.value	 = "factura/AyudaClientesFacturas.jsp";	
		document.abriClie.txrazons.value = document.getElementById("txrazons").value;
		document.abriClie.submit();
		
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


<div class="fondo2">
	<div class="testata">
		<img src="/JLet/common/img/icons/clientes.png"/>
	</div>	
	
	
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
				<tr class="oddRow"  style="border:1px solid;cursor:pointer" onclick="selecCliente('<%=cdintern %>','<%=idemisor %>')" >
				<% } else { %>
				<tr class="evenRow" style="border:1px solid;cursor:pointer" onclick="selecCliente('<%=cdintern %>','<%=idemisor %>')" >
				<% }%>
					<td align="right"  class="fonts6jej"><%=cdintern%>&nbsp;&nbsp;</td>
					<td align="left"   class="fonts6jej"><%=rzsocial%></td>
					<td align="center" class="fonts6jej"><%=idfiscal%></td>
					<td align="left"   class="fonts6jej"><%=txmailxx%></td>
					<td align="left"   class="fonts6jej"><%=tfnofijo%></td>
				</tr>
		 <% } 
		}%>
	 </table>
	</div>	
</div>	
	

