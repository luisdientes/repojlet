<%@ include file="/common/jsp/include.jsp"%>
<link rel="stylesheet" href="common/css/calendario.css" type="text/css"/>
<script type="text/javascript" src="common/js/calendar.js"></script>
<script type="text/javascript" src="common/js/calendar-es.js"/></script>
<script type="text/javascript" src="common/js/calendar-setup.js"/></script>

<style>
    .europa{
      background-image: url(/JLet/common/img/icons/UE.png);
      height: 32px;
      width: 100%;
      background-repeat: no-repeat;
    }
    .america{
      background-image: url(/JLet/common/img/icons/US.png);
      height: 32px;
      width: 100%;
      background-repeat: no-repeat;
    }
    .reinounido{
      background-image: url(/JLet/common/img/icons/UK.png);
      height: 32px;
      width: 100%;
      background-repeat: no-repeat;
    }
</style>

<%
	String idemisor = null; 
	String benefici = "";
	String porcbene = "";
	String rzsocial = "";
	Grid   gridProd = null;
	DecimalFormat formateador = new DecimalFormat("###,##0.00");

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			rzsocial = io.getStringValue("rzsocial");
			gridProd = io.getGrid("gridProd");
			benefici = io.getStringValue("benefici");
			porcbene = io.getStringValue("porcbene");
			
		} catch (Exception e) {
		System.err.println("ERROR - Recibiendo los Emisor en upgrade/selecimei.jsp "+ e.getMessage());	
	}
}
	
	
	String codprodu = "";
	String imeicode = "";
	String txmarcax = "";
	String txmodelo = "";
	String tpproduc = "";
	String withboxx = "";
	String withusbx = "";
	String withchar = "";
	String withheph = "";
	String idgrupox = "";
	String rutaimag = "";
	String imagewpt = "";
	String idcatego = "";
	String pricecmp = "";
	String txtcateg = "";
	String cdestado = "";
	String idfactur = "";
	String idclient = "";
	String tpclient = "";
	String tipocone = "";
	String fhcompra = "";
	//String pricecmp = "";
	String diviscmp = "";
	String prusdcmp = "";
	String pricevnt = "";
	String divisvnt = "";
	String prusdvnt = "";
	String txprovid = "";
	String txbuyerx = "";
	String txfundin = "";
	String filecrea = "";
	String cdfactur = "";
	String mcpagado = "";
	String pagadoxx = "";
	
	
	
	String withboxxI = "";
	String withusbxI = "";
	String withcharI = "";
	String withhephI = "";



%>


<script>

var withboxx;
var withusbx;
var withchar;
var withheph;
var tipocone;

function listStock(estado){
	
	if(estado =="VENDIDO"){
		document.formMenu.view.value	 = "stock/listadoVendido.jsp";
	}else{
		document.formMenu.view.value	 = "stock/listadoStock.jsp";
	}
	
	document.formMenu.services.value = "ListStockSrv";
	
	document.formMenu.cdestado.value = estado;
	document.formMenu.submit();
}

function abrirFactura(namefile){
	document.abriFactu.filename.value = namefile;
	document.abriFactu.submit();
}

function abrirCliente(idcli,tpclient){
	document.abriClie.idclient.value = idcli;
	document.abriClie.tpclient.value = tpclient;
	document.abriClie.view.value	 = "clientes/altaCliente.jsp";		
	document.abriClie.submit();
}

function modifica(mod){
	
	if(mod == "S"){
		document.getElementById("modifica").style.display = "block";
		document.getElementById("modacces").style.display = "block";
		document.getElementById("verdetal").style.display = "none";
		document.getElementById("vistaacc").style.display = "none";
		document.getElementById("acepta").style.display = "none";
		document.getElementById("btnmod").style.display = "";
	}else{
		document.getElementById("modifica").style.display = "none";
		document.getElementById("modacces").style.display = "none";
		document.getElementById("verdetal").style.display = "block";
		document.getElementById("vistaacc").style.display = "block";
		document.getElementById("acepta").style.display = "";
		document.getElementById("btnmod").style.display = "none";
		
	}
	
	
	
}

function cambiaAccesorio(tipoAcc,estado){
	
	if(tipoAcc == "withchar"){
		if(withchar =="N"){
			document.getElementById("cargador").src ="/JLet/common/img/icons/check.png";
			withchar = "S";
		}else{
			document.getElementById("cargador").src ="/JLet/common/img/icons/cancel.png";
			withchar = "N";
		}
	}
	
	if(tipoAcc == "withboxx"){
		if(withboxx =="N"){
			document.getElementById("cajaxxxx").src ="/JLet/common/img/icons/check.png";
			withboxx = "S";
		}else{
			document.getElementById("cajaxxxx").src ="/JLet/common/img/icons/cancel.png";
			withboxx = "N";
		}
	
	}
	if(tipoAcc == "withusbx"){
		if(withusbx =="N"){
			document.getElementById("conusbxx").src ="/JLet/common/img/icons/check.png";
			withusbx = "S";
		}else{
			document.getElementById("conusbxx").src ="/JLet/common/img/icons/cancel.png";
			withusbx = "N";
		}
	}	
	
	if(tipoAcc == "withheph"){
		if(withheph =="N"){
			document.getElementById("conauric").src ="/JLet/common/img/icons/check.png";
			withheph ="S";
		}else{
			document.getElementById("conauric").src ="/JLet/common/img/icons/cancel.png";
			withheph ="N";
		}
	}
	
	if(tipoAcc == "conector"){
		if(estado =="N"){
			document.getElementById("cajaxxxx").src ="/JLet/common/img/icons/check.png";
		}else{
			document.getElementById("cajaxxxx").src ="/JLet/common/img/icons/cancel.png";
		}
	}	
}

function conector(tipoconet){
	document.getElementById("cajaxxxx").src ="/JLet/common/img/icons/"+tipoconet+".png";
}

function enviadatos(){
	
	var cadena = "";
	fhcompra = document.getElementById("fhcompra").value;
	diviscmp = document.getElementById("diviscmp").value;
	pricecmp = document.getElementById("pricecmp").value;
	prusdcmp = document.getElementById("prusdcmp").value;
	prusdvnt = document.getElementById("prusdvnt").value;
	pricevnt = document.getElementById("pricevnt").value;
	divisvnt = document.getElementById("divisvnt").value;
	prusdvnt = document.getElementById("prusdvnt").value;
	//txprovid = document.getElementById("txprovid").value;
	//txbuyerx = document.getElementById("txbuyerx").value;
	//txfundin = document.getElementById("txfundin").value;
	tipocone = document.getElementById("tipocone").value;
	
	
	if(pricecmp == ""){
		cadena += "<p>Debes rellenar el precio de compra</p>";
	}
	if(isNaN(pricecmp)){
		cadena += "<p>El precio de compra debe ser númerico</p>";
	}
	
	if(prusdcmp == ""){
		cadena += "<p>Debes rellenar el precio de compra en USD</p>";
	}
	if(isNaN(prusdcmp)){
		cadena += "<p>El precio de compra en USD debe ser númerico</p>";
	}
	
	if(pricevnt == ""){
		cadena += "<p>Debes rellenar el precio de venta</p>";
	}
	if(isNaN(pricevnt)){
		cadena += "<p>El precio de venta debe ser númerico</p>";
	}
	
	if(prusdvnt == ""){
		cadena += "<p>Debes rellenar el precio de venta EN USD</p>";
	}
	if(isNaN(prusdvnt)){
		cadena += "<p>El precio de venta en USD debe ser númerico</p>";
	}
	
	
	
	
	document.formAltaProd.fhcompra.value = fhcompra;
	document.formAltaProd.diviscmp.value = diviscmp;
	document.formAltaProd.pricecmp.value = pricecmp;
	document.formAltaProd.prusdcmp.value = prusdcmp;
	document.formAltaProd.prusdvnt.value = prusdvnt;
	document.formAltaProd.pricevnt.value = pricevnt;
	document.formAltaProd.divisvnt.value = divisvnt;
	document.formAltaProd.prusdvnt.value = prusdvnt;
	/*document.formAltaProd.txprovid.value = txprovid;
	document.formAltaProd.txbuyerx.value = txbuyerx;
	document.formAltaProd.txfundin.value = txfundin;*/
	document.formAltaProd.withboxx.value = withboxx;
	document.formAltaProd.withusbx.value = withusbx;
	document.formAltaProd.withheph.value = withheph;
	document.formAltaProd.withchar.value = withchar;
	document.formAltaProd.tipocone.value = tipocone;
	
	if(cadena != ""){
		alertify.error(cadena, function () {});
	}else{
		
		alertify.confirm("<p>&iquest;Esta seguro que desea modificar el producto ?</p> ", function (e) {
			if (e) {
					document.formAltaProd.submit();
			} 
		}); return false
		
	}
}

function calendarSetup(){
	
	Calendar.setup({ 
		inputField : "fhcompra",    // id del campo de texto 
		ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
		button     : "lanzador"     // el id del botón que lanzará el calendario 
	});
}

function altaCostes(idunicox){
	
	document.formMenu.controller.value = "DesgCostesHttpHandler";
	document.formMenu.services.value 	= "InitAltaDesgloseSrv";
	document.formMenu.view.value 		= "desgcostes/altaDesglose.jsp";
	document.formMenu.idunicox.value 	= idunicox;
			
	document.formMenu.submit();
		
}

</script>

<body onload="calendarSetup()">

   <div class="fondo2">
		<table width="100%" align="center" style="margin-left:13%">
			<tr>
				<td width="20%"><img width="50px" src="/JLet/common/img/icons/title-up-tablet.png"></td>
				<td width="60%" align="center"><img title="Usuario" src="<%=pathimgx%>icons/emisores/logo_<%=pre_idemisor%>.png"></td>
				<td width="20%">
				 	<div class="nompanta" >Detalle de Producto</div>
				</td>						
			</tr>
		</table>
	</div>

<div class="fondo">
	
			<div style="width:100%">
			
				<% if(gridProd != null && gridProd.rowCount() > 0){
						cdestado = gridProd.getStringCell(0,"cdestado");
						idfactur = gridProd.getStringCell(0,"idfactur");
						imeicode = gridProd.getStringCell(0,"imeicode");
						codprodu = gridProd.getStringCell(0,"codprodu");
						txmarcax = gridProd.getStringCell(0,"txmarcax");
						txmodelo = gridProd.getStringCell(0,"txmodelo");
						imagewpt = gridProd.getStringCell(0,"imagenxx");
						tpproduc = gridProd.getStringCell(0,"tpproduc");
						idcatego = gridProd.getStringCell(0,"idcatego");
						idclient = gridProd.getStringCell(0,"idclient");
						tpclient = gridProd.getStringCell(0,"tpclient");
						
						
						fhcompra = gridProd.getStringCell(0,"fhcompra");
						diviscmp = gridProd.getStringCell(0,"diviscmp");
						pricecmp = gridProd.getStringCell(0,"pricecmp");
						prusdcmp = gridProd.getStringCell(0,"prusdcmp");
						pricevnt = gridProd.getStringCell(0,"pricevnt");
						divisvnt = gridProd.getStringCell(0,"divisvnt");
						prusdvnt = gridProd.getStringCell(0,"prusdvnt");
						txprovid = gridProd.getStringCell(0,"txprovid");
						txbuyerx = gridProd.getStringCell(0,"txbuyerx");
						txfundin = gridProd.getStringCell(0,"txfundin");
						
						withboxx = gridProd.getStringCell(0,"withboxx");
						withusbx = gridProd.getStringCell(0,"withusbx");
						withheph = gridProd.getStringCell(0,"withheph");
						withchar = gridProd.getStringCell(0,"withchar");
						tipocone = gridProd.getStringCell(0,"tipocone");
						filecrea = gridProd.getStringCell(0,"filecrea");
						mcpagado = gridProd.getStringCell(0,"mcpagado");
						cdfactur = gridProd.getStringCell(0,"cdfactur");
						
						
						pricecmp = gridProd.getStringCell(0,"pricecmp");
						prusdcmp = gridProd.getStringCell(0,"prusdcmp");
						pricevnt = gridProd.getStringCell(0,"pricevnt");
						
						if (pricecmp.equals("")) {
							pricecmp ="0.0";
						}
						
						if (prusdcmp.equals("")) {
							prusdcmp ="0.0";
						}
						
						if (pricevnt.equals("")) {
							pricevnt ="0.0";
						}
						if (prusdvnt.equals("")) {
							prusdvnt ="0.0";
						}
						
				    	if(codprodu.substring(0,2).equals("PI")){
				    		rutaimag ="http://mallproshop.com/images/pieces/";
				    	}else{
				    		rutaimag ="http://mallproshop.com/";
				    	}
				    	
				    	if (withboxx.equals("N")) {
	  						withboxxI = "-";
	  						withboxxI="/JLet/common/img/icons/cancel.png";
	  					}else{
	  						
	  						withboxxI="/JLet/common/img/icons/check.png";
	  					}
	  					
	  					if (withusbx.equals("N")) {
	  						withusbxI = "-";
	  						withusbxI ="/JLet/common/img/icons/cancel.png";
	  					}else{
	  						withusbxI="/JLet/common/img/icons/check.png";
	  					}
	  					
	  					if (withheph.equals("N")) {
	  						withhephI = "-";
	  						withhephI ="/JLet/common/img/icons/cancel.png";
	  					}else{
	  						withhephI="/JLet/common/img/icons/check.png";
	  					}
	  					
	  					if (withchar.equals("N")) {
	  						withcharI = "-";
	  						withcharI ="/JLet/common/img/icons/cancel.png";
	  					}else{
	  						withcharI="/JLet/common/img/icons/check.png";
	  					}
	  					

	  					if(idcatego.equals("N")){
	  						idcatego  ="Nuevo";
	  					}else if(idcatego.equals("N7")){
	  						idcatego  ="Nuevo 7 dias";
	  					}else if(idcatego.equals("R")){
	  						idcatego  ="Refurbished";
	  					}else if(idcatego.equals("A")){
	  						idcatego  ="Usado Clase A";
	  					}else if(idcatego.equals("B")){
	  						idcatego  ="Usado Clase B";
	  					}else if(idcatego.equals("C")){
	  						idcatego  ="Usado Clase C";
	  					}
	  					
	  					if(mcpagado.equals("S")){
	  						pagadoxx ="( Pagada )";
	  					}else{
	  						pagadoxx ="( Sin pagar )";
	  					}
					
					%>
					
				<table border="0" width="75%" align="center" cellspacing="10" cellpadding="2" >
					<tr>
						<td class="input-b1" width="20%">Estado</td>
						<td class="input-b1" width="20%">Factura</td>
						<td class="input-b1" width="20%">Cliente</td>
						<td class="input-b1" width="20%">Desglose costes</td>
					</tr>
						<tr>
							<td class="fonts6">
								<table  width="100%" align="center">
									<tr>
										<td class="fonts6" style="cursor:pointer" align="center"  width="50%"><%=cdestado %></td>
						
									</tr>
								</table>
							</td>
						<td class="fonts6">
							<table width="100%" align="center">
									<tr align="center">
										<%if(idfactur != null && !idfactur.equals("")){ %>
									
										<td class="fonts6"  style="cursor:pointer" onclick="abrirFactura('<%=filecrea %>');" align="center"><%=cdfactur %>&nbsp;<%=pagadoxx%></td>
										<% }else{%>
										<td class="fonts6"  style="cursor:pointer" align="center">-</td>	
											
									<% }%>
									</tr>
							</table>
						</td>
						<td class="fonts6">
							<table width="100%" align="center">
									<tr align="center">
										<%if(idclient != null && !idclient.equals("") && !idclient.equals("0")){ %>
									
										<td class="fonts6"  style="cursor:pointer" align="center" onclick="abrirCliente('<%=idclient%>','<%=tpclient%>')"><%=rzsocial%></td>
										<% }else{%>
										<td class="fonts6"  style="cursor:pointer" align="center">-</td>	
											
									<% }%>
									</tr>
							</table>
						</td>
						<td class="fonts6">
							<table width="100%" align="center">
									<tr align="center">
										<td class="fonts6"  style="cursor:pointer" align="center" onclick="altaCostes('<%=imeicode%>')"><img src="/JLet/common/img/icons/altacoste.png" width="16px" height="16px"  style="cursor:pointer"/></td>
									</tr>
							</table>
						</td>
					</tr>
				</table>
				<table border="0" width="75%" align="center" cellspacing="10" cellpadding="2">
					<tr>
						<td>
							<table width="100%">
								<tr>
								<td colspan=2 class="fonts6" align="center" style="line-height:1.7"><%=txmarcax %>&nbsp;<%=txmodelo %></td>
								</tr>
								<tr>
								
									<td><img src="<%=rutaimag%><%=imagewpt%>" style="max-width:400px;max-height:400px"></td>
									<td width="60%">
										<table width="100%">	
											<tr>
												<td width="50%" class="input-b1" width=50%>Imei: </td>
												<td width="50%" class="fonts6" style="width:50%"><%=imeicode%></td>
											</tr>
											<tr>
												<td width="50%" class="input-b1" width=50%>Marca: </td>
												<td width="50%" class="fonts6" style="width:50%" align="center"><%=txmarcax%></td>
											</tr>
											<tr>
												<td width="50%" class="input-b1" width=50%>Cod. Producto: </td>
												<td width="50%" class="fonts6" align="center"><%=codprodu%></td>
											</tr>
											<tr>
												<td width="50%" class="input-b1" width=50%>Clase: </td>
												<td width="50%" class="fonts6" align="center"><%=idcatego%></td>
											</tr>
										 </table>
									</td>
								</tr>
								<tr>
									<td colspan=2 >
										<div id="vistaacc">
											<table width="100%">
											<tr>
												<td width=20% align="center" class="fonts6">Cargador</td>
												<td width=20% align="center" class="fonts6">Caja</td>
												<td width=20% align="center" class="fonts6">Usb</td>
												<td width=20% align="center" class="fonts6">Conector</td>
												<td width=20% align="center" class="fonts6">Auriculares</td>
											</tr>
											<tr>
												<td width=20% align="center"><img src="<%=withcharI %>" style="width:32px"></td>
												<td width=20% align="center"><img src="<%=withboxxI %>" style="width:32px"></td>
												<td width=20% align="center"><img src="<%=withusbxI %>" style="width:32px"></td>
												<td width=20% align="center"><%
												if(!tipocone.equals("")){ %>
													<img src="/JLet/common/img/icons/<%=tipocone %>.png" style="width:32px">
												<%}else{%>
													--
												<% }%>
											</td>
												<td width=20% align="center"><img src=" <%=withhephI %>" style="width:32px"></td>
											</tr>
											</table>
										</div>
										
										<div id="modacces" style="display:none">
											<table width="100%">
											<tr>
												<td width=20% align="center" class="fonts6">Cargador</td>
												<td width=20% align="center" class="fonts6">Caja</td>
												<td width=20% align="center" class="fonts6">Usb</td>
												<td width=20% align="center" class="fonts6">Conector</td>
												<td width=20% align="center" class="fonts6">Auriculares</td>
											</tr>
											<tr>
												<td width=20% align="center" style="cursor:pointer" onclick="cambiaAccesorio('withchar')"><img id="cargador" src="<%=withcharI %>" style="width:32px"></td>
												<td width=20% align="center" style="cursor:pointer" onclick="cambiaAccesorio('withboxx')"><img id="cajaxxxx" src="<%=withboxxI %>" style="width:32px"></td>
												<td width=20% align="center" style="cursor:pointer" onclick="cambiaAccesorio('withusbx')"><img id="conusbxx" src="<%=withusbxI %>" style="width:32px"></td>
												<td width=20% align="center" style="cursor:pointer" onclick="cambiaAccesorio('tipocone')">
													<select id="tipocone" style="width:100%">
      													<option class="europa" value="UE" <% if(tipocone.equals("UE")) {%>selected<%} %>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;EU</option>
     													 <option class="america" value="US" <% if(tipocone.equals("US")) {%>selected<%} %>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;US</option>
      													<option class="reinounido" value="UK" <% if(tipocone.equals("UK")) {%>selected<%} %>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;UK</option>
													</select>
												</td>
												
											<td width=20% align="center" style="cursor:pointer" onclick="cambiaAccesorio('withheph')"><img id="conauric" src="<%=withhephI %>"style="width:32px"></td>
											</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
						<td width="40%">
						<div id="verdetal">
							<table width=100% align="center">
								<tr>
									<td class="input-b1" width=50%>Fecha compra</td>
									<td class="fonts6"><%=fhcompra %></td>
							
								</tr>
						
								<tr>
									<td class="input-b1" width=50%>Precio compra</td>
									<td class="fonts6"><%=formateador.format(Double.parseDouble(pricecmp)) %></td>
								</tr>
								<tr>
									<td class="input-b1" width=50%>Divisa Compra</td>
									<td class="fonts6"><%=diviscmp %></td>
								</tr>
								<tr>
									<td class="input-b1" width=50%>Precio USD compra</td>
									<td class="fonts6"><%=formateador.format(Double.parseDouble(prusdcmp)) %></td>
								</tr>
								<tr>
									<td class="input-b1" width=50%>Precio venta</td>
									<td class="fonts6"><%=formateador.format(Double.parseDouble(pricevnt)) %></td>
								</tr>
								<tr>
									<td class="input-b1" width=50%>Divisa venta</td>
									<td class="fonts6"><%=divisvnt %></td>
								</tr>
								<tr>
									<td class="input-b1" width=50%>Precio USD venta</td>
									<td class="fonts6"><%=formateador.format(Double.parseDouble(prusdvnt)) %></td>
								</tr>
								<tr>
									<td class="input-b1" width=50%>Beneficio USD</td>
									<td class="fonts6"><%=benefici %></td>
								</tr>
								<tr>
									<td class="input-b1" width=50%>% Beneficio USD</td>
									<td class="fonts6"><%=porcbene %></td>
								</tr>
								
								<tr>
									</tr>
							</table>
							</div>
							
							<!-- formulario para modificar -->
							<div style="display:none" id="modifica">
								<table width=100% align="center">
									<tr>
										<td class="input-b1" width=50%>Fecha compra</td>
										<td><input class="input-m" type="text" value="<%=fhcompra %>" id="fhcompra"><img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:6px" width="24" height="24" border="0"  id="lanzador"></td>
									</tr>
									<tr>
										<td class="input-b1" width=50%>Precio compra</td>
										<td ><input class="input-m" type="text" value="<%=pricecmp %>" id="pricecmp"></td>
									</tr>
									<tr>
										<td class="input-b1" width=50%>Divisa Compra</td>
										<td>
											<select class="input-m" style="width:80%" id="diviscmp">
												<option value="RD$" <% if(diviscmp.equals("RD$")) {%>selected<%} %>>RD$</option>
												<option value="&euro;" <% if(diviscmp.equals("&euro;")) {%>selected<%} %>>&euro;</option>
												<option value="CHF" <% if(diviscmp.equals("CHF")) {%>selected<%} %>>CHF</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="input-b1" width=50%>Precio USD compra</td>
										<td ><input class="input-m" type="text" value="<%=prusdcmp %>" id="prusdcmp"></td>
									</tr>
									<tr>
										<td class="input-b1" width=50%>Precio venta</td>
										<td><input class="input-m" type="text" value="<%=pricevnt %>" id="pricevnt"></td>
									</tr>
									<tr>
										<td class="input-b1" width=50%>Divisa venta</td>
										<td >
										   <select class="input-m" style="width:80%" id="divisvnt">
											<option value="RD$" 	<% if(divisvnt.equals("RD$")) {%>selected<%} %>>RD$</option>
											<option value="&euro;"  <% if(divisvnt.equals("&euro;")) {%>selected<%} %>>&euro;</option>
											<option value="CHF" 	<% if(divisvnt.equals("CHF")) {%>selected<%} %>>CHF</option>
										  </select>
										</td>
									</tr>
									<tr>
										<td class="input-b1" width=50%>Precio USD venta</td>
										<td ><input class="input-m" type="text" value="<%=prusdvnt %>" id="prusdvnt"></td>
									</tr>
									<tr>
										</tr>
								</table>
							</div>
							<!-- Formulario para modificar -->
						</td>
					</tr>
					</table>

	<br>
    <br>
    <br>
					<table border="0" width="40%" align="center" id="acepta">
						<tr>
							<td  align="center"><a class="boton" onclick="listStock('<%=cdestado%>')">Aceptar</a></td>
							<td  align="center"><a class="boton" onclick="modifica('S')">Modificar</a></td>							
						</tr>
				
					</table>
					<table border="0" width="40%" align="center" id="btnmod" style="display:none">
						<tr>
							<td  align="center"><a class="boton" onclick="modifica('N')">Cancelar</a></td>
							<td  align="center"><a class="boton" onclick="enviadatos()">Actualizar</a></td>
							
						</tr>
					</table>
					<br>
					<br>
			<%}else{%>
			<table border="0" width="60%" align="center" >
				<tr>
					<td align="center">
						<h3 style="color:#363636">No se ha encontrado el producto</h3>	
					</td>
				</tr>
				<tr>
					<td align="center">
						<a class="boton" onclick="listStock('<%=cdestado%>')">Aceptar</a>
					</td>
				</tr>
			</table>
			<% 	}
			%>
			
			
			<table id="myTable" border="0" width=90% align="center" style="display:none">
        		<tr>
        			<td class="input-b1">Imei</td>
        			<td class="input-b1">Clase</td>
        			<td class="input-b1">Divisa</td>
        			<td class="input-b1">P. compra</td>
        			<td class="input-b1">Cargador</td>
        			<td class="input-b1">Conector</td>
        			<td class="input-b1">USB</td>
        			<td class="input-b1">Caja</td>
        			<td class="input-b1">Auriculares</td>
        		</tr>
    		</table>
    
    <br>
    <br>
    <br>
    
		
	</div>

	<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="StockHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value="<%=pre_idemisor%>"/>
			<input type="hidden" name="tpclient" 	value=""/>
			<input type="hidden" name="cdestado" 	value=""/>
			<input type="hidden" name="tipocons" 	value=""/>
			<input type="hidden" name="cdpantal" 	value=""/>
			<input type="hidden" name="logoemis" 	value=""/>
			<input type="hidden" name="idunicox" 	value=""/>
		</form>
		
	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
		<input type="hidden" name="idusuari" value="1"/>
		<input type="hidden" name="tipofile" value="FRA"/>
		<input type="hidden" name="pathfile" value=""/>
		<input type="hidden" name="filename" value=""/>
		<input type="hidden" name="idemisor" value="<%=pre_idemisor%>"/>
	</form>
	
	<form method="post" name="abriClie" action="/JLet/App" >
		<input type="hidden" name="controller" 	value="ClientesHttpHandler"/>
		<input type="hidden" name="services"	value="ListClientesSrv"/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idclient" 	value=""/>
		<input type="hidden" name="tpclient" 	value=""/>
		<input type="hidden" name="idemisor" 	value="<%=pre_idemisor%>"/>
		<input type="hidden" name="txrazons" 	value=""/>
</form>

<form name="formAltaProd" method="POST" action="">
		<input type="hidden" name="controller" 	value="StockHttpHandler"/>
		<input type="hidden" name="services"	value="UpdateStockSrv"/>
		<input type="hidden" name="view" 		value="stock/resultado.jsp"/>	
		<input type="hidden" name="idemisor"    value="<%=pre_idemisor%>"/>
		<input type="hidden" name="codprodu"    value="<%=codprodu%>"/>
		<input type="hidden" name="txmarcax"    value="<%=txmarcax%>">
		<input type="hidden" name="txmodelo"    value="<%=txmodelo%>">
		<input type="hidden" name="tpclient"    value="<%=tpclient%>">
		<input type="hidden" name="idclient"    value="<%=idclient%>">
		<input type="hidden" name="arrayPro"    value="">
		<input type="hidden" name="tpproduc"    value="TA">
		<input type="hidden" name="fhcompra"    value="">
		<input type="hidden" name="diviscmp"    value="">
		<input type="hidden" name="pricecmp"    value="">
		<input type="hidden" name="prusdcmp"    value="">
		<input type="hidden" name="pricevnt"    value="">
		<input type="hidden" name="divisvnt"    value="">
		<input type="hidden" name="prusdvnt"    value="">
		<input type="hidden" name="txprovid"    value="">
		<input type="hidden" name="txbuyerx"    value="">
		<input type="hidden" name="txfundin"    value="">
		<input type="hidden" name="withboxx"    value="">
		<input type="hidden" name="withusbx"    value="">
		<input type="hidden" name="withchar"    value="">
		<input type="hidden" name="withheph"    value="">
		<input type="hidden" name="tipocone"    value="">
		<input type="hidden" name="cdestado"    value="<%=cdestado%>">
		<input type="hidden" name="imeicode"    value="<%=imeicode%>">
		
	</form>

<script>
	withboxx = "<%=withboxx%>";
	withusbx = "<%=withusbx%>";
	withchar = "<%=withchar%>";
	withheph = "<%=withheph%>";
	tipocone = "<%=tipocone%>";
</script>

		
</body>