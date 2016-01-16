<html>
<%@ include file="/common/jsp/include.jsp"%>
<script type="text/javascript" src="factura/js/albaran.js"/></script>
	<script src="common/js/validacionesComunes.js"></script>
	<script src="common/js/calendar.js" type="text/javascript"></script>
	<script src="common/js/calendar-es.js" type="text/javascript"></script>
	<script src="common/js/calendar-setup.js" type="text/javascript"></script>
	<link rel="stylesheet" href="common/css/calendario.css" type="text/css" />

	<head>
		<title>Facturas</title>

	</head>
   <%
   Grid gridFact 	= null;
   Grid gridClie 	= null;
   Grid tipFac 		= null;
   Grid GrFecMax 	= null;
   Grid gridFrPg	= null;
   Grid gridArne    = null;
  
  	String idclient = "";
   	String fechafac = "";
   	String tipoFact = "";
   	String idemisor = "";
   	String tipovist = "";
	String cdfactur = "";
	String idlineax = "";
	String codprodu = "";
	String imgfilex = "";
	String fhfechax = "";
	String txrazons = "";
	String cifnifxx = "";	
	String telefon  = "";
	String txmailxx = "";
	String concepto = "";
	String tpclient = "";
	String idtmpfra = "";
	String idalbara = "";
	String factasoc = "";
	String informad = "";
	
	double tipoPorc = 0;
    double cantidad = 0;
    double precioun = 0;
	double descuent	= 0;
	double precioto = 0;
	double baseimp  = 0;
	double impuesto = 0;
	double total    = 0;

	

	DecimalFormat formateador = new DecimalFormat("###,##0.00");
   
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
			//rsCli 	= io.getGrid("gridClie");
			
			try {
				gridArne		= io.getGrid("gridArne");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo gridArne");		
			}
			
			try {
				gridFact		= io.getGrid("gridLine");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo gridLine");		
			}
			
			try {
				gridFrPg		= io.getGrid("gridFrPg");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo gridFrPg");		
			}
			
			
			
			try {
				GrFecMax = io.getGrid("fecmaxfa");
				
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo fecmaxfa");		
			}
			
			
			try {
				gridClie		= io.getGrid("gridClie");
			}catch(Exception ex){
				System.err.println("ERROR - gridClie");	
			}
			
			try {
				tipFac		= io.getGrid("gridTpFa");
			}catch(Exception ex){
				System.err.println("ERROR - gridTpFa");	
			}

			try {
				idclient 	= io.getStringValue("idclient");
				System.out.println("Hola Holita vistaPrevia.jsp "+idclient);
   			}catch(Exception ex){
   				System.err.println("ERROR - idclient");	
			}
			
			try {
				fechafac	= io.getStringValue("fechafac");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo fechafac");	
			}
			
			try {
				tipoPorc 	= Double.parseDouble(io.getStringValue("tipoPorc"));
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo tipoPorc");	
			}
			
			try {
				tipoFact	= io.getStringValue("tipoFact");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo tipoFact");	
			}
			
			try {
				idemisor	= io.getStringValue("idemisor");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo idemisor");	
			}
			
			try {
				tipovist	= io.getStringValue("tipovist");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo tipovis");	
			}
			
			try {
				cdfactur	= io.getStringValue("cdfactur");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo tipovis");	
			}
			
			try {
				tpclient	= io.getStringValue("tpclient");
			}catch(Exception ex){
				tpclient ="0";
				System.err.println("ERROR - Recibiendo tpclient");	
			}
			
			try {
				idtmpfra	= io.getStringValue("idtmpfra");
			}catch(Exception ex){
				idtmpfra ="0";
				System.err.println("ERROR - Recibiendo idtmpfra");	
			}
			
			try {
				idalbara	= io.getStringValue("idalbara");
			}catch(Exception ex){
				idalbara ="0";
				System.err.println("ERROR - Recibiendo idalbara");	
			}
			
			try {
				factasoc	= io.getStringValue("factasoc");
			}catch(Exception ex){
				factasoc ="";
				System.err.println("ERROR - Recibiendo factasoc");	
			}
			
			try {
				informad	= io.getStringValue("informad");
			}catch(Exception ex){
				informad ="";
				System.err.println("ERROR - Recibiendo informad");	
			}
			
			
			
	}
 
    
   %> 

 <script>
 	var idemisor	= "";
 	var f = new Date();
 	var fechaHoy = "";
 	dia 		=  f.getDate();
 	mes 		= f.getMonth() +1;
 	anio		= f.getFullYear();
	var fechaMax = "";
	var fechaFac	= new Array();
	var tipoFact	= new Array();
	var arrPorcTaxe = new Array();
	var arrLineas	 = new Array();
	var ArrTxnom 	= new Array();
	var baseImp = 0;
 	
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
	   
	   try{
	   		for (int j = 0; j < GrFecMax.rowCount(); j++){ 
		   		fechaMax = GrFecMax.getStringCell(j, "fechafac");
				tipofact = GrFecMax.getStringCell(j, "tipofact");
	   		%>
	   fechaFac[<%=j%>] = "<%=fechaMax%>";
	   tipoFact[<%=j%>] = "<%=tipofact%>";
	   	
	   <%}
	   		
	   	}catch(Exception ex){
		 System.out.println("No se recibe fecha Maxima"); 
	 	}
	
	   try{
	   
		   for (int j = 0; j < tipFac.rowCount(); j++){ 
				tipofact = tipFac.getStringCell(j, "tipofact");
				porctaxe = tipFac.getStringCell(j, "porctaxe");
			   %>
			   	arrPorcTaxe[<%=tipofact%>] = "<%=porctaxe%>";
			   	iva = "<%=tipFac.getStringCell(0, "porctaxe")%>";
			   
		 <% }
  		 }catch(Exception ex){
		 		System.out.println("No se recibe fecha Maxima"); 
	 	  }
	   	 %>
	   	 
	   	 
	function cargaCli(){
		<% for (int i= 0; i< gridClie.rowCount();i++){%>
			ArrTxnom[<%= gridClie.getStringCell(i,"idclient") %>]="<%= gridClie.getStringCell(i,"txnombre") %> <%= gridClie.getStringCell(i,"txapelli") %>";

 		<%}%>	
	}
	
	function pintanombre(idcli){

		return ArrTxnom[idcli];
	}
	
	cargaCli();
	
	
	/*Asigno la primera fecha por defecto que es la que carga en el select*/
	fechaMax = fechaFac[0];
 
 </script>	
	<body class="fondo">
	<div class="fondo2">
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>	
	
		
 <div class="centradoFac">
	<table cellspacing="10" border="0" align="center" class="tablaGrande">
			  <tr>
			  	 	<td class="input-b1" >Fecha</td>
				 <% if(!tipovist.equals("VP")){%>
			  	 	<td width=25%><input style="width:80%;height:100%" type="text" name="fhfechax" id="fechafac" class="input-m" onchange="comparaFechas(this.value);"> <img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:5px" width="30" height="30" border="0" title="Fecha Factura" id="lanzador"></td>
			  	 <% }else{%>
			  		<td class="fonts6" width=25%><div id="fhfechax"><%=fechafac%></div></td>
			 	 <% }%>
			  	 	<td class="input-b1">Razon Social</td>
			     	<td class="fonts6"><div id="txrazons"><script>document.write(pintanombre(<%= gridClie.getStringCell(0,"idclient") %>));</script></div></td>
			  	 	
			  </tr>
			   <tr>
			  		<td class="input-b1">Cliente</td>
			  	 	<td class="fonts6"><div id="idclient"><%=idclient%></div></td>
			  	 	<td class="input-b1">Telefono</td>			  	 
			  	 	<td class="fonts6"><div id="telefon"><%=telefon%></div></td>
			  </tr>	
			
			  <tr>
			  		<td class="input-b1">Tipo Factura</td>
			  	 	<td>
			  	 		<select type="text" name="tipofac" id="tipofac" title="Tipo Factura" class="input-m" onChange="cambiaTasa(this.value)">
				  	 		<% 
				  	 		String tpfactur = null;
				  	 		String nomtipfa = null;
				  	 	   
				  	 	   	for (int j = 0; j < tipFac.rowCount(); j++){ 
				  	 			tpfactur = tipFac.getStringCell(j, "tipofact");
				  	 			nomtipfa = tipFac.getStringCell(j, "txnombre");
				  	 		
					  	 		if(!tpfactur.equals("0") && !tpfactur.equals("6") &&  !tpfactur.equals("7") ){
					  	 	   %>
					  	 			<option value="<%=tpfactur%>"><%=nomtipfa%></option>
					  	 	    
					  	 	 <% }
					  	 	} %>
			  	 	 	</select>
			  	 	</td>
			
		
			<td class="input-b1">Categoria</td>
					<td style="width:200px;">
						<select name="catefact" id="catefact" title="Tipo Factura" class="input-m" onChange="" style="width:100%">
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
				  </tr>
			<tr>
				  	<td class="input-b1">Forma Pago</td>
				  	<td style="width:200px;">
					<% 
						try {
							if (gridFrPg.rowCount() > 0){ %>
					  			<select class="input-m" name="tmp_formpago" id="tmp_formpago" style="width:100%">
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
					  	<td style="width:200px;">
					  		<select class="input-m" name="condpago" id="tmp_condpago" style="width:100%">
					  	 		<option value="0">Contado</option>
					  	 		<option value="30">30 dias</option>
					  	 		<option value="60">60 dias</option>
					  	 	</select>
					  	 </td>
					  <% } else if (idemisor.equals("3")){ %>
					  	<td class="fonts6" style="width:200px;">
					  		<select class="fonts6" name="tipovent" id="tmp_tipovent">
				  	 			<option value="0">-- Tipo Venta --</option>
				  	 			<option value="1">Ricardo.ch</option>
				  	 			<option value="2">Ebay</option>
				  	 		</select>
				  	 	</td>
				  	 	<td class="fonts6" style="width:200px;">
				  	 		<input class="fonts6" type="text" name="numtrans" id="tmp_numtrans"/>
				  	 	</td>
					  <% } else { %>
					  	<td>&nbsp;</td>
						<td>&nbsp;</td>
					  <% } %>
				  	<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
				<td class="input-b1">CIF /NIF</td>
			  	 	<td class="fonts6"><div id="txdirecc"><%=cifnifxx%></div></td>
				</tr>
	  	</table>

	  	<br>
	  	<br>
	  	<br>
	  	<br>	
	  	<br>		
	
		<div id="lineastmp">
		  
  		<table width="100%" border=0 >
  	   		<tr align="center">
  	   			<td width="5%" class="input-b1">Imagen</td>
  	     		<td width="15%" class="input-b1">C&oacute;digo R.</td>
		  	     <td width="5%" class="input-b1">Cant</td>
		  	     <td width="25%" class="input-b1">Concepto</td>
		  	     <td width="20%" class="input-b1">Precio /U</td>
		  	     <td width="10%" class="input-b1">%Dto</td>
		  	     <td width="20%" class="input-b1">Total</td>
				
  	  		</tr>
  	  		<tr>
  	  			<td colspan="7"><hr></td>	
  	  		</tr>
  		
  		<%
  			try {
	  			for (int j = 0; j < gridFact.rowCount(); j++){
	  		 	  	
	  				try{
	  					imgfilex 	= gridFact.getStringCell(j,"imagedet");
	  				}catch(Exception ex){
	  					System.out.println("No tiene Imagen");
	  					imgfilex = "";
	  				}
	  				idlineax 	= gridFact.getStringCell(j,"idtmpreg");
	  				codprodu 	= gridFact.getStringCell(j,"codprodu");
	  				cantidad 	= Double.parseDouble(gridFact.getStringCell(j,"cantidad"));
	  				concepto    = gridFact.getStringCell(j,"concepto");
	  				precioun	= Double.parseDouble(gridFact.getStringCell(j,"precioun"));
	  				descuent	= Double.parseDouble(gridFact.getStringCell(j,"descuent"));
	  		 	 	precioto	= Double.parseDouble(gridFact.getStringCell(j,"precioto"));
	  		 	 	fhfechax	= gridFact.getStringCell(j,"fecha");
	  		 	 	baseimp += precioto;	
	  		 	 	%>  	
				 <tr>
				  	<td width="5%">
				  		<div>
		  					<%	if(!imgfilex.equals("")) {%>
		  							<img style="width:80;height:80" src="http://www.mallproshop.com<%=imgfilex%>">
		  					<%}else{
	  							%>
	  							&nbsp;
	  						<% 
	  						  }	
	  					     %>
				  		</div>
				  	</td>
			  		<td width="15%" class="fonts6"><%=codprodu%></td>
			  	    <td width="5%"  class="fonts6"><div id="C<%=j %>"><%=formateador.format(cantidad)%></div></td>
			  	    <td width="20%" class="fonts6"><%=concepto%></td>
			  	    <td width="20%" class="input-m"><input type="text" class="input-m" style="width:100%;border:none;" id="P<%=j %>"  value="<%=formateador.format(precioun)%>" style="width:100%;height:100%"></td>
			  	    <td width="10%" class="input-m"><input class="input-m" style="width:100%;border:none;" type="text" id="D<%=j %>" onchange="obtieneDatos('<%=j%>','<%=idlineax%>','<%=idclient%>','<%=cdfactur%>','<%=idemisor %>')" value="<%=formateador.format(descuent)%>"></td>
			  	    <td width="20%" class="fonts6"><%=formateador.format(precioto)%></td>
			  	    <td width="5%"><input type="checkbox" value="<%=idlineax%>" name="idlineas[]"></td>
				</tr>
				<%	} 
				}catch (Exception e) { 
					e.printStackTrace();
					System.out.println("ERROR recuperando linea factura");
				}
			
		%>					  					
		</table>  	
		<%
		impuesto =  baseimp *(tipoPorc);
	    total=baseimp + impuesto;
	    impuesto = Math.rint(impuesto*100)/100;
	    baseimp  = Math.rint(baseimp*100)/100;
	    total    = Math.rint(total*100)/100;
		
		 %>	
		 	<br><br>
		
		<table align="right" class="piefactu">
			<tr>
				<td class="input-b1">Base imponible</td>
			  	<td class="fonts6"><div id="baseimp"><%=formateador.format(baseimp)%></td>
			</tr>
			<tr>
				<td class="input-b1">Impuesto</td>
			  	<td class="fonts6" ><div id="impuesto"><%=formateador.format(impuesto)%></div></td>
			</tr>
			<tr>
				<td class="input-b1">Total</td>
			  	<td class="fonts6"><div id="total"><%=formateador.format(total)%></div></td>
			</tr>		
		</table>	
  	</div>	
  	  
  	  	<div id="vistapre">
	  		<table>
				<tr><td align="center"><a class="boton" onClick="genFactur();">Generar</a></td></tr>
			</table>
		</div>	

			<form method="post" name="generaFactu" action="/JLet/App">
				<input type="hidden" name="controller" 	value="FacturaHttpHandler"/>			
				<input type="hidden" name="services" 	value="GeneraFacturaSrv"/>
				<input type="hidden" name="view" 		value="factura/abrirFactura.jsp"/>
				<input type="hidden" name="idemisor" 	value="<%=idemisor %>">
				<input type="hidden" name="idclient" 	value="<%=idclient%>">
				<input type="hidden" name="aniofact" 	value="">
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>">
				<input type="hidden" name="tipofact" 	value="">
				<input type="hidden" name="idtmpfra" 	value="<%=idtmpfra%>">
				<input type="hidden" name="mcagrupa" 	value="0">
				<input type="hidden" name="fhfactur" 	value="">
				<input type="hidden" name="tipologo" 	value="<%=idemisor %>">
				<input type="hidden" name="catefact" 	value="">
				<input type="hidden" name="formpago" 	value="">
				<input type="hidden" name="condpago" 	value="">
				<input type="hidden" name="facalbar" 	value="S">
				<input type="hidden" name="albawebx" 	value="S">
				<input type="hidden" name="marcados" 	value="">
				<input type="hidden" name="cdfactur" 	value="<%=cdfactur%>">
				<input type="hidden" name="factasoc" 	value="<%=factasoc%>">
				<input type="hidden" name="idorderx" 	value="<%=informad%>">
				
				
		
				
				
			</form>
  	
	</div>
	
	  <%if(!tipovist.equals("VP")){ %>
		<script>
			
			Calendar.setup({ 
		    inputField     :"fechafac",     // id del campo de texto 
		    ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
		    button     :    "lanzador"     // el id del botón que lanzará el calendario 
			});
			baseImp = '<%=baseimp%>';
			cambiaTasa(<%=tipFac.getStringCell(1, "tipofact")%>,'<%=baseimp%>');
			
  		</script>
  	<%} %>
  	
  	</div>
  
	</body>
</html>

