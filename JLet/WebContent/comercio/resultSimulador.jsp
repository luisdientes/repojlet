<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid gdDivisa = null;

	String imptotal = "";
	String custotax = "";
	String consutax = "";
	String fletecst = "";
	String itbisimp = "";
	String tramadua = "";
	String almacena = "";
	String movconte = "";
	String cargnavi = "";
	String prcustotax = "";
	String prconsutax = "";
	String prfletecst = "";
	String pritbisimp = "";
	String prtramadua = "";
	String pralmacena = "";
	String prmovconte = "";
	String prcargnavi = "";
	String prmargorig = "";
	String prmargmpsp = "";
	String prmargizum = "";
	
	//SHOW en las cajas
	String sprcustotax = "";
	String sprconsutax = "";
	String sprfletecst = "";
	String spritbisimp = "";
	String sprtramadua = "";
	String spralmacena = "";
	String sprmovconte = "";
	String sprcargnavi = "";
	String sprmargorig = "";
	String sprmargmpsp = "";
	String sprmargizum = "";

	String txmensaj = "";

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			imptotal   = io.getStringValue("imptotal");
			custotax   = io.getStringValue("custotax");
			consutax   = io.getStringValue("consutax");
			fletecst   = io.getStringValue("fletecst");
			itbisimp   = io.getStringValue("itbisimp");
			tramadua   = io.getStringValue("tramadua");
			almacena   = io.getStringValue("almacena");
			movconte   = io.getStringValue("movconte");
			cargnavi   = io.getStringValue("cargnavi");
			prcustotax = io.getStringValue("prcustotax");
			prconsutax = io.getStringValue("prconsutax");
			prfletecst = io.getStringValue("prfletecst");
			pritbisimp = io.getStringValue("pritbisimp");
			prtramadua = io.getStringValue("prtramadua");
			pralmacena = io.getStringValue("pralmacena");
			prmovconte = io.getStringValue("prmovconte");
			prcargnavi = io.getStringValue("prcargnavi");
			prmargorig = io.getStringValue("prmargorig");
			prmargmpsp = io.getStringValue("prmargmpsp");
			prmargizum = io.getStringValue("prmargizum");	
			txmensaj = io.getStringValue("txmensaj");		
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listEnvio.jsp");	
		}
	}
	
	try {

		sprcustotax = format2d.format(Double.parseDouble(prcustotax) * 100);
		sprconsutax = format2d.format(Double.parseDouble(prconsutax) * 100);
		sprfletecst = format2d.format(Double.parseDouble(prfletecst) * 100);
		spritbisimp = format2d.format(Double.parseDouble(pritbisimp) * 100);
		sprtramadua = format2d.format(Double.parseDouble(prtramadua) * 100);
		spralmacena = format2d.format(Double.parseDouble(pralmacena) * 100);
		sprmovconte = format2d.format(Double.parseDouble(prmovconte) * 100);
		sprcargnavi = format2d.format(Double.parseDouble(prcargnavi) * 100);
		sprmargorig = format2d.format(Double.parseDouble(prmargorig) * 100);
		sprmargmpsp = format2d.format(Double.parseDouble(prmargmpsp) * 100);
		sprmargizum = format2d.format(Double.parseDouble(prmargizum) * 100);
		
	} catch (Exception e) {
		System.err.println("ERROR - Multiplicando por 100 los porcentajes");
		e.printStackTrace();
	}
	
	
	
%>

<head>
	<script>
	
		var imfixing  = parseFloat(43.05);
	
		var costesto  = 0;
		
		var imptotal  = 0;
		var custotax  = 0;
		var consutax  = 0;
		var fletecst  = 0;
		var itbisimp  = 0;
		var tramadua  = 0;
		var almacena  = 0;
		var movconte  = 0;
		var cargnavi  = 0;
		var margorig  = 0;
		var margmpsp  = 0;
		var margizum  = 0;
		var prcustotax  = 0;
		var prconsutax  = 0;
		var prfletecst  = 0;
		var pritbisimp  = 0;
		var prtramadua  = 0;
		var pralmacena  = 0;
		var prmovconte  = 0;
		var prcargnavi  = 0;
		var prmargorig  = 0;
		var prmargmpsp  = 0;
		var prmargizum 	= 0;
	
		function init(){
							
			prcustotax  = parseFloat(<%=prcustotax%>);
			prconsutax  = parseFloat(<%=prconsutax%>);
			prfletecst  = parseFloat(<%=prfletecst%>);
			pritbisimp  = parseFloat(<%=pritbisimp%>);
			prtramadua  = parseFloat(<%=prtramadua%>);
			pralmacena  = parseFloat(<%=pralmacena%>);
			prmovconte  = parseFloat(<%=prmovconte%>);
			prcargnavi  = parseFloat(<%=prcargnavi%>);
			prmargorig  = parseFloat(<%=prmargorig%>);
			prmargmpsp  = parseFloat(<%=prmargmpsp%>);
			prmargizum  = parseFloat(<%=prmargizum%>);

			document.simulado.imptotal.focus();
			
					
		}
		
		function introduceValor(valor){

			//imptotal = valor + (valor * prmargorig);
			imptotal = parseFloat(valor) + (parseFloat(valor) * parseFloat(prmargorig));
			
			document.simulado.costemar.value = imptotal;
			document.simulado.rdcostemar.value = imptotal * imfixing;
			
			custotax = parseFloat(imptotal) * parseFloat(prcustotax);
			document.simulado.custotax.value = custotax;
			document.simulado.rdcustotax.value = custotax * imfixing;
			
			consutax = imptotal * prconsutax;
			document.simulado.consutax.value = consutax;
			document.simulado.rdconsutax.value = consutax * imfixing;
			
			fletecst = imptotal * prfletecst;
			document.simulado.fletecst.value = fletecst;
			document.simulado.rdfletecst.value = fletecst * imfixing;
			
			itbisimp = imptotal * pritbisimp;
			document.simulado.itbisimp.value = itbisimp;
			document.simulado.rditbisimp.value = itbisimp * imfixing;
			
			tramadua = imptotal * prtramadua;
			document.simulado.tramadua.value = tramadua;
			document.simulado.rdtramadua.value = tramadua * imfixing;
			
			almacena = imptotal * pralmacena;
			document.simulado.almacena.value = almacena;
			document.simulado.rdalmacena.value = almacena * imfixing;
			
			movconte = imptotal * prmovconte;
			document.simulado.movconte.value = movconte;
			document.simulado.rdmovconte.value = movconte * imfixing;
			
			cargnavi = imptotal * prcargnavi;
			document.simulado.cargnavi.value = cargnavi;
			document.simulado.rdcargnavi.value = cargnavi * imfixing;
			
			margorig = parseFloat(valor) * prmargorig;
			document.simulado.margorig.value = margorig;
			document.simulado.rdmargorig.value = margorig * imfixing;
			
			costesto = parseFloat(custotax) + parseFloat(consutax) + parseFloat(fletecst) + parseFloat(itbisimp) + parseFloat(tramadua);
			costesto = parseFloat(costesto) + parseFloat(almacena) + parseFloat(movconte) + parseFloat(cargnavi) + parseFloat(margorig);
			
			var costedef = imptotal + costesto;
			
			document.simulado.costedef.value = costedef;
			document.simulado.rdcostedef.value = costedef * imfixing;
			
			margmpsp = parseFloat(costedef) + (parseFloat(costedef) * parseFloat(prmargmpsp));
			document.simulado.margmpsp.value = margmpsp;
			document.simulado.rdmargmpsp.value = margmpsp * imfixing;
			
			margizum = parseFloat(costedef) + (parseFloat(costedef) * parseFloat(prmargizum));
			document.simulado.margizum.value = margizum;
			document.simulado.rdmargizum.value = margizum * imfixing;
			
			redondear();
			
		}
		
		function redondear() {
			
			document.simulado.costemar.value   = formato_numero(document.simulado.costemar.value  ,2,",",".");
			document.simulado.rdcostemar.value = formato_numero(document.simulado.rdcostemar.value,2,",",".");
			document.simulado.custotax.value   = formato_numero(document.simulado.custotax.value  ,2,",",".");
			document.simulado.rdcustotax.value = formato_numero(document.simulado.rdcustotax.value,2,",",".");
			document.simulado.consutax.value   = formato_numero(document.simulado.consutax.value  ,2,",",".");
			document.simulado.rdconsutax.value = formato_numero(document.simulado.rdconsutax.value,2,",",".");
			document.simulado.fletecst.value   = formato_numero(document.simulado.fletecst.value  ,2,",",".");
			document.simulado.rdfletecst.value = formato_numero(document.simulado.rdfletecst.value,2,",",".");
			document.simulado.itbisimp.value   = formato_numero(document.simulado.itbisimp.value  ,2,",",".");
			document.simulado.rditbisimp.value = formato_numero(document.simulado.rditbisimp.value,2,",",".");
			document.simulado.tramadua.value   = formato_numero(document.simulado.tramadua.value  ,2,",",".");
			document.simulado.rdtramadua.value = formato_numero(document.simulado.rdtramadua.value,2,",",".");
			document.simulado.almacena.value   = formato_numero(document.simulado.almacena.value  ,2,",",".");
			document.simulado.rdalmacena.value = formato_numero(document.simulado.rdalmacena.value,2,",",".");
			document.simulado.movconte.value   = formato_numero(document.simulado.movconte.value  ,2,",",".");
			document.simulado.rdmovconte.value = formato_numero(document.simulado.rdmovconte.value,2,",",".");
			document.simulado.cargnavi.value   = formato_numero(document.simulado.cargnavi.value  ,2,",",".");
			document.simulado.rdcargnavi.value = formato_numero(document.simulado.rdcargnavi.value,2,",",".");
			document.simulado.margorig.value   = formato_numero(document.simulado.margorig.value  ,2,",",".");
			document.simulado.rdmargorig.value = formato_numero(document.simulado.rdmargorig.value,2,",",".");
			document.simulado.costedef.value   = formato_numero(document.simulado.costedef.value  ,2,",",".");
			document.simulado.rdcostedef.value = formato_numero(document.simulado.rdcostedef.value,2,",",".");
			document.simulado.margmpsp.value   = formato_numero(document.simulado.margmpsp.value  ,2,",",".");
			document.simulado.rdmargmpsp.value = formato_numero(document.simulado.rdmargmpsp.value,2,",",".");
			document.simulado.margizum.value   = formato_numero(document.simulado.margizum.value  ,2,",",".");
			document.simulado.rdmargizum.value = formato_numero(document.simulado.rdmargizum.value,2,",",".");

		}
		
		function salirSimulacion(){
			
			if (confirm("¿Seguro que quieres salir? Luego tendrás que volver a rellenar los datos.")){				
				document.simulado.controller.value = "ComunHttpHandler";
				document.simulado.services.value = "InitMenuPrincSrv";
				document.simulado.view.value = "common/jsp/menuPrinc.jsp";
				document.simulado.cdpantal.value = "impoexpo";
				
			} 
			
		}		
		
				
	</script>
	
</head>


<body onload="init()">
	<div class="fondo2">
			<div class="centrado" style="width:70%">
			<form name="simulado" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
				<input type="hidden" name="services"	value="ListSimuladorSrv"/>
				<input type="hidden" name="view" 		value="comercio/listSimulador.jsp"/>
				<input type="hidden" name="cdpantal" 	value=""/>
				
			
				<table width="60%" align="right">
					<tr class="fonts">
						<td width="5%" align="center">&nbsp;</td>
						<td width="20%" align="center">Euros</td>
						<td width="20%" align="center">CHF</td>
						<td width="20%" align="center">$RD</td>
						<td width="35%" align="center">&nbsp;</td>
					</tr>		
					<tr class="fonts">
						<td align="center">$</td>
						<td align="center"><input class="input-j" name="ctusdeur" style="width:90%" readonly/></td>
						<td align="center"><input class="input-j" name="ctusdchf" style="width:90%" readonly/></td>
						<td align="center"><input class="input-j" name="ctusddop" style="width:90%" readonly/></td>
						<td align="center">20/08/2014 9:45:14</td>
					</tr>
				</table>
				
				<br/>
				<br/>
				
				<table width="80%" align="center">

					<tr class="fonts">
						<td width="5%"  align="center">&nbsp;</td>
						<td width="20%" align="center">Importe Total</td>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr class="fonts">
						<td width="5%"  align="center">$</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=imptotal%>" readonly/></td>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr class="fonts">
						<td width="5%"  align="center">&nbsp;</td>
						<td width="20%" align="center">Gravamen Aduana</td>
						<td width="5%"  align="center">&nbsp;</td>
						<td width="20%" align="center">Selectivo al Consumo</td>
						<td width="5%"  align="center">&nbsp;</td>
						<td width="20%" align="center">Flete</td>
						<td width="5%"  align="center">&nbsp;</td>
						<td width="20%" align="center">ITBIS Imp.</td>						
					</tr>
					<tr class="fonts">
						<td width="5%"  align="center">$</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=custotax%>" readonly/></td>
						<td width="5%"  align="center">$</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=consutax%>" readonly/></td>
						<td width="5%"  align="center">$</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=fletecst%>" readonly/></td>
						<td width="5%"  align="center">$</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=itbisimp%>" readonly/></td>						
					</tr>
					<tr class="fonts">
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=sprcustotax%>" readonly/></td>
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=sprconsutax%>" readonly/></td>
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=sprfletecst%>" readonly/></td>
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=spritbisimp%>" readonly/></td>						
					</tr>
					<tr class="fonts">
						<td width="5%"  align="center">&nbsp;</td>
						<td width="20%" align="center">Tram. Aduana</td>
						<td width="5%"  align="center">&nbsp;</td>
						<td width="20%" align="center">Almacenaje</td>
						<td width="5%"  align="center">&nbsp;</td>
						<td width="20%" align="center">Trans. Contenedor</td>
						<td width="5%"  align="center">&nbsp;</td>
						<td width="20%" align="center">Carga Naviera</td>						
					</tr>
					<tr class="fonts">
						<td width="5%"  align="center">$</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=tramadua%>" readonly/></td>
						<td width="5%"  align="center">$</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=almacena%>" readonly/></td>
						<td width="5%"  align="center">$</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=movconte%>" readonly/></td>
						<td width="5%"  align="center">$</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=cargnavi%>" readonly/></td>						
					</tr>
					<tr class="fonts">
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=sprtramadua%>" readonly/></td>
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=spralmacena%>" readonly/></td>
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=sprmovconte%>" readonly/></td>
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=sprcargnavi%>" readonly/></td>						
					</tr>
					<tr class="fonts">
						<td colspan="2">Margenes</td>						
						<td>&nbsp;</td>
						<td align="center">Origen</td>
						<td>&nbsp;</td>
						<td align="center">MallProShop</td>
						<td>&nbsp;</td>
						<td align="center">Izumba</td>
					</tr>
					<tr class="fonts">
						<td colspan="2">&nbsp;</td>
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=sprmargorig%>" readonly/></td>
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=sprmargmpsp%>" readonly/></td>
						<td width="5%"  align="center">%</td>
						<td width="20%" align="center"><input class="input-j" style="width:90%" value="<%=sprmargizum%>" readonly/></td>						
					</tr>
				</table>

				<br />
				<br />
			
				<table width="70%" align="center" border="0" class="fonts">
					<tr>
						<td width="40%">Importe Producto</td>
						<td width="30%"><input type="text" name="imptotal" placeholder="$" onchange="introduceValor(this.value)"/></td>
						<td width="30%">&nbsp;</td>
					</tr>
					<tr>
						<td>Coste + Margen</td>
						<td id="tdcostemar"><input class="input-j"  name="costemar" id="costemar"/></td>
						<td id="tdcostemar"><input class="input-j"  name="rdcostemar" id="rdcostemar"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td align="center">$</td>
						<td align="center">$RD</td>
					</tr>
					<tr>
						<td>Gravamen Aduana</td>
						<td id="tdcustotax"><input class="input-j"  name="custotax" id="custotax"/></td>
						<td id="tdcustotax"><input class="input-j"  name="rdcustotax" id="rdcustotax"/></td>
					</tr>
					<tr>
						<td>Selectivo al Consumo</td>
						<td id="tdconsutax"><input class="input-j"  name="consutax" id="consutax"/></td>
						<td id="tdconsutax"><input class="input-j"  name="rdconsutax" id="rdconsutax"/></td>
					</tr>
					<tr>
						<td>Flete</td>
						<td id="tdfletecst"><input class="input-j"  name="fletecst" id="fletecst"</td>
						<td id="tdfletecst"><input class="input-j"  name="rdfletecst" id="rdfletecst"</td>
					</tr>
					<tr>
						<td>ITBIS Importación</td>
						<td id="tditbisimp"><input class="input-j"  name="itbisimp" id="itbisimp"/></td>
						<td id="tditbisimp"><input class="input-j"  name="rditbisimp" id="rditbisimp"/></td>
					</tr>
					<tr>
						<td>Tramites Aduaneros</td>
						<td id="tdtramadua"><input class="input-j"  name="tramadua" id="tramadua"/></td>
						<td id="tdtramadua"><input class="input-j"  name="rdtramadua" id="rdtramadua"/></td>
					</tr>
					<tr>
						<td>Almacenaje</td>
						<td id="tdalmacena"><input class="input-j"  name="almacena" id="almacena"/></td>
						<td id="tdalmacena"><input class="input-j"  name="rdalmacena" id="rdalmacena"/></td>
					</tr>
					<tr>
						<td>Trans. Contenedor</td>
						<td id="tdmovconte"><input class="input-j"  name="movconte" id="movconte"/></td>
						<td id="tdmovconte"><input class="input-j"  name="rdmovconte" id="rdmovconte"/></td>
					</tr>
					<tr>
						<td>Carga Naviera</td>
						<td id="tdcargnavi"><input class="input-j"  name="cargnavi" id="cargnavi"/></td>
						<td id="tdcargnavi"><input class="input-j"  name="rdcargnavi" id="rdcargnavi"/></td>
					</tr>
					<tr>
						<td>Margen Origen (Tse-Yang)</td>
						<td id="tdmargorig"><input class="input-j"  name="margorig" id="margorig"/></td>
						<td id="tdmargorig"><input class="input-j"  name="rdmargorig" id="rdmargorig"/></td>
					</tr>
					<tr>
						<td colspan="3"><hr/></td>
					</tr>
					<tr>
						<td><b>Coste Total</b></td>
						<td id="tdmargorig"><input class="input-j"  name="costedef" id="costedef"/></td>
						<td id="tdmargorig"><input class="input-j"  name="rdcostedef" id="rdcostedef"/></td>
					</tr>
					<tr>
						<td>Precio MallProShop</td>
						<td id="tdmargmpsp"><input class="input-j"  name="margmpsp" id="margmpsp"/></td>
						<td id="tdmargmpsp"><input class="input-j"  name="rdmargmpsp" id="rdmargmpsp"/></td>
					</tr>
					<tr>
						<td>Precio Izumba</td>
						<td id="tdmargizum"><input class="input-j"  name="margizum" id="margizum"/></td>
						<td id="tdmargizum"><input class="input-j"  name="rdmargizum" id="rdmargizum"/></td>
					</tr>
				</table>
				
				<br/>
				<br/>
				
				<table width="100%" align="center">
					<tr>
						<td align="center">
							<a class="boton" onClick="salirSimulador()">Salir</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>