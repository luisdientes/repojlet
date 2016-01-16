
<link rel="stylesheet" href="/JLet/common/css/Hojacss.css" type="text/css" />
<script>
	function enviar(){
		document.formLogin.submit();
	}
</script>
<!-- COmentario prueba git BORRAR -->
<div class="fondo2">
<div class="centrado">
 <form method="post" name="formLogin" action="/JLet/App">
 	
    
	<table width=60% align="center" class="tab-login">
    <tr class="logo-login"><img src="/JLet/common/img/icons/logo.png" class="logo-login"/>
    </tr>
		<tr>
			<td>¡ Bienvenido a JLet!</td>
		</tr>
        <td class="bg-login"><img src="/JLet/common/img/icons/login.png"/></td>
			<td colspan=2 align="center"><input type="button" onclick="enviar()" value="Login" class="button-login" ></td>
		</tr>
			<tr>
			<td>&nbsp; </td>
			<td>&nbsp;</td>
		</tr>
				
	</table>
</form>
</div>
</div>