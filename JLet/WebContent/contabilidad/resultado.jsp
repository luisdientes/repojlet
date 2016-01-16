<%@ include file="/common/jsp/include.jsp"%>

<body class="fondo">

   <%
   	String filecrea = "";
   	String txmensaj = "";
   	String idemisor = "";
	String idcuenta = "";
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor	= io.getStringValue("idemisor");
			idcuenta	= io.getStringValue("idcuenta");
			txmensaj	= io.getStringValue("txmensaj");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores stock/resultado");	
		}
	}
   
%>

<script>
	function verApuntes(){
		document.formAlta.submit();
	}
</script>

	
	

<div class="fondo2">
<h2 class="txt1" style="color:#363636"> <%=txmensaj%></h2>
<br><br>

		
	  		<table align="center" class="tab-listado" width="100%">

				<tr>
					<td align="center">&nbsp;</td>
				</tr>
		  		<tr>
					<td align="center">
						<a class="boton" onclick="verApuntes()">Aceptar</a>
					</td>
				</tr>
			</table>
	</div>	
	
	<form method="POST" name="formAlta" action="/JLet/App">
		<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
		<input type="hidden" name="services" 	value="ListApuntesSrv"/>
		<input type="hidden" name="view" 		value="contabilidad/listApuntes.jsp"/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>">
		<input type="hidden" name="idcuenta" value="<%=idcuenta%>">
	</form>
</body>
</html>

