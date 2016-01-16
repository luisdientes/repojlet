
<link rel="stylesheet" href="/JLet/common/css/Hojacss.css" type="text/css" />
<script>
	function enviar(){
		document.formLogin.submit();
	}
</script>

<%
	try {
		HttpSession sesion = request.getSession();
		sesion.invalidate();
		System.out.println("... SESIÓN INVALIDADA ...");
		
	} catch (Exception e) {
		System.out.println("HA HABIDO UN ERROR - Invalidando la sesión");
	}
%>

<script>

	function redirigirLogout(){
		top.location.href = "/JLet/acceso/login.jsp";
	}

</script>


<body onload="setTimeout('redirigirLogout();', 3000);"/>
	<div class="fondo2">
		<div class="centrado">
			<table width=60% align="center" class="tab-login">    
				<tr>
					<td >Eliminando datos de la sesion.</td>
				</tr>				
			</table>
		</div>
	</div>
</body>