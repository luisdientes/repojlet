<%@ include file="/common/jsp/include.jsp"%>

<body class="fondo" onload="document.abriFactu.submit()">

   <%
    String idemisor = "";
   	String filecrea = "";
   	String txmensaj = "";
   	String cdrecibo = "";
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor 	= io.getStringValue("idemisor");
			filecrea 	= io.getStringValue("filecrea");
			cdrecibo 	= io.getStringValue("cdrecibo");
			txmensaj	= io.getStringValue("txmensaj");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores genera codigo barras");	
		}
	}
   
%>

	

<div class="centrado" style="width:100% !important; min-height:350px !important">
<br><br>
	<div>
		
	  		<table align="center" width=30%>
	  		
	  			<% if (cdrecibo !=null && !cdrecibo.equals("")){ %>
				
				<tr>
					<td class="cabecera input-b1">Generar Código Barras</td>
					<td align="center" class="fonts6" style="padding-left:0px !important">
					<img onclick="document.formImprime.submit()" src="/JLet/common/img/varios/barcode-Grande.jpg" height="32px" title="Código de Barras" style="cursor:pointer"/>
					<br>
						<%=cdrecibo%>
					</td>
				</tr>
				<%} %>	
			
				<tr>
					<td class="cabecera input-b1">Abrir PDF </td>
					<td class="fonts6" align="center" style="padding-left:6px !important">
						<img onclick="document.abriFactu.submit()" src="/JLet/common/img/varios/iconPDF.png" height="32px" title="Documento PDF" style="cursor:pointer"/>
					<br>
					PDF
					</td>
				</tr>
				
				<tr>
					<td align="center">&nbsp;</td>
				</tr>
		  		<tr>
					<td align="center" colspan=2>
						<a class="boton" href="/JLet/common/jsp/menuPrinc.jsp">Aceptar</a>
					</td>
				</tr>
			</table>
			
			
		</div>

		<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="tipofile" value="REC"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value="<%=filecrea%>"/><!-- Cambiar -->
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/><!-- Cambiar -->
		</form>
		
		<form name="formImprime" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="ReparaHttpHandler"/>
				<input type="hidden" name="services"	value="GenCodBarrasReciboSrv"/>
				<input type="hidden" name="view" 		value="reparaciones/GeneraCodBarras.jsp"/>
				<input type="hidden" name="filereci" 		value="<%=cdrecibo%>"/>
				<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
		</form>
		
		
		
	</div>	
</body>
</html>


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<!--
		
		
	

		<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="tipofile" value="FRA"/> 
			<input type="hidden" name="pathfile" value=""/>
			<input type="hidden" name="filename" value="<%=filecrea%>"/>
		</form>
		
		
		-->
	</div>	
</body>
</html>

