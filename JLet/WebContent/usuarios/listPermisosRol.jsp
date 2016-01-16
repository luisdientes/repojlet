<%@ include file="/common/jsp/include.jsp"%>

<%
	Grid gdPermis = null;
	Grid gdPantal = null;

	String txmensaj = "";
	String idemisor = "";
	String idusuari = "";
	String username = "";
	String cdrolxxx = "";
	String txrolxxx = "";
	
	String cdpantal = "";
	String txnombre = "";
	String txpadrex = "";
	String tipoperm = "";
	
	
	if (request.getAttribute("io") != null ) {
	ObjectIO io = (ObjectIO)request.getAttribute("io");
	try {
		idusuari = io.getStringValue("idusuari");
		cdrolxxx = io.getStringValue("cdrolxxx");
		tipoperm = io.getStringValue("tipoperm");
		gdPermis = io.getGrid("gdPermis");	
		gdPantal = io.getGrid("gdPantal");
		//txmensaj = io.getStringValue("txmensaj");		
	} catch (Exception e) {
		System.err.println("ERROR - Recibiendo en listado Usuarios "+ e.getMessage());	
	}
}
%>

<script>

var listPermi = "";

function leerTodosCheck(){
    checkboxes = document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
    for(i=0; i<checkboxes.length; i++) //recoremos todos los controles
    {
        if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
        {
            if(checkboxes[i].checked){
            	listPermi += checkboxes[i].value+";";
            }; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
        }
    }
}

function actualizaPer(){
	
	leerTodosCheck();
	document.formDetalle.services.value ="ActualizaPermisosSrv";
	document.formDetalle.listperm.value = listPermi;
	document.formDetalle.submit();
	
}
</script>

<body>
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	
	<div class="fondo2">
		<div class=nompanta >Listado Permisos Rol <%=cdrolxxx %></div>	
		<div class="centrado" style="width:85%">
			<table align="center" class="reportTable" width="50%" border="0" >
				<tr>
					<td><div>&nbsp; </div></td>
					<td><div class="input-b1">Nombre </div></td>
					<td><div class="input-b1"> Pantalla</div></td>
					<td><div class="input-b1"> Dependencia</div></td>
				</tr>
				<% for (int i = 0; i < gdPantal.rowCount(); i++){
					
					txnombre = gdPantal.getStringCell(i,"txnombre");
					cdpantal = gdPantal.getStringCell(i,"cdpantal"); 
					txpadrex = gdPantal.getStringCell(i,"cdpadrex");
					txrolxxx  = gdPantal.getStringCell(i,"cdrolxxx");
					
					int tieneper = 0;
					
					 for (int x = 0; x < gdPermis.rowCount(); x++){
					 
					 	if(cdpantal.equals(gdPermis.getStringCell(x,"cdpantal"))){
					 		tieneper = 1;
					 	}
					
					}
				
					if (i % 2 == 0) { %>
				  		<tr class="oddRow" style="border:1px solid;cursor:pointer">
					<% } else { %>
						<tr class="evenRow" style="border:1px solid;cursor:pointer">
					<% }%>
					<% if (tieneper == 1) { %>
							<td class="fonts6jej" style="font-size:12px"> <input type="checkbox" value="<%=cdpantal%>" checked></td>
						<% } else { %>
							<td class="fonts6jej" style="font-size:12px"> <input type="checkbox" value="<%=cdpantal%>"> </td>
						<% }%>
					
						<td class="fonts6jej" style="font-size:12px" align="left"> <%=txnombre %></td>
						<td class="fonts6jej" style="font-size:12px"> <%=cdpantal %></td>
						<td class="fonts6jej" style="font-size:12px"> <%=txpadrex %></td>
						<% if (tieneper == 1) { %>
							<td class="fonts6jej" style="font-size:12px"><img style="width:22px" src="/JLet/common/img/icons/check.png"></td>
						<% } else { %>
							<td class="fonts6jej" style="font-size:12px"><img style="width:22px" src="/JLet/common/img/icons/cancel.png"></td>
						<% }%>
					</tr>
					
					<%}%>
					
					<tr>
						<td colspan=5>&nbsp;</td>
					</tr>
					
					<tr>
						<td colspan=5 align="center"><a class="boton" onclick="actualizaPer()">Actualizar</a></td>
					</tr>
				
			</table>
			<form method="POST" name="formDetalle" action="/JLet/App">
				<input type="hidden" name="controller" 	value="UsuariosHttpHandler"/>
				<input type="hidden" name="services" 	value="ListUsuariosSrv"/>
				<input type="hidden" name="view" 		value="usuarios/detalleUsuario.jsp"/>
				<input type="hidden" name="idemisor" value="<%=idemisor%>">
				<input type="hidden" name="idusuari" value="<%=idusuari%>">
				<input type="hidden" name="cdrolxxx" value="<%=cdrolxxx%>" >
				<input type="hidden" name="tipoperm" value="PANTALLA" >
				
				<input type="hidden" name="listperm" >
				
			</form>
		</div>
	</div>
	
	
</body>