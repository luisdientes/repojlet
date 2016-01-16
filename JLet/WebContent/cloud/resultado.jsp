<%@ include file="/common/jsp/include.jsp"%>
	
<html>

<link rel="stylesheet" href="/JLet/common/css/Hojacss.css" type="text/css" />

<head>
	<title>Validar facturas</title>
</head>
<body class="fondo">

   <%

   	String idemisor  = "";
   	String filepath  = ""; 

		try {
			filepath = (String) session.getAttribute("filepath");
			idemisor = (String) session.getAttribute("idemisor");
			
			System.out.println("Entraaa ----- request" + (String)request.getParameter("filepath"));
		
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx cloud/resultado.jsp");	
		}
   
%>

<script>
	function generaFactu(){
		document.genFactu.submit();
		
	}
</script>

<div class="fondo1">
<br><br>
	<div>
			<br/>
			<br/>
			<br/>
			<br/>
			
		</div>
		
		
		<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="CloudHttpHandler"/>
			<input type="hidden" name="services" 	value="InitCloudSrv"/>
			<input type="hidden" name="view" 		value="cloud/principalCloud.jsp"/>
			<input type="hidden" name="idemisor" 	value="<%=idemisor%>" />
			<input type="hidden" name="filepath" 	value="<%=filepath%>"/>
		</form>
	</div>	
	
	<script>
		document.formMenu.submit();
	</script>
</body>
</html>

