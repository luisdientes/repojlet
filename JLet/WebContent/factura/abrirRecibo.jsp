<%@ include file="/common/jsp/include.jsp"%>

<body class="fondo" onload="document.abriFactu.submit()">

   <%
   	String filecrea = "";
   	String txmensaj = "";
   	
   if (request.getAttribute("filecrea") != null ) {
	  
		try {
			
			filecrea 	= (String)request.getAttribute("filecrea");
			txmensaj	= (String)request.getAttribute("txmensaj");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx factura/index.jsp");	
		}
	}
   
%>

	

<div class="fondo2">
<h2 class="txt1"><%=txmensaj %> Nombre Factura: <%=filecrea%></h2>
<br><br>
	<div>
		
	  		<table align="center">
			
		  		<tr>
					<td align="center">
						<a class="boton" onclick="document.abriFactu.submit()">Reabrir PDF</a>
					</td>
				</tr>
				<tr>
					<td align="center">&nbsp;</td>
				</tr>
		  		<tr>
					<td align="center">
						<a class="boton" href="/JLet/common/jsp/menuPrinc.jsp">Aceptar</a>
					</td>
				</tr>
			</table>
			
			
		</div>

		<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="tipofile" value="FRA"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value="<%=filecrea%>"/><!-- Cambiar -->
		</form>
		
		
		
	</div>	
</body>
</html>

