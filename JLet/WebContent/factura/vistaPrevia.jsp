<%@ include file="/common/jsp/include.jsp"%>

<html>

	<head>
		<title>Vista Previa</title>

	</head>

<%
   Grid rsFact = null;
   Grid gdClie = null;
   double tipoPorc = 0;
   double porcrete = 0;
   String idclient = "";
   String tpclient = "";
   String fechafac = "";
   String tipoFact = "";
   String idemisor = "";
   String formpago  = "";
   String condpago  = "";
   String catefact = "";
   String tipologo  = "";
   String idunicox = "";
   String cdintern = "";
   String cddivisa = "";
   String txtpfact = "";
   String txformpa = "";
   String txcatefa = "";
   String txcondpa = "";
   String tipovent = "";
   String autopaga = "";
   String numtrans = "";
   
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
			//rsCli 	= io.getGrid("gridClie");
			
			try {
				rsFact		= io.getGrid("gridLine");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo gridLine");		
			}
			
			
			try {
				gdClie		= io.getGrid("gridClie");
			}catch(Exception ex){
				System.err.println("ERROR - gridClie");	
			}

			try {
				idclient 	= io.getStringValue("idclient");
   			}catch(Exception ex){
   				System.err.println("ERROR - idclient");	
			}

			try {
				tpclient 	= io.getStringValue("tpclient");
   			}catch(Exception ex){
   				System.err.println("ERROR - tpclient");	
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
				porcrete 	= Double.parseDouble(io.getStringValue("porcrete"));
			}catch(Exception ex){
				porcrete = 0;
				System.err.println("ERROR - Recibiendo porcrete");	
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
				formpago	= io.getStringValue("formpago");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo formpago");	
			}
			
			
			try {
				condpago	= io.getStringValue("condpago");
			}catch(Exception ex){
				condpago = "0";
				System.err.println("ERROR - Recibiendo formpago");	
			}
			try {
				catefact	= io.getStringValue("catefact");
			}catch(Exception ex){
				catefact = "0";
				System.err.println("ERROR - Recibiendo catefact");	
			}
			
			try {
				tipologo	= io.getStringValue("tipologo");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo tipologo");	
			}

			try {
				txtpfact	= io.getStringValue("txtpfact");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo txtpfact");	
			}
			
			
			try {
				txformpa	= io.getStringValue("txformpa");
			}catch(Exception ex){
				txformpa = "0";
				System.err.println("ERROR - Recibiendo formpago");	
			}
			try {
				txcatefa	= io.getStringValue("txcatefa");
			}catch(Exception ex){
				txcatefa = "0";
				System.err.println("ERROR - Recibiendo catefact");	
			}
			
			try {
				txcondpa	= io.getStringValue("txcondpa");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo txcondpa");	
			}
			
			try {
				tipovent	= io.getStringValue("tipovent");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo tipovent");	
			}
			
			try {
				cddivisa	= io.getStringValue("cddivisa");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo tipovent");	
			}
			
			try {
				autopaga	= io.getStringValue("autopaga");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo autopaga");	
			}
			
			try {
				numtrans	= io.getStringValue("numtrans");
			}catch(Exception ex){
				numtrans  ="";
				System.err.println("ERROR - Recibiendo numtrans");	
			}
			
			
			
			
			
			
				
	}

    
    String idlineax = "";
	String concepto = "";
	String codprodu = "";
	String imgfilex = "";
	String fhfechax = "";
	String txrazons = "";
	String cifnifxx = "";	
	String telefon  = "";
	String txmailxx = "";
	
    double  precioun = 0;
	double  descuent = 0;
    double  cantidad = 0;
	double precioto  = 0;
	double baseimp   = 0;
	double impuesto  = 0;
	double impreten  = 0;
	double total     = 0;
	
	
	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
   %>

 <script>
 	
	function valida(){
		
		fecha = document.validar.fhfactur.value;
		fecha = fechaMysql(fecha);
		anio  = fecha.substring(0,4);
		
		document.validar.aniofact.value = anio;
		document.validar.informda.value = document.getElementById("informda").value;
		document.validar.factasoc.value = document.getElementById("factasoc").value;
		document.validar.codvende.value = document.getElementById("codvende").value;
		
		if (document.getElementById('mcpagado').checked){
			document.validar.mcpagado.value ="S";
		}else{
			document.validar.mcpagado.value="N";
		}
		document.validar.submit();
		window.close();
	}
	
	
	function fechaMysql(fec){
		
		anio = fec.substring(6, 10)
		mes  = fec.substring(3, 5);
		dia  = fec.substring(0, 2); 
		
		fecha = anio +"-"+mes+"-"+dia;
		return fecha;
	}


	
	function comparaFechas(fechaSel){
	
		if (compare_dates(fechaMax, fechaSel)){
			alert("Fecha anterior a la fecha m\u00e1xima de factura");
			document.getElementById("fechafac").value = "";
		} else {
			
			if (compare_dates(fechaSel,fechaHoy) ){
				if(confirm("Atenci\u00f3n va a marcar una fecha posterior a la fecha de hoy")){
				}
				else{
					document.getElementById("fechafac").value = "";
				}
			}
			
		}
	
	}
 
 </script>	

 <body class="fondo">
	
	<%		try{
				idclient = gdClie.getStringCell(0, "idclient");
				cdintern = gdClie.getStringCell(0, "cdintern");
				txrazons = gdClie.getStringCell(0,"txrazons"); 
				cifnifxx = gdClie.getStringCell(0,"txidenti");	
				telefon  = gdClie.getStringCell(0,"telefono");
				txmailxx = gdClie.getStringCell(0,"txmailxx");
			} catch (Exception e) { 
				e.printStackTrace();
				System.out.println("ERROR recuperando linea Cliente"); 
			}
	
	%>
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
		
	<div class="centradoFac" style="width:80%">
	
		<table cellspacing="10" border="0" align="center" class="tablaGrande">
				<tr>
					<td class="input-b1" style="width:10%">Tipo</td>
					<td  style="width:15%" class="fonts6">
						<%=txtpfact %>
					</td>
					<td class="input-b1" style="width:20%">Categoria</td>
					<td style="width:200px;" class="fonts6">
					<%=txcatefa %>
					</td>
					<td class="input-b1" style="width:20%">Fecha</td>
				  	<td style="width:15%" class="fonts6">
				  		<%=fechafac %>
				  	</td>
				</tr>
				<tr>
					<td class="input-b1">Id. Cliente</td>
					<td  style="width:200px;" class="fonts6"><%=cdintern %></td>
				  	<td class="input-b1" style="width:20%">Razon Social</td>
				  	<td style="width:35%;" class="fonts6">
				  		<%=txrazons %>
				  	</td>
				  	<td class="input-b1">Ident.</td>
				  	<td style="width:200px;" class="fonts6">
				  		<%=cifnifxx%>
				  	</td>
				</tr>
				<tr>
				  	<td class="input-b1">Forma Pago</td>
				  	<td  width=20% class="fonts6"><%=txformpa %></td>
				  	 
				  	 <% if (idemisor.equals("1")){ %>
				  	 	<td class="input-b1" >Condiciones de Pago</td>
					  	<td  style="width:200px;" class="fonts6">
					  		<%=txcondpa %>
					  	 </td>
					  <% } else if (idemisor.equals("3")){ %>
					  	<td style="width:200px;" class="fonts6">
					  		<%=tipovent%>
				  	 	</td>
				  	 	<td  style="width:200px;" class="fonts6">
				  	 		<%=numtrans %>
				  	 	</td>
					  <% } else { %>
					  	<td>&nbsp;</td>
						<td>&nbsp;</td>
					  <% } %>
				  	<td class="input-b1" >Cod. Vendedor</td>
					<td class="input-m" style="width:200px;" class="fonts6">
				  	 		<input class="input-m" style="border:none" type="text" name="txcodvende" id="codvende"/>
				  	 	</td>
				</tr>
				<tr>
				  	<td class="input-b1">Pagado </td>
				  	<td  width=20% class="fonts6" align="center"><input type="checkbox" value="S" id="mcpagado" <% if(autopaga!=null && autopaga.equals("S")){%>checked<%} %>></td>
				  	<td>&nbsp;</td>
					<td>&nbsp;</td>
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
  	     		<td width="15%" class="input-b1">Ident. Uni.</td>
		  	     <td width="5%" class="input-b1">Cant</td>
		  	     <td width="25%" class="input-b1">Concepto</td>
		  	     <td width="10%" class="input-b1">Precio /U</td>
		  	     <td width="10%" class="input-b1">%Dto</td>
		  	     <td width="20%" class="input-b1">Total</td>
				
  	  		</tr>
  	  		<tr>
  	  			<td colspan="8"><hr></td>	
  	  		</tr>
  		
  		<%
  			try {
	  			for (int j = 0; j < rsFact.rowCount(); j++){
	  		 	  	
	  				try{
	  					imgfilex 	= rsFact.getStringCell(j,"imagedet");
	  				}catch(Exception ex){
	  					System.out.println("No tiene Imagen");
	  					imgfilex = "";
	  				}
	  			
	  				codprodu 	= rsFact.getStringCell(j,"codprodu");
	  				idunicox 	= rsFact.getStringCell(j,"idunicox");
	  				cantidad 	= Double.parseDouble(rsFact.getStringCell(j,"cantidad"));
	  				concepto    = rsFact.getStringCell(j,"concepto");
	  				precioun	= Double.parseDouble(rsFact.getStringCell(j,"precioun"));
	  				descuent	= Double.parseDouble(rsFact.getStringCell(j,"descuent"));
	  		 	 	precioto	= Double.parseDouble(rsFact.getStringCell(j,"precioto"));
	  		 	 	fhfechax	= rsFact.getStringCell(j,"fecha");
	  		 	 	baseimp += precioto;	
	  		 	 	%>  	
				  		<tr>
				  			<td width="5%">
				  					<div>
				  					<%	if(!imgfilex.equals("")) {%>
				  							<img style="width:80;height:80" src="http://www.mallproshop.com<%=imgfilex%>">
				  					<%}
				  						else{
				  							%>
				  							&nbsp;
				  							<% 
				  						}	
				  					%>
				  					</div>
				  			</td>
					  		<td width="15%" class="fonts6"><%=codprodu%></td>
					  		<td width="15%" class="fonts6"><%=idunicox%></td>
					  	    <td width="5%"  class="fonts6"><%=formateador.format(cantidad)%></td>
					  	    <td width="20%" class="fonts6"><%=concepto%></td>
					  	    <td width="10%" class="fonts6"><%=formateador.format(precioun)%> <%=cddivisa %></td>
					  	    <td width="10%" class="fonts6"><%=formateador.format(descuent)%></td>
					  	    <td width="20%" class="fonts6"><%=formateador.format(precioto)%> <%=cddivisa %></td>
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
		impreten =  baseimp *(porcrete);
	    total=baseimp + impuesto - impreten;
	    
	    impuesto = Math.rint(impuesto*100)/100;
	    baseimp  = Math.rint(baseimp*100)/100;
	    total    = Math.rint(total*100)/100;
		
		 %>	
		 	<br><br>
		
		<table align="right" class="piefactu">
			<tr>
				<td class="input-b1">Base imponible</td>
			  	<td class="fonts6"><div id="baseimp"><%=formateador.format(baseimp)%> <%=cddivisa %></td>
			</tr>
			<tr>
				<td class="input-b1">Impuesto</td>
			  	<td class="fonts6" ><div id="impuesto"><%=formateador.format(impuesto)%> <%=cddivisa %></div></td>
			</tr>
			<tr id="muesrete" <%if(porcrete > 0){ %> style="table-row"<%}else{%>style="display:none" <%} %>>
				<td class="input-b1">Retención IRPF</td>
				<td class="fonts6"><input style="border:none" type="text" name="retencio" id="retencio" disabled class="fonts6" value="<%=formateador.format(impreten)%> <%=cddivisa %>"></td>
			</tr>
			<tr>
				<td class="input-b1">Total</td>
			  	<td class="fonts6"><div id="total"><%=formateador.format(total)%> <%=cddivisa %></div></td>
			</tr>		
		</table>	
  	</div>	
  	
		<br>
		<br>
		<table>
			<tr>
				<td align="center" class="input-b1">Factura / albarán asociado</td>
				<td align="center"><input type="text" class="input-m" maxlength=30 style="width:450px;text-align:left" id="factasoc">
			</td>
			</tr>
		
			<tr>
				<td align="center" class="input-b1">Datos adicionales</td>
				<td align="center"><textarea class="input-m" style="width:450px; height:200px;text-align:left" id="informda"></textarea></td>
			</tr>
		</table>
		
		<div id="vistapre">
	  		<table style="width:90%; align;center">
				<tr>
					<td align="center">
						<a class="boton" onClick="valida();">Validar</a>
						
					</td>
				</tr>
			</table>
		</div>
		
		
		<form method="post" name="validar" action="/JLet/App">
			<input type="hidden" name ="controller" 	value="FacturaHttpHandler"/>
			<input type="hidden" name ="services"	value="ValidarFacturaSrv.GeneraFacturaSrv"/>
			<input type="hidden" name ="view" 		value="factura/abrirFactura.jsp"/>
			<input type="hidden" name ="idemisor" 	value ="<%=idemisor %>">
			<input type="hidden" name ="idclient" 	value="<%=idclient%>">
			<input type="hidden" name ="tpclient" 	value="<%=tpclient%>">
			<input type="hidden" name ="aniofact" 	value ="">
			<input type="hidden" name ="fhfactur" 	value="<%=fechafac%>"/>			
			<input type="hidden" name ="tipofact" 	value="<%=tipoFact%>">
			<input type="hidden" name ="formpago" 	value ="<%=formpago %>">
			<input type="hidden" name ="condpago" 	value ="<%=condpago %>">
			<input type="hidden" name ="catefact" 	value ="<%=catefact %>">
			<input type="hidden" name ="tipologo" 	value ="<%=tipologo %>">
			<input type="hidden" name ="informda" 	value ="">
			<input type="hidden" name ="factasoc" 	value ="">
			<input type="hidden" name ="codvende" 	value ="">
			<input type="hidden" name ="mcpagado" 	value ="">
			<input type="hidden" name ="mcagrupa" 	value="0">  
			<input type="hidden" name ="porcrete" 	value="<%=porcrete %>"> 
			
		</form>
		
  	
	</div>
	</body>

</html>
