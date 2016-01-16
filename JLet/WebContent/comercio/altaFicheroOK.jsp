<%@ include file="/common/jsp/include.jsp"%>

<!DOCTYPE html>
<html lang="es">
	<%

		String filename = (String)request.getAttribute("filename");
	
	%>
    <head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script>
        	function procesarFichero(){
        		document.formPrinc.submit();
        	}
        </script>
    </head>
    <body>
	    <div class="fondo2">
			<div class="centrado" style="width:70%">
				<form name="formPrinc" method="POST" action="/JLet/App">
					<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
					<input type="hidden" name="services"	value="AltaProductosSrv"/>
					<input type="hidden" name="view" 		value="comercio/altaDatosEnvio.jsp"/>
					<input type="hidden" name="filename" 	value="<%=filename%>"/>
		        	
					<p>Se ha cargado el fichero correctamente <%=filename%></p>
					<p>&nbsp;</p>
					<p><a class="boton" onclick="procesarFichero();">Aceptar</a></p>
		        </form>
		   </div>
		</div>
    </body>
</html>
