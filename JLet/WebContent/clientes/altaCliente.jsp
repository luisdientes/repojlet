<%@ include file="/common/jsp/include.jsp"%>

<%
	
	String aniofact = null;

	String idclient = "";
	String rzsocial = "";
	String idemisor = "";
	String tpclient = "";
	String cdintern = "";
	String idfiscal = "";
	String txdirecc = "";
	String txciudad = "";
	String cdpostal = "";
	String txmailxx = "";
	String tfnofijo = "";
	String tfnomovi = "";
	String tfnofaxx = "";
	String txwebxxx = "";
	String clientea = "";
	//Textos Multiidioma
	String txnombre = "";
	String txidfisc = "";
	String txcodpos = "";
	String factasoc = "";
	String txprovin = "";
	String txpaisxx = "";
	
	Grid grClient   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			tpclient = io.getStringValue("tpclient");
			factasoc = io.getStringValue("factasoc");
			grClient = io.getGrid("gdClient");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Al.jsp "+ e.getMessage());	
		}
	}
	
	try {
		
		System.out.println("factasociada :"+factasoc);
		idclient = grClient.getStringCell(0,"idclient"); 
		rzsocial = grClient.getStringCell(0,"rzsocial");
		idemisor = grClient.getStringCell(0,"idemisor");
		cdintern = grClient.getStringCell(0,"cdintern");
		idfiscal = grClient.getStringCell(0,"idfiscal");
		txdirecc = grClient.getStringCell(0,"txdirecc");
		txciudad = grClient.getStringCell(0,"txciudad");
		cdpostal = grClient.getStringCell(0,"cdpostal");
		txmailxx = grClient.getStringCell(0,"txmailxx");
		tfnofijo = grClient.getStringCell(0,"tfnofijo");
		tfnomovi = grClient.getStringCell(0,"tfnomovi");
		tfnofaxx = grClient.getStringCell(0,"tfnofaxx");
		txwebxxx = grClient.getStringCell(0,"txwebxxx");
		txpaisxx = grClient.getStringCell(0,"txpaisxx");
		txprovin = grClient.getStringCell(0,"txprovin");
		
	} catch (Exception e) {
		System.out.println(" ERROR  Recuperando Valores");
	}
	
	if ((idemisor.equals("1")) || (idemisor.equals("5"))){
		txcodpos = "Sector";
		if (tpclient.equals("0")){
			txnombre = "Nombre";
			txidfisc = "C&eacute;dula";
		} else {
			txnombre = "Raz&oacute;n Social";
			txidfisc = "RNC";
		}
	} else {
		txcodpos = "C&oacute;digo Postal";
		txnombre = "Nombre / Raz&oacute;n Social";
		txidfisc = "Id. Fiscal";
	}
	
%>


<head>

	<script>
	
	var factasoc ="<%=factasoc%>";
	
		function validarAlta(){
			
			var cadena = "";
			
			if(document.formAltaCli.rzsocial.value == ""){
				cadena+="Campo Razon social no valido";
			}
    	  
    	  	if (cadena == ""){
    	   		document.formAltaCli.submit();
    	  	}else{
    		  alert(cadena);
    	  	}
	  	}	

	function EliminarCliente(){
		
		if(factasoc == 1){
			alert("El cliente tiene facturas, debes asignarlas a otro cliente para confirmar borrado");
			document.formMenu.services.value = "ListClientesSrv";
			document.formMenu.view.value	 = "clientes/listClientes.jsp";		
			document.formMenu.idemisor.value = <%=idemisor%>;
			document.formMenu.tpclient.value = <%=tpclient%>;
			document.formMenu.clientea.value = "<%=idclient%>";
			document.formMenu.asignafa.value = "S";
			document.formMenu.submit();
		}else{
			
			if(confirm("\u00bfSeguro que desea eliminar el cliente?")){
			document.formMenu.services.value = "BorraClientesSrv";
			document.formMenu.view.value	 = "clientes/resultAltaCliente.jsp";		
			document.formMenu.idemisor.value = <%=idemisor%>;
			document.formMenu.tpclient.value = <%=tpclient%>;
			document.formMenu.idclient.value = "<%=idclient%>";
			document.formMenu.submit();
			}
		}
		
	}
	</script>
	
</head>


<body>

	<div class="fondo2">
    <div class="testata"><img src="/JLet/common/img/icons/title-alta-clientes.png"></div>
    <div class="nompanta" >Alta  Clientes</div>
		<div class="centrado" style="width:85%;">
			<form name="formAltaCli" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="ClientesHttpHandler"/>
				<input type="hidden" name="services"	value="AltaClienteSrv"/>
				<input type="hidden" name="view" 		value="clientes/resultAltaCliente.jsp"/>
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>"/>
				<input type="hidden" name="idclient" 	value="<%=idclient%>"/>
				
				<br>
				<br>
			
				<table border="0" width="50%" align="center" border="1">
				
					<% if ((idclient != null) && (!idclient.equals(""))){ %>
						<tr class="fonts">
							<td align="center" class="input-b1" style="width:110px;">Cod. Cliente</td>
							<td><input style="width:50%;text-align:center" type="text" name="cdintern" value="<%=cdintern%>" size="20" class="fonts6" readonly/></td>
							<td>&nbsp;</td>
						
							<td>&nbsp;</td>
						</tr>
					<% } %>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:35%"><%=txnombre%></td>
						<td style="width:25%"><input style="width:100%" type="text" name="rzsocial" value="<%=rzsocial %>" size="20" class="input-m"/></td>
					
							
					</tr>
					<tr>
						<td align="center" class="input-b1"  style="width:35%"><%=txidfisc%></td>
						<td  style="width:25%;"><input style="width:100%"  type="text" name="idfiscal" value="<%=idfiscal %>" size="20" class="input-m"/></td>		
					</tr>
					
					<tr class="fonts">
						<td align="center" class="input-b1">Calle</td>
						<td><input style="width:100%" type="text" name="txdirecc" value="<%=txdirecc %>" size="20" class="input-m"/></td>	
					</tr>
					
					<tr class="fonts">			
						<td align="center" class="input-b1" style="width:35%">Ciudad</td>
						<td><input style="width:100%"  type="text" name="txciudad" value="<%=txciudad %>" size="20" class="input-m"/></td>		
					</tr>
					
					<tr class="fonts">			<!-- añadir a BBDD IMPORTANTE -->
						<td align="center" class="input-b1" style="width:35%">Provincia o Estado</td>
						<td><input style="width:100%"  type="text" name="txprovin" value="<%=txprovin %>" size="20" class="input-m"/></td>		
					</tr>
					
					
					
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;"><%=txcodpos%></td>
						<td><input style="width:100%"  type="text" name="cdpostal" value="<%=cdpostal %>" size="20" class="input-m"/></td>
					</tr>
					
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Pa&iacute;s</td>
						<td><input style="width:100%"  type="text" name="txpaisxx" value="<%=txpaisxx %>" size="20" class="input-m"/></td>
					</tr>
					
					
					<tr class="fonts">	
						<td align="center" class="input-b1" style="width:35%">E-Mail</td>
						<td ><input style="width:100%" type="text" name="txmailxx" value="<%=txmailxx %>" size="20" class="input-m"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Teléfono fijo</td>
						<td><input style="width:100%" type="text" name="tfnofijo" value="<%=tfnofijo %>" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
					<td class="input-b1" style="width:35%">Teléfono movil</td>
						<td ><input style="width:100%"  type="text" name="tfnomovi" value="<%=tfnomovi %>" size="20" class="input-m"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Fax: </td>
						<td style="width:25%;"><input style="width:100%"  type="text" name="tfnofaxx" value="<%=tfnofaxx %>" size="20" class="input-m"/></td>
						<td align="center">&nbsp;</td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Web: </td>
						<td  style="width:100%;"><input style="width:100%" type="text" name="txwebxxx" value="<%=txwebxxx %>"  size="20" class="input-m"/></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>		
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>						
					</tr>
				</table>
	
			
			</form>	
			<table width="50%" align="center">
				<tr>
					<td align="center">
					<%if(idclient.equals("")){ %>
						<a class="boton" style="margin-left:0%;" onClick="validarAlta()">Alta Cliente</a>
						<%}else{%>
						<a class="boton" style="margin-left:0%;" onClick="validarAlta()">Actualizar Cliente</a>
						&nbsp;&nbsp;
						<a class="boton" style="margin-left:0%;" onClick="EliminarCliente()">Eliminar Cliente</a>
						<% } %>
					</td>
				</tr>
			</table>
				
		</div>
	</div>
	<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ClientesHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value=""/>
			<input type="hidden" name="tpclient" 	value=""/>
			<input type="hidden" name="aniofact" 	value=""/>
			<input type="hidden" name="asignafa" 	value=""/>
			<input type="hidden" name="clientea" 	value=""/>	
			<input type="hidden" name="idclient" 	value=""/>	
		</form>
</body>