<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="common/css/calendario.css" type="text/css"/>
<script type="text/javascript" src="common/js/calendar.js"></script>
<script type="text/javascript" src="common/js/calendar-es.js"/></script>
<script type="text/javascript" src="common/js/calendar-setup.js"/></script>

<script type="text/javascript" src="factura/js/altaFactura.js"/></script>

<head>
	<title>Modificación Facturas</title>
<%

	int idfact = 0;

	String idemisor = null;
	String tpclient = null;
	String idfactur = null;
	String logoemis = null;
	String cddivisa = null;
	
	String idclient = "";
	String cdintern = "";
	String txrazons = "";
	String cdnifxxx = "";
	
	String txrazfac = "";
	String cdintefa = "";
	String idclifac = "";
	String cdniffac = "";
	String idtmpfra = "";
	
	String fhfactur = "";
	String tipofaAc = "";
	
	
	Grid rsCli 		= null;
	Grid gdProduc   = null;
	Grid gdPiezPh   = null;
	Grid gdTipFac 	= null;
	Grid gdFecMax 	= null;
	Grid gridFrPg	= null;
	Grid gridArne   = null;
	Grid gridVend   = null;
	Grid gridDepo   = null;
	Grid gridRepa   = null;
	Grid gridLine   = null;
	Grid gridResu   = null;
	
	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			tpclient = io.getStringValue("tpclient");
			idfactur = io.getStringValue("idfactur");
			idtmpfra = io.getStringValue("idtmpfra");
			cddivisa = io.getStringValue("cddivisa");
			idclifac = io.getStringValue("idclient");
			cdintefa = io.getStringValue("cdintern");
			txrazfac = io.getStringValue("txrazons");
			cdniffac = io.getStringValue("cdnifxxx");
			fhfactur = io.getStringValue("fhfactur");
			tipofaAc = io.getStringValue("tipofact");
			//logoemis = io.getStringValue("logoemis");
			rsCli 	 = io.getGrid("gridClie");
			gdProduc = io.getGrid("gridPhon");
			gdPiezPh = io.getGrid("gridProd");
			gdTipFac = io.getGrid("gridTpFa");
			gridVend = io.getGrid("gridVend");
			gridDepo = io.getGrid("gridDepo"); 
			gdFecMax = io.getGrid("gridfmax");
			gridFrPg = io.getGrid("gridFrPg");
			gridArne = io.getGrid("gridArne");
			gridRepa = io.getGrid("gridRepa");
			gridResu = io.getGrid("gridResu");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx factura/altaFactura.jsp");	
		}
	}
	
	
	try{
		idfact = Integer.parseInt(idtmpfra);
	}catch(Exception ex){
		System.out.println("ERROR --- AL CONVERTIR idfactur");
	}


	String telefono = "";
	String descuent = "";
	String nifcifxx = "";
	String txmarcax	= "";
	String txmodelo	= "";
	String txmailxx = "";

	String idphonex	= "";
	String txcatego = "";
	String cdimeixx = "";
	String idunicox = "";
	String porcmarg = "";
	String preGruPr = "";
	String iditemxx = "";
	Double precioxx	= 0.0;
	
	String codrepar = "";
	String idclirep = "";
	String txnombre = "";
	String precrepa = "";
	String autopaga = "";
	String porcrete = "";
	
	
	
	/*lineas factura*/
	
	String idlineax = "";
	double cantidad = 0;
	String concepto = "";
	String codigopr = "";
	

	double precioun = 0;
	
	double precioto = 0;
	double baseimp  = 0;
	double impuesto = 0;
	double impreten = 0;
	double total    = 0;
	double retencio = 0;
	double desculin = 0;
	String porcenta = "";

	double iva  = 0;
	double irpf = 0;
	/*Fin lineas factura*/
	
%>

	<script>

		var razonSoci	= new Array();
		var idMayoris	= new Array();
		var cdIntern	= new Array();
		var telefonox	= new Array();
		var txmailxxx	= new Array();
		var porDescu	= new Array();
		var cdnifxx		= new Array();
		
		var MarcaMov    = new Array();
		var ModelMov    = new Array();
		var precioMo    = new Array();
		var idPhonex    = new Array();
		var imeiMovi    = new Array();
		var idunicox	= new Array();
		var arrVendi	= new Array();
		var arrDepos	= new Array();
		var arrCliDe	= new Array();
		var arrAutPa	= new Array();
		
		
		/*reparaciones*/
		var arrcdRep    = new Array();
		var arrnomre    = new Array();
		var arrprere    = new Array();
		
		
		
		/* moviles y piezas*/
			
		var arrMarca    = new Array();
		var arrModel    = new Array();
		var arrPreci    = new Array();
		var arridMod    = new Array();
		
		/*moviles y piezas*/

		var categoxx    = new Array();
		var fechaFac	= new Array();
		var arrPorcr	= new Array();
		var tipoFact	= new Array();
		var arrPorcTaxe = new Array();
		var porcenta	= 0;
		var idfactu		= 0;
		var existeCli   = 0;
	   
		var idfactu  =<%=idfact%>; // para recuperar lineas de la factura
	 	var idemisor ="<%=idemisor%>";
	 	var fechaMax = "";
	 	var f = new Date();
	 	var fechaHoy = "";
	 	dia 	=  f.getDate();
	 	mes 	= f.getMonth() +1;
	 	anio	= f.getFullYear();
	 	
	 		if(dia<10){
	 			dia="0"+dia;
	 		}
	 		
	 		if(mes<10){
				mes="0"+mes;
			}
	 	
	 		fechaHoy = dia+ "/" + mes + "/" + anio;
	 	
	 		<%
	
			   String tipofact = null;
			   String porctaxe = null;
			   
			   String fechaMax = null;
			   
			   
			  for (int j = 0; j < gdFecMax.rowCount(); j++){ 
				   	fechaMax = gdFecMax.getStringCell(j, "fechafac");
					tipofact = gdFecMax.getStringCell(j, "tipofact");
			  %>
			  
			  fechaFac[<%=j%>] = "<%=fechaMax%>";
			  tipoFact[<%=j%>] = "<%=tipofact%>";
		   
			  <% }
		   
			  	for (int j = 0; j < gdTipFac.rowCount(); j++){ 
			  		tipofact = gdTipFac.getStringCell(j, "tipofact");
			  		porctaxe = gdTipFac.getStringCell(j, "porctaxe");
			  		porcrete = gdTipFac.getStringCell(j, "porcrete");
			  %>
			  arrPorcTaxe[<%=tipofact%>] = "<%=porctaxe%>";
			  arrPorcr[<%=tipofact%>]	 = "<%=porcrete%>";
		   
		 <% } %>
		 
		 
		 function marcaIva(tpfac){
			 iva = arrPorcTaxe[tpfac]/100;
			 document.getElementById("tipofac").value = tpfac;
			 cambiaTasa(tpfac)
		 }
		 
		 iva = "<%=porctaxe%>";
		 iva = iva /100;
		 /*Asigno la fecha de la factura con tpfactur = 0 que es la que carga en el select*/
		 fechaMax = fechaFac[0];
		
		 function cargaArrays(){
			 i=0;
			<% for (int j = 0; j < rsCli.rowCount(); j++){
		 	  	
				idclient 	= rsCli.getStringCell(j,"idclient"); //cdintern
				cdintern	= rsCli.getStringCell(j,"cdintern");
				txrazons 	= rsCli.getStringCell(j,"txrazons");
		 	  	telefono 	= rsCli.getStringCell(j,"telefono");
		 	  	txmailxx    = rsCli.getStringCell(j,"txmailxx");
		 	  	nifcifxx	= rsCli.getStringCell(j,"txidenti");
		 	  	descuent	= rsCli.getStringCell(j,"pcdescue");
		 
		 	%>
		 		razonSoci[i]	= "<%=txrazons%>";
		 		idMayoris[i] 	= <%=idclient%>;
		 		cdIntern[i]		= <%=cdintern%>;
		 		telefonox[i] 	= "<%=telefono%>";
		 		txmailxxx[i]	= "<%=txmailxx%>";
		 		cdnifxx[i]		= "<%=nifcifxx%>";
		 		porDescu[i] 	= "<%=descuent%>";
				i++;
			<% } %>
		 	
		 	i=0;
		<% 
		
		String precitmp ="";
			try{
			  for (int j = 0; j < gdProduc.rowCount(); j++){
				  
		 	  	txmarcax 	= gdProduc.getStringCell(j,"txmarcax");
		 	  	txmodelo 	= gdProduc.getStringCell(j,"txmodelo");
		 	  	precitmp	= gdProduc.getStringCell(j,"pricechf");
		 	  	
		 	  	if(precitmp ==null || precitmp.equals("")  ){
		 	  		precitmp ="0.00";
		 	  	}
		 	  	
		 	  	precioxx	= Double.parseDouble(precitmp);
		 	  	idphonex	= gdProduc.getStringCell(j,"codprodu");
			  	cdimeixx    = gdProduc.getStringCell(j,"imeicode");
			  	porcmarg	= gdProduc.getStringCell(j,"porcmarg");
			  	idunicox	= gdProduc.getStringCell(j,"idunicox");
			  
			//  	if(precioxx == null || precioxx.equals("null")){
			  	//	precioxx =0.00;
			 // 	}
			  	
			  	precioxx = precioxx +(precioxx * (Double.parseDouble(porcmarg)/100));
		 	  	
			%>
				MarcaMov[i] = "<%=txmarcax%>";
				ModelMov[i] = "<%=txmodelo%>";
				precioMo[i] = "<%=precioxx%>";
			 	idPhonex[i] = "<%=idphonex%>";
				imeiMovi[i] = "<%=cdimeixx%>";
				idunicox[i] = "<%=idunicox%>";
				i++;
			<% }%>
			  
			  i=0;
			  <%
			
				  for (int j = 0; j < gridVend.rowCount(); j++){%>
				  	arrVendi[i] = "<%=gridVend.getStringCell(j,"imeicode")%>";
				  	i++;
				 <% }%>
			  
			  i=0;
			  <%
			
				  for (int j = 0; j < gridDepo.rowCount(); j++){%>
				  	arrDepos[i] = "<%=gridDepo.getStringCell(j,"imeicode")%>";
				  	arrCliDe[i] = "<%=gridDepo.getStringCell(j,"idclient")%>";
				  	i++;
				 <% } 
			  
			  
			}catch(Exception e){
				System.err.println("ERROR - CARGANDO arrays del stock "+precioxx);	
					
			} 
			 %>
			 
		
			 	i=0;
				<% 
					
					String prepitmp = "";
					try{
					  for (int j = 0; j < gdPiezPh.rowCount(); j++){
						
				 	  	txmarcax 	= gdPiezPh.getStringCell(j,"txmarcax");
				 	  	txmodelo 	= gdPiezPh.getStringCell(j,"txmodelo");
				 	  	
				 	  	prepitmp =  gdPiezPh.getStringCell(j,"impdefve");
				 	  	
				 	  	if(prepitmp ==null || prepitmp.equals("")  ){
				 	  		prepitmp ="0.00";
				 	  	}
				 	  	precioxx	= Double.parseDouble(prepitmp);
				 	  	idphonex	= gdPiezPh.getStringCell(j,"idgrupox");
				
					%>
					arrMarca[i] = "<%=txmarcax%>";
					arrModel[i] = "<%=txmodelo%>";
					arrPreci[i] = "<%=precioxx%>";
					arridMod[i] = "<%=idphonex%>";
						i++;
					<% }
					  
					}catch(Exception e){
						System.err.println("ERROR - CARGANDO arrays del de izumgrph ");	
							
					} 
					 %>
		
		}
		 

		<%
			try{
				if (gridFrPg.rowCount() > 0){
				  for (int j = 0; j < gridFrPg.rowCount(); j++){
					  autopaga = gridFrPg.getStringCell(j,"autopaga");
				%>	
					  arrAutPa[<%=gridFrPg.getStringCell(j,"idfrmpag")%>]='<%=autopaga%>';
					<% }
			   	  }
			}catch(Exception ex){
				System.out.println("Error al cargar Autopaga");
			}
		%>
		 
		 
function initRepara(){

	
	<%
	try{
		  for (int j = 0; j < gridRepa.rowCount(); j++){
			
			  codrepar 	= gridRepa.getStringCell(j,"cdrecibo");
			  txnombre  = gridRepa.getStringCell(j,"txnombre");
			  precrepa =  gridRepa.getStringCell(j,"costordx");
	 	  	
	 	  	if(precrepa ==null || precrepa.equals("")  ){
	 	  		prepitmp ="0.00";
	 	  	}
	 	  	precioxx	= Double.parseDouble(precrepa);
	
		%>
		arrcdRep[i] = "<%=codrepar%>";
		arrnomre[i] = "<%=txnombre%>";
		arrprere[i] = "<%=precioxx%>";
			i++;
		<% }
		  
		}catch(Exception e){
			System.err.println("ERROR - CARGANDO arrays del de izumgrph ");	
				
		} 
		 %>	
}


	

	function selecGrupo(idgrupox){
		//alert("entra");
		muestraGrupoPhone(idgrupox);
	}
	
	function buscaClien(idclient, idemisor, actualiza){
		
		muestraCli(idclient, idemisor, actualiza)
		
	}
		 
	function calendarSetup(){
			
			Calendar.setup({ 
				inputField : "fechafac",    // id del campo de texto 
				ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
				button     : "lanzador"     // el id del botón que lanzará el calendario 
			});
	}
	
	function modFactura(){
		
		document.vistaPrevia.idclient.value  = document.getElementById("idclient").value;
		document.vistaPrevia.formpago.value  = document.getElementById("tmp_formpago").value;
		document.vistaPrevia.submit();
		//document.vistaPrevia.condpago.value  = document.getElementById("tmp_condpago").value;
		
		
	}
		
		
		cargaArrays();
		
	</script>
	
	
	
</head>
	
	
<body class="fondo">
    <div class="fondo2">
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="<%=pathimgx%>icons/emisores/<%=VariablesStatic.getLogoEmisor(idemisor,tpclient)%>"></td>						
		</tr>
	</table>

 	<div class="testata"><img src="/JLet/common/img/icons/title-alta-factura.png"></div>
 	<div class="nompanta" >Modificaci&oacute;n Factura</div>	
 	
		
		<div class="centradoFac">
			<table width="98%" align="center">
				<tr>
				<td colspan="7" align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/clientes.png" onclick="muestraAlta()"><br>Alta de Cliente<br></td>						
				</tr>
			</table>
			<!-- [ INICIO ] ALTA DE CLIENTES -->
			<div id="altaCliente" style="display:none;">
				<form method="post" name="altaClie" action="/JLet/App">
					<input type="hidden" name="controller"	value="ClientesHttpHandler"/>
					<input type="hidden" name="services" 	value="AltaClienteSrv"/>
					<input type="hidden" name="view" 		value="factura/resultAjaxCliente.jsp"/>
					<input type="hidden" name="idemisor" 	value="<%=idemisor%>">
					<input id="tpclient" type="hidden" name="tpclient" 	value="<%=tpclient%>">
					<table width="98%" align="center">
					
						<tr>
							<td width="8%" class="input-b1" >Raz&oacute;n Social</td>
							<td width="17%" style="width:200px;"><input type="text" name="rzsocial" class="input-m"/></td>
							<td width="8%" class="input-b1">Id. Fiscal</td>
							<td width="17%" style="width:200px;"><input type="text" name="idfiscal" class="input-m"/></td>
							<td width="8%" class="input-b1" >Direccion</td>
							<td width="17%" style="width:200px;"><input type="text" name="txdirecc" class="input-m"/></td>
							<td width="8%" class="input-b1">Ciudad</td>
							<td width="17%" style="width:200px;"><input type="text" name="txciudad" class="input-m"/></td>
						</tr>
						<tr>
							<td class="input-b1">Cd. Postal</td>
							<td  style="width:200px;"><input type="text" name="cdpostal" class="input-m"/></td>
							<td class="input-b1">Mail</td>
							<td style="width:200px;"><input type="text" name="txmailxx" class="input-m"/></td>
							<td class="input-b1">Tel&eacute;fono</td>
							<td  style="width:200px;"><input type="text" name="tfnofijo" class="input-m"/></td>
							<td width="17%"   class="input-b1">Persona Contacto</td>
							<td   style="width:200px;"><input type="text" name="pnaconta" class="input-m"/></td>
						</tr>
						<tr>
							<td colspan="8" align="center" bordercolor="#666666"><a class="boton" style="margin-top:25px;" onClick="altaNuevoCliente();">Alta Cliente</a></td>						
						</tr>
						<tr>
							<td colspan="8" bordercolor="#666666"><hr/></td>						
						</tr>
					</table>
				</form>
			</div>
			<!-- [ FIN ] ALTA DE CLIENTES -->
			
			<table cellspacing="10" border="0" align="center" class="tablaGrande">
				<tr>
					<td class="input-b1" style="width:35%">Tipo</td>
					<td  style="width:350px;">
						<select name="tipofac" disabled id="tipofac" title="Tipo Factura" class="input-m" onChange="cambiaTasa(this.value)">
					  	<%
					  	 	   
					  		String tpfactur = null;
					  	 	String nomtipfa = null;
					  	 	   
					  	 	for (int j = 0; j < gdTipFac.rowCount(); j++){ 
					  	 		tpfactur = gdTipFac.getStringCell(j, "tipofact");
					  	 		nomtipfa = gdTipFac.getStringCell(j, "txnombre");
					  	 %>
					  	 	<option value="<%=tpfactur%>"><%=nomtipfa%></option>
					  	 	  
					  	 <% }  %>
					  	 </select>
					</td>
					<td class="input-b1">Categoria</td>
					<td style="width:200px;">
						<select name="catefact" style="width:100%" id="catefact" title="Tipo Factura" class="input-m" onChange="">
							<option value="">&nbsp;</option>
						<% if ((gridArne != null) && (gridArne.rowCount() > 0)) {
							String arnegoci = null;
					  	 	String nomnegoc = null;
					  	 	
					  	 		for (int j = 0; j < gridArne.rowCount(); j++){ 
					  	 			arnegoci = gridArne.getStringCell(j, "areanego");
					  	 			nomnegoc = gridArne.getStringCell(j, "txnombre");
					  	 		%>
									<option value="<%=arnegoci%>"><%=nomnegoc%></option>		
					  		
					  		 <% }
							}	
					  	 	%>
					  	</select>
					</td>
					<td class="input-b1" style="width:35%">Fecha</td>
				  	<td style="width:200px;" class="fonts6">
				  		<input type="text" name="fhfechax" id="fechafac" class="input-m"  style="width:80%;display:none" >
				  		 <%=fhfactur%>
				  	</td>
				</tr>
				<tr>
					<td class="input-b1">Id. Cliente</td>
					<td  style="width:200px;">
						<input style="width:70%" type="text" id="idclibusq" name="idclibusq" value="<%=cdintefa%>" onChange="muestraCli(this.value,<%=idemisor %>,'S');" class="input-m">
						&nbsp;&nbsp;
					</td>
				  	<td class="input-b1" style="width:35%">Razon Social</td>
				  	<td style="width:200px;">
				  		<input type="text" name="txrazons" id="txrazons" value="<%=txrazfac %>" onChange="buscaNombre(this.value,'S');" class="input-m">
				  	</td>
				  	<td class="input-b1">Ident.</td>
				  	<td style="width:200px;">
				  		<input type="text" name="cdnifxxx" id="cdnifxxx" value="<%=cdniffac %>" onChange="buscaCif(this.value,'S')"  class="input-m">
				  	</td>
				</tr>
				<tr>
				  	<td class="input-b1">Forma Pago</td>
				  	<td  style="width:200px;">
					<% 
						try {
							if (gridFrPg.rowCount() > 0){ %>
					  			<select class="input-m" name="tmp_formpago" id="tmp_formpago">
					  		 		<% for (int i=0; i < gridFrPg.rowCount(); i++){ %>
					  		 			<option value='<%=gridFrPg.getStringCell(i,"idfrmpag")%>'><%=gridFrPg.getStringCell(i,"txnombre")%></option>			  		 						  		 			
					  		 		<% } %>
					  		 	</select>
					  	    <% } else { %>
					  	    	<p style="color:#FF0000">-- ERROR, No existe.</p>
					  		<% } 
					  	 } catch (Exception e) {
					  		 System.err.println("ERROR recuperando la Forma de Pago.");
					  	 } %>
				  	 </td>
				  	 
				  	 <% if (idemisor.equals("1")){ %>
				  	 	<td class="input-b1" >Condiciones de Pago</td>
					  	<td  style="width:200px;">
					  		<select class="input-m" style="width:100%" name="condpago" id="tmp_condpago" >
					  	 		<option value="0">Contado</option>
					  	 		<option value="30">30 dias</option>
					  	 		<option value="60">60 dias</option>
					  	 	</select>
					  	 </td>
					  <% } else if (idemisor.equals("3")){ %>
					  	<td style="width:200px;">
					  		<select class="input-m" id="tmp_tipovent">
				  	 			<option value="0">-- Tipo Venta --</option>
				  	 			<option value="1">Ricardo.ch</option>
				  	 			<option value="2">Ebay</option>
				  	 		</select>
				  	 	</td>
				  	 	<td style="width:200px;">
				  	 		<input class="input-m" type="text" name="numtrans" id="tmp_numtrans" placeholder="Num. Transacción"/>
				  	 	</td>
					  <% } else { %>
					  	<td class="input-b1" >Aplicar retención</td>
						<td><input type="checkbox" onclick="aplicaRetencion()" id="chkreten" />&nbsp;&nbsp;<span id="impreten" style="display:none"><input onchange="aplicaRetencion('S')" type="text" id="porcrete" name="impreten" size=3 style="width:30px"/>%</span></td>
					  <% } %>
				  	<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				
		  	</table>
		  	
		  	<br>
		  	
		  	<div id="cliente" style="display:none;color:#C80000;font-size=8px">
		  		<b>No existe ning&uacute;n cliente con ese id</b>
		  	</div>
		   	<div id="txcliente" style="display:none">
		   		<p style="color:#FF8040;font-size=10px">Existen varios clientes.</p>
		   	</div>
		  	
		   	<br>
		  	<br>
		  	<br>	
		  	<br>		
		
			<div id="lineastmp">
				  
		  		<table cellspacing="10" border="0" align="center" class="tablaGrande">
		  	   		<tr>
		  	   			<td width="5%">&nbsp;</td>
		  	   			<td width="12%"><div class="cabecera input-b1">C&oacute;digo R.</div></td>
		  	   			<td width="12%"><div class="cabecera input-b1">Ident. Único.</div></td>
				  	    <td width="5%" ><div class="cabecera input-b1">Cant</div></td>
				  	    <td width="29%"><div class="cabecera input-b1">Concepto</div></td>
				  	    <td width="8%" ><div class="cabecera input-b1">Precio /U</div></td>
				  	    <td width="10%"><div class="cabecera input-b1">%Dto</div></td>
				  	    <td width="20%"><div class="cabecera input-b1">Total</div></td>
					
		  	  		</tr>
		  	  		<tr>
		  	  			<td colspan="7"><hr></td>	
		  	  		</tr>
		  		 
		  		
		  		
		  			<%
  		
	  		if ((gridResu != null) && (gridResu.rowCount() > 0)) { 
	  			
	  			for (int i = 0; i < gridResu.rowCount(); i++){
	  				
	  				try {
		  				idlineax = gridResu.getStringCell(i,"idtmpreg");
		  				codigopr = gridResu.getStringCell(i,"codprodu");
		  				idunicox = gridResu.getStringCell(i,"idunicox");
		  				cantidad = Float.parseFloat(gridResu.getStringCell(i,"cantidad"));
		  				concepto = gridResu.getStringCell(i,"concepto");
		  				precioun = Float.parseFloat(gridResu.getStringCell(i,"precioun"));
		  				desculin = Float.parseFloat(gridResu.getStringCell(i,"descuent"));
		  				precioto = Float.parseFloat(gridResu.getStringCell(i,"precioto"));
		  				
		  				baseimp += precioto;
		  				
		%>  	
				  		<tr>
				  			<td width="2%">&nbsp;</td>
				  			<td width="11%" class="fonts6"><%=codigopr%></td>
					  		<td width="12%" class="fonts6"><%=idunicox%></td>
					  	    <td width="5%" class="fonts6"><%=formateador.format(cantidad)%></td>
					  	    <td width="29%" class="fonts6"><%=concepto%></td>
					  	    <td width="8%" class="fonts6" style="text-align:right" nowrap onclick="MuestraBorraLinea('<%=formateador.format(precioun)%>')"><%=formateador.format(precioun)%> <%=cddivisa %></td>
					  	    <td width="10%" class="fonts6" style="text-align:right" nowrap><%=formateador.format(desculin)%></td>
					  	    <td width="20%" class="fonts6" style="text-align:right" nowrap><%=formateador.format(precioto)%> <%=cddivisa %></td>
							<td width="5%"><a class="boton" onclick="borraLinea(<%=idlineax%>,<%=idemisor%>,'S')" style="font-size:9px;font-weight:900;font-family:Arial;min-width:20px;border-radius:100%;min-height:20px;background-color:#C13030 !important">X</a> </td>
						</tr>
				<%	} catch (Exception e) { 
						e.printStackTrace();
						System.out.println("ERROR recuperando linea "+ e.getMessage()); %>
						<tr>
							<td align="center" colspan="6" style="color:#FF0000">-- ERROR --</td>
						</tr>
				<%	}
				}
			}
		%>	
		  		
					<tr>
						<td width="5%" onclick="listadoProd('<%=idemisor%>')"><a class="boton" style="font-size:9px;font-weight:900;font-family:Arial;min-width:25px;border-radius:100%;min-height:20px;">+</a></td>
		  	   		
				  		<td width="12%"><input class="input-m" type="text" style="width:100%" name="idcodigo" id="codigo" onChange="muestraGrupoPhone(this.value)"  onkeyup="if (event.keyCode == 13) muestraGrupoPhone(this.value)"></td>
				  		<td width="12%"><input class="input-m" type="text" style="width:100%" name="idunicox" id="idunicox" onChange="muestraPhone(this.value)"  onkeyup="if (event.keyCode == 13) muestraPhone(this.value)"></td>
				  	    <td width="5%" ><input class="input-m" type="text" style="width:100%" name="cantidad" id="cantidad" onKeyUp="if (event.keyCode == 13) acceso(1,'S')"></td>
				  	    <td width="29%"><input class="input-m" type="text" style="width:100%" name="concepto" id="concepto" onKeyUp="if (event.keyCode == 13) acceso(1,'S')"></td>
				  	    <td width="8%" ><input class="input-m" type="text" style="width:100%" name="precioun" id="precioun" onKeyUp="if (event.keyCode == 13) acceso(1,'S')"></td>
				  	    <td width="10%"><input class="input-m" type="text" style="width:100%" name="descuent" id="descuent" onBlur="acceso(1,'S')" class="input-m"></td>
				  	    <td width="20%"><input class="input-m" type="text" style="width:100%" name="precioto" id="precioto"  readonly></td>
				  	    <input type="hidden" name="miliseg" id="M0M" value="<%=System.currentTimeMillis() %>" class="input-m">
					</tr>		  
				</table>
				<div id="prodexist" style="display:none">
					<h3 style="color:#C80000;font-size:12px">No existen datos para el c&oacute;digo de producto introducido</h3>
				</div>
				
				<br>
				<br>
			
				<%
					impuesto =  baseimp *(iva);
					impreten =  baseimp *(irpf);
				    total = baseimp + impuesto - impreten;
				    
				    impuesto = Math.rint(impuesto*100)/100;
				    baseimp  = Math.rint(baseimp*100)/100;
				    total    = Math.rint(total*100)/100;
		
				 %>	
		 		<div id="prodexist" style="display:none"><h3 style="color:#ff0000">No existen datos para el c&oacute;digo de producto introducido</h3></div>
			   	<br><br>
		
		<table align="right" class="piefactu-ok">
			<tr>
				<td class="input-b1"><span>Base imponible</span></td>
			  	<td class="fonts6"><input style="border:none;width:110px"  class="fonts6" type="text" name="baseimp" id="baseimp" disabled value="<%=formateador.format(baseimp)%>" ><%=cddivisa %></td>
			</tr>
			<tr>
				<td class="input-b1"><span>Impuesto</span></td>
			  	<td class="fonts6"><input style="border:none;width:110px"  class="fonts6" type="text" name="impuesto" id="impuesto" disabled value="<%=formateador.format(impuesto)%>"><%=cddivisa %></td>
			</tr>
			
			<tr id="muesrete" <%if(irpf > 0){ %> style="table-row"<%}else{%>style="display:none" <%} %>>
				<td class="input-b1">Retención IRPF</td>
				<td class="fonts6"><input style="border:none;width:110px"  type="text" name="retencio" id="retencio" disabled class="fonts6" value="<%=formateador.format(impreten)%>"><%=cddivisa %></td>
			</tr>
			<tr>
				<td class="input-b1"><span>Total</span></td>
			  	<td class="fonts6"><input style="border:none;width:110px"  class="fonts6" type="text" name="txtotal" ID="total" disabled value="<%=formateador.format(total)%>"><%=cddivisa %></td>
			</tr>		
		</table>	
		  		
		  	</div>
		  	
		  	<div style="" id="vistapre">
		  		<table>
					<tr><td align="center"><a class="boton" onClick="modFactura();">Modificar</a></td></tr>
				</table>
			</div>
			</div>
		 
		</div>
		<form method="post" name="vistaPrevia" action="/JLet/App">
				<input type="hidden" name="controller"	value="FacturaHttpHandler"/>
				<input type="hidden" name="services" 	value="UpdateFacturaSrv"/>
				<input type="hidden" name="view" 		value="factura/abrirFactura.jsp"/>
				<input type="hidden" name="fechafac" 	value=""/>
				<input type="hidden" name="idclient" 	value="<%=idclifac %>" id="idclient">
				<input type="hidden" name="tipoPorc" 	value="">
				<input type="hidden" name="porcrete" 	value="">
				<input type="hidden" name="tipoFact" 	value="">
				<input type="hidden" name="catefact" 	value="">
				<input type="hidden" name="formpago" 	value="">
				<input type="hidden" name="condpago" 	value="">
				<input type="hidden" name="tipologo" 	value="<%=tpclient%>">
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>">
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>">
				<input type="hidden" name="txtpfact" 	value="">
				<input type="hidden" name="txformpa" 	value="">
				<input type="hidden" name="txcatefa" 	value="">
				<input type="hidden" name="txcondpa" 	value="">
				<input type="hidden" name="tipovent" 	value="">
				<input type="hidden" name="autopaga" 	value="">
				<input type="hidden" name="cddivisa" 	value="<%=cddivisa%>">
				<input type="hidden" name="numtrans" 	value="">	
				<input type="hidden" name="idfactur" 	value="<%=idfactur%>">	
				<input type="hidden" name="idtmpfra" 	value="<%=idfact%>">	
			

		</form>
		
	</body>
	
	<script>
		initRepara();
		tipof = <%=tipofaAc%>
		marcaIva(tipof);
	</script>

</html>

