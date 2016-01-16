<%@ include file="/common/jsp/include.jsp"%>

<%


	String codeenvi = "";
	String imptotal = "";
	String porcmarg = "";

	String apcustotax = "N";
	String apconsutax = "N";
	String apfletecst = "N";
	String apitbisimp = "N";
	String aptramadua = "N";
	String apalmacena = "N";
	String apmovconte = "N";
	String apcargnavi = "N";

	Grid gdDivisa = null;
	String txmensaj = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			codeenvi = io.getStringValue("codeenvi");
			imptotal = io.getStringValue("imptotal");
			porcmarg = io.getStringValue("porcmarg");
			apcustotax = io.getStringValue("apcustotax");
			apconsutax = io.getStringValue("apconsutax");
			apfletecst = io.getStringValue("apfletecst");
			apitbisimp = io.getStringValue("apitbisimp");
			aptramadua = io.getStringValue("aptramadua");
			apalmacena = io.getStringValue("apalmacena");
			apmovconte = io.getStringValue("apmovconte");
			apcargnavi = io.getStringValue("apcargnavi");
			
			gdDivisa = io.getGrid("gdDivisa");	
			txmensaj = io.getStringValue("txmensaj");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listSimulador.jsp");	
		}
	}
	
	for (int i = 0; i < gdDivisa.rowCount(); i++){
		System.out.println(" SUSTITUIR - DIVISA "+ gdDivisa.getStringCell(i,"diviscam") +" fixing "+ gdDivisa.getStringCell(i,"fixingxx"));
	}
	
%>

<head>
		
	<script>
	
		function init(){
							
			<% if (apcustotax.equals("S")){ %>
				document.getElementById("tdprcustotax").style.display = "none";
			<% } %>
			<% if (apconsutax.equals("S")){ %>
				document.getElementById("tdprconsutax").style.display = "none";
			<% } %>
			<% if (apfletecst.equals("S")){ %>
				document.getElementById("tdprfletecst").style.display = "none";
			<% } %>
			<% if (apitbisimp.equals("S")){ %>
				document.getElementById("tdpritbisimp").style.display = "none";
			<% } %>
			<% if (aptramadua.equals("S")){ %>
				document.getElementById("tdprtramadua").style.display = "none";
			<% } %>
			<% if (apalmacena.equals("S")){ %>
				document.getElementById("tdpralmacena").style.display = "none";
			<% } %>
			<% if (apmovconte.equals("S")){ %>
				document.getElementById("tdprmovconte").style.display = "none";
			<% } %>
			<% if (apcargnavi.equals("S")){ %>
				document.getElementById("tdprcargnavi").style.display = "none";
			<% } %>
			
			document.simulado.ctusdeur.value = "0,7523";
			document.simulado.ctusdchf.value = "1,0739";
			document.simulado.ctusddop.value = "43,5000";
					
			cambiaDOP();
			
		}
		
		function clickCheckbox(codigo){
									
			inpchcbx = document.getElementById('ch'+ codigo);
			inpimpor = document.getElementById(codigo);
			inpporce = document.getElementById('pr'+ codigo);
			inptdimp = document.getElementById('td'+ codigo);
			inptdpor = document.getElementById('tdpr'+ codigo);																			
			
			if (inpchcbx.checked){				
				inpimpor.value = "";
				inptdimp.style.display = "none";
				inptdpor.style.display = "block";
			} else {				
				inpporce.value = "";
				inptdimp.style.display = "block";
				inptdpor.style.display = "none";
			}
		}
		
		function cambiaDOP(){
						
			imptotal = parseFloat(document.getElementById("imptotal").value.replace(".","").replace(",","."));
			ctusddop = parseFloat(document.getElementById("ctusddop").value.replace(",","."));
			imptotrd = imptotal * ctusddop;
			document.getElementById("imptotrd").value = formato_numero(imptotrd ,2,",",".");
		}
		
		function simularCotizacion(){
			
			document.simulado.submit();
			
		}
		
		
				
	</script>
	
</head>


<body onload="init()">
	<div class="fondo2">
	<div class="nompanta" >Cotización</div>
			<div class="centrado" style="width:70%">
			<form name="simulado" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
				<input type="hidden" name="services"	value="ListCotizadorPiezasSrv"/>
				<input type="hidden" name="view" 		value="comercio/resultSimulador.jsp"/>
				<input type="hidden" name="codeenvi" 	value="<%=codeenvi%>"/>
				
			
				<table width="60%" align="right"  class="fonts">
					<tr>
						<td width="5%" align="center">&nbsp;</td>
						<td width="20%" align="center">Euros</td>
						<td width="20%" align="center">CHF</td>
						<td width="20%" align="center">$RD</td>
						<td width="35%" align="center">&nbsp;</td>
					</tr>		
					<tr>
						<td align="center" >$</td>
						<td align="center"><input class="input-j" name="ctusdeur" style="width:90%" readonly/></td>
						<td align="center"><input class="input-j" name="ctusdchf" style="width:90%" readonly/></td>
						<td align="center"><input type="text" id="ctusddop" name="ctusddop" style="width:90%" onchange=cambiaDOP(); style="cursor:pointer"/></td>
						<td align="center">20/08/2014 9:45:14</td>
					</tr>
				</table>
				
				<br />
				<br />
				<br />
				<br />
			
				<table width="50%" align="center" border="0" class="fonts">
					<tr>
						<td colspan="2" width="60%">Importe Total</td>
						<td width="40%"><input type="text" name="imptotal" id="imptotal" placeholder="$" value="<%=imptotal%>" readOnly/></td>
					</tr>
					<tr>
						<td colspan="2" width="60%">Importe Total</td>
						<td width="40%"><input type="text" name="imptotrd" id="imptotrd" placeholder="$" readOnly/></td>
					</tr>
					<% if (apcustotax.equals("S")){ %>
						<tr>
							<td>Gravamen Adu.</td>
							<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chcustotax" id="chcustotax" onclick="clickCheckbox('custotax')"/></td>
							<td id="tdcustotax"><input type="text" name="custotax" id="custotax" placeholder="$RD"/></td>
							<td id="tdprcustotax"><input type="text" name="prcustotax" id="prcustotax" placeholder="%"/></td>
						</tr>
					<% } else {%>
						<input type="hidden" name="custotax">
						<input type="hidden" name="prcustotax">
					<% } %>
					<% if (apconsutax.equals("S")){ %>
					<tr>
						<td>Sel. Consumo</td>
						<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chconsutax" id="chconsutax" onclick="clickCheckbox('consutax')"/></td>
						<td id="tdconsutax"><input type="text" name="consutax" id="consutax" placeholder="$RD"/></td>
						<td id="tdprconsutax"><input type="text" name="prconsutax" id="prconsutax" placeholder="%"/></td>
					</tr>
					<% } else {%>
						<input type="hidden" name="consutax">
						<input type="hidden" name="prconsutax">
					<% } %>
					<% if (apfletecst.equals("S")){ %>
						<tr>
							<td>Flete</td>
							<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chfletecst" id="chfletecst" onclick="clickCheckbox('fletecst')"/></td>
							<td id="tdfletecst"><input type="text" name="fletecst" id="fletecst" placeholder="$RD"/></td>
							<td id="tdprfletecst"><input type="text" name="prfletecst" id="prfletecst" placeholder="%"/></td>
						</tr>
					<% } else {%>
						<input type="hidden" name="fletecst">
						<input type="hidden" name="prfletecst">
					<% } %>
					<% if (apitbisimp.equals("S")){ %>
						<tr>
							<td>ITBIS Importación</td>
							<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chitbisimp" id="chitbisimp" onclick="clickCheckbox('itbisimp')"/></td>
							<td id="tditbisimp"><input type="text" name="itbisimp" id="itbisimp" placeholder="$RD"/></td>
							<td id="tdpritbisimp"><input type="text" name="pritbisimp" id="pritbisimp" placeholder="%"/></td>
						</tr>
					<% } else {%>
						<input type="hidden" name="itbisimp">
						<input type="hidden" name="pritbisimp">
					<% } %>
					<% if (aptramadua.equals("S")){ %>
						<tr>
							<td>Tramites Aduaneros</td>
							<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chtramadua" id="chtramadua" onclick="clickCheckbox('tramadua')"/></td>
							<td id="tdtramadua"><input type="text" name="tramadua" id="tramadua" placeholder="$RD"/></td>
							<td id="tdprtramadua"><input type="text" name="prtramadua" id="prtramadua" placeholder="%"/></td>
						</tr>
					<% } else {%>
						<input type="hidden" name="tramadua">
						<input type="hidden" name="prtramadua">
					<% } %>
					<% if (apalmacena.equals("S")){ %>
						<tr>
							<td>Almacenaje</td>
							<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chalmacena" id="chalmacena" onclick="clickCheckbox('almacena')"/></td>
							<td id="tdalmacena"><input type="text" name="almacena" id="almacena" placeholder="$RD"/></td>
							<td id="tdpralmacena"><input type="text" name="pralmacena" id="pralmacena" placeholder="%"/></td>
						</tr>
					<% } else {%>
						<input type="hidden" name="almacena">
						<input type="hidden" name="pralmacena">
					<% } %>
					<% if (apmovconte.equals("S")){ %>
						<tr>
							<td>Trans. Contenedor</td>
							<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chmovconte" id="chmovconte" onclick="clickCheckbox('movconte')"/></td>
							<td id="tdmovconte"><input type="text" name="movconte" id="movconte" placeholder="$RD"/></td>
							<td id="tdprmovconte"><input type="text" name="prmovconte" id="prmovconte" placeholder="%"/></td>
						</tr>
					<% } else {%>
						<input type="hidden" name="movconte">
						<input type="hidden" name="prmovconte">
					<% } %>
					<% if (apcargnavi.equals("S")){ %>
						<tr>
							<td>Carga Naviera</td>
							<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chcargnavi" id="chcargnavi" onclick="clickCheckbox('cargnavi')"/></td>
							<td id="tdcargnavi"><input type="text" name="cargnavi" id="cargnavi" placeholder="$RD"/></td>
							<td id="tdprcargnavi"><input type="text" name="prcargnavi" id="prcargnavi" placeholder="%"/></td>
						</tr>
					<% } else {%>
						<input type="hidden" name="cargnavi">
						<input type="hidden" name="prcargnavi">
					<% } %>
					<tr>
						<td>Margen Origen (Tse-Yang)</td>
						<td>&nbsp;</td>
						<td id="tdprmargorig"><input type="text" name="prmargorig" value="<%=porcmarg%>" readOnly/></td>
					</tr>
					<tr>
						<td>Margen MallProShop</td>
						<td>&nbsp;</td>
						<td id="tdprmargmpsp"><input type="text" name="prmargmpsp" placeholder="%"/></td>
					</tr>
					<tr>
						<td>Margen Izumba</td>
						<td>&nbsp;</td>
						<td id="tdprmargizum"><input type="text" name="prmargizum" placeholder="%"/></td>
					</tr>
				</table>
				
				<br/>
				<br/>
				
				<table width="100%" align="center">
					<tr>
						<td align="center">
							<a class="boton" onClick="simularCotizacion()">Realizar Simulación</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>