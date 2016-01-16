<%@ include file="/common/jsp/include.jsp"%> 
  <title>Listado Proyectos.</title>

</head>
<%

	
	String idproyec = "";
	String txnombre = "";
	String idempres = "";
	String tpproyec = "";
	String numperso = "";
	String txduraci = "";
	String txdescri = "";
	String fhinicio = "";
	String fhfinxxx = "";
	Grid gridProy	= null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
		try {
			gridProy = io.getGrid("gridProy");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo en el listado de proyectos");	
		}
	}
	
	
	
	%>
	
	
	<script>
		function modificar(idproyec){
			document.formDetalle.idproyec.value = idproyec;
			document.formDetalle.submit();
		}
		function enviar(urldesti){
			document.location.href = urldesti;
		}
	</script>
	
<div class="fondo2">
<div class="testata"><img src="common/img/icons/title-list-project.png"/></div>	
<div class="centrado4b"> 
<table width="100%">
	<tr>
			<td class="input-b1">Nombre</td>
			<td class="input-b1">Empresa</td>
			<td class="input-b1">Tipo</td>
			<td class="input-b1">Nro.Personas</td>
			<td class="input-b1">Fecha Inicio</td>
			<td class="input-b1">Fecha Fin</td>
			<td class="input-b1">Duraci&oacute;n</td>
            <td></td>
	</tr>		
	<%
	
	for (int j = 0; j < gridProy.rowCount(); j++){
			
			try{
			 	idproyec = gridProy.getStringCell(j,"idproyec");
			}catch(Exception ex){
				System.out.println("No tiene id proyect");
			}
			
			try{
				txnombre = gridProy.getStringCell(j,"txnombre");
			}catch(Exception ex){
				System.out.println("No tiene id proyect");
			}
			
			try{
				idempres = gridProy.getStringCell(j,"txrazons");
			}catch(Exception ex){
				System.out.println("No tiene idempresa");
			}
			
			try{		
				tpproyec = gridProy.getStringCell(j,"tpproyect");
			}catch(Exception ex){
				System.out.println("tipo proyecto es nulo");
			}
			
			try{
				numperso = gridProy.getStringCell(j,"numperso");
			}catch(Exception ex){
				System.out.println("Numero personas es nulo");
			}
			try{
				txdescri = gridProy.getStringCell(j,"txdescri");
			}catch(Exception ex){
				System.out.println("descripcion es nula");
			}
			
			try{
				txduraci = gridProy.getStringCell(j,"txduracio");
			}catch(Exception ex){
				System.out.println("duracion es nula");
			}
			try{
				fhinicio = gridProy.getStringCell(j,"fhinicio");
			}catch(Exception ex){
				System.out.println("descripcion es nula");
			}
			
			try{
				fhfinxxx = gridProy.getStringCell(j,"fhfinxxx");
			}catch(Exception ex){
				System.out.println("duracion es nula");
			}
			
	
		%>
		<tr>
			<td class="input-m"><%=txnombre %></td>
			<td class="input-m"><%=idempres %></td>
			<td class="input-m"><%=tpproyec %></td>
			<td class="input-m"><%=numperso %></td>
			<td class="input-m"><%=fhinicio %></td>
			<td class="input-m"><%=fhfinxxx %></td>
			<td class="input-m"><%=txduraci %></td>
			<td onclick="modificar(<%=idproyec%>)" style="cursor:pointer" align="center"><a class="boton marginboton">Modificar</a></td>
		</tr>
	<% 	
	}
%>
<tr><td>&nbsp;</td></tr>

</table>

 <form method="post" name="formDetalle" action="/JLet/App">
			<input type="hidden" name="idproyec" />
			<input type="hidden" name="tipoda" value="DP"/>
			<input type="hidden" name="controller" value="PersonalHttpHandler"/>
			<input type="hidden" name="services" value="ListAccesosSrv"/>
			<input type="hidden" name="view" value="personal/administracion/altaProyectos.jsp"/>
 </form>
</div>
</div>
</body>
</html>