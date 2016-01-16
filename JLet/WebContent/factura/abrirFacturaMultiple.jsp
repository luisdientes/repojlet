<%@ include file="/common/jsp/include.jsp"%>

<body class="fondo">

   <%
   	String filecrea = "";
   	String txmensaj = "";
   	String idemisor = "";
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor	= io.getStringValue("idemisor");
			filecrea 	= io.getStringValue("filecrea");
			txmensaj	= io.getStringValue("txmensaj");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx factura/index.jsp");	
		}
	}
   
    String[] ficheros = filecrea.split("#");
   
%>

<script>

	function abrirFichero(filecrea) {
		
		document.abriFactu.filename.value = filecrea;
		document.abriFactu.submit();
		
	}

</script>

<h2 class="txt1"><%=txmensaj %> Nombre Factura: <%=filecrea%></h2>
<br>
<br>
	<div>
  		<table align="center">
  		
  			<% for (int i = 0; i < ficheros.length; i++){ %>
		  		<tr>
					<td align="center">
						<a class="boton" onclick="abrirFichero('<%=ficheros[i]%>')">Reabrir PDF <%=ficheros[i]%></a>
					</td>
				</tr>
			<% } %>
			<tr>
				<td align="center">&nbsp;</td>
			</tr>
	  		<tr>
				<td align="center">
					<a class="boton" href="/JLet/common/jsp/menuPrinc.jsp">Aceptar</a>
				</td>
			</tr>
		</table>
			
		<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="tipofile" value="FRA"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value="<%=filecrea%>"/><!-- Cambiar -->
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
		</form>
		
	</div>	
</body>
</html>

