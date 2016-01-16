<%@ include file="/common/jsp/include.jsp"%>


<body class="fondo">

   <%
   	String filecrea = "";
   	String txmensaj = "";
   	String idemisor = "";
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			filecrea 	= io.getStringValue("filecrea");
			idemisor	= io.getStringValue("idemisor");
			txmensaj	= io.getStringValue("txmensaj");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx factura/index.jsp");	
		}
	}
   
%>

<script>
	function menuprinc(){
		document.location.href="/JLet/common/jsp/menuPrinc.jsp"
	}
	function abrirFactura(){
		 document.abriFactu.submit();
	}
	window.onload = abrirFactura;
</script>


	
	

<div class="fondo2">
<%
	if(txmensaj.equals("ERROR")){%>
		<h2 class="txt1" style="color:#363636"><%=txmensaj %> La factura ya esta creada</h2>
		<br>
		<br>

		
	  		<table align="center" class="tab-listado" width="100%">
		  		<tr>
					<td align="center">
						<a class="boton" href="/JLet/common/jsp/menuPrinc.jsp">Aceptar</a>
					</td>
				</tr>
			</table>
			

	
		
	<% }else{%>
		<h2 class="txt1" style="color:#363636"><%=txmensaj %> Nombre Factura: <%=filecrea%></h2>
<br><br>

		
	  		<table align="center" class="tab-listado" width="100%">
			
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
			<script>
			
			
			</script>
		
	<%}%>

			

		<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
			
			<input type="hidden" name="tipofile" value="FRA"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value="<%=filecrea%>"/><!-- Cambiar -->
		</form>
		
		
		
	</div>	
</body>
</html>

