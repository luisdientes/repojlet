<%@ include file="/common/jsp/include.jsp"%>

<%
	Grid gdUsuari = null;
	Grid gdRolxxx = null;

	String txmensaj = "";
	String tpclient = "";
	String idemisor = "";
	
	
	String idusuari = "";
	String username = "";
	String cdrolxxx = "";
	String txmailxx = "";
	String telefono = "";
	String fhaltaxx = "";
	String txrolxxx = "";
	
	if (request.getAttribute("io") != null ) {
	ObjectIO io = (ObjectIO)request.getAttribute("io");
	try {
		gdUsuari = io.getGrid("gdUsuari");	
		gdRolxxx = io.getGrid("gdRolxxx");
		txmensaj = io.getStringValue("txmensaj");	
		txrolxxx = io.getStringValue("cdrolxxx");
		
	} catch (Exception e) {
		txmensaj = "";
		System.err.println("ERROR - Recibiendo en listado Usuarios "+ e.getMessage());	
	}
	
	
	idusuari = gdUsuari.getStringCell(0,"idusuari");
	username = gdUsuari.getStringCell(0,"username"); 
  	
  	txmailxx = gdUsuari.getStringCell(0,"txmailxx");
  	telefono = gdUsuari.getStringCell(0,"telefono");
  	fhaltaxx = gdUsuari.getStringCell(0,"fhaltaxx");
    cdrolxxx = gdUsuari.getStringCell(0,"cdrolxxx");
  	
}
%>

<script>
	function verPermisos(tipoperm){
		
		if(tipoperm =="EMISOR"){
			document.formPermisos.view.value ="usuarios/listEmisoresRol.jsp";
		}else{
			document.formPermisos.view.value ="usuarios/listPermisosRol.jsp";
		}
		
		document.formPermisos.cdrolxxx.value = document.getElementById("cdrolxxx").value;
		document.formPermisos.tipoperm.value = tipoperm;
		
		document.formPermisos.submit();
		
	}
	function enviaForm(){
		
		document.formUpdate.tpaccion.value="UPD";
		document.formUpdate.cdrolxxx.value = document.getElementById("cdrolxxx").value;
		document.formUpdate.submit();
	}
	
	function cancelar(){
		document.formUpdate.tpaccion.value = "";
		document.formUpdate.idusuari.value = "";
		document.formUpdate.view.value = "usuarios/listUsuarios.jsp";
		
		document.formUpdate.submit();
		
	}
	
</script>

<body>
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	
	<div class="fondo2">
		<div class=nompanta >Listado Usuarios</div>	
		<div class="centrado" style="width:95%">
			<table align="center" width=60% class="tdRound">
				<tr class="oddRow">
					<td><b>ID Usuario: </b></td>
					<td><%=idusuari %></td>
				</tr>
				<tr class="evenRow">
					<td><b>Usuario:</b></td>
					<td ><%=username %></td></tr>
				<tr class="oddRow">
					<td><b>Rol :</b></td>
					<td><%=cdrolxxx %></td>
				</tr>
				<tr class="evenRow">
					<td><b>Email :</b></td>
					<td><%=txmailxx%></td>
				</tr>
				
				<tr class="oddRow">
					<td><b>Telefono:</b></td>
					<td><%=telefono %></td>
				</tr>
				<tr class="evenRow">
					<td><b>Fecha de alta:</b></td>
					<td><%=fhaltaxx %></td>
				</tr>
				
				<tr class="evenRow">
					<td><b>Seleccionar Rol:</b></td>
					<td>
							<select name="cdrolxxx" id="cdrolxxx">
							<% for (int i=0; i<gdRolxxx.rowCount();i++) {
								txrolxxx = gdRolxxx.getStringCell(i, "cdrolxxx");
							%>
								<option value="<%=txrolxxx%>" <% if(cdrolxxx.equals(txrolxxx)){%>selected<%} %>><%=txrolxxx%></option>
							<%} %>
							</select>
							&nbsp;
							<span onclick="verPermisos('PANTALLA')">Ver Permisos pantalla</span>&nbsp;
							<span onclick="verPermisos('EMISOR')">Ver Permisos Empresas</span>
					</td>
				</tr>
				
				<tr>
					<td><%=txmensaj %></td>
				</tr>
				
				<tr>
					<td align="center" colspan="2"><a class="boton" onclick="enviaForm()">Aceptar</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="boton" onclick="cancelar()">Cancelar</a></td>
				</tr>
				
			</table>
		</div>
	</div>
	
			<form method="POST" name="formPermisos" action="/JLet/App">
				<input type="hidden" name="controller" 	value="UsuariosHttpHandler"/>
				<input type="hidden" name="services" 	value="ListPermisosSrv"/>
				<input type="hidden" name="view" 		value="usuarios/listPermisosRol.jsp"/>
				<input type="hidden" name="idemisor" value="<%=idemisor%>">
				<input type="hidden" name="idusuari" value="<%=idusuari%>">
				<input type="hidden" name="tipoperm">
				
				<input type="hidden" name="cdrolxxx">
			</form>
			<form method="POST" name="formUpdate" action="/JLet/App">
				<input type="hidden" name="controller" 	value="UsuariosHttpHandler"/>
				<input type="hidden" name="services" 	value="ListUsuariosSrv"/>
				<input type="hidden" name="view" 		value="usuarios/detalleUsuario.jsp"/>
				<input type="hidden" name="idemisor" value="<%=idemisor%>">
				<input type="hidden" name="idusuari" value="<%=idusuari%>">
				<input type="hidden" name="tipoperm">
				<input type="hidden" name="cdrolxxx">
				<input type="hidden" name="tpaccion">
			</form>
	
	
</body>