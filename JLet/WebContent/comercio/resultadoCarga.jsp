<%@ include file="/common/jsp/include.jsp"%>
<html>

>

<head>
	<title>Resultado carga Productos</title>
</head>
<body class="fondo2">

   <%
   	String resultado = "";
    String numcarga = "";

   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			resultado 	= io.getStringValue("txmensaj");
			numcarga 	= io.getStringValue("numcarga");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/resultadoCarga.jsp");	
		}
	}
   
%>

<script>
	function generaFactu(){
		document.genFactu.submit();
		
	}
</script>

<div class="fondo2">
<h2 class="txt1"><%=resultado %> &nbsp; <%=numcarga%></h2>
<br><br>

<table width=90% align="center">
				<tr>
					<td align="center">
						<a class="boton" href="/JLet/common/jsp/menuPrinc.jsp">Aceptar</a>
					</td>
				</tr>
				
			</table>

		
	</div>	
</body>
</html>

