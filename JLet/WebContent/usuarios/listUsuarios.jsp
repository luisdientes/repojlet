<%@ include file="/common/jsp/include.jsp"%>

<%
	Grid gdUsuari = null;

	String txmensaj = "";
	String tpclient = "";
	String idemisor = "";
	
	
	String idusuari = "";
	String username = "";
	String cdrolxxx = "";
	String txmailxx = "";
	String fhaltaxx = "";
	
	if (request.getAttribute("io") != null ) {
	ObjectIO io = (ObjectIO)request.getAttribute("io");
	try {
		gdUsuari = io.getGrid("gdUsuari");	
		txmensaj = io.getStringValue("txmensaj");		
	} catch (Exception e) {
		System.err.println("ERROR - Recibiendo en listado Usuarios "+ e.getMessage());	
	}
}
%>

<script>

	function verDetalle(idusuario){
		document.formDetalle.idusuari.value = idusuario;
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
		<div class=nompanta >Listado Usuarios</div>	
		<div class="centrado" style="width:95%">
			<table align="center" class="reportTable" width="100%" border="0" >
				<tr>
					<td><div class="input-b1"> ID. </div></td>
					<td><div class="input-b1"> Usuario</div></td>
					<td><div class="input-b1"> Rol </div></td>
					<td><div class="input-b1"> F. Alta </div></td>
				</tr>
				<% for (int i = 0; i < gdUsuari.rowCount(); i++){
					
					idusuari = gdUsuari.getStringCell(i,"idusuari");
					username = gdUsuari.getStringCell(i,"username"); 
				  	cdrolxxx = gdUsuari.getStringCell(i,"cdrolxxx");
				  	txmailxx = gdUsuari.getStringCell(i,"txmailxx");
				  	fhaltaxx = gdUsuari.getStringCell(i,"fhaltaxx");
				
					if (i % 2 == 0) { %>
				  		<tr class="oddRow" style="border:1px solid;cursor:pointer" onclick="verDetalle('<%=idusuari %>')">
					<% } else { %>
						<tr class="evenRow" style="border:1px solid;cursor:pointer" onclick="verDetalle('<%=idusuari %>')">
					<% }%>
					
						<td class="fonts6jej" style="font-size:12px" align="center"> <%=idusuari %></td>
						<td class="fonts6jej" style="font-size:12px"> <%=username %></td>
						<td class="fonts6jej" style="font-size:12px"> <%=cdrolxxx %></td>
						<td class="fonts6jej" style="font-size:12px" align="center"> <%=fhaltaxx %></td>
					</tr>
					
					<%}%>
				
			</table>
			<form method="POST" name="formDetalle" action="/JLet/App">
				<input type="hidden" name="controller" 	value="UsuariosHttpHandler"/>
				<input type="hidden" name="services" 	value="ListUsuariosSrv"/>
				<input type="hidden" name="view" 		value="usuarios/detalleUsuario.jsp"/>
				<input type="hidden" name="idemisor" value="<%=idemisor%>">
				<input type="hidden" name="idusuari">
				<input type="hidden" name="cdrolxxx">
			</form>
		</div>
	</div>
	
	
</body>