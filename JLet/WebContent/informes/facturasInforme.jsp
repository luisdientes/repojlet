<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>

<%
	
	HttpSession sesion = request.getSession();
	HashMap<String,String> permEmis = new HashMap<String,String>();
	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");

	String aniofact = null;
	String idemisor = null;
	String fhdesdex = null;
	String fhhastax = null;
	String predesde = null;
	String prehasta = null;
	String idclient = null;
	String logoemis = null;
	String tpclirec = null;
	String tipofact = null;
	String totalpag = "";
	String tipodata = "";
	String txmensaj  = "";
	Grid grfactur   = null; 

	Grid gdAniosx   = null; 
	
	//Texto paises
	String txcdfact = "";

	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			tipodata = io.getStringValue("tipodata");
			aniofact = io.getStringValue("aniofact");
			txmensaj = io.getStringValue("txmensaj");
			grfactur = io.getGrid("grfactur");	
			
		
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/listadoFacturasEmisor.jsp "+ e.getMessage());	
		}
	
	
		try{
			idclient = io.getStringValue("idcliere");
			if(idclient.equals("null")){
				idclient = "";
			}
		
		}catch(Exception ex) {
			idclient = "";
			System.out.println("Error - al recibir fecha desde");
		}
		
		try{
			fhdesdex = io.getStringValue("fhdesdex");
			if(fhdesdex.equals("null")){
				fhdesdex = "";
			}
		
		}catch(Exception ex) {
			fhdesdex = "";
			System.out.println("Error - al recibir fecha desde");
		}
		try{
			fhhastax = io.getStringValue("fhhastax");
			if(fhhastax.equals("null")){
				fhhastax = "";
			}
		
		}catch(Exception ex) {
			fhhastax ="";
			System.out.println("Error - al recibir fecha desde");
		}
		
		try{
			predesde = io.getStringValue("predesde");
			if(predesde.equals("null")){
				predesde = "";
			}
		
		}catch(Exception ex) {
			predesde = "";
			System.out.println("Error - al recibir precio desde");
		}
		
		try{
			prehasta = io.getStringValue("prehasta");
			if(prehasta.equals("null")){
				prehasta = "";
			}
		
		}catch(Exception ex) {
			prehasta ="";
			System.out.println("Error - al recibir precio hasta");
		}
		
		try{
			tpclirec = io.getStringValue("tpclient");
		}catch(Exception ex) {
			tpclirec ="0";
			System.out.println("Error - al recibir tpclient ");
		}
		
	}
	
	if ((idemisor.equals("1")) || (idemisor.equals("5"))){
		txcdfact = "NCF";
		
	} else {
		txcdfact = "NCF";
	}
	
	if ((fhdesdex == null) || (fhdesdex.equals(""))) {
		fhdesdex = "01/01/"+ aniofact;
	}
	
	
	
%>

<script>

function abrirFactura(namefile){
	document.abriFactu.filename.value = namefile;
	document.abriFactu.submit();
}

function redirigeGrafica(){
		document.formMenu.controller.value="InformesHttpHandler"
		document.formMenu.services.value = "ListInformeAnualSrv";
		document.formMenu.view.value = "informes/informeAnual.jsp";		
		document.formMenu.aniofact.value = "2015";
		document.formMenu.submit();
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
		<input type="hidden" name="controller" 	value="FacturaHttpHandler"/>
		<input type="hidden" name="services" 	value=""/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
		<input type="hidden" name="aniofact" 	value=""/>
		<input type="hidden" name="tpclient" 	value=""/>
		<input type="hidden" name="idcliere" 	value=""/>
		<input type="hidden" name="fhdesdex" 	value=""/>
		<input type="hidden" name="fhhastax" 	value=""/>
		<input type="hidden" name="predesde" 	value=""/>
		<input type="hidden" name="prehasta" 	value=""/>
		<input type="hidden" name="idfactur" 	value=""/>
		<input type="hidden" name="mcpagado" 	value=""/>
		<input type="hidden" name="divisaxx" 	value=""/>
		
	</form>
	

	
	
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	

<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="nompanta" ><%=txmensaj %></div>
	<div class="centradoFac" width="100%">
	<br>
	
		<!-- INICIO FORMULARIO FILTRO -->
	
	
		<!-- FIN FORMULARIO FILTRO -->
	
		<table align="center" class="reportTable" width="100%" border="0" >
			<% 
			
			  String cddivisa = ""; 
			  String antTpFac = "";
			  String tipoClie = "";
			  String logotpcl = "";
			  String mcpagado = "";
			  String formpago = "";
			  String cdestado = "";
			  String admdevol = "";
			  double totBasIm = 0;
			  double totImpue = 0;
			  double totTotal = 0;
			  double totPagad = 0;
			  double totPendi = 0;
			  
			  double pagadoxx = 0;
			  double pendient = 0;
			  
			  for (int i = 0; i < grfactur.rowCount(); i++){ 
				  
				  	cddivisa = grfactur.getStringCell(i,"cddivisa");
				  	tipoClie = grfactur.getStringCell(i,"tpclient"); 
				  	cdestado = grfactur.getStringCell(i,"cdestado");
				  	tipofact = grfactur.getStringCell(i,"tipofact");
				  	admdevol = grfactur.getStringCell(i,"admdevol");
				  	mcpagado = grfactur.getStringCell(i,"mcpagado");
				  	totalpag = grfactur.getStringCell(i,"totalpag");
				  	
				  	
					if (!antTpFac.equals(grfactur.getStringCell(i,"tipofact"))){ 

			  			%>
			  			
			  			
			  			<% if (i > 0){ %>
			  			
				  			<tr class="usuario">
								<td colspan="3">&nbsp;</td>
								<td class="fonts6" style="font-size:12px" align="center"><b>TOTAL</b></td>
								<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totBasIm) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
								<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totImpue) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
								<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totTotal) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
								
								<%	if (tipodata != null && tipodata.equals("cliimpa")){ %>
									<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b> <%= formateador.format(totPagad) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </div></td>
									<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b><%= formateador.format(totPendi) %> <%= cddivisa %></b>&nbsp;&nbsp;&nbsp;</div></td>
					  			<% } %>
							</tr>
							
						<% } %>
			  			
				  		<tr>
				  			<td colspan="100%" ><div class="input-b1"><%= grfactur.getStringCell(i,"txtpfact") %></div></td>
				  		</tr>
				  		
						<tr>
							<td>&nbsp;</td>
							<td><div class="input-b1"> <%=txcdfact%> </div></td>
							<td><div class="input-b1"> Fecha</div></td>
							<td><div class="input-b1"> Cliente </div></td>
							<td><div class="input-b1"> Base Imp.</div></td>
							<td><div class="input-b1"> Impuesto</div></td>
							<td><div class="input-b1"> Total </div></td>
							<%if (tipodata != null && tipodata.equals("cliimpa")){
								
								%>
								<td><div class="input-b1"> Pagado</div></td>
								<td><div class="input-b1"> Pendiente </div></td>
							<%
							totPendi = 0;
							totPagad = 0;
							} %>
						</tr>
				  		
				 <%	
				 		totBasIm = 0;
						totImpue = 0;
						totTotal = 0;
			  		
			  		}
			  		antTpFac = grfactur.getStringCell(i,"tipofact");
			  		
			  	%>
			  	
			  	<% if (i % 2 == 0) { %>
			  		<tr class="oddRow" style="border:1px solid;cursor:pointer;" onclick="asignaDocumento('<%=grfactur.getStringCell(i,"idfactur")%>','<%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %>')">
				<% } else { %>
					<tr class="evenRow" style="border:1px solid;cursor:pointer;" onclick="asignaDocumento('<%=grfactur.getStringCell(i,"idfactur")%>','<%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %>')">
				<% } %>
					<td align="center" class="fonts5" style="font-size:12px" onclick="abrirFactura('<%=grfactur.getStringCell(i,"filecrea") %>');" style="cursor:pointer">
						<img src="/JLet/common/img/varios/factura.png" height="24px" title="Factura" style="cursor:pointer"/>
					</td>
					
					<td class="fonts6jej" style="font-size:12px"><%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %></td>
					<td class="fonts6jej" style="font-size:12px" align="center" ><%= grfactur.getStringCell(i,"fhfactur") %></td>
					<td class="fonts6jej" style="font-size:12px"><%= grfactur.getStringCell(i,"rzsocial") %></td>
					<td class="fonts6jej" style="font-size:12px" align="right"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"baseimpo"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<td class="fonts6jej" style="font-size:12px" align="right"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptaxes"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<td class="highlightedColumn" style="font-size:12px" align="right"><%= formateador.format(Double.parseDouble(grfactur.getStringCell(i,"imptotal"))) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<%
						
						
						if (tipodata != null && tipodata.equals("cliimpa")){ 
							totalpag = grfactur.getStringCell(i,"totalpag");
							pagadoxx = Double.parseDouble(totalpag);
							pendient = Double.parseDouble(grfactur.getStringCell(i,"imptotal")) - pagadoxx;
							
							
							totPagad += pagadoxx;
							totPendi += pendient;
						%>
							<td class="highlightedColumn" style="font-size:12px" align="right"> <%= formateador.format(pagadoxx) %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </div></td>
							<td class="highlightedColumn" style="font-size:12px" align="right"><%= formateador.format(pendient) %> <%= cddivisa %>&nbsp;&nbsp;&nbsp;</div></td>
					  <% } %>
					
					<%
					
						totBasIm += Double.parseDouble(grfactur.getStringCell(i,"baseimpo"));
						totImpue += Double.parseDouble(grfactur.getStringCell(i,"imptaxes"));
						totTotal += Double.parseDouble(grfactur.getStringCell(i,"imptotal"));
						mcpagado = grfactur.getStringCell(i,"mcpagado");
						formpago = grfactur.getStringCell(i,"formpago");
						
						String imgmoney = "";
						String tpfacrec = "";
						
						/*DABER SI ES UNA FACTURA CON IMPUESTOS O NO PARA GENERAR DISTINTO CODIGO DE RECIBO*/
					%>	
				</tr>
				<% } %>
				
					<% if (grfactur.rowCount() > 0){ %>
  			
	  			<tr style="font-weight:bold;" class="usuario">
					<td colspan="3">&nbsp;</td>
					<td class="fonts6" style="font-size:12px;" align="center"><b>TOTAL</b></td>
					<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b><%= formateador.format(totBasIm) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b><%= formateador.format(totImpue) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b><%= formateador.format(totTotal) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					
					<%  if (tipodata != null && tipodata.equals("cliimpa")) { %>
				  	 		<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b><%= formateador.format(totPagad) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
							<td class="fonts6" style="font-size:12px;padding:5px" align="right"><b><%= formateador.format(totPendi) %> <%= cddivisa %></b> &nbsp;&nbsp;&nbsp; </td>
					<% } %>
				</tr>
				
			<% } %>
				
	
				
		 </table>
		 <br>
		 <br>
		 <br>
		 
		 <table align="center"  width="100%" border="0" >
		 <tr>
		 	<td align="center" onclick="redirigeGrafica();" style="cursor:pointer"><img src="/JLet/common/img/icons/pantallas/graffact.png"></td>
		 </tr>
		 <tr>
		 	<td align="center"><b>Ir a Gr�fica</b></td>
		 </tr>
		 </table>
	</div>	
</div>

	<script>
		Calendar.setup({ 
	    	inputField     :"fhdesd",     // id del campo de texto 
	    	ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
	    	button     :    "lanzador"     // el id del botón que lanzará el calendario 
		});
		
		Calendar.setup({ 
		    inputField     :"fhasta",     // id del campo de texto 
		    ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
		    button     :    "lanzador2"     // el id del botón que lanzará el calendario 
		});
	
	</script>	
	

