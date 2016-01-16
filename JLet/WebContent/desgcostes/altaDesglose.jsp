<%@ include file="/common/jsp/include.jsp"%>

<%
	
	HashMap<String, String> hmCostRc = new HashMap<String, String>();
	
	String idemisor	= "";
	String idunicox	= "";
	String tipooper	= "";
	
	Grid gdcdcost   = null;
	Grid gddscost   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			tipooper = io.getStringValue("tipooper");
			idunicox = io.getStringValue("idunicox");
			gdcdcost = io.getGrid("gdcdcost");
			gddscost = io.getGrid("gddscost");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en alta Gimnasio/Al.jsp "+ e.getMessage());	
		}
	}
	 
	
	for (int i = 0; i < gddscost.rowCount(); i++){
		hmCostRc.put(gddscost.getStringCell(i,"codedesg"), gddscost.getStringCell(i,"desvalue"));
		//hmCostRc.put(gddscost.getStringCell(i,"codedesg").substring(0,2) +"IVA"+ gddscost.getStringCell(i,"codedesg").substring(2,7), gddscost.getStringCell(i,"desvalue"));		
	}

	
%>

<script>

	function sustituyePuntosComas(element){		
		element.value = element.value.replace(",",".");
	}
	
	function validaValor(element,cdgrupox){
		
		element.value.trim();
		sustituyePuntosComas(element);
		
		if (isNaN(element.value)){
			alert(element.value +" no es valido para este campo. Solo se pueden introducir numeros.");
			element.value = "";
			element.focus();
			return;
		}
		
		
		valor = parseFloat(element.value);
		
	}
	
	function sumaValor(element,cdgrupox){
		
		element.value.trim();
		
		var family = element.name.substr(0, 2);
		var acumula = 0;
		var acumiva = 0;
		
		var frm = document.getElementById("formcost");
		
		var sAux= 0;
		for (i = 0; i < frm.elements.length; i++) {
			
			if (frm.elements[i].name.substr(2,3) == "IVA") {
				//alert(frm.elements[i].name.substr(0,2) +"=="+ family +" | "+ frm.elements[i].name +"!="+ family+"IVATOTAL");
				if ((frm.elements[i].name.substr(0,2) == family) && (frm.elements[i].name != family+"IVATOTAL")){
					if (frm.elements[i].value != "") {
						acumiva = acumiva + parseFloat(frm.elements[i].value);
					} 
	
				}
			} else {
				if ((frm.elements[i].name.substr(0,2) == family) && (frm.elements[i].name != family+"TOTAL")){
					//alert(frm.elements[i].name +" = "+ frm.elements[i].value);
					if (frm.elements[i].value != "") {
						acumula = acumula + parseFloat(frm.elements[i].value);
					} 
	
				}
			}
			
		}
		

		var famtotal = family;
		var fivtotal = family;
		
		famtotal += "TOTAL";
		fivtotal += "IVATOTAL";
		
		familyTotal = document.getElementById(famtotal);
		famivaTotal = document.getElementById(fivtotal);
		
		familyTotal.value = acumula;
		famivaTotal.value = acumiva;
		
	}
	


	function selecCoste(tipocost){
		
		if (tipocost == "costecompra"){
			document.getElementById('costecompra').style.display = "block";
			document.getElementById('costetransp').style.display = "none";
			document.getElementById('costeaduana').style.display = "none";
		} else if (tipocost == "costetrasnp"){
			document.getElementById('costecompra').style.display = "none";
			document.getElementById('costetransp').style.display = "block";
			document.getElementById('costeaduana').style.display = "none";
		} else if (tipocost == "costeaduana"){
			document.getElementById('costecompra').style.display = "none";
			document.getElementById('costetransp').style.display = "none";
			document.getElementById('costeaduana').style.display = "block";
		}
		
	}
	
	function guardaCostes(){

		document.formcost.services.value = "ActualizaCostesSrv.DetalleDesgloseSrv";
		document.formcost.view.value 	 = "desgcostes/detalleDesglose.jsp";
		document.formcost.tipooper.value = "T";
		document.formcost.idunicox.value = "<%=idunicox%>";
		document.formcost.submit();
		
	}

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
	
	function init(){
		
		if (document.formcost.CATOTAL){
			if (document.formcost.CATOTAL.value == ""){
				document.formcost.CATOTAL.value = 0;
				document.formcost.CAIVATOTAL.value = 0;
			}

			document.formcost.CATOTAL.readOnly = true;
			document.formcost.CAIVATOTAL.readOnly = true;
		}
		
		if (document.formcost.CPTOTAL){
			if (document.formcost.CPTOTAL.value == ""){
				document.formcost.CPTOTAL.value = 0;
				document.formcost.CPIVATOTAL.value = 0;
			}
			document.formcost.CPTOTAL.readOnly = true;
			document.formcost.CPIVATOTAL.readOnly = true;
		}
		
		if (document.formcost.CTTOTAL){ 
			if (document.formcost.CTTOTAL.value == ""){
				document.formcost.CTTOTAL.value = 0;
				document.formcost.CTIVATOTAL.value = 0;
			}
			document.formcost.CTTOTAL.readOnly = true;
			document.formcost.CTIVATOTAL.readOnly = true;
		}
		
		if (document.formcost.VATOTAL){
			if (document.formcost.VATOTAL.value == ""){
				document.formcost.VATOTAL.value = 0;
			}
			document.formcost.VATOTAL.readOnly = true;
		}
		
		if (document.formcost.VPTOTAL){
			if (document.formcost.VPTOTAL.value == ""){
				document.formcost.VPTOTAL.value = 0;
			}
			document.formcost.VPTOTAL.readOnly = true;
		}
		
		if (document.formcost.VTTOTAL){
			if (document.formcost.VTTOTAL.value == ""){
				document.formcost.VTTOTAL.value = 0;
			}
			document.formcost.VTTOTAL.readOnly = true;
		}
		
		if (document.formcost.IGTOTAL){
			if (document.formcost.IGTOTAL.value == ""){
				document.formcost.IGTOTAL.value = 0;
			}
			document.formcost.IGTOTAL.readOnly = true;
		}
		
	}
	
</script>
    
<body onload="init()">
	<div class="fondo1" style="margin: 0 auto;">
    	
		<% if ((tipooper != null) && (tipooper.equals("C"))){%>
			<h1 align="center">Alta de Costes Compra</h1>
		<% } else if ((tipooper != null) && (tipooper.equals("V"))){ %>
			<h1 align="center">Alta de Costes Venta</h1>
		<% } else if ((tipooper != null) && (tipooper.equals("I"))){ %>
			<h1 align="center">Alta de Ingresos</h1>
		<% } %>
		
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
					<td width="10%">&nbsp;</td>
					<td width="80%">
			    		<table class="reportTable" style="margin: 0 auto;" id="costecompra" width="100%">
			    			<tr style="cursor:default; background-color:#000000; color:#FFFFFF; text-align: center; font-weight: bold">
				    			<td width="40%">Nombre Coste</td>
				    			<td width="20%">Código</td>
				    			<td width="20%">Coste Neto</td>
			    				<td width="20%">Coste IVA</td>
			    			</tr>
			    			
					    	<% for (int i = 0; i < gdcdcost.rowCount(); i++){ 
					    			String codedesg = gdcdcost.getStringCell(i,"codedesg");
					    			String civadesg = codedesg.substring(0,2) +"IVA"+ codedesg.substring(2,7);
					    			String cdgrupox = gdcdcost.getStringCell(i,"cdgrupox");
					    			String desvalue = "";
					    			String desivava = "";
					    			
					    			if (hmCostRc.containsKey(codedesg)){
					    				desvalue = hmCostRc.get(codedesg);					    				
					    			}
					    			
					    			if (hmCostRc.containsKey(civadesg)){
					    				desivava = hmCostRc.get(civadesg);
					    			}
					    			
					    		%>
					    		<% if (i % 2 == 0) { %>
							  		<tr class="oddRow" style="border:1px solid">
								<% } else { %>
									<tr class="evenRow" style="border:1px solid">
								<% } %>
										<% if (gdcdcost.getStringCell(i,"codedesg").indexOf("TOTAL") != -1){ %>
							    			<td width="40%" style="cursor:default; background-color:#555555; color:#FFFFFF; text-align: center; font-weight: bold">TOTAL  <%=gdcdcost.getStringCell(i,"txnombre")%></td>
							    			<td width="20%" style="cursor:default; background-color:#555555; color:#FFFFFF"></td>
							    			<td width="20%" style="background-color:#FFFFFF; text-align: center">
						    					<input class="input-b1" type="text" id="<%=codedesg%>" name="<%=codedesg%>" onchange="validaValor(this,'<%=cdgrupox%>');sumaValor(this,'<%=cdgrupox%>');" value="<%=desvalue%>" style="cursor:default"/>
						    				</td>
						    				<td width="20%" style="background-color:#FFFFFF; text-align: center">
						    					<input class="input-b1" type="text" id="<%=civadesg%>" name="<%=civadesg%>" onchange="validaValor(this,'<%=cdgrupox%>');sumaValor(this,'<%=cdgrupox%>');" value="<%=desivava%>" style="cursor:default"/>
						    				</td>
						    			<% } else { %>
							    			<td width="60%" style="cursor:default"><%=gdcdcost.getStringCell(i,"txnombre")%></td>
							    			<td width="15%" style="cursor:default; text-align: center"><%=gdcdcost.getStringCell(i,"codedesg")%></td>
							    			<td width="25%" style="cursor:default; text-align: center">
						    					<input class="fonts6" type="text" id="<%=codedesg%>" name="<%=codedesg%>" onchange="validaValor(this,'<%=cdgrupox%>');sumaValor(this,'<%=cdgrupox%>');" value="<%=desvalue%>" style="cursor:pointer"/>
						    				</td>
						    				<td width="25%" style="cursor:default; text-align: center">
						    					<input class="fonts6" type="text" id="<%=civadesg%>" name="<%=civadesg%>" onchange="validaValor(this,'<%=cdgrupox%>');sumaValor(this,'<%=cdgrupox%>');" value="<%=desivava%>" style="cursor:pointer"/>
						    				</td>
						    				<% } %>
						    			
						    		</tr>
					    	<% } %>
					    </table>
					 </td>   
					 <td width="10%">&nbsp;</td>
				</tr>
				<tr>
			    	<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
			    	<td colspan="3">
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
					    	<tr>
						    	<td width="33%" align="center">&nbsp;</td>
					    	</tr>
					    	<tr>
				    			<td>&nbsp;</td>
					    		<td style="cursor:pointer" align="center" onclick="guardaCostes();"><img src="/JLet/common/img/icons/graficadesglose.png" width="64px" height="64px"/></td>
					    		<td>&nbsp;</td>
					    	</tr>
					    	<tr>
					    		<td>&nbsp;</td>
					    		<td class="fonts-6" style="cursor:pointer" align="center" onclick="guardaCostes();">Ver Total Costes</td>
					    		<td>&nbsp;</td>
					    	</tr>
					    </table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>