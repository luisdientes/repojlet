<%@ include file="/common/jsp/include.jsp"%> 
  <title>Listado empleados.</title>
 
</head>
<%

	String idusuari =	"";
	String txnombre =	"";
	String txapelli = 	"";
	String telefono =	"";
	String nifcifxx = 	"";
	String txmailxx =	"";
	String fhaltaxx = 	"";
	String idemisor = "";
	
	Grid gridEmple	= null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
		try {
			idemisor = io.getStringValue("idemisor");
			gridEmple = io.getGrid("gridEmpl");	
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo en el listado de proyectos");	
		}
	}
	%>
	
	
	<script>
		function modificar(idusuari){
			document.formDetalle.cduserid.value=idusuari;
			document.formDetalle.submit();
		}
		function enviar(urldesti){
			document.location.href=urldesti;
		}
		

		function verCalenda(idemplea){
			document.calendario.idemplea.value = idemplea;
			document.calendario.submit();
		}

	</script>
	
	<table width="98%" align="center">
	<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
		<tr>
			<td width=100% align="center"><img src="common/img/icons/title-list-usuario.png"/></td>						
		</tr>
		<tr>
			<td width=100% align="center">Listado Personal</td>						
		</tr>
	</table>
	
<div class="centrado-all">	
	<table  align="center" class="listado-tab" >
		<tr>
			<td class="input-b1">Fecha de Alta</td>
			<td class="input-b1">Nombre</td>
			<td class="input-b1">Apellidos</td>
			<td class="input-b1">Telefono</td>
			<td class="input-b1">NIF / CIF</td>
			<td class="input-b1">Mail</td>
            <td class="fonts3vacio">Modificar</td>
            <td class="fonts3vacio">Calendario</td>
		</tr>		
	<%
	
	for (int j = 0; j < gridEmple.rowCount(); j++){
		
		idusuari = gridEmple.getStringCell(j,"idtrabaj");
		txnombre = gridEmple.getStringCell(j,"txnombre");
		txapelli = gridEmple.getStringCell(j,"txapelli");
		telefono = gridEmple.getStringCell(j,"telefono");
		nifcifxx = gridEmple.getStringCell(j,"nifcifxx");
		txmailxx = gridEmple.getStringCell(j,"txmailxx");
		fhaltaxx = gridEmple.getStringCell(j,"fhaltaxx");
		
		if (j % 2 == 0) { %>
			<tr class="oddRow"  style="border:1px solid;cursor:pointer">
		<% } else { %>
			<tr class="evenRow" style="border:1px solid;cursor:pointer" >
		<% }%>
			<td align="center"><%=fhaltaxx %></td>
			<td><%=txnombre %></td>
			<td ><%=txapelli %></td>
			<td ><%=telefono %></td>
			<td ><%=nifcifxx %></td>
			<td ><%=txmailxx %></td>
			<td onclick="modificar(<%=idusuari%>)" align="center"><a class="boton">Modificar</a></td>
			<td onclick="verCalenda(<%=idusuari%>)" align="center"><img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" ></td>
		</tr>
		
	<% 
		
	}

%>
<tr><td>&nbsp;</td></tr>
</table>
</div>

 <form method="post" name="formDetalle" action="/JLet/App">
			<input type="hidden" name="cduserid" />
			<input type="hidden" name="tipoda" value="DET"/>
			<input type="hidden" name="controller" value="PersonalHttpHandler"/>
			<input type="hidden" name="services" value="ListAccesosSrv"/>
			<input type="hidden" name="view" value="personal/administracion/altaTrabajador.jsp"/>
			<input type="hidden" name="idemisor" value="<%=idemisor %>" />
 </form>
 <form method="POST" name="calendario" action="/JLet/App">
	 <input type="hidden" name="controller" value="PersonalHttpHandler"/>
	 <input type="hidden" name="services" value="InitCalendarioSrv"/>
	 <input type="hidden" name="view" value="personal/empleado/calendario.jsp"/>
	 <input type="hidden" name="idemplea" />
</form>
	


</body>
</html>