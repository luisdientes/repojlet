<%@ include file="/common/jsp/include.jsp"%>

<%

	String idunicox	= "";
	String idemisor	= "";
	String imgfilex	= "";
	String ingtotal	= "";
	String benefici	= "";
	String mostriva = "";
	String costetot	= "";
	Grid gdcdcost   = null;
	Grid gddscost   = null;
	Grid gddetall   = null;
	
	double dingtotal = 1;
	double gastoAcu = 0;
	 
	DecimalFormat formatDivi = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idunicox = io.getStringValue("idunicox");
			idemisor = io.getStringValue("idemisor");
			imgfilex = io.getStringValue("imgfilex");
			ingtotal = io.getStringValue("ingtotal");
			benefici = io.getStringValue("benefici");
			mostriva = io.getStringValue("mostriva");
			gdcdcost = io.getGrid("gdcdcost");
			gddscost = io.getGrid("gddscost");
			gddetall = io.getGrid("gddetall");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores detallDesglose.jsp "+ e.getMessage());	
		}
	}
	
	imgfilex = imgfilex.replaceAll(".png","_480x480.png");
	
	NumberFormat defaultFormat = NumberFormat.getPercentInstance();
	defaultFormat.setMinimumFractionDigits(2);
	
	//PORCENTAJE BENEFICIO	
	double dbenefici = 0;
	double dporcenbe = 0;
	String porcenbe = "";
	
	try {
		dingtotal = Double.parseDouble(ingtotal);
		
		String partbene = benefici.replaceAll("\\.","");
		partbene = partbene.replaceAll(",","\\.");
		
		dbenefici = Double.parseDouble(partbene);
		dporcenbe = dbenefici / dingtotal;
		porcenbe = defaultFormat.format(dporcenbe);
			
	} catch (Exception e) { 
		System.out.println("ERROR -     NO SE RECIBIÓN INGRESOS TOTALES QW");
		e.printStackTrace();
	}
	
%>

<link href="/JLet/common/css/switchHTML5.css" type="text/css" rel="stylesheet">

<script>

	var detailca = false;
	var detailcp = false;
	var detailct = false;
	var detailva = false;
	var detailvp = false;
	var detailvt = false;
	var detailig = false;
	
	function guardaCostesCambiaEntrada(tipooper){
	
		document.formcost.services.value = "ActualizaCostesSrv";
		document.formcost.view.value 	 = "desgcostes/altaDesglose.jsp";
		document.formcost.tipooper.value = tipooper;
		document.formcost.idunicox.value = "<%=idunicox%>";
		document.formcost.submit();
		
	}
	
	function altaDetalle(tipooper){

		document.formcost.services.value = "ActualizaCostesSrv.InitAltaDesgloseSrv";
		document.formcost.view.value 	 = "desgcostes/altaDetalle.jsp";
		document.formcost.idunicox.value = "<%=idunicox%>";
		document.formcost.submit();
		
	}
	
	function mostrarIVA(){

		if (document.formcost.mostriva.value == "S") {
			document.formcost.mostriva.value = "N";
			document.getElementById("checkiva").checked = false;
		} else {
			document.formcost.mostriva.value = "S";
			document.getElementById("checkiva").checked = true;
		}
		
		document.formcost.services.value = "DetalleDesgloseSrv";
		document.formcost.view.value 	 = "desgcostes/detalleDesglose.jsp";
		document.formcost.tipooper.value = "T";
		document.formcost.idunicox.value = "<%=idunicox%>";

		document.formcost.submit();
			
	}
	
	function mostrarDetalle(grupodet) {
		
		if (grupodet == "CA") {
			elements = document.getElementsByClassName("class_detalleCA");
			if (detailca){
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "none";
				}
				detailca = false;
			} else {
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "";
				}
				detailca = true;
			}
		}
		
		if (grupodet == "CP") {			
			elements = document.getElementsByClassName("class_detalleCP");
			if (detailcp){
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "none";
				}
				detailcp = false;
			} else {
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "";
				}
				detailcp = true;
			}
		}
		
		if (grupodet == "CT") {			
			elements = document.getElementsByClassName("class_detalleCT");
			if (detailct){
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "none";
				}
				detailct = false;
			} else {
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "";
				}
				detailct = true;
			}
		}
		
		if (grupodet == "VA") {
			elements = document.getElementsByClassName("class_detalleVA");
			if (detailva){
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "none";
				}
				detailva = false;
			} else {
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "";
				}
				detailva = true;
			}
		}
		
		if (grupodet == "VP") {			
			elements = document.getElementsByClassName("class_detalleVP");
			if (detailvp){
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "none";
				}
				detailvp = false;
			} else {
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "";
				}
				detailvp = true;
			}
		}
		
		if (grupodet == "VT") {			
			elements = document.getElementsByClassName("class_detalleVT");
			if (detailvt){
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "none";
				}
				detailvt = false;
			} else {
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "";
				}
				detailvt = true;
			}
		}
		
		if (grupodet == "IG") {			
			elements = document.getElementsByClassName("class_detalleIG");
			if (detailig){
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "none";
				}
				detailig = false;
			} else {
				for (var i=0; i < elements.length; i++) {
					elements[i].style.display = "";
				}
				detailig = true;
			}
		}
		
	}
	
	
</script>

<body>
	<div class="fondo1" style="margin: 0 auto;">
    	
    	<form method="POST" name="formcost" action="/JLet/App">
			<input type="hidden" name="controller" 	value="DesgCostesHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
			<input type="hidden" name="idunicox" 	value="<%=idunicox%>"/>
			<input type="hidden" name="mostriva" 	value="<%=mostriva%>"/>
			<input type="hidden" name="tipooper" 	value=""/>
			
			<br/>
			<h2 align="center">Desglose Costes: <%=idunicox%></h2>
			<br/>
			
			<table width="20%" align="center">
				<tr>
					<td>
						<div class="onoffswitch" style="margin: 0 auto">
						
							<% if ((mostriva != null) && (mostriva.equals("S"))) { %>
								<input type="checkbox" name="checkiva" class="onoffswitch-checkbox" id="checkiva" checked/>
							<% } else { %>
								<input type="checkbox" name="checkiva" class="onoffswitch-checkbox" id="checkiva"/>
							<% }  %>
					
					        <label class="onoffswitch-label" for="myonoffswitch" onclick="javascript:mostrarIVA()">
					            <span class="onoffswitch-inner"></span>
					            <span class="onoffswitch-switch"></span>
					        </label>
				        </div>
				    </td>
				    <td>
				    &nbsp;&nbsp;&nbsp;Mostrar impuestos (IVA)
				    </td>
				</tr>
			</table>
		    
			<br/>
			
			<% if ((imgfilex != null) && (!imgfilex.equals("")) && (!imgfilex.equals("VACIO"))){ %>
				<table width="80%" align="center">
					<tr>
						 <td width="35%" valign="middle" align="center">
							<img id="icongraf" src="/JLet/imageServlet?imagenam=<%=imgfilex%>&tipoimgx=stat" style="cursor:pointer"/>
						 </td>
						 <td width="5%" rowspan="2">&nbsp;</td>
						 <td width="60%" rowspan="2">
							 <table align="center" width="100%">
							 	<tr>
					    			<td class="input-b1" colspan="4">DESGLOSE DE COSTES</td>
					    		</tr>
					    		
						    	<% for (int i = 0; i < gddscost.rowCount(); i++) { 
						    		if (!gddscost.getStringCell(i,"codedesg").substring(0,2).equals("IG")) {						    			
						    			if (!gddscost.getStringCell(i,"desvalue").equals("0.0")) {
						    				if (gddscost.getStringCell(i,"codedesg").indexOf("TOTAL") != -1) { %>
										    	<tr class="evenRow" style="cursor:pointer" onclick="mostrarDetalle('<%=gddscost.getStringCell(i,"codedesg").substring(0,2)%>')" style="font-weight: bold">
										    <% } else { %>
											    <tr class="oddRow class_detalle<%=gddscost.getStringCell(i,"codedesg").substring(0,2)%>" style="display:none">
											<% } %>
									    	<td width="45%"><%=gddscost.getStringCell(i,"txnombre")%></td>
								    		<td width="15%" align="center"><%=gddscost.getStringCell(i,"codedesg")%></td>
								    		<td width="25%" align="right"><%=formatDivi.format(Double.parseDouble(gddscost.getStringCell(i,"desvalue")))%> <%=gddscost.getStringCell(i,"cddivisa")%> &nbsp;&nbsp;&nbsp;</td>
						    				<% 
						    					//PORCENTAJES
						    				double ddesvalue = 0;
					    					double dporcenta = 0;
					    					String porcenta = "";
					    					
						    				try {	
						    					
						    					ddesvalue = Double.parseDouble(gddscost.getStringCell(i,"desvalue"));
						    					dporcenta = ddesvalue / dingtotal;
						    					porcenta = defaultFormat.format(dporcenta);
						    					
						    					if (gddscost.getStringCell(i,"codedesg").indexOf("TOTAL") != -1) { 
										    		gastoAcu += ddesvalue;
										    	}
						    					
						    				} catch (Exception e) {
		
						    				} %>
							    			
							    			<td width="15%" align="right"><%=porcenta%> &nbsp;&nbsp;</td>
							    			
								    	</tr>
							    	<% }
						    		}
						    	} %>
						    	<tr>
							    	<td class="input-b1" colspan="2">TOTAL GASTOS</td>
						    		<td class="fonts6"   align="right"><%=formatDivi.format(gastoAcu)%> <%=gddscost.getStringCell(0,"cddivisa")%> &nbsp;&nbsp;&nbsp;</td>
			    				
				    				<% 
				    					//PORCENTAJES
			    					double dgasporc = 0;
			    					String gasporce = "";
			    					
				    				try {	
				    					
				    					dgasporc = gastoAcu / dingtotal;
				    					gasporce = defaultFormat.format(dgasporc);
				    					
				    				} catch (Exception e) {
				    					e.printStackTrace();
					    			} %>
					    			
					    			<td class="fonts6" align="right"><%=gasporce%> &nbsp;&nbsp;</td>
					    			
						    </table>
						 </td>
					</tr>
					<tr>
						 <td width="45%" valign="middle" align="center" >
							Beneficio Neto: <span style="color:#228B22"><b><%=benefici%> <%=gddscost.getStringCell(0,"cddivisa")%> &nbsp;&nbsp;&nbsp;(<%=porcenbe%>)</b></span>
						 </td>
					 </tr>
					<tr>
						 <td>
						 	<table width="50%" align="center">
						 		<tr>
						 			<td colspan='2'><hr></td>
						 		</tr>
							 	<% for (int i = 0; i < gddetall.rowCount(); i++) { %> 
							 		<tr>
									    <td width="45%"><b><%=gddetall.getStringCell(i,"txnombre")%></b></td>
								    	<td width="55%"><%=gddetall.getStringCell(i,"desvalue")%></td>
								    </tr>
							    <% } %>
						    </table>
					     </td>
						 <td>&nbsp;</td>
						 <td>
							 <table align="center" width="100%">
							 	<tr>
					    			<td class="input-b1" colspan="4">DESGLOSE DE INGRESOS</td>
					    		</tr>
					    		
						    	<% for (int i = 0; i < gddscost.rowCount(); i++) { 
						    		if (gddscost.getStringCell(i,"codedesg").substring(0,2).equals("IG")) {						    			
						    			if (!gddscost.getStringCell(i,"desvalue").equals("0.0")) {
						    				if (gddscost.getStringCell(i,"codedesg").indexOf("TOTAL") != -1) { %>
										    	<tr class="evenRow" style="cursor:pointer" onclick="mostrarDetalle('<%=gddscost.getStringCell(i,"codedesg").substring(0,2)%>')" style="font-weight: bold">
										    <% } else { %>
											    <tr class="oddRow class_detalle<%=gddscost.getStringCell(i,"codedesg").substring(0,2)%>" style="display:none">
											<% } %>
									    	<td width="45%"><%=gddscost.getStringCell(i,"txnombre")%></td>
								    		<td width="15%" align="center"><%=gddscost.getStringCell(i,"codedesg")%></td>
								    		<td width="25%" align="right"><%=formatDivi.format(Double.parseDouble(gddscost.getStringCell(i,"desvalue")))%> <%=gddscost.getStringCell(i,"cddivisa")%> &nbsp;&nbsp;&nbsp;</td>
						    				<% 
						    					//PORCENTAJES
						    				double ddesvalue = 0;
					    					double dporcenta = 0;
					    					String porcenta = "";
					    					
						    				try {	
						    					
						    					ddesvalue = Double.parseDouble(gddscost.getStringCell(i,"desvalue"));
						    					dporcenta = ddesvalue / dingtotal;
						    					porcenta = defaultFormat.format(dporcenta);
						    					
						    					if (gddscost.getStringCell(i,"codedesg").indexOf("TOTAL") != -1) { 
										    		gastoAcu += ddesvalue;
										    	}
						    					
						    				} catch (Exception e) {
		
						    				} %>
							    			
							    			<td width="15%" align="right"><%=porcenta%> &nbsp;&nbsp;</td>
							    			
								    	</tr>
							    	<% }
						    		}
						    	} %>
						    	<tr>
							    	<td class="input-b1" colspan="2">TOTAL INGRESOS</td>
						    		<td class="fonts6"   align="right"><%=formatDivi.format(dingtotal)%> <%=gddscost.getStringCell(0,"cddivisa")%> &nbsp;&nbsp;&nbsp;</td>
			    				
				    				<% 
				    					//PORCENTAJES
			    					double dingporc = 0;
			    					String ingporce = "";
			    					
				    				try {	
				    					
				    					dgasporc = dingtotal / dingtotal;
				    					ingporce = defaultFormat.format(dgasporc);
				    					
				    				} catch (Exception e) {
				    					e.printStackTrace();
					    			} %>
					    			
					    			<td class="fonts6" align="right"><%=ingporce%> &nbsp;&nbsp;</td>
					    			
						    </table>
					    </td>
					 </tr>
				</table>
			<% } else { %>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				
				<p align="center" style="color:#AA0114"><b>Debe incluir los ingresos generados por la venta del producto para poder ver el gr&aacute;fico</b></p>

				<br/>
				<br/>
				<br/>
				
			<% } %>
			
			<br/>
			<br/>
			
			<table align="center" width="40%">
		    	<tr>
		    		<td width="33%" align="center" style="cursor:pointer" onclick="altaDetalle();"><img src="/JLet/common/img/icons/detaComp.png" width="64px" height="64px"/></td>
		    		<td width="33%" align="center" style="cursor:pointer" onclick="guardaCostesCambiaEntrada('C');"><img src="/JLet/common/img/icons/costComp.png" width="64px" height="64px"/></td>
	    			<td width="33%" align="center" style="cursor:pointer" onclick="guardaCostesCambiaEntrada('V');"><img src="/JLet/common/img/icons/costVent.png" width="64px" height="64px"/></td>
	    			<td width="33%" align="center" style="cursor:pointer" onclick="guardaCostesCambiaEntrada('I');"><img src="/JLet/common/img/icons/ingresos.png" width="64px" height="64px"/></td>
		    	</tr> 
		    	<tr>
		    		<td class="fonts-6" style="cursor:pointer" align="center" onclick="altaDetalle();">Detalle Compra</td>
		    		<td class="fonts-6" style="cursor:pointer" align="center" onclick="guardaCostesCambiaEntrada('C');">Costes Compra</td>
	    			<td class="fonts-6" style="cursor:pointer" align="center" onclick="guardaCostesCambiaEntrada('V');">Costes Venta</td>
	    			<td class="fonts-6" style="cursor:pointer" align="center" onclick="guardaCostesCambiaEntrada('I');">Ingresos</td>
		    	</tr>
		    </table>
		</form>
	</div>
</body>