<link rel="stylesheet" href="/JLet/common/css/Hojacss.css" type="text/css" />

<script>
	function llamaCalendario(){
		document.formMenu.submit();
	}
</script>

<div class="fondo2b">
<div class="centrado"> 



<form method="POST" name="formMenu" action="/JLet/App">
	<input type="hidden" name="controller" value="PersonalHttpHandler"/>
	<input type="hidden" name="services" value="InitCalendarioSrv"/>
	<input type="hidden" name="view" value="personal/empleado/calendario.jsp"/>
	
<table width=80% align="center" class="tab-login">
    <tr class="logo-login"><img src="/JLet/common/img/icons/logo.png" class="logo-login"/>
    <tr class="usuario">
      <td class="icon-tab"> <img src="/JLet/common/img/icons/fechas.png"> </td>
			<td class="tab-cal"><input type="button" value="Calendario" class="tab-cal" onclick="llamaCalendario()"></td>
		</tr>
		
	</table>
</form>
