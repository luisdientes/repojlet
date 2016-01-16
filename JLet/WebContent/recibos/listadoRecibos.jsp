<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid grListRe = null;
	String txmensaj = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			grListRe = io.getGrid("grListRe");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listEnvio.jsp "+ e.getMessage());	
		}
	}
	
%>


<head>

</head>


<body>
	<div class="fondo2">
	   <div class="testata"><img src="/JLet/common/img/icons/title-listado-recibos.png"></div>
		<div class="centrado" style="width:95%">
			
			<form name="formenvi" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="RecibosHttpHandler"/>
				<input type="hidden" name="services"	value="ListRecibosSrv"/>
				<input type="hidden" name="view" 		value="gimnasio/altaGym.jsp"/>
				<input type="hidden" name="idclient" 	value=""/>
						
				
				<table width="90%" align="center">
					<tr class="fonts">
					
						<td width="28%" align="center"><div class="cabecera input-b1">Cliente</div></td>
						<td width="8%" align="center"><div class="cabecera input-b1">Cantidad</div></td>
						<td width="8%" align="center"><div class="cabecera input-b1">Concepto</div></td>
						<td width="14%" align="center"><div class="cabecera input-b1">Cajero</div></td>
						<td width="7%" align="center"><div class="cabecera input-b1">Factura</div></td>
						<td width="7%" align="center"><div class="cabecera input-b1">Valor Recibo</div></td>
					
					</tr>
					
					
					<% for (int i = 0; i < grListRe.rowCount(); i++) { 
						
						String idrecibo = "";
						String rzsocial = "";
						String cantidad = "";
						String concepto = "";
						String txcajero = "";
						String cdfactur = "";
						String valortot = "";
				
						
						try {
							idrecibo = grListRe.getStringCell(i,"idrecibo");
							rzsocial = grListRe.getStringCell(i,"rzsocial");
							cantidad = grListRe.getStringCell(i,"cantidad");
							concepto = grListRe.getStringCell(i,"concepto");
							txcajero = grListRe.getStringCell(i,"txcajero");
							cdfactur = grListRe.getStringCell(i,"cdfactur");
							valortot = grListRe.getStringCell(i,"valortot");
							
	
							%>
							<tr style="cursor:pointer;font-size:12px" onclick="verDetalle('<%=idrecibo%>')">
								
								<td align="center" class="fonts5" style="font-size:12px"><%=rzsocial%></td>
								<td align="center" class="fonts5" style="font-size:12px"><%=cantidad%></td>
								<td align="center" class="fonts5" style="font-size:12px"><%=concepto%></td>
								<td align="center" class="fonts5" style="font-size:12px"><%=txcajero%></td>
								<td align="center" class="fonts5" style="font-size:12px"><%=cdfactur%></td>
								<td align="center" class="fonts5" style="font-size:12px"><%=valortot%></td>
	
								
							</tr>
						<% } catch (Exception e) { 							
							System.out.println(" ERROR - Recuperando linea subasta: "+ i +" - "+ e.getMessage());
						   }
						
					} %>						
				
				</table>											

			</form>
</body>
