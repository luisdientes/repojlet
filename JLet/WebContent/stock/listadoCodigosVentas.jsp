<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>
<script type="text/javascript" src="stock/js/altaStock.js"/></script>

<%
	
	HttpSession sesion = request.getSession();
	HashMap<String,String> permEmis = new HashMap<String,String>();
	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");


	String idemisor = null;
	Grid gridCodi   = null; 
	Grid gridMarc = null;
	String txmarsel = "";
	String cddivisa = ""; 


	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			cddivisa = io.getStringValue("cddivisa");
			gridCodi = io.getGrid("gridCodi");	
			gridMarc = io.getGrid("gridMarc");
			txmarsel = io.getStringValue("txmarcax");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Listado Recibos Facturas.jsp "+ e.getMessage());	
		}
	}

	
	System.out.println(divsimbol +" divissssssssssssssssssssssssssssssssssssssssssssssssssssssa");
%>

<script>

cddivisa = "<%=cddivisa%>";

if(cddivisa =="&euro;"){
	cddivisa="EUR";
}
	
	

function abrirFactura(namefile){
	document.abriFactu.filename.value = namefile;
	document.abriFactu.submit();
}

function abrirFactura(namefile){
		document.abriFactu.filename.value = namefile;
		document.abriFactu.submit();
	}
	
	function generaRecibo(){
		
		totalPend = document.formaltaRecibo.totalpen.value;
		totalIngr = document.formaltaRecibo.cantidad.value;
		totalFact = document.formaltaRecibo.totalfac.value;
		totalIngr = totalIngr.replace(",","");
		

		if(totalIngr == "" || totalIngr == 0 || isNaN(totalIngr)){
			alert("El importe introducido no es valido" );
		}else{
			
			totalPend = parseFloat(totalPend);
			totalIngr = parseFloat(totalIngr);
			totalFact = parseFloat(totalFact);
			
			if(totalIngr > totalPend){
				alert("El importe pendiente es inferior al importe a ingresar");
			}else{
				
				
				totalPend = totalPend - totalIngr;
				
				if(totalPend == 0){
					document.formaltaRecibo.marcapag.value = "S";
				}
				
				document.formaltaRecibo.cantidad.value = totalIngr;
				
				if(validaFecha()){
					document.formaltaRecibo.submit();
				}else{
					alert("Fecha no valida");
				}
	
			}	
		}
	}
	
	 function validaFecha(){
		 if(!ver_fecha(document.getElementById("fecharec").value) || !fechaCorrecta(document.getElementById("fecharec").value)){
			 return false;
		 } else {
			 return true;
		 }	  
	 }
	 
	 function valida(idemisor){
		 
		 var cadena ="";
		 codprodu = document.getElementById("codprodu").value;
		 txmarcax = document.getElementById("txmarcax").value;
		 txdescri = document.getElementById("txdescri").value;
		 impdefve = document.getElementById("impdefve").value;
		 cantidad = document.getElementById("cantidad").value;
		 tpproduc = document.getElementById("tpproduc").value;
		 
		 if(codprodu == ""){
			 cadena+="<p>El codigo de producto no puede estar vacio.</p>";
		 }
		 
		 if(impdefve == "" || isNaN(impdefve)){
			 cadena+="<p>El precio no es correcto.</p>";
		 }
		 
		 if (cadena != ""){
			 alertify.error(cadena); 
		 } else{
		 
		 	altacodprodu(idemisor,codprodu, txmarcax, txdescri,impdefve, cantidad,tpproduc,cddivisa);
		 	alertify.success("C&oacute;digo"+codprodu+" dado de alta con la descripci&oacute;n"+txdescri);
		 }
	 }
	 
function busca(){
		 txdescri = document.getElementById("txbusque").value;
		 selmarca = document.getElementById("selmarca").value;
		 buscaCodi(<%=idemisor%>, txdescri,selmarca );
	 }
	 
function listadoProd(idemisor){

	ventana = window.open("/JLet/App?controller=StockHttpHandler&services=ListCodVentasSrv&view=factura/AyudaProdFacturas.jsp&idemisor="+ idemisor,'','toolbar=no,titlebar=no,menubar=no,scrollbars=yes,resizable=yes,maximize=yes,width=950,height=600');

}
	
</script>

	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
		<input type="hidden" name="idusuari" value="1"/>
		<input type="hidden" name="tipofile" value="FRA"/>
		<input type="hidden" name="pathfile" value=""/>
		<input type="hidden" name="filename" value=""/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
	</form>
	
	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value=""/>
		<input type="hidden" name="view" value=""/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
		<input type="hidden" name="aniofact" value=""/>
		<input type="hidden" name="tpclient" value=""/>
		<input type="hidden" name="idcliere" value=""/>
		<input type="hidden" name="fhdesdex" value=""/>
		<input type="hidden" name="fhhastax" value=""/>
		<input type="hidden" name="predesde" value=""/>
		<input type="hidden" name="prehasta" value=""/>
		<input type="hidden" name="idfactur" value=""/>
		<input type="hidden" name="mcpagado" value=""/>
	</form>
	
	<form method="POST" name="formRegenera" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value="RegeneraReciboSrv"/>
		<input type="hidden" name="view" 		value="factura/abrirFactura.jsp"/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>">
		<input type="hidden" name="idrecibo" value="">
		<input type="hidden" name="idfactur" value="">

	</form>
	
	
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	

<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="nompanta" >Alta códigos venta</div>
	<div class="centradoFac" width="100%">
	<br>
		<table border="0" width="60%" align="center" border="1">
						<tr>
							<td class="input-b1">Cod. producto</td>
							<td align="left">
								<input class="input-m" type="text" value="" id="codprodu" name="nameinve" />
								<a onclick="listadoProd('<%=idemisor%>')" class="boton" style="font-size:9px;font-weight:900;font-family:Arial;min-width:25px;border-radius:100%;min-height:20px;">+</a>					
							</td>	
						</tr>
						
						<tr>
							<td class="input-b1">Marca</td>                                      				        
							<td align="left"><input class="input-m" type="text" id="txmarcax" name="txmarcax"/></td>
						</tr>
						
						<tr>
							<td class="input-b1">Descripción</td>                                      				        
							<td align="left" ><input style="width:100%" class="input-m" type="text" id="txdescri" name="txdescri"/></td>
						</tr>
						<tr>
							<td class="input-b1">Tipo de producto:</td>                                      				        
							<td align="left" >
								<select class="input-m2" id="tpproduc">
									
									<option value="PH">-- Celular --</option>
									<option value="EL">-- Electrodomestico --</option> 
									<option value="LA">-- Laptop --</option> 
									<option value="PI">-- Pieza --</option> 
									<option value="TA">-- Tablet --</option> 
									<option value="OT">-- Otros --</option> 
								</select>
						</td>
						</tr>
						<tr>
							<td class="input-b1">Precio</td>                                      				        
							<td align="left"><input class="input-m" type="text" id="impdefve" name="impdefve"/></td>
						</tr>
						<tr>
							<td class="input-b1">Cantidad</td>                                      				        
							<td align="left"><input class="input-m" type="text" id="cantidad" name="cantidad" value="1"/></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>                                     				        
							<td align="center" colspan=2><a class="boton" onclick="valida(<%=idemisor%>)">Aceptar</a></td>
						</tr>
						
					</table>

	</div>
		 
		 <br>
		 <br>

	</div>	

	
	

