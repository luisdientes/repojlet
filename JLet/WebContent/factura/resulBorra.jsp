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
			txmensaj	= io.getStringValue("txmensaj");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores stock/resultado");	
		}
	}
   
%>

	
	

<div class="fondo2">
<h2 class="txt1" style="color:#363636"> <%=txmensaj%></h2>
<br><br>

		
	  		<table align="center" class="tab-listado" width="100%">

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
</body>
</html>

