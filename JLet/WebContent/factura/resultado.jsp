<%@ include file="/common/jsp/include.jsp"%>
	
<html>

<link rel="stylesheet" href="/JLet/common/css/Hojacss.css" type="text/css" />

<head>
	<title>Validar facturas</title>
</head>
<body class="fondo">

   <%
   	String resultado = "";
   	String aniofact  = "";
   	String idclient  = "";
   	String tpclient  = "";
   	String idemisor  = ""; 
   	String fechafac  = "";
   	String tipofact  = ""; 
   	String formpago  = "";
   	String condpago  = "";
   	String catefact  = "";
   	String tipologo  = "";
   	String informda  = "";
   	String factasoc  = "";
   	String codvende  = "";
   	String mcpagado  = "";
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			resultado 	= io.getStringValue("txmensaj");
			aniofact	= io.getStringValue("aniofact");
			idclient 	= io.getStringValue("idclient");
			tpclient 	= io.getStringValue("tpclient");
			idemisor	= io.getStringValue("idemisor");
			fechafac	= io.getStringValue("fechafac");
			tipofact	= io.getStringValue("tipofact");
			formpago	= io.getStringValue("formpago");
			condpago 	= io.getStringValue("condpago");
			tipologo	= io.getStringValue("tipologo");
			catefact	= io.getStringValue("catefact");
			informda    = io.getStringValue("informda");
			factasoc 	= io.getStringValue("factasoc");
			codvende 	= io.getStringValue("codvende");
			mcpagado    = io.getStringValue("mcpagado"); 
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx factura/index.jsp");	
		}
	}
   
%>

<script>
	function generaFactu(){
		document.genFactu.submit();
		
	}
</script>

<div class="fondo1">
<h2 class="txt1"><%=resultado %></h2>
<br><br>
	<div>
			<br/>
			<br/>
			<br/>
			<br/>
			
	  		<table align="center">
				<tr>
					<td align="center">
						<input type="button" class="button-trab" onclick="generaFactu()" value="Generar Pdf">
					</td>
				</tr>
			</table>
			
		</div>
		
		
			<form method="post" name="genFactu" action="/JLet/App">
				<input type="hidden" name="controller" 	value="FacturaHttpHandler"/>			
				<input type="hidden" name="services" 	value="GeneraFacturaSrv"/>
				<input type="hidden" name="view" 		value="factura/abrirFactura.jsp"/>
				<input type="hidden" name="emisclie" 	value="<%=idemisor%>">
				<input type="hidden" name="receclie" 	value="<%=idclient%>">
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>">
				<input type="hidden" name="aniofact" 	value="<%=aniofact%>">
				<input type="hidden" name="tipofact" 	value="<%=tipofact%>">
				<input type="hidden" name="mcagrupa" 	value="0">
				<input type="hidden" name="fhfactur" 	value="<%=fechafac%>">
				<input type="hidden" name="formpago" 	value="<%=formpago%>">
				<input type="hidden" name="condpago" 	value="<%=condpago%>">
				<input type="hidden" name="catefact" 	value="<%=catefact%>">
				<input type="hidden" name="tipologo" 	value="<%=tipologo%>">
				<input type="hidden" name="informda" 	value="<%=informda%>">
				<input type="hidden" name="factasoc" 	value="<%=factasoc%>">
				<input type="hidden" name="codvende" 	value="<%=codvende%>">
				<input type="hidden" name="mcpagado" 	value="<%=mcpagado%>">
				
				
				
			</form>
	</div>	
	
	<script>
		document.genFactu.submit();
	</script>
</body>
</html>

