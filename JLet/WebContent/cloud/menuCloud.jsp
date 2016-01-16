<table style="width:20%;margin-left:20px;">
	<tr height=100>
		<td onclick="inicio()" align="center" style="cursor:pointer"><img src="/JLet/cloud/img/inicio.png">
		<br>
		Inicio
		</td>
	</tr>
	<tr height=100>
		<td onclick="muestraFormCarpeta()" align="center" style="cursor:pointer"><img src="/JLet/cloud/img/crear-carpeta.png">
			<br>
			Crear carpeta
		</td>
	</tr>
	<tr height=100>
		<td onclick="abreCarpeta()" align="center" style="cursor:pointer"><img src="/JLet/cloud/img/directorio.png" width=58px;height:63px>
		<br>
		Ver directorio
		</td>
	</tr>
	
	<tr height=100>
		<td onclick="delArchivo()" align="center" style="cursor:pointer"><img src="/JLet/cloud/img/borrar.png">
			<br>
			Borrar
		</td>
	</tr>

	<tr height=100>
	    <td onclick="subeFichero()" align="center" style="cursor:pointer"><img src="/JLet/cloud/img/subir-fichero.png">
	    <br>
	    Subir fichero
	    </td>
		
	</tr>
	<tr height=100>
		<td onclick="comprimir()" align="center" style="cursor:pointer"><img src="/JLet/cloud/img/comprimir.png">
			<br>
			Comprimir
		</td>
	</tr>
	<tr height=100>
		<td onclick="versiones()" align="center" style="cursor:pointer"><img src="/JLet/cloud/img/versiones.png">
			<br>
			Versiones
		</td>
	</tr>
	<tr height=100>
	<td onclick="descargaMenu()" align="center" style="cursor:pointer"><img src="/JLet/cloud/img/descargar.png">
		<br>Descargas</td>
	</tr>
		<tr height=100>
	<td onclick="descomprime()" align="center" style="cursor:pointer"><img src="/JLet/cloud/img/descargar.png">
		<br>Descomprimir</td>
	</tr>
	

</table>



	<form method="POST" name="formInicio" action="/JLet/App">
			<input type="hidden" name="controller" 	value="CloudHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value="<%=pre_idemisor%>"/>
			<input type="hidden" name="txdirect" 	value=""/>
			<input type="hidden" name="filepath" 	value=""/>
			<input type="hidden" name="listfile" 	value=""/>
			<input type="hidden" name="tipofich" 	value=""/>
			<input type="hidden" name="txnombre" 	value=""/>
			<input type="hidden" name="idinodox" 	value=""/>
			
	</form>
	
	
	<form name="fordownload" action="/JLet/App" method="POST">
						
				<input type="hidden" name="idusuari" value="1"/>
				<input type="hidden" name="tipofile" value="CLO"/>
				<input type="hidden" name="pathfile" value=""/>
				<input type="hidden" name="filename" value=""/>
				<input type="hidden" name="directcl" value=""/>
				<input type="hidden" name="idemisor" value="<%=pre_idemisor%>"/>
	</form>



