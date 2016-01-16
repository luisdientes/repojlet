<%@ include file="/common/jsp/include.jsp"%>
<script type="text/javascript" src="common/js/alertas/lib/alertify.js"/></script>
<link rel="stylesheet" href="common/js/alertas/themes/alertify.core.css" />
<link rel="stylesheet" href="common/js/alertas/themes/alertify.default.css" />
<link rel="stylesheet" href="common/css/calendario.css" type="text/css"/>
<script type="text/javascript" src="common/js/calendar.js"></script>
<script type="text/javascript" src="common/js/calendar-es.js"/></script>
<script type="text/javascript" src="common/js/calendar-setup.js"/></script>
<script type="text/javascript" src="stock/js/altaStock.js"/></script>


<%
	String idemisor = null; 
	String claseant = null;
	String imeiintr = null;
	String horacomi = null;
	String costpiez = null;
	String cuantasp = null;
	String tiepmtra = null;
	String tpclient = null;
	String logoemis = null;
	String costsoft = null;
	String costlimp = null;
	String manoobra = null;
	String hardrese = null;
	String divisaxx = null;
	String idcolorx = null;
	String idgralta = "0";
	double costminu = 1.00;
	Grid gridLine = null;
	Grid gridProd = null;
	Grid gridColo = null;

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			claseant = io.getStringValue("claseant");
			imeiintr = io.getStringValue("imeicode");
			horacomi = io.getStringValue("horacomi");
			tpclient = io.getStringValue("tpclient");
			idgralta = io.getStringValue("idgralta");
		
			gridLine = io.getGrid("gridLine");
			gridProd = io.getGrid("gridProd");
			gridColo = io.getGrid("gridColo");
			
			divisaxx = VariablesStatic.getDivisa(idemisor);
			System.out.println("Divas "+divisaxx);
			
			
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

	String cdimeixx = "";
	String diviscmp = "";
	String tipocone = "";

	
	
	if(idemisor.equals("1")){
		logoemis = idemisor+"_"+tpclient;
	}else{
		logoemis = idemisor;
	}

%>


<script>



	var arrayPro = new Array();
	var arrImeix = new Array();
	var cdimeixx = "";
	var pricecmp = "";
	var divisaxx = "";
	var proveedo = "";
	var clasexxx = "A";
	var cargador = "N";
	var enchufex = "UE";
	var usbxxxxx = "N";
	var cajaxxxx = "N";
	var cascosxx = "N";
	var numcelda = 0;
	var idemisor = "<%=idemisor%>";
	var idgralta = "<%=idgralta%>";
	var tpproduc = "";

function selectPro(){
		document.formMenu.services.value = "SelecProducSrv";
		document.formMenu.view.value	 = "stock/selecProduc.jsp";
		document.formMenu.submit();
	}
	

function agregaArray (){
	
	var cadena = "";
	cdimeixx = document.getElementById("cdimeixx").value;
	pricecmp = document.getElementById("pricecmp").value;
	divisaxx = document.getElementById("divisaxx").value;
	pricevnt = document.getElementById("pricevnt").value;
	divisvnt = document.getElementById("divisvnt").value;
	tpproduc = document.getElementById("tpproduc").value;
	proveedo = document.getElementById("proveedo").value;
	fechacmp = document.getElementById("fechacmp").value;
	idcolorx = document.getElementById("idcolorx").value;
	
	
	
	cdimeixx  = cdimeixx.trim();
	pricevnt = pricevnt.replace(",",".");
	pricecmp = pricecmp.replace(",",".");
	
	if(cdimeixx == "" || cdimeixx.length < 15){
		cadena = "<p>Debes rellenar el IMEI correctamente</p>";
	}
	
	if(idcolorx == "OTRO"){
		idcolorx = document.getElementById("txtcolor").value;
	}
	
	
	if(pricecmp == ""){
		cadena += "<p>Debes rellenar el precio de compra</p>";
	}
	if(isNaN(pricecmp)){
		cadena += "<p>El precio de compra debe ser númerico</p>";
	}
	
	if(pricevnt == ""){
		cadena += "<p>Debes rellenar el precio de venta</p>";
	}
	if(isNaN(pricevnt)){
		cadena += "<p>El precio de venta debe ser númerico</p>";
	}
	
	
	
	if(proveedo == ""){
		cadena += "<p>Debes rellenar el proveedor</p>";
	}
	
	 if(!ver_fecha(document.getElementById("fechacmp").value) || !fechaCorrecta(document.getElementById("fechacmp").value)){
		 cadena += "<p>Fecha de compra no válida</p>";
	 } 
	
 if (cadena == ""){	
	 
	 codprodu = document.formAltaProd.codprodu.value;
	 tipoalta = document.formAltaProd.tipoalta.value;
	
	
		if(arrImeix.length == 0){
			arrayPro.push(cdimeixx+"@"+pricecmp+"@"+divisaxx+"@"+proveedo+"@"+clasexxx+"@"+cargador+"@"+enchufex+"@"+usbxxxxx+"@"+cajaxxxx+"@"+cascosxx);
			arrImeix.push(cdimeixx);
			//creceTabla();
			alertify.success("IMEI "+cdimeixx+" agregado correctamente");
			ajaxAltaProd(idemisor,tipoalta,idgralta,fechacmp,codprodu,tpproduc,cdimeixx,fechacmp,pricecmp,divisaxx,divisvnt,pricevnt,proveedo,clasexxx,cargador,enchufex,usbxxxxx,cajaxxxx,cascosxx,idcolorx)
		}else{
		
			if(arrImeix.indexOf(cdimeixx)==-1){
				arrayPro.push(cdimeixx+"@"+pricecmp+"@"+divisaxx+"@"+proveedo+"@"+clasexxx+"@"+cargador+"@"+enchufex+"@"+usbxxxxx+"@"+cajaxxxx+"@"+cascosxx);
				arrImeix.push(cdimeixx);
				alertify.success("IMEI "+cdimeixx+" agregado correctamente");
				ajaxAltaProd(idemisor,tipoalta,idgralta,fechacmp,codprodu,tpproduc,cdimeixx,fechacmp,pricecmp,divisaxx,divisvnt,pricevnt,proveedo,clasexxx,cargador,enchufex,usbxxxxx,cajaxxxx,cascosxx,idcolorx)
				//creceTabla();
			} else{
				alertify.error("El IMEI "+cdimeixx+" ya ha sido introducido");
			}
		}
 }else{
	 alertify.error(cadena, function () {});
	
 }
 document.getElementById("cdimeixx").value = "";	
 document.getElementById("cdimeixx").focus();
}


function borraLineaProd (cdimeixx){
	
	tipoalta = "BORRAR";
	idemisor = <%=idemisor%>
	codprodu = document.formAltaProd.codprodu.value;
	AjaxBorraLineaProd(idemisor, cdimeixx, codprodu, tipoalta);
	
}


function selecClase(seleclas){
	
	
	if(seleclas == "A"){
		document.getElementById("clasexxa").className = "fonts6v2";
		document.getElementById("clasexxb").className = "fonts6";
		document.getElementById("clasexxc").className = "fonts6";
		document.getElementById("clasexxr").className = "fonts6";
	}
	if(seleclas == "B"){
		document.getElementById("clasexxa").className = "fonts6";
		document.getElementById("clasexxb").className = "fonts6v2";
		document.getElementById("clasexxc").className = "fonts6";
		document.getElementById("clasexxr").className = "fonts6";
	}
	if(seleclas == "C"){
		document.getElementById("clasexxa").className = "fonts6";
		document.getElementById("clasexxb").className = "fonts6";
		document.getElementById("clasexxc").className = "fonts6v2";
		document.getElementById("clasexxr").className = "fonts6";
	}
	if(seleclas == "R"){
		document.getElementById("clasexxa").className = "fonts6";
		document.getElementById("clasexxb").className = "fonts6";
		document.getElementById("clasexxc").className = "fonts6";
		document.getElementById("clasexxr").className = "fonts6v2";
	}
	
	clasexxx = seleclas;
	
}

function cargaArray(){
	
	<%
	if ((gridProd != null) && (gridProd.rowCount() > 0)) { 
			
			for (int i = 0; i < gridProd.rowCount(); i++){
				cdimeixx = gridProd.getStringCell(i,"cdimeixx");
			%>
	
	arrImeix.push("<%=cdimeixx%>");
	<%}
	}
	%>
}



function selecUsb(selecusb){
	
	
	if(selecusb == "S"){
		document.getElementById("usbxxxsi").className = "fonts6v2";
		document.getElementById("usbxxxno").className = "fonts6";
	}else{
		document.getElementById("usbxxxsi").className = "fonts6";
		document.getElementById("usbxxxno").className = "fonts6v2";
	}
	
	usbxxxxx = selecusb;
	
}
function selecCargador(selecarg){
	
	
	
	if(selecarg == "S"){
		document.getElementById("cargasi").className = "fonts6v2";
		document.getElementById("cargano").className = "fonts6";
	}else{
		document.getElementById("cargasi").className = "fonts6";
		document.getElementById("cargano").className = "fonts6v2";
	}
	
	
	cargador = selecarg
	//document.formMenu.submit();	
}
function selecEnchufe(enchufe){
	
	
	if(enchufe == "UE"){
		document.getElementById("enchufeue").className = "fonts6v2";
		document.getElementById("enchufeuk").className = "fonts6";
		document.getElementById("enchufeus").className = "fonts6";
	}
	if(enchufe == "UK"){
		document.getElementById("enchufeue").className = "fonts6";
		document.getElementById("enchufeuk").className = "fonts6v2";
		document.getElementById("enchufeus").className = "fonts6";
	}
	if(enchufe == "US"){
		document.getElementById("enchufeue").className = "fonts6";
		document.getElementById("enchufeuk").className = "fonts6";
		document.getElementById("enchufeus").className = "fonts6v2";
	}
	
	enchufex = enchufe;
}

function selecCaja(selecaja){
	
	if (selecaja == "S") {
		document.getElementById("cajaxxsi").className = "fonts6v2";
		document.getElementById("cajaxxno").className = "fonts6";
	} else{
		document.getElementById("cajaxxsi").className = "fonts6";
		document.getElementById("cajaxxno").className = "fonts6v2";
	}
	
	cajaxxxx = selecaja;
}

function selecCascos(selecasc){
	
	if (selecasc == "S") {
		document.getElementById("cascoxsi").className = "fonts6v2";
		document.getElementById("cascoxno").className = "fonts6";
	} else{
		document.getElementById("cascoxsi").className = "fonts6";
		document.getElementById("cascoxno").className = "fonts6v2";
	}
	
	cascosxx = selecasc
}


function selecImei(){
	document.getElementById("myTable").style.display = "";
	creceTabla();
	//document.formMenu.submit();	
}


function creceTabla(){
	
	
	
	document.getElementById("myTable").style.display = "";
	
	var table = document.getElementById("myTable");
	
    var row = table.insertRow(1);
    var cell0 = row.insertCell(0);
    var cell1 = row.insertCell(1);
    var cell2 = row.insertCell(2);
    var cell3 = row.insertCell(3);
    var cell4 = row.insertCell(4);
    var cell5 = row.insertCell(5);
    var cell6 = row.insertCell(6);
    var cell7 = row.insertCell(7);
    var cell8 = row.insertCell(8);
    var cell9 = row.insertCell(9);
    numcel = table.rowIndex;
    
    row.className = "rowGris";
    row.setAttribute('id',cdimeixx );
 
// CREO UN ELEMENTO DEL TIPO INPUT CON document.createElement("NOMBRE TAG HTML QUE QUIERO CREAR");

    var divimeix = document.createElement("div");
    var divclase = document.createElement("div");
    var divdivis = document.createElement("div");
    var divprcmp = document.createElement("div");
    var divcarga = document.createElement("div");
    var diviench = document.createElement("div");
    var divusbxx = document.createElement("div");
    var divcajax = document.createElement("div");
    var divcasco = document.createElement("div");
    var divborra = document.createElement("a");
    
    
    
    divimeix.innerHTML = cdimeixx
    divclase.innerHTML = clasexxx
    divdivis.innerHTML = divisaxx
    divprcmp.innerHTML = pricecmp
    divcarga.innerHTML = cargador
    diviench.innerHTML = enchufex
    divusbxx.innerHTML = usbxxxxx
    divcajax.innerHTML = cajaxxxx
    divcasco.innerHTML = cascosxx
    divborra.innerHTML  = "X";
    
    divimeix.setAttribute('style', 'text-align:center');
    divclase.setAttribute('style', 'text-align:center');
    divdivis.setAttribute('style', 'text-align:center');
    divprcmp.setAttribute('style', 'text-align:right');
    divcarga.setAttribute('style', 'text-align:center');
    diviench.setAttribute('style', 'text-align:center');
    divusbxx.setAttribute('style', 'text-align:center');
    divcajax.setAttribute('style', 'text-align:center');
    divcasco.setAttribute('style', 'text-align:center');
    divborra.setAttribute('style', 'text-align:center;font-size:9px;font-weight:900;font-family:Arial;min-width:20px;border-radius:100%;min-height:20px;background-color:#C13030 !important');
    divborra.className = "boton";
    divborra.setAttribute('onclick', 'Eliminar(this,"'+cdimeixx+'")');
    numcelda++;

    
// CON EL METODO appendChild(); LOS AGREGO A LA CELDA QUE QUIERO
 	cell0.style.align= "center";
    cell0.appendChild(divimeix);   
    cell1.appendChild(divclase);
    cell2.appendChild(divdivis);   
    cell3.appendChild(divprcmp);
    cell4.appendChild(divcarga);   
    cell5.appendChild(diviench);
    cell6.appendChild(divusbxx);   
    cell7.appendChild(divcajax);
    cell8.appendChild(divcasco);
    cell9.appendChild(divborra);
    cell9.setAttribute('style', 'background:#ffffff');
    
	
}

function confirmar(){
	
	document.formAltaProd.tipoalta.value="STOCK";
	document.formAltaProd.submit();
}


function enviar(){
	
	
	var arrValores = new Array();
	var numclasea = 0;
	var numclaseb = 0;
	var numclasec = 0;
	var numclaser = 0;
	
	if (arrayPro.length <1) {
		alertify.error("Error no has introducido datos ");
	} else {
		//arrCuantos = arrayPro.split(',');
		
		for(i=0; i< arrayPro.length; i++){
			
			arrValores = arrayPro[i].split("@");
			
			clase = arrValores[4];
			
			if (clase =="A"){
				numclasea++;
			} 
			if (clase =="B"){
				numclaseb++;
			} 
			if (clase =="C"){
				numclasec++;
			} 
			if (clase =="R"){
				numclaser++;
			} 
		}
		
		alertify.confirm("<p>&iquest;Esta seguro que desea agregar al stock los productos.?</p> <p><b>Resumen:</b></p><p>Clase A: "+numclasea+"</p><p>Clase B: "+numclaseb+"</p><p>Clase C: "+numclasec+"</p><p>Refurbished: "+numclaser+"</p><p>&nbsp;</p><p><b>TOTAL : "+arrayPro.length+"</b></p>", function (e) {
			if (e) {
					document.formAltaProd.arrayPro.value = arrayPro;
					document.formAltaProd.submit();
			} 
		}); return false
	}
}

	function compruebaCampos(){
		
		campreci = document.getElementById("pricecmp").value;
		camprove = document.getElementById("proveedo").value;
		campreve = document.getElementById("pricevnt").value;
		
		if(campreci != "" && camprove !="" && campreve !=""){
			return true
		}else{
			return false
		}
		
	}
	
function deleteRow(){
	 document.getElementById("myTable").deleteRow(this);
}

function Eliminar(t, imeicod) {

	
	if(arrImeix.indexOf(imeicod)!=-1){
		
		indice = arrImeix.indexOf(imeicod)
		arrImeix.splice(indice,1);
		arrayPro.splice(indice,1);
        var td = t.parentNode;
        var tr = td.parentNode;
        var table = tr.parentNode;
        table.removeChild(tr);
	}
}

function calendarSetup(){
	
	Calendar.setup({ 
		inputField : "fechacmp",    // id del campo de texto 
		ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
		button     : "lanzador"     // el id del botón que lanzará el calendario 
	});
}

function compColor(idcolor){
	
	if (idcolor == "OTRO"){
		document.getElementById("txtcolor").style.display = "";
	}else{
		document.getElementById("txtcolor").style.display = "none";
	}
}

cargaArray();
</script>

<body onload="calendarSetup();">
	<div class="fondo2">
		<table width="100%" align="center" style="margin-left:13%">
			<tr>
				<td width="20%"><img width="50px" src="/JLet/common/img/icons/title-up-tablet.png"></td>
				<td width="60%" align="center"><img title="Usuario" src="<%=pathimgx%>icons/emisores/logo_<%=pre_idemisor%>.png"></td>
				<td width="20%">
				 	<div class="nompanta" >Alta Stock</div>
				</td>						
			</tr>
		</table>
	</div>

<div style="width:100%">

	<% if (gridLine != null && gridLine.rowCount() > 0){
			codprodu = gridLine.getStringCell(0,"codprodu");
			txmarcax = gridLine.getStringCell(0,"txmarcax");
			txmodelo = gridLine.getStringCell(0,"txmodelo");
			imagewpt = gridLine.getStringCell(0,"imagenxx");
			tpproduc = gridLine.getStringCell(0,"tpproduc");
	
	    	if (codprodu.substring(0,2).equals("PI")) {
	    		rutaimag ="http://mallproshop.com/images/pieces/";
	    	} else {
	    		rutaimag ="http://mallproshop.com/";
	    	}
	%>						
	
		<table border="0" width="75%" align="center" cellspacing="10" cellpadding="2" style="background-color:#FFFFFF;">
			<tr>
				<td class="input-b1" width="20%">Cargador</td>
				<td class="input-b1" width="20%">Conector</td>
				<td class="input-b1" width="20%">Usb</td>
				<td class="input-b1" width="20%">Caja</td>
				<td class="input-b1" width="20%">Cascos</td>
			</tr>
			<tr>
				<td class="fonts6">
					<table  width="100%" align="center">
						<tr>
							<td class="fonts6" style="cursor:pointer" align="center"  width="50%" onclick="selecCargador('S')" id="cargasi">Si</td>
							<td class="fonts6v2" style="cursor:pointer" align="center"  width="50%" onclick="selecCargador('N')" id="cargano">No</td>
						</tr>
					</table>
				</td>
				<td class="fonts6">
					<table width="100%" align="center">
							<tr align="center">
								<td class="fonts6v2"  style="cursor:pointer" align="center" width="33.3%" onclick="selecEnchufe('UE')" id="enchufeue">UE</td>
								<td class="fonts6"  style="cursor:pointer" align="center" width="33.3%" onclick="selecEnchufe('UK')" id="enchufeuk">UK</td>
								<td class="fonts6"  style="cursor:pointer" align="center" width="33.3%" onclick="selecEnchufe('US')" id="enchufeus">US</td>
							</tr>
						</table>
				</td>
				<td class="fonts6">
					<table  width="100%" align="center">
							<tr>
								<td class="fonts6" style="cursor:pointer" align="center"  width="50%" onclick="selecUsb('S')" id="usbxxxsi">Si</td>
								<td class="fonts6v2" style="cursor:pointer" align="center"  width="50%" onclick="selecUsb('N')" id="usbxxxno">No</td>
							</tr>
					</table>
				</td>
				<td class="fonts6">
					<table  width="100%" align="center">
						<tr>
							<td class="fonts6" style="cursor:pointer" align="center"  width="50%" onclick="selecCaja('S')" id="cajaxxsi">Si</td>
							<td class="fonts6v2" style="cursor:pointer" align="center"  width="50%" onclick="selecCaja('N')" id="cajaxxno">No</td>
						</tr>
					</table>
				</td>
				<td class="fonts6">
					<table  width="100%" align="center">
						<tr>
							<td class="fonts6" style="cursor:pointer" align="center"  width="50%" onclick="selecCascos('S')" id="cascoxsi">Si</td>
							<td class="fonts6v2" style="cursor:pointer" align="center"  width="50%" onclick="selecCascos('N')" id="cascoxno">No</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br/>
		<table border="1" width="75%" align="center" cellspacing="10" cellpadding="2" style="margin-left:13%">
			<tr>
				<td colspan="5">
					<table width="100%" border="1">
							<tr>
								<td>
									<img src="<%=rutaimag%><%=imagewpt%>">
								</td>
								<td width="70%">
									<table width="100%">
										<tr>
											<td class="input-b1" width=30%>Marca: </td>
											<td class="fonts6" style="width:70%"><%=txmarcax%></td>
										</tr>
										<tr>
											<td class="input-b1">Modelo: </td>
											<td class="fonts6"><%=txmodelo%></td>
										</tr>
										<tr>
											<td class="input-b1">Cod. Producto: </td>
											<td class="fonts6"><%=codprodu%></td>
										</tr>
									 </table>
								</td>
							</tr>
						</table>
					</td>
					<td>
						
					<table>
						<tr>
							<td class="fonts6v2" style="cursor:pointer" align="center" id="clasexxa" onclick="selecClase('A')">Clase A</td>
						</tr>
						<tr>
							<td class="fonts6" style="cursor:pointer" align="center" id="clasexxb" onclick="selecClase('B')">Clase B</td>
						</tr>
						<tr>
							<td class="fonts6" style="cursor:pointer" align="center" id="clasexxc" onclick="selecClase('C')">Clase C</td>
						</tr>
						<tr>
							<td class="fonts6" style="cursor:pointer" align="center" id="clasexxr" onclick="selecClase('R')">Refurbished</td>
						</tr>
					</table>
					</td>
				</tr>
			<tr>
				<td class="input-b1" nowrap>Fecha compra</td>
				<td><input type="text" class="input-m" maxlength="10" size="10" id="fechacmp"><img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:6px" width="24" height="24" border="0" title="Fecha Factura" id="lanzador"></td>
				<td class="input-b1" nowrap>Precio compra</td>
				<td><input type="text" class="input-m" maxlength="16" size="12" id="pricecmp" onChange="compruebaCampos()"></td>
				<td class="input-b1" nowrap>Divisa Compra</td>
				<td align="center" style="cursor:pointer">
					<select class="input-m" style="width:60%" id="divisaxx">
						<option value="RD$" <% if(divisaxx.equals("RD$")){%>selected<%} %>>RD$</option>
						<option value="EUR" <% if(divisaxx.equals("EUR")){%>selected<%} %>>&euro;</option>
						<option value="CHF" <% if(divisaxx.equals("CHF")){%>selected<%} %>>CHF</option>
					</select>
				</td>
			</tr>
			<tr>			
				<td class="input-b1">Proveedor</td>
				<td><input type="text" class="input-m" id="proveedo" maxlength="50" onChange="compruebaCampos()"></td>
			
				<td class="input-b1" nowrap>Precio Venta</td>
				<td><input type="text" class="input-m" maxlength="16" size="12" id="pricevnt" onChange="compruebaCampos()"></td>
				<td class="input-b1" nowrap>Divisa Venta</td>
				<td align="center" style="cursor:pointer">
					<select class="input-m" style="width:60%" id="divisvnt">
						<option value="RD$" <% if(divisaxx.equals("RD$")){%>selected<%} %>>RD$</option>
						<option value="EUR" <% if(divisaxx.equals("EUR")){%>selected<%} %>>&euro;</option>
						<option value="CHF" <% if(divisaxx.equals("CHF")){%>selected<%} %> >CHF</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="input-b1" colspan="2">Imei / Cod. Único</td>					
				<td colspan="2"><input type="text" class="input-m" maxlength="20" size="30" id="cdimeixx"  onchange="agregaArray()"></td>
				<td  align="center" class="input-b1"> Color</td>
				<td class="input-m" align="center">
					<select  style="width:60%" id="idcolorx" onchange="compColor(this.value)">
					<%for (int i=0; i < gridColo.rowCount(); i++){
						idcolorx = gridColo.getStringCell(i,"txnombre");
					%>
						<option value="<%=idcolorx %>"><%=idcolorx %></option>
					<%} %>
					<option value="OTRO">OTRO</option>
				</select>
				<input style="display:none;width:55px" type="text" value="" id="txtcolor">
				</td>
			</tr>		
		</table>
	
	    <br>
				
		<table border="0" width="60%" align="center" >
	
			<tr>
				<td colspan="2" align="center"><a class="boton" onclick="confirmar()">Confirmar</a></td>
				
			</tr>
	
		</table>
		
		<br>
	
	<% } else { %>
			
	<table border="0" width="60%" align="center" >
		<tr>
			<td align="center">
				<h3 style="color:#363636">No se ha encontrado el producto</h3>	
			</td>
		</tr>
		<tr>
			<td align="center">
				<a class="boton" onclick="selectPro()">Aceptar</a>
			</td>
		</tr>
	</table>
	
	<% } %>
	
	<div id="lineastmp">
	
  		<table id="myTable" border="0" width=90% align="center">
      		<tr>
      			<td class="input-b1">Imei</td>
      			<td class="input-b1">Clase</td>
      			<td class="input-b1">Divisa Compra</td>
      			<td class="input-b1">P. compra</td>
      			<td class="input-b1">Cargador</td>
      			<td class="input-b1">Conector</td>
      			<td class="input-b1">USB</td>
      			<td class="input-b1">Caja</td>
      			<td class="input-b1">Auriculares</td>
      		
      		</tr>
  			
  	  		<tr>
  	  			<td colspan="8"><hr></td>	
  	  		</tr>
  		
  		<%
  		
	  		if ((gridProd != null) && (gridProd.rowCount() > 0)) { 
	  			
	  			for (int i = 0; i < gridProd.rowCount(); i++){
	  				
	  				try {
	  					cdimeixx = gridProd.getStringCell(i,"cdimeixx");
	  					idcatego = gridProd.getStringCell(i,"idcatego");
	  					diviscmp = gridProd.getStringCell(i,"diviscmp");
	  					pricecmp = gridProd.getStringCell(i,"pricecmp");
	  					withchar = gridProd.getStringCell(i,"withchar");	  					
	  					tipocone = gridProd.getStringCell(i,"tipocone");	
	  					withusbx = gridProd.getStringCell(i,"withusbx");
	  					withboxx = gridProd.getStringCell(i,"withboxx");
	  					withheph = gridProd.getStringCell(i,"withheph");
	  					
	  					
	  					
						if (i % 2 == 0) { %>
							<tr class="oddRow"  style="border:1px solid">
						<% } else { %>
							<tr class="evenRow" style="border:1px solid">
						<% }%>
				  			<td align="center"><div class="fonts6jej">--<%=cdimeixx%>--</div></td>
						  	<td align="center"><div class="fonts6jej"><%=idcatego%></div></td>
							<td align="center"><div class="fonts6jej"><%=diviscmp%></div></td>
							<td><div class="fonts6jej"><%=pricecmp%></div></td>
							<td><div class="fonts6jej"><%=withchar%></div></td>
						  	<td><div class="fonts6jej"><%=tipocone%></div></td>
							<td><div class="fonts6jej"><%=withusbx%></div></td>
							<td><div class="fonts6jej"><%=withboxx%></div></td>
							<td><div class="fonts6jej"><%=withheph%></div></td>
							<td width="5%" class="fonts6jej"><img src="common/img/varios/delete.jpg" width=20 height=20 style="cursor:pointer" onclick="borraLineaProd('<%=cdimeixx%>')"></td>
						
					</tr>
				<%	} catch (Exception e) { 
						e.printStackTrace();
						System.out.println("ERROR recuperando linea "+ i); %>
						<tr>
							<td align="center" colspan="6" style="color:#FF0000">-- ERROR --</td>
						</tr>
				<%	}
				} %>
	  		
			<% } %>			
		</table>	
		<br/>
		<br/>
  	</div>		
  	
    
    <br>
    <br>
    <br>
    
		
	</div>
	
	
	<form name="formAltaProd" method="POST" action="">
		<input type="hidden" name="controller" 	value="StockHttpHandler"/>
		<input type="hidden" name="services"	value="AltaProdSrv"/>
		<input type="hidden" name="view" 		value="stock/resultado.jsp"/>	
		<input type="hidden" name="idemisor"    value="<%=pre_idemisor%>"/>
		<input type="hidden" name="codprodu"    value="<%=codprodu%>"/>
		<input type="hidden" name="txmarcax"    value="<%=txmarcax%>">
		<input type="hidden" name="txmodelo"    value="<%=txmodelo%>">
		<input type="hidden" name="tpclient"    value="0">
		<input type="hidden" name="arrayPro"    value="">
		<input type="hidden" name="tpproduc" id="tpproduc"    value="<%=tpproduc%>">
		<input type="hidden" name="tipoalta"    value="LINEA">
		<input type="hidden" name="cdestado"    value="P">
	</form>
	
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
		</form>
	

		
</body>