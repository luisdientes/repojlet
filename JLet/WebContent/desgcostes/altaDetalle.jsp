<%@ include file="/common/jsp/include.jsp"%>

<%
	
	HashMap<String, String> hmCostRc = new HashMap();
	
	String idemisor	= "";
	String idunicox	= "";
	String tipooper	= "";
	String canalven = "";
	
	Grid gddsdeta   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			tipooper = io.getStringValue("tipooper");
			idunicox = io.getStringValue("idunicox");
			gddsdeta = io.getGrid("gddsdeta");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en alta Gimnasio/Al.jsp "+ e.getMessage());	
		}
	}
	 
	for (int i = 0; i < gddsdeta.rowCount(); i++){
		System.out.println(gddsdeta.getStringCell(i,"codedeta")+"-"+gddsdeta.getStringCell(i,"desvalue"));
		hmCostRc.put(gddsdeta.getStringCell(i,"codedeta"), gddsdeta.getStringCell(i,"desvalue"));
	}
	
    canalven = hmCostRc.containsKey("CANALVEN")?hmCostRc.get("CANALVEN"):"";
	
%>

<script>

	function guardaDetalleCambiaEntrada(tipooper){
		
		document.formcost.services.value = "ActualizaDetalleSrv.ActualizaCostesSrv";
		document.formcost.view.value 	 = "desgcostes/altaDesglose.jsp";
		document.formcost.tipooper.value = tipooper;
		document.formcost.idunicox.value = "<%=idunicox%>";
		document.formcost.submit();
		
	}

	function guardaDetalle(){

		document.formcost.services.value = "ActualizaDetalleSrv.DetalleDesgloseSrv";
		document.formcost.view.value 	 = "desgcostes/detalleDesglose.jsp";
		document.formcost.tipooper.value = "T";
		document.formcost.idunicox.value = "<%=idunicox%>";
		document.formcost.submit();
		
	}

	
</script>
    
<body>
	<div class="fondo1" style="margin: 0 auto;">
    	
		<h3 align="center">Id. Unico (<%=idemisor%>): <%=idunicox%></h3>
	
    	<form id="formcost" method="POST" name="formcost" action="/JLet/App">
			<input type="hidden" name="controller" 	value="DesgCostesHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
			<input type="hidden" name="tipooper" 	value=""/>
			<input type="hidden" name="idunicox" 	value="<%=idunicox%>"/>
		
			<table width="90%" align="center">
				<tr>
					<td width="30%">&nbsp;</td>
					<td width="40%">
			    		<table style="margin: 0 auto;" id="costecompra" width="100%">
				    		<tr>
				    			<td class="input-b1" width="50%">Cod. Cliente</td>
				    			<td>
			    					<input class="fonts6" type="text" id="idclient" name="idclient"  value='<%=hmCostRc.containsKey("IDCLIENT")?hmCostRc.get("IDCLIENT"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="input-b1" width="50%">Cliente</td>
				    			<td>
			    					<input class="fonts6" type="text" id="txclient" name="txclient"  value='<%=hmCostRc.containsKey("TXCLIENT")?hmCostRc.get("TXCLIENT"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="input-b1" width="50%">Cod. Factura</td>
				    			<td>
			    					<input class="fonts6" type="text" id="cdfactur" name="cdfactur"  value='<%=hmCostRc.containsKey("CDFACTUR")?hmCostRc.get("CDFACTUR"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="input-b1" width="50%">Fecha Factura</td>
				    			<td>
			    					<input class="fonts6" type="text" id="fhfactur" name="fhfactur"  value='<%=hmCostRc.containsKey("FHFACTUR")?hmCostRc.get("FHFACTUR"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="input-b1" width="50%">Fecha Stock</td>
				    			<td>
			    					<input class="fonts6" type="text" id="fhstockx" name="fhstockx"  value='<%=hmCostRc.containsKey("FHSTOCKX")?hmCostRc.get("FHSTOCKX"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="input-b1" width="50%">Fecha Venta</td>
				    			<td>
			    					<input class="fonts6" type="text" id="fhventax" name="fhventax"  value='<%=hmCostRc.containsKey("FHVENTAX")?hmCostRc.get("FHVENTAX"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="input-b1" width="50%">Transportista</td>
				    			<td>
			    					<input class="fonts6" type="text" id="txtransp" name="txtransp"  value='<%=hmCostRc.containsKey("TXTRANSP")?hmCostRc.get("TXTRANSP"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
				    		<tr>
					    		<td class="input-b1" width="50%">Tracking Id.</td>
				    			<td>
			    					<input class="fonts6" type="text" id="tracking" name="tracking"  value='<%=hmCostRc.containsKey("TRACKING")?hmCostRc.get("TRACKING"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="input-b1" width="50%">Canal Venta</td>
				    			<td>
				    				<select id="canalven" name="canalven" class="fonts6" style="cursor:pointer;width:192px" >
										<option value="AMAZON (ES)" <% if (canalven.equals("AMAZON (ES)")){%> selected<%} %>>AMAZON ES</option>
										<option value="AMAZON (FR)" <% if (canalven.equals("AMAZON (FR)")){%> selected<%} %>>AMAZON FR</option>
										<option value="AMAZON (DE)" <% if (canalven.equals("AMAZON (DE)")){%> selected<%} %>>AMAZON DE</option>
										<option value="AMAZON (IT)" <% if (canalven.equals("AMAZON (IT)")){%> selected<%} %>>AMAZON IT</option>
										<option value="AMAZON (UK)" <% if (canalven.equals("AMAZON (UK)")){%> selected<%} %>>AMAZON UK</option>
										<option value="EBAY" <% if (canalven.equals("EBAY")){%> selected<%} %>>EBAY</option>
										<option value="EBAY (ES)" <% if (canalven.equals("EBAY (ES")){%> selected<%} %>>EBAY (ES)</option>
										<option value="EBAY (ES) CAMBIO" <% if (canalven.equals("EBAY (ES) CAMBIO")){%> selected<%} %>>EBAY CAMBIO</option>
										<option value="EBAY CONTRAOFERTA" <% if (canalven.equals("EBAY CONTRAOFERTA")){%> selected<%} %>>EBAY CONTRAOFERTA</option>
										<option value="EBAY SUBASTA" <% if (canalven.equals("EBAY SUBASTA")){%> selected<%} %>>EBAY SUBASTA</option>
										<option value="MILANUNCIOS" <% if (canalven.equals("MILANUNCIOS")){%> selected<%} %>>MIL ANUNCIOS</option>
										<option value="TELEFONO" <% if (canalven.equals("TELEFONO")){%> selected<%} %>>TELEFONO</option>
										<option value="PERSONAL" <% if (canalven.equals("PERSONAL")){%> selected<%} %>>PERSONAL</option>
										<option value="OTROS" <% if (canalven.equals("OTROS")){%> selected<%} %>>OTROS</option>
									</select>
				    			<!--  
			    					<input class="fonts6" type="text" id="canalven" name="canalven"  value='<%=hmCostRc.containsKey("CANALVEN")?hmCostRc.get("CANALVEN"):""%>' style="cursor:pointer"/>
				    			-->
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="input-b1" width="50%">Cod. Venta Ext.</td>
				    			<td>
			    					<input class="fonts6" type="text" id="cdextern" name="cdextern"  value='<%=hmCostRc.containsKey("CDEXTERN")?hmCostRc.get("CDEXTERN"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td class="input-b1" width="50%">País Cliente</td>
				    			<td>
			    					<input class="fonts6" type="text" id="cdpaisxx" name="cdpaisxx"  value='<%=hmCostRc.containsKey("CDPAISXX")?hmCostRc.get("CDPAISXX"):""%>' style="cursor:pointer"/>
				    			</td>
				    		</tr>
					    </table>
					 </td>   
					 <td width="30%">&nbsp;</td>
				</tr>
				<tr>
			    	<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
			    	<td colspan="3">
					    <table align="center" width="40%">
						    <tr>
					    		<td width="33%" align="center" style="cursor:pointer"><img src="/JLet/common/img/icons/detaComp.png" width="64px" height="64px"/></td>	
					    		<td width="33%" align="center" style="cursor:pointer" onclick="guardaDetalleCambiaEntrada('C');"><img src="/JLet/common/img/icons/costComp.png" width="64px" height="64px"/></td>
				    			<td width="33%" align="center" style="cursor:pointer" onclick="guardaDetalleCambiaEntrada('V');"><img src="/JLet/common/img/icons/costVent.png" width="64px" height="64px"/></td>
				    			<td width="33%" align="center" style="cursor:pointer" onclick="guardaDetalleCambiaEntrada('I');"><img src="/JLet/common/img/icons/ingresos.png" width="64px" height="64px"/></td>
					    	</tr>
					    	<tr>
					    		<td class="fonts-6" style="cursor:pointer" align="center">Detalle Compra</td>
						    	<td class="fonts-6" style="cursor:pointer" align="center" onclick="guardaDetalleCambiaEntrada('C');">Costes Compra</td>
				    			<td class="fonts-6" style="cursor:pointer" align="center" onclick="guardaDetalleCambiaEntrada('V');">Costes Venta</td>
				    			<td class="fonts-6" style="cursor:pointer" align="center" onclick="guardaDetalleCambiaEntrada('I');">Ingresos</td>
					    	</tr>
					    	<tr>
						    	<td width="33%" align="center">&nbsp;</td>
					    	</tr>
					    	<tr>
				    			<td>&nbsp;</td>
					    		<td style="cursor:pointer" align="center" onclick="guardaDetalle();"><img src="/JLet/common/img/icons/graficadesglose.png" width="64px" height="64px"/></td>
					    		<td>&nbsp;</td>
					    	</tr>
					    	<tr>
					    		<td>&nbsp;</td>
					    		<td class="fonts-6" style="cursor:pointer" align="center" onclick="guardaDetalle();">Ver Total Costes</td>
					    		<td>&nbsp;</td>
					    	</tr>
					    </table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>