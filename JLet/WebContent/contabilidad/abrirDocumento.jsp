<%@ include file="/common/jsp/include.jsp"%>


<body class="fondo">

   <%
   	String filecrea = "";
   	String txmensaj = "";
   	String idemisor = "";
   	String tipofile = "";
   	String directcl = "";
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			filecrea 	= io.getStringValue("filecrea");
			idemisor	= io.getStringValue("idemisor");
			tipofile 	= io.getStringValue("tipofile");
			directcl 	= io.getStringValue("directcl");
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
<br><br>

		
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

			<input type="hidden" name="idusuari" value="1"/>
				<input type="hidden" name="tipofile" value="CLO"/>
				<input type="hidden" name="pathfile" value=""/>
				<input type="hidden" name="filename" value=""/>
				<input type="hidden" name="directcl" value=""/>
				<input type="hidden" name="idemisor" value="<%=pre_idemisor%>"/>

		<form method="post" name="abriFactu" action="/JLet/JLetDownload">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
			<input type="hidden" name="directcl" value="<%=directcl%>"/>
			<input type="hidden" name="tipofile" value="<%=tipofile%>"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value="<%=filecrea%>"/><!-- Cambiar -->
		</form>
		
		
		
	</div>	
</body>
</html>

