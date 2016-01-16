<%@ include file="/common/jsp/include.jsp"%>
  <title>Listado Empresas.</title>

</head>
<%

	String txrazons =	"";
	String txdirecc =	"";
	String nifcifxx = 	"";
	String txwebxxx =	"";
	String txmailxx = 	"";
	String txcontac =	"";
	String idempres = 	"";
	String fhaltaxx = "";
	
	
	Grid gridEmpre	= null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
		try {
			gridEmpre = io.getGrid("gridEmpr");
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
	
<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/title-list-empreza.png"/></div>	
<div class="centrado4b"> 
<table width="100%">
	<tr >
			<td class="input-b1">Fecha de Alta</td>
			<td class="input-b1">Raz&oacute;n Social</td>
			<td class="input-b1">CIF</td>
			<td class="input-b1">E-MAIL</td>
            <td ></td>
	</tr>		
	<%
	
	for(int i=0;i<gridEmpre.rowCount();i++){
		
		 idempres = gridEmpre.getStringCell(i, "idempres");
		 txrazons = gridEmpre.getStringCell(i, "txrazons");
		 txdirecc = gridEmpre.getStringCell(i, "txdirecc");
		 nifcifxx = gridEmpre.getStringCell(i, "nifcifxx");
		 txwebxxx = gridEmpre.getStringCell(i, "txwebxxx");
		 fhaltaxx = gridEmpre.getStringCell(i, "fhaltaxx");
		 txmailxx = gridEmpre.getStringCell(i, "txmailxx");
		 txcontac = gridEmpre.getStringCell(i, "txcontac");


		%>
		<tr>
			<td class="fonts6"><%=fhaltaxx %></td>
			<td class="fonts6"><%=txrazons %></td>
			<td class="fonts6"><%=nifcifxx %></td>
			<td class="fonts6"><%=txmailxx %></td>
			<td onclick="modificar(<%=idempres%>)" align="center"><a class="boton marginboton">Modificar</a></td>
		</tr>
		
	<% 
		
	}

%>
<tr><td>&nbsp;</td></tr>


</table>

 <form method="post" name="formDetalle" action="/JLet/App">
			<input type="hidden" name="idempres" />
			<input type="hidden" name="tipoda" value="DEM"/>
			<input type="hidden" name="controller" value="PersonalHttpHandler"/>
			<input type="hidden" name="services" value="ListAccesosSrv"/>
			<input type="hidden" name="view" value="personal/administracion/altaEmpresa.jsp"/>
 </form>
</div>
</div>
</body>
</html>