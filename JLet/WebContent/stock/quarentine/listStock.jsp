<%@ include file="/common/jsp/include.jsp"%>

<%


	String codeenvi = "";
	String imptotal = "";
	String porcmarg = "";
	Grid gdSedepo = null;
	Grid gdDivisa = null;
	String txmensaj = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			codeenvi = io.getStringValue("gdStockA");
			imptotal = io.getStringValue("gdSedepo");
			porcmarg = io.getStringValue("porcmarg");
			gdSedepo = io.getGrid("gdSedepo");	
			gdDivisa = io.getGrid("gdSedepo");	
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
							
			document.getElementById("tdprcustotax").style.display = "none";
			document.getElementById("tdprconsutax").style.display = "none";
			document.getElementById("tdprfletecst").style.display = "none";
			document.getElementById("tdpritbisimp").style.display = "none";
			document.getElementById("tdprtramadua").style.display = "none";
			document.getElementById("tdpralmacena").style.display = "none";
			document.getElementById("tdprmovconte").style.display = "none";
			document.getElementById("tdprcargnavi").style.display = "none";
			
			document.simulado.ctusdeur.value = "0,7523";
			document.simulado.ctusdchf.value = "1,0739";
			document.simulado.ctusddop.value = "43,5000";
					
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
		
		function simularCotizacion(){
			
			document.simulado.submit();
			
		}
		
		
				
	</script>
	
</head>


<body onload="init()">
	<div class="fondo2">
			<div class="centrado" style="width:70%">
			<form name="simulado" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
				<input type="hidden" name="services"	value="ListCotizadorSrv"/>
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
						<td align="center"><input class="input-j" name="ctusddop" style="width:90%" readonly/></td>
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
						<td width="40%"><input type="text" name="imptotal" placeholder="$" value="<%=imptotal%>" readOnly/></td>
					</tr>
					<tr>
						<td>Custom Tax</td>
						<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chcustotax" id="chcustotax" onclick="clickCheckbox('custotax')"/></td>
						<td id="tdcustotax"><input type="text" name="custotax" id="custotax" placeholder="$RD"/></td>
						<td id="tdprcustotax"><input type="text" name="prcustotax" id="prcustotax" placeholder="%"/></td>
					</tr>
					<tr>
						<td>Consumo Tax</td>
						<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chconsutax" id="chconsutax" onclick="clickCheckbox('consutax')"/></td>
						<td id="tdconsutax"><input type="text" name="consutax" id="consutax" placeholder="$RD"/></td>
						<td id="tdprconsutax"><input type="text" name="prconsutax" id="prconsutax" placeholder="%"/></td>
					</tr>
					<tr>
						<td>Flete</td>
						<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chfletecst" id="chfletecst" onclick="clickCheckbox('fletecst')"/></td>
						<td id="tdfletecst"><input type="text" name="fletecst" id="fletecst" placeholder="$RD"/></td>
						<td id="tdprfletecst"><input type="text" name="prfletecst" id="prfletecst" placeholder="%"/></td>
					</tr>
					<tr>
						<td>ITBIS Importación</td>
						<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chitbisimp" id="chitbisimp" onclick="clickCheckbox('itbisimp')"/></td>
						<td id="tditbisimp"><input type="text" name="itbisimp" id="itbisimp" placeholder="$RD"/></td>
						<td id="tdpritbisimp"><input type="text" name="pritbisimp" id="pritbisimp" placeholder="%"/></td>
					</tr>
					<tr>
						<td>Tramites Aduaneros</td>
						<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chtramadua" id="chtramadua" onclick="clickCheckbox('tramadua')"/></td>
						<td id="tdtramadua"><input type="text" name="tramadua" id="tramadua" placeholder="$RD"/></td>
						<td id="tdprtramadua"><input type="text" name="prtramadua" id="prtramadua" placeholder="%"/></td>
					</tr>
					<tr>
						<td>Almacenaje</td>
						<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chalmacena" id="chalmacena" onclick="clickCheckbox('almacena')"/></td>
						<td id="tdalmacena"><input type="text" name="almacena" id="almacena" placeholder="$RD"/></td>
						<td id="tdpralmacena"><input type="text" name="pralmacena" id="pralmacena" placeholder="%"/></td>
					</tr>
					<tr>
						<td>Trans. Contenedor</td>
						<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chmovconte" id="chmovconte" onclick="clickCheckbox('movconte')"/></td>
						<td id="tdmovconte"><input type="text" name="movconte" id="movconte" placeholder="$RD"/></td>
						<td id="tdprmovconte"><input type="text" name="prmovconte" id="prmovconte" placeholder="%"/></td>
					</tr>
					<tr>
						<td>Carga Naviera</td>
						<td>%&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chcargnavi" id="chcargnavi" onclick="clickCheckbox('cargnavi')"/></td>
						<td id="tdcargnavi"><input type="text" name="cargnavi" id="cargnavi" placeholder="$RD"/></td>
						<td id="tdprcargnavi"><input type="text" name="prcargnavi" id="prcargnavi" placeholder="%"/></td>
					</tr>
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