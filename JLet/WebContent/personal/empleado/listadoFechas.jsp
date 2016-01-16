<%@ include file="/common/jsp/include.jsp"%>
  <title>Listado Empresas.</title>

</head>
<%

	String fechatra =	"";
	String horasinv =	"";
	String txnombre = 	"";
	String txwebxxx =	"";
	String txmailxx = 	"";
	String txcontac =	"";
	String idempres = 	"";
	String fhaltaxx = "";
	
	
	Grid gFechasx	= null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
		try {
			gFechasx = io.getGrid("gFechasx");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo en el listado de empresas");	
		}
	}
	%>
	
	
	<script>
		function modificar(idempr){
			document.formDetalle.idempres.value=idempr;
			document.formDetalle.submit();
		}
		function enviar(urldesti){
			document.location.href=urldesti;
		}
	</script>
	
<div class="fondo1">
<div class="testata"><img src="/JLet/common/img/icons/title-list-empreza.png"/></div>	
<div class="centrado3"> 
<table width="70%" align="center">
	<tr class="fonts3">
			<td>Fecha</td>
			<td>Horas</td>
			<td>Proyecto</td>           
	</tr>		
	<%
	
	for(int i=0;i<gFechasx.rowCount();i++){
		
		 fechatra = gFechasx.getStringCell(i, "fechatra");
		 horasinv = gFechasx.getStringCell(i, "horasinv");
		 txnombre = gFechasx.getStringCell(i, "txnombre");



		%>
		<% 
 	 	if (i % 2 == 0) { %>
 				<tr class="oddRow" style="border:1px solid">
		<% } else { %>
			<tr class="evenRow" style="border:1px solid">
		<% } %>
			<td align="center"><%=fechatra %></td>
			<td align="center"><%=horasinv %></td>
			<td><%=txnombre %></td>
		</tr>
		
	<% 
		
	}

%>
<tr><td>&nbsp;</td></tr>
<tr>
</tr>

</table>

 <form method="post" name="formDetalle" action="/JLet/App">
			<input type="hidden" name="idempres" />
			<input type="hidden" name="tipoda" value="DEM"/>
			<input type="hidden" name="controller" value="AccesoHttpHandler"/>
			<input type="hidden" name="services" value="ListAccesosSrv"/>
			<input type="hidden" name="view" value="gestion/administracion/altaEmpresa.jsp"/>
 </form>
</div>
</div>
</body>
</html>