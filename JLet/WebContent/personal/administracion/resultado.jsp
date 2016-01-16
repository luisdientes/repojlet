<%@ include file="/common/jsp/include.jsp"%>
<html>
<head>
	<title>Resultado Operaci&oacute;n</title>
</head>
<body class="fondo">

	<script>
		function enviar(urldesti){
			document.location.href=urldesti;
		}
	</script>


   <%
   	String resultado = "";
    String resopera = "";
    String tximagen = "";
    
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			resultado = io.getStringValue("txmensaj");
			resopera = io.getStringValue("resopera");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en resultado.jsp");	
		}
	}
   
	if(resopera.equals("OK")){
		tximagen = "title-ok.png";
	}
	else{
		tximagen = "title-error.png";
	}
   
%>
<div class="fondo1">
<div class="testata"><img src="/JLet/common/img/icons/<%=tximagen%>"/></div>
<h2 class="txt1"><%=resultado %></h2>
<br><br>
	<div>
	  		<table align="center" width=25%>
				<tr><td ><input type="button" class="button-trab" onclick="enviar('/JLet/common/jsp/menuPrinc.jsp')" value="Aceptar"></td></tr>
			</table>
		</div>
</div>		
</body>
</html>

