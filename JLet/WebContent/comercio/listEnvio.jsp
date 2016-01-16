<%@ include file="/common/jsp/include.jsp"%>

<%
	Grid gdEnvios = null;
	String txmensaj = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gdEnvios = io.getGrid("gdEnvios");	
			txmensaj = io.getStringValue("txmensaj");		
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listEnvio.jsp "+ e.getMessage());	
		}
	}
	
%>


<head>
	<script>
		
		function generaExcelEnvio(codeenvi){
			
			document.formlsen.codeenvi.value = codeenvi;
			document.formlsen.submit();
			
		}
		
		function asignaTasas(codeenvi){
			
			document.formenvi.action = "/JLet/App";
			document.formenvi.codeenvi.value = codeenvi;
			document.formenvi.controller.value = "ComercioHttpHandler";
			document.formenvi.services.value = "ListComparaEnvioSrv";
			document.formenvi.view.value = "comercio/validarTasas.jsp";
			document.formenvi.submit();
			
		}
		
		function asignaCodigoProdEnvio(codeenvi){
			
			document.formenvi.action = "/JLet/App";
			document.formenvi.target = "_self";
			
			document.formenvi.codeenvi.value = codeenvi;
			
			//alert(document.formenvi.codeenvi.value);
			
			document.formenvi.controller.value = "ComercioHttpHandler";
			document.formenvi.services.value = "ListComparaEnvioSrv";
			document.formenvi.view.value = "comercio/asignaCodProducto.jsp";
			
			
			document.formenvi.submit();
			
		}
		
		function cotizacionEnvio(codeenvi){
			
			document.formenvi.action = "/JLet/App";
			document.formenvi.target = "_self";
			
			document.formenvi.codeenvi.value = codeenvi;
			
			document.formenvi.controller.value = "ComercioHttpHandler";
			document.formenvi.services.value = "InitCotizacionSrv";
			document.formenvi.view.value = "comercio/listCotizacion.jsp";
			
			document.formenvi.submit();
			
		}
		
		
		function descargarFichero(namefile){
			
			document.formenvi.action = "/JLet/JLetDownload";
			document.formenvi.target = "_blank";
			
			document.formenvi.filename.value = namefile;
			
			document.formenvi.submit();
		}
		
		
	function verDetalleEnvio(codeenvi,idemisor){
			
		
			document.formenvi.action = "/JLet/App";
			document.formenvi.codeenvi.value = codeenvi;
			document.formenvi.idemisor.value = idemisor;
			document.formenvi.services.value = "AltaDetalleEnvioSrv";
			document.formenvi.view.value = "comercio/altaDetalleEnvio.jsp";
			document.formenvi.mostrdet.value = "S";
			document.formenvi.submit();
		}
		
		
		
	</script>
</head>


<body>
	<div class="fondo2">
	<div class="nompanta" >Listado Envios</div>
		<div class="centrado" style="width:95%">
			
			<form name="formenvi" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
				<input type="hidden" name="services"	value="ListComparaEnvioSrv"/>
				<input type="hidden" name="view" 		value="comercio/asignaCodProducto.jsp"/>
				<input type="hidden" name="codeenvi" 	value=""/>
						
				<input type="hidden" name="idusuari" value="1"/>
				<input type="hidden" name="tipofile" value="ENV"/>
				<input type="hidden" name="pathfile" value=""/>
				<input type="hidden" name="filename" value=""/>
				<input type="hidden" name="mostrdet" value=""/>
				<input type="hidden" name="idemisor" value=""/>
				
				<table width="90%" align="center">
					<tr class="fonts">
						<td width="5%" align="center" 	class="input-b1">Aplicar Tasas</td>
						<td width="20%" align="center" 	class="input-b1">Codigo Envío</td>
						<td width="5%" align="center" 	class="input-b1">&nbsp;</td>
						<td width="20%" align="center" 	class="input-b1">Fecha Envío</td>
						<td width="5%" align="center" 	class="input-b1">&nbsp;</td>
						<td width="20%" align="center"  class="input-b1">Fecha Factura</td>
						<td width="5%" align="center"   class="input-b1">&nbsp;</td>
						<td width="15%" align="center"  class="input-b1">Fecha Cotizacion</td>
						<td width="5%" align="center"   class="input-b1">&nbsp;</td>
						
					</tr>
					<% for (int i = 0; i < gdEnvios.rowCount(); i++) { 
						try {
							String idenviox = gdEnvios.getStringCell(i,"idenviox");
							String codeenvi = gdEnvios.getStringCell(i,"codeenvi");
							String fhcreaci = gdEnvios.getStringCell(i,"fhcreaci");
							String fhfactur = gdEnvios.getStringCell(i,"fhfactur");
							String filefact = gdEnvios.getStringCell(i,"filefact");
							String fileenvi = gdEnvios.getStringCell(i,"fileenvi");
							String fhcotiza = gdEnvios.getStringCell(i,"fhcotiza");
							String filecoti = gdEnvios.getStringCell(i,"filecoti");
							String mcintern = gdEnvios.getStringCell(i,"mcintern");
							String idemisor = gdEnvios.getStringCell(i,"idemisor");
							
							%>
							<tr style="cursor:pointer">
								<td align="center" class="fonts6" onClick="asignaTasas('<%=codeenvi%>')"><img style="cursor:pointer" width="24px" src="/JLet/common/img/varios/iconcoin.png"/></td>
								<td align="center" class="fonts6" onClick="verDetalleEnvio('<%=codeenvi%>','<%=idemisor%>')"><%=codeenvi%></td>
								<% if ((fhcreaci != null) && (!fhcreaci.equals(""))){ %>
									<td align="center" class="fonts6" onClick="descargarFichero('<%=fileenvi%>');"><img style="cursor:pointer" width="24px" src="/JLet/common/img/varios/iconExcel.png"/></td>
									<td align="center" class="fonts6"><%=fhcreaci%></td>
								<% } else { %>
									<td align="center" class="fonts6">&nbsp;</td>
									<td align="center"class="fonts6">-</td>
								<% } %>
								<% if ((fhfactur != null) && (!fhfactur.equals(""))){ %>
									<td align="center" class="fonts6" onClick="descargarFichero('<%=filefact%>');"><img style="cursor:pointer" width="24px" src="/JLet/common/img/varios/iconPDF.png"/></td>
									<td align="center" class="fonts6"><%=fhfactur%></td>
								<% } else { %>
									<td align="center" class="fonts6">&nbsp;</td>
									<td align="center" class="fonts6">-</td>
								<% } %>
								<% if ((fhcotiza != null) && (!fhcotiza.equals(""))){ %>
									<td align="center" class="fonts6" onClick="descargarFichero('<%=filecoti%>');"><img style="cursor:pointer" width="24px" src="/JLet/common/img/varios/iconExcel.png"/></td>
									<td align="center" class="fonts6"><%=fhcotiza%></td>
								<% } else { %>
									<td align="center" class="fonts6" onClick="cotizacionEnvio('<%=codeenvi%>');"><img style="cursor:pointer" width="24px" src="/JLet/common/img/varios/iconcoin.png"/></td>
									<td align="center" class="fonts6">-</td>
								<% } %>

								<% if ((mcintern != null) && (!mcintern.equals(""))){ %>
									<td align="center" class="fonts6" onClick="asignaCodigoProdEnvio('<%=codeenvi%>');"><img style="cursor:pointer" width="24px" src="/JLet/common/img/icons/password.png"/></td>
								<% } %>
								
							</tr>
						<% } catch (Exception e) { 							
							System.out.println(" ERROR - Recuperando envio linea: "+ i);
						   } %>						
					<% } %>
				
				</table>											
			</form>
</body>