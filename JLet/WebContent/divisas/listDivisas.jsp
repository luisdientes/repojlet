<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid gdFixing = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gdFixing = io.getGrid("gdFixing");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listEnvio.jsp "+ e.getMessage());	
		}
	}
	
%>

<body>
	<div class="fondo2">
	   <div class="testata"><img src="/JLet/common/img/icons/factura.png"></div>
	   <div class="nompanta" >Cotización Divisa</div>
		<div class="centrado" style="width:95%">
			
			<form name="formenvi" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="GimnasioHttpHandler"/>
				<input type="hidden" name="services"	value="ListGimnasioSrv"/>
				<input type="hidden" name="view" 		value="gimnasio/altaGym.jsp"/>
				<input type="hidden" name="idclient" 	value=""/>
						
				
				<table width="70%" align="center">
					<tr class="fonts">
					
						<td width="40%" align="center"><div class="cabecera input-b1">Fecha</div></td>
						<td width="15%" align="center"><div class="cabecera input-b1">EUR</div></td>
						<td width="15%" align="center"><div class="cabecera input-b1">RD$</div></td>
						<td width="15%" align="center"><div class="cabecera input-b1">CHF</div></td>
						<td width="15%" align="center"><div class="cabecera input-b1">GBP</div></td>
					</tr>
					
					
					<% for (int i = 0; i < gdFixing.rowCount(); i++) { 
						
						String cdcotiza = "";
						String fhfechax = "";
						String altatime = "";
						String fixingxx = "";
						
						String fxusdeur = "";
						String fxusddop = "";
						String fxusdchf = "";
						String fxusdgbp = "";
						
						try {
							
							fhfechax = gdFixing.getStringCell(i,"fhfechax");
							fxusdeur = gdFixing.getStringCell(i,"fxusdeur");
							fxusddop = gdFixing.getStringCell(i,"fxusddop");
							fxusdchf = gdFixing.getStringCell(i,"fxusdchf");
							fxusdgbp = gdFixing.getStringCell(i,"fxusdgbp");
							
							%>
							
							<tr style="cursor:pointer;font-size:12px">
								
								<td align="center" class="fonts5" style="font-size:12px" title="<%=fhfechax%>"><%=fhfechax%></td>
								<td align="center" class="fonts5" style="font-size:12px"><%=fxusdeur%></td>
								<td align="center" class="fonts5" style="font-size:12px"><%=fxusddop%></td>
								<td align="center" class="fonts5" style="font-size:12px"><%=fxusdchf%></td>
								<td align="center" class="fonts5" style="font-size:12px"><%=fxusdgbp%></td>
							</tr>
						<% } catch (Exception e) { 							
							System.out.println(" ERROR - Recuperando linea subasta: "+ i +" - "+ e.getMessage());
						   }
						
					} %>						
				
				</table>											

			</form>
</body>
