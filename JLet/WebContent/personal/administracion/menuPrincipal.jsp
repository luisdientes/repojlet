<link rel="stylesheet" href="/JLet/common/css/Hojacss.css" type="text/css" />
<script>
	function enviar(tipodato,urldesti){
		
		document.formMenu.tipoda.value = tipodato;
		document.formMenu.view.value    = "gestion/administracion/"+urldesti;
		document.formMenu.submit();
	}
	
function gestion(tipodato,urldesti){
		
		document.formGestion.tipoOpera.value = tipodato;
		document.formGestion.view.value    = "gestion/administracion/"+urldesti;
		document.formGestion.submit();
	}
	
	
	function irDetalleMenu(urldesti){
		
		document.location.href = urldesti;
	}
</script>

<%
	String txmensaj = "";
	String tprolxxx = "";

	try {
	tprolxxx =  session.getAttribute("tprolxxx").toString();
	}catch(Exception ex){
		
	}
try{
	txmensaj = (String) request.getAttribute("txmensaj");
	if ((txmensaj == null) || ("null".equals(txmensaj))){
		txmensaj = "";
	}
} catch (Exception e){
	txmensaj = "";
}
if(tprolxxx.equalsIgnoreCase("A")){
%>
<div class="fondo2b">
<div class="centrado">    
	<table width=80% align="center" class="tab-login">
    <tr class="logo-login"><img src="/JLet/common/img/icons/logo.png" class="logo-login"/>
    </tr>
		<tr class="usuario">
		  <td class="icon-tab"> <img src="/JLet/common/img/icons/account.png"> </td>
			<td class="tab-cal"><input type="button" value="Alta Trabajador" class="tab-cal" onclick="enviar('IE','altaTrabajador.jsp')"></td>
		</tr>
		<tr class="usuario">
		  <td class="icon-tab"> <img src="/JLet/common/img/icons/icon-folder.png"> </td>
			<td class="tab-cal"><input type="button" value="Alta Proyectos" class="tab-cal" onclick="enviar('AP','altaProyectos.jsp')"></td>
		</tr>
		 <tr class="usuario">
		  <td class="icon-tab"> <img src="/JLet/common/img/icons/icon-empreza.png"> </td>
			<td class="tab-cal"><input type="button" value="Alta Empresa" class="tab-cal" onclick="irDetalleMenu('/JLet/gestion/administracion/altaEmpresa.jsp')"></td>
		</tr>
		<tr class="usuario">
		  <td class="icon-tab"> <img src="/JLet/common/img/icons/icon-folder.png"> </td>
			<td class="tab-cal"><input type="button" value="Facturas" class="tab-cal" onclick="irDetalleMenu('/JLet/factura/menuEmisores.jsp')"></td>
		</tr>
		<tr>
       	<tr class="usuario">
		  <td class="icon-tab"> <img src="/JLet/common/img/icons/icon-list-trab.png"> </td>
			<td class="tab-cal"><input type="button" value="Listado Usuarios" class="tab-cal" onclick="enviar('LE','listadoEmpleados.jsp')"></td>
		</tr>
		 	<tr class="usuario">
		  <td class="icon-tab"> <img src="/JLet/common/img/icons/icon-list-folder.png"> </td>
			<td class="tab-cal"><input type="button" value="Listado de Proyectos" class="tab-cal" onclick="enviar('LP','listadoProyectos.jsp')"></td>
		</tr>
			<tr class="usuario">
		  <td class="icon-tab"> <img src="/JLet/common/img/icons/icon-list-empreza.png"> </td>
			<td class="tab-cal"><input type="button" value="Listado empresas" class="tab-cal" onclick="enviar('LEM','listadoEmpresas.jsp')"></td>
		</tr>
		
		
			<tr>
			<td>&nbsp; </td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td colspan=2 class="fonts"><%=txmensaj %></td>
		</tr>
	</table>

		<form method="post" name="formMenu" action="/JLet/App">
			<input type="hidden" name="tipoda" value=""/>
			<input type="hidden" name="controller" value="AccesoHttpHandler"/>
			<input type="hidden" name="services" value="ListAccesosSrv"/>
			<input type="hidden" name="view" value=""/>
		</form>
		
		
		<form method="post" name="formGestion" action="/JLet/App">
			<input type="hidden" name="tipoOpera" value=""/>
			<input type="hidden" name="controller" value="AccesoHttpHandler"/>
			<input type="hidden" name="services" value="GestTrabajaSrv"/>
			<input type="hidden" name="view" value=""/>
		</form>
</div>
</div>
<% }

%>