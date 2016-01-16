<%@ include file="/common/jsp/include.jsp"%>

<head>
   <%
   	String txresult = "";
    String tximagen = "";
	String idemisor = "";
    String tpclient = "";
    
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			txresult = io.getStringValue("txmensaj");
			idemisor = io.getStringValue("idemisor");
			tpclient = io.getStringValue("tpclient");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en resultado.jsp");	
		}
	}
   
   tximagen = "ok3.png";

   %>


	<script>
	
		function enviar(urldesti){
			document.location.href=urldesti;
		}
		
		function listClientes(){
			document.formMenu.services.value = "ListClientesSrv";
			document.formMenu.view.value	 = "clientes/listClientes.jsp";		
			document.formMenu.idemisor.value = "<%=idemisor%>";
			document.formMenu.tpclient.value = "<%=tpclient%>";
			document.formMenu.submit();
			
		}
		
	</script>
</head>

<body>
	<div class="fondo2">
		<div class="testata">
			<img src="/JLet/common/img/icons/<%=tximagen%>"/>
		</div>
		<br>
		<h2 class="nompanta" style="margin-top:35px !important"><%=txresult %></h2>
		<br>
		<br> 	
		<br><a class="boton" onclick="listClientes()">Volver</a>
	</div>
	
	<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ClientesHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value=""/>
			<input type="hidden" name="tpclient" 	value=""/>
			<input type="hidden" name="aniofact" 	value=""/>
		</form>
</body>
