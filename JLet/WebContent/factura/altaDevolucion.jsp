<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="common/css/calendario.css" type="text/css"/>
<script type="text/javascript" src="common/js/calendar.js"></script>
<script type="text/javascript" src="common/js/calendar-es.js"/></script>
<script type="text/javascript" src="common/js/calendar-setup.js"/></script>

<script type="text/javascript" src="factura/js/altaFactura.js"/></script>

<head>
	<title>Alta Devolución</title>

<%

	int idfact = 0;

	String idemisor = null;
	String tpclient = null;
	String idfactur = null;
	String logoemis = null;
	Grid rsCli 		= null;
	Grid gdProduc   = null;
	Grid gdPiezPh   = null;
	Grid gdTipFac 	= null;
	Grid gdFecMax 	= null;
	Grid gridFrPg	= null;
	Grid gridArne   = null;
	Grid gridResu   = null;
	Grid gdFactur   = null;
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			tpclient = io.getStringValue("tpclient");
			idfactur = io.getStringValue("idfactur");
			logoemis = io.getStringValue("logoemis");
			gridResu = io.getGrid("gridResu");
			gdFactur = io.getGrid("gdFactur");
			rsCli 	 = io.getGrid("gridClie");
			gdProduc = io.getGrid("gridPhon");
			gdPiezPh = io.getGrid("gridProd");
			gdTipFac = io.getGrid("gridTpFa");
			gdFecMax = io.getGrid("gridfmax");
			gridFrPg = io.getGrid("gridFrPg");
			gridArne = io.getGrid("gridArne");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx factura/altaFactura.jsp");	
		}
	}
	
	
	try{
		idfact = Integer.parseInt(idfactur);
	}catch(Exception ex){
		System.out.println("ERROR --- AL CONVERTIR idfactur");
	}

	
	String idclient = "";
	String cdintern = "";
	String txrazons = "";
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
		
		/*factur*/
		
		var idtmpFac = new Array();
		var idtmpLin = new Array();
		var porctaxe = new Array();
		
		/**/
		
		/*devoluciones*/
		var indiceU	= new Array();
		var idcliDev    = new Array();
		var idunicol    = new Array();
		var concepDe   = new Array();
		var precioun    = new Array();
		var descuent    = new Array();
		var precioto	= new Array();
		
		
		/* moviles y piezas*/
			
		var arrMarca    = new Array();
		var arrModel    = new Array();
		var arrPreci    = new Array();
		var arridMod    = new Array();
		
		/*moviles y piezas*/

		var categoxx    = new Array();
		var fechaFac	= new Array();
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
			  %>
			  arrPorcTaxe[<%=tipofact%>] = "<%=porctaxe%>";
		   
		 <% } %>
		 
		 
		 function marcaIva(tpfac){
			 iva = arrPorcTaxe[tpfac]/100;
		 }
		 
		 iva = "<%=porctaxe%>";
		 iva = iva /100;
		 /*Asigno la fecha de la factura con tpfactur = 7 que es la que carga en el select*/
		 fechaMax = fechaFac[7];
		
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
			try{
			  for (int j = 0; j < gdProduc.rowCount(); j++){
		 	  	txmarcax 	= gdProduc.getStringCell(j,"txmarcax");
		 	  	txmodelo 	= gdProduc.getStringCell(j,"txmodelo");
		 	  	idphonex	= gdProduc.getStringCell(j,"idproduc");
			  	cdimeixx    = gdProduc.getStringCell(j,"imeicode");
			  	porcmarg	= gdProduc.getStringCell(j,"porcmarg");
			  	idunicox	= gdProduc.getStringCell(j,"idunicox");
			  	
			  	try {
			  		precioxx	= Double.parseDouble(gdProduc.getStringCell(j,"pricechf"));
				  	precioxx = precioxx +(precioxx * (Double.parseDouble(porcmarg)/100));
					System.out.println("Despues "+precioxx);
			  		
			  	}catch(Exception ex){
			  		precioxx =0.00;
			  	}
			  	
			
		 	  	
			%>
				MarcaMov[i] = "<%=txmarcax%>";
				ModelMov[i] = "<%=txmodelo%>";
				precioMo[i] = "<%=precioxx%>";
			 	idPhonex[i] = "<%=idphonex%>";
				imeiMovi[i] = "<%=cdimeixx%>";
				idunicox[i] = "<%=idunicox%>";
				i++;
			<% }
			  
			}catch(Exception e){
				System.err.println("ERROR - CARGANDO arrays del stock ");	
					
			} 
			 %>
			 
		
			 	i=0;
				<% 
					String prefixxx = "";
					try{
					  for (int j = 0; j < gdPiezPh.rowCount(); j++){
						prefixxx	= gdPiezPh.getStringCell(j,"prefijox");
				 	  	txmarcax 	= gdPiezPh.getStringCell(j,"txmarcax");
				 	  	txmodelo 	= gdPiezPh.getStringCell(j,"txmodelo");
				 	  	try {
				 	  		precioxx	= Double.parseDouble(gdPiezPh.getStringCell(j,"impdefve"));
					  		
					  	}catch(Exception ex){
					  		
					  		precioxx =0.00;
					  		
					  	}
				 	  	
				 	  	idphonex	= gdPiezPh.getStringCell(j,"idgrupox");
				
					%>
					arrMarca[i] = "<%=txmarcax%>";
					arrModel[i] = "<%=txmodelo%>";
					arrPreci[i] = "<%=precioxx%>";
					arridMod[i] = "<%=prefixxx%><%=idphonex%>";
						i++;
					<% }
					  
					}catch(Exception e){
						System.err.println("ERROR - CARGANDO arrays del de izumgrph ");	
							
					} 
					 %>
					 
			
			<%
			String idunic ="";
			  for (int j = 0; j < gridResu.rowCount(); j++){ 
			
			  idunic = gridResu.getStringCell(j, "idunicox");  %>

			  indiceU[<%=j%>] =  "<%=idunic%>";
			  idcliDev[<%=j%>] = "<%= gridResu.getStringCell(j, "idclient") %>";
			  idunicol[<%=j%>] = "<%= gridResu.getStringCell(j, "idunicox")%>";
			  concepDe[<%=j%>] = "<%= gridResu.getStringCell(j, "concepto")%>";
			  precioun[<%=j%>] = "<%= gridResu.getStringCell(j, "precioun")%>";
			  descuent[<%=j%>] = "<%= gridResu.getStringCell(j, "descuent")%>";
			  precioto[<%=j%>] = "<%= gridResu.getStringCell(j, "precioto")%>";
			  idtmpLin[<%=j%>] = "<%= gridResu.getStringCell(j, "idtmpfra")%>";
		   
			  <% }
			  
	  		for (int j = 0; j < gdFactur.rowCount(); j++){ 
			 %>
			 	idtmpFac[<%=j%>] = "<%= gdFactur.getStringCell(j, "idtmpfra") %>";
			 	porctaxe[<%=j%>] = "<%= gdFactur.getStringCell(j, "porctaxe")%>";
	   
		  <% }%>		  
					
		}
		 
		 
		function calendarSetup(){
			
			Calendar.setup({ 
				inputField : "fechafac",    // id del campo de texto 
				ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
				button     : "lanzador"     // el id del botón que lanzará el calendario 
			});
		}
		
		cargaArrays();
		
	</script>
	
	
	
</head>
	
	
<body class="fondo2" onLoad="calendarSetup();">
<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
</table>
 	<div class="testata"><img src="/JLet/common/img/icons/title-alta-factura.png"></div>
 	<div class="nompanta" >Alta Nota Crédito</div>
 	
		
		<div class="centradoFac">
			<table width="100%" align="center">
				<tr>
					<td colspan="8" align="center"><img style="cursor:pointer" src="/JLet/common/img/icons/clientes.png" onclick="muestraAlta()"><br>Alta de Cliente<br></td>						
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
					<table width="100%" align="center">
					
						<tr>
							<td width="8%" class="input-b1" >Raz&oacute;n Social</td>
							<td width="17%" style="width:200px;"><input type="text" name="rzsocial" class="input-m"/></td>
							<td width="8%" class="input-b1">Id. Fiscal</td>
							<td width="17%"  style="width:200px;"><input type="text" name="idfiscal" class="input-m"/></td>
							<td width="8%" class="input-b1" >Direccion</td>
							<td width="17%" style="width:200px;"><input type="text" name="txdirecc" class="input-m"/></td>
							<td width="8%" class="input-b1">Ciudad</td>
							<td width="17%"  style="width:200px;"><input type="text" name="txciudad" class="input-m"/></td>
						</tr>
						<tr>
							<td class="input-b1">Cd. Postal</td>
							<td  style="width:200px;"><input type="text" name="cdpostal" class="input-m"/></td>
							<td class="input-b1">Mail</td>
							<td  style="width:200px;"><input type="text" name="txmailxx" class="input-m"/></td>
							<td class="input-b1">Tel&eacute;fono</td>
							<td style="width:200px;"><input type="text" name="tfnofijo" class="input-m"/></td>
							<td class="input-b1">Persona Contacto</td>
							<td  style="width:200px;"><input type="text" name="pnaconta" class="input-m"/></td>
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
			
			<table cellspacing="10" border="0" align="center" class="tablaGrande" style="width:100%">
				<tr>
					<td class="input-b1">Tipo</td>
					<td  style="width:200px;">
						<select name="tipofac" id="tipofac" title="Tipo Factura" class="input-m" onChange="cambiaTasa(this.value)">
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
					<td class="input-b1" style="display:none">Categoria</td>
					<td style="width:200px;display:none">
						<select name="catefact" id="catefact" title="Tipo Factura" class="input-m" onChange="">
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
					<td class="input-b1">Fecha</td>
				  	<td style="width:200px;">
				  		<input type="text" name="fhfechax" id="fechafac" class="input-m" style="width:80%" onChange="comparaFechas(this.value);"> <img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:5px" width="24" height="24" border="0" title="Fecha Factura" id="lanzador">
				  	</td>
				</tr>
				<tr>
					<td class="input-b1" width=30%>Id. Cliente</td>
					<td  style="width:200px;"><input type="text" id="idclibusq" name="idclibusq" value="" onChange="muestraCli(this.value);" class="input-m"></td>
				  	<td class="input-b1">Razon Social</td>
				  	<td style="width:200px;">
				  		<input type="text" name="txrazons" id="txrazons" value="" onChange="buscaNombre(this.value);" class="input-m">
				  	</td>
				  	<td class="input-b1" style="display:none">Ident.</td>
				  	<td class="input-m" style="width:200px;display:none">
				  		<input type="text" name="cdnifxxx" id="cdnifxxx" value="" onChange="buscaCif(this.value)"  class="input-m">
				  	</td>
				</tr>
				<div >
				<tr style="display:none">
				  	<td class="input-b1">Forma Pago</td>
				  	<td class="input-m" style="width:200px;">
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
					  	<td class="input-m" style="width:200px;">
					  		<select class="input-m" name="condpago" id="tmp_condpago" >
					  	 		<option value="0">Contado</option>
					  	 		<option value="30">30 dias</option>
					  	 		<option value="60">60 dias</option>
					  	 	</select>
					  	 </td>
					  <% } else if (idemisor.equals("3")){ %>
					  	<td class="input-m" style="width:200px;">
					  		<select class="input-m" name="tipovent" id="tmp_tipovent">
				  	 			<option value="0">-- Tipo Venta --</option>
				  	 			<option value="1">Ricardo.ch</option>
				  	 			<option value="2">Ebay</option>
				  	 		</select>
				  	 	</td>
				  	 	<td class="input-m" style="width:200px;">
				  	 		<input class="input-m" type="text" name="numtrans" id="tmp_numtrans"/>
				  	 	</td>
					  <% } else { %>
					  	<td>&nbsp;</td>
						<td>&nbsp;</td>
					  <% } %>
				  	<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				</div>
				
		  	</table>
		  	
		  	<br>
		  	
		  	<div id="cliente" style="display:none" style="color:#C80000;font-size=8px">
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
		  	   			<td width="12%"><div class="input-b1">C&oacute;digo R.</div></td>
		  	   			<td width="12%"><div class="input-b1">Ident. Único.</div></td>
				  	    <td width="5%" ><div class="input-b1">Cant</div></td>
				  	    <td width="29%"><div class="input-b1">Concepto</div></td>
				  	    <td width="8%" ><div class="input-b1">Precio /U</div></td>
				  	    <td width="10%"><div class="input-b1">%Dto</div></td>
				  	    <td width="20%"><div class="input-b1">Total</div></td>
					
		  	  		</tr>
		  	  		<tr>
		  	  			<td colspan="7"><hr></td>	
		  	  		</tr>
		  		</table>  
		  		
		  		<table cellspacing="10" border="0" align="center" class="tablaGrande">
					<tr>
				  		<td width="12%"><input class="input-m" type="text" style="width:100%" name="idcodigo" id="codigo" onChange="muestraGrupoPhone(this.value)"  onkeyup="if (event.keyCode == 13) muestraGrupoPhone(this.value)"></td>
				  		<td width="12%"><input class="input-m" type="text" style="width:100%" name="idunicox" id="idunicox" onChange="muestraPhone(this.value)"  onkeyup="if (event.keyCode == 13) muestraPhone(this.value)"></td>
				  	    <td width="5%" ><input class="input-m" type="text" style="width:100%" name="cantidad" id="cantidad" onKeyUp="if (event.keyCode == 13) acceso(1)"></td>
				  	    <td width="29%"><input class="input-m" type="text" style="width:100%" name="concepto" id="concepto" onKeyUp="if (event.keyCode == 13) acceso(1)"></td>
				  	    <td width="8%" ><input class="input-m" type="text" style="width:100%" name="precioun" id="precioun" onKeyUp="if (event.keyCode == 13) acceso(1)"></td>
				  	    <td width="10%"><input class="input-m" type="text" style="width:100%" name="descuent" id="descuent" onBlur="acceso(1)" class="input-m"></td>
				  	    <td width="20%"><input class="input-m" type="text" style="width:100%" name="precioto" id="precioto" onKeyUp="if (event.keyCode == 13) acceso(1)" readonly></td>
				  	    <input type="hidden" name="miliseg" id="M0M" value="<%=System.currentTimeMillis() %>" class="input-m">
					</tr>		  
				</table>
				<div id="prodexist" style="display:none">
					<h3 style="color:#C80000;font-size:8px">No existen datos para el c&oacute;digo de producto introducido</h3>
				</div>
				
				<br>
				<br>
			
				<table align="right" class="piefactu">
					<tr>
						<td class="input-b1">Base imponible</td>
						<td>
							<input type="text" name="baseimp" id="baseimp" disabled class="input-m">
						</td>
					</tr>
					<tr>
						<td class="input-b1">Impuesto</td>
				  		<td>
				  			<input type="text" name="impuesto" id="impuesto" disabled class="input-m">
				  		</td>
				  	</tr>
				  	<tr>
				  		<td class="input-b1">Total</td>
				  		<td>
				  			<input type="text" name="txtotal" ID="total" disabled class="input-m">
				  		</td>
				  	</tr>		
				 </table>	
		  		
		  	</div>
		  	
		  	<div style="display:none" id="vistapre">
		  		<table>
					<tr><td align="center"><a class="boton" onClick="verVistaPrevia();">Vista Previa</a></td></tr>
				</table>
			</div>
			
		 
		</div>
		<form method="post" name="vistaPrevia" action="/JLet/App">
				<input type="hidden" name="controller"	value="FacturaHttpHandler"/>
				<input type="hidden" name="services" 	value="VistaPreviaSrv"/>
				<input type="hidden" name="view" 		value="factura/vistaPrevia.jsp"/>
				<input type="hidden" name="fechafac" 	value=""/>
				<input type="hidden" name="idclient" 	value="" id="idclient">
				<input type="hidden" name="tipoPorc" 	value="">
				<input type="hidden" name="tipoFact" 	value="">
				<input type="hidden" name="catefact" 	value="">
				<input type="hidden" name="formpago" 	value="-1">
				<input type="hidden" name="condpago" 	value="-1">
				<input type="hidden" name="txtpfact" 	value="">
				<input type="hidden" name="txformpa" 	value="">
				<input type="hidden" name="txcatefa" 	value="">
				<input type="hidden" name="txcondpa" 	value="">
				
				<input type="hidden" name="tipologo" 	value="<%=tpclient%>">
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>">
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>">
		</form>
		
	</body>
	
	<script>
		tipof = document.getElementById("tipofac").value;
		marcaIva(tipof);
	</script>

</html>

