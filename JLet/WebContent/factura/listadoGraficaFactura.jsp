<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<meta charset='ISO-8859-1'>
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>

<%

	String aniofact = null;
	String idemisor = null;
	String filename = null;
	String fhdesdex = null;
	String fhhastax = null;
	String tipodata = null;
	String idclirec = null;
	String idclient = null;
	Grid gridAcum 	= null;
	Grid gridAgru	= null;
	Grid gridFact   = null; 
	Grid gridClie	= null; 
	
	//Texto paises
	String txcdfact = "";

	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			aniofact = io.getStringValue("aniofact");
			filename = io.getStringValue("filename");
			fhdesdex = io.getStringValue("fhdesdex");
			fhhastax = io.getStringValue("fhhastax");
			tipodata = io.getStringValue("tipodata");
			
			gridAcum = io.getGrid("gridAcum");
			gridFact = io.getGrid("grfactur");
			gridAgru = io.getGrid("gragrupa");
			gridClie = io.getGrid("gridClie");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/listadoFacturasEmisor.jsp "+ e.getMessage());	
		}
	
	
		try{
			idclient = io.getStringValue("idcliere");
			if(idclient.equals("null")){
				idclient = "";
			}
		
		}catch(Exception ex) {
			idclient = "";
			System.out.println("Error - al recibir fecha desde");
		}
	}
				
%>

<script>

	function abrirFactura(namefile){
		document.abriFactu.filename.value = namefile;
		document.abriFactu.submit();
	}
	
	function clickAgrupada(cdagrupa){
		for (i = 0; i < document.getElementById("tabagrup").rows.length; i++){
		
			if (document.getElementById("tabagrup").rows[i].className == cdagrupa) {
				if (document.getElementById("tabagrup").rows[i].style.display == "none"){
					document.getElementById("tabagrup").rows[i].style.display = "";
				} else {
					document.getElementById("tabagrup").rows[i].style.display = "none";
				}
			}
		}
			
	}

</script>

	<div class="fondo2">
		<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
			<table width=100% align="center" class="tab-listado">
				<tr>
					<td class="input-b1">Gráfica <%=tipodata%> de facturación: desde el <%=fhdesdex%> al <%=fhhastax%></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<img id="icongraf" src="/JLet/imageServlet?imagenam=<%=filename%>&tipoimgx=stat" style="cursor:pointer" width="100%"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
						<table id="tabagrup" width=100% align="center" class="tab-listado" style="max-width:100% !important">
							<tr >
								<td class="input-b1">Fecha</td>
								<td class="input-b1">Cliente</td>
								<td class="input-b1">Tipo Factura</td>
								<td class="input-b1">Factura</td>
								<td class="input-b1">Base Imponible</td>
								<td class="input-b1">Impuesto</td>
								<td class="input-b1">Total</td>
							</tr>
							
							<% 
							
								int ndetal = 0;
								
								if (gridFact.rowCount() > 0){
									String cddivisa = gridFact.getStringCell(0,"cddivisa");
								 
							
									for (int i = 0; i < gridAgru.rowCount(); i++){
										 
										String cdagrupa = gridAgru.getStringCell(i,"cdagrupa");%>
										<tr class="usuario" onclick="clickAgrupada('<%=cdagrupa%>')" style="cursor:pointer">
											<td align="center" width="10%" class="fonts6"><%=gridAgru.getStringCell(i,"txagrupa")%></td>
											<td align="center" width="26%" class="fonts6">-</td>
											<td align="center" width="15%" class="fonts6">-</td>
											<td align="center" width="15%" class="fonts6">-</td>
											<td align="right"  width="12%" class="fonts6"><%=formateador.format(Double.parseDouble(gridAgru.getStringCell(i,"basacumu")))%> <%=cddivisa%></td>
											<td align="right"  width="10%" class="fonts6"><%=formateador.format(Double.parseDouble(gridAgru.getStringCell(i,"taxacumu")))%> <%=cddivisa%></td>
											<td align="right"  width="12%" class="fonts6"><%=formateador.format(Double.parseDouble(gridAgru.getStringCell(i,"totacumu")))%> <%=cddivisa%></td>
										</tr>
										<% for (int j = ndetal; j < gridFact.rowCount(); j++){
											
											if ((tipodata.equals("Diaria") && (gridAgru.getStringCell(i,"txagrupa").equals(gridFact.getStringCell(j,"fhfactur")))) ||
											   (tipodata.equals("Mensual") && gridAgru.getStringCell(i,"cdagrupa").equals(gridFact.getStringCell(j,"fhfactur").substring(3,5)))){ %>
											
												<tr class="<%=cdagrupa%>" style="display: none">
													<td  class="fonts6" style="background-color:ccc;">-</td>
													<td  class="fonts6" style="background-color:ccc;"><%=gridFact.getStringCell(j,"rzsocial")%></td>
													<td  class="fonts6" style="background-color:ccc;"><%=gridFact.getStringCell(j,"txtpfact")%></td>
													<td  class="fonts6" style="background-color:ccc;"><%=gridFact.getStringCell(j,"cdfactur")%></td>
													<td  class="fonts6" align="right" style="background-color:ccc;"><%=formateador.format(Double.parseDouble(gridFact.getStringCell(j,"baseimpo")))%> <%=cddivisa%>&nbsp;&nbsp;</td>
													<td  class="fonts6" align="right" style="background-color:ccc;"><%=formateador.format(Double.parseDouble(gridFact.getStringCell(j,"imptaxes")))%> <%=cddivisa%>&nbsp;&nbsp;</td>
													<td  class="fonts6" align="right" style="background-color:ccc;"><%=formateador.format(Double.parseDouble(gridFact.getStringCell(j,"imptotal")))%> <%=cddivisa%>&nbsp;&nbsp;</td>
												</tr>
										<% 	}
										} %>

								<% } %>
							<% } else { %>
								<p>No existen facturas para este periodo</p>
							<% } %>
							
						</table>
					</td>
				</tr>
			</table>
		</div>	
	</div>
</body>	
	

