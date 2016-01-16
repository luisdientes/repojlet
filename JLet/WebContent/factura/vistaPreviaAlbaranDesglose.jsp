<html>
<%@ include file="/common/jsp/include.jsp"%>
<script type="text/javascript" src="factura/js/albaran.js"/></script>
	<script src="common/js/validacionesComunes.js"></script>
	<script src="common/js/calendar.js" type="text/javascript"></script>
	<script src="common/js/calendar-es.js" type="text/javascript"></script>
	<script src="common/js/calendar-setup.js" type="text/javascript"></script>
	<link rel="stylesheet" href="common/css/calendario.css" type="text/css" />

	<head>
		<title>Desglose Albarán</title>

	</head>
   <%
   Grid gridFact 	= null;
   Grid gdClient 	= null;
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
	String telefono = "";
	String txmailxx = "";
	String concepto = "";
	String tpclient = "";
	String idtmpfra = "";
	String idalbara = "";
	
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
				gdClient		= io.getGrid("gridClie");
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
				
	}
 
   
   try{
		
		idclient = gdClient.getStringCell(0,"idclient");
		txrazons = gdClient.getStringCell(0,"txrazons"); 
		cifnifxx = gdClient.getStringCell(0,"txidenti");	
		telefono = gdClient.getStringCell(0,"telefono");
		txmailxx = gdClient.getStringCell(0,"txmailxx");
	} catch (Exception e) { 
		e.printStackTrace();
		System.out.println("ERROR recuperando linea Cliente"); 
	}
    
   %> 

	<body class="fondo">
	
	<div class="testata"><img src="/JLet/common/img/icons/factura.png"></div>
	
	<div class="centradoFac">
	<table cellspacing="10" border="0" align="center" class="tablaGrande">
			  <tr>
			  	 	<td class="input-b1">Fecha</td>
			  		<td class="fonts6"><div id="fhfechax"><%=fechafac%></div></td>
			  		<input type="hidden" id="fechafac" value="<%=fechafac%>"/>
			  	 	<td class="input-b1">Razon Social</td>
			     	<td class="fonts6"><div id="txrazons"><%=txrazons%></div></td>
			  	 	<td class="input-b1">CIF /NIF</td>
			  	 	<td class="fonts6"><div id="txdirecc"><%=cifnifxx%></div></td>
			  </tr>
			   <tr>
			  		<td class="input-b1">Cliente</td>
			  	 	<td class="fonts6"><div id="idclient"><%=idclient%></div></td>
			  	 	<td class="input-b1">Telefono</td>			  	 
			  	 	<td class="fonts6"><div  id="telefon"><%=telefono%></div></td>
			  	 	<td class="input-b1">Email</td>
			  	 	<td class="fonts6"><div id="txmailxx"><%=txmailxx%></div></td>
			  </tr>	
			  <tr>
				  	<td class="input-b1">Tipo</td>
			  	 	<td class="fonts6"><div id="tipofact">Conduce</div></td>
			  	 	<td colspan="4">&nbsp;</td>			  	 
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
  	   			<td width="5%"  class="input-b1">Imagen</td>
  	   			<td width="15%" class="input-b1">C&oacute;digo R.</td>
		  	    <td width="5%"  class="input-b1">Cant</td>
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
			  	    <td width="20%" class="input-m"><input class="input-m" style="width:100%;border:none;"  type="text" id="P<%=j %>"  value="<%=formateador.format(precioun)%>"></td>
			  	    <td width="10%" class="input-m"><input class="input-m" style="width:100%;border:none;"  type="text" id="D<%=j %>" onchange="obtieneDatos('<%=j%>','<%=idlineax%>','<%=idclient%>','<%=cdfactur%>','<%=idemisor %>')" value="<%=formateador.format(descuent)%>"></td>
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
				<tr><td align="center"><a class="boton" onClick="genAlbaranDesglosado();">Generar</a></td></tr>
			</table>
		</div>	
  	 
			
		<form method="post" name="generaFactu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="FacturaHttpHandler"/>			
			<input type="hidden" name="services" 	value="GeneraDesgAlbaranSrv"/>
			<input type="hidden" name="view" 		value="factura/abrirFacturaMultiple.jsp"/>
			<input type="hidden" name="emisclie" 	value="<%=idemisor %>">
			<input type="hidden" name="receclie" 	value="<%=idclient%>">
			<input type="hidden" name="aniofact" 	value="">
			<input type="hidden" name="tpclient" 	value="<%=tpclient%>">
			<input type="hidden" name="tipofact" 	value="">
			<input type="hidden" name="idtmpfra" 	value="<%=idtmpfra%>">
			<input type="hidden" name="mcagrupa" 	value="0">
			<input type="hidden" name="fhfactur" 	value="">
			<input type="hidden" name="tipologo" 	value="0">
			<input type="hidden" name="catefact" 	value="">
			<input type="hidden" name="formpago" 	value="">
			<input type="hidden" name="condpago" 	value="">
			<input type="hidden" name="facalbar" 	value="S">
			<input type="hidden" name="marcados" 	value="">
			<input type="hidden" name="cdfactur" 	value="<%=cdfactur%>">
		</form>
  	
	</div>
	
	</body>
</html>

