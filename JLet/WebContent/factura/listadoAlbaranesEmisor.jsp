<%@ include file="/common/jsp/include.jsp"%>
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />



<%

	HttpSession sesion = request.getSession();
	HashMap<String,String> permEmis = new HashMap<String,String>();
	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");
	
	int devuelto  = 0;
	String aniofact = null;
	String idemisor = null;
	String fhdesdex = null;
	String fhhastax = null;
	String logoemis = null;
	String predesde = null;
	String prehasta = null;
	String idclient = null;
	String tpclient = null;
	Grid grfactur   = null; 
	Grid gridClie	= null;
	Grid gdAniosx   = null; 
	
	
	
	

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			aniofact = io.getStringValue("aniofact");
			idemisor = io.getStringValue("idemisor");
			tpclient = io.getStringValue("tpclient");
			logoemis = io.getStringValue("logoemis");
			grfactur = io.getGrid("grfactur");
			gridClie = io.getGrid("gridClie");
			gdAniosx = io.getGrid("gdAniosx");
			
			System.out.println("El idclient es  --------"+idclient);
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/listadoAlbaranesEmisor.jsp "+ e.getMessage());	
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

	}
	
	

%>
<head>
	<title>Listado Albaranes</title>
</head>	








<script>
	
	var arrAlbarane	= new Array();
	var arridCient	= new Array();
	var arrAniofac	= new Array();
	var cdalbaranx	= new Array();

	function cargaAlbaranes(){
		
		<%
			int pendient = 0;  
				for (int i = 0; i < grfactur.rowCount(); i++){
					pendient = Integer.parseInt(grfactur.getStringCell(i,"pendient"));
					if (pendient >0){
				%>
						arrAlbarane[<%=i%>] = '<%=grfactur.getStringCell(i,"cdfactur")%>';
						arridCient [<%=i%>] = '<%=grfactur.getStringCell(i,"idclient")%>';
						arrAniofac[<%=i%>]  = '<%= grfactur.getStringCell(i,"aniofact") %>';
						cdalbaranx[<%=i%>]   ='<%= grfactur.getStringCell(i,"preffact") %><%= grfactur.getStringCell(i,"cdfactur") %>';
		<% }
		}
		%>
	}
	
	function getAlbaran(idalbara){
		return arrAlbarane[idalbara];
		
	}
	

	
	
	function leeAlbaran(idalbara){
		
		idclient = "";
		encuentra  = 0;
		cdalbaran = "";
		for(i=0;i<arrAlbarane.length ;i++){
			
			
			if((cdalbaranx[i] == idalbara)){
				
				idclient   	= arridCient[i];
				cdalbaran 	= cdalbaranx[i];
				idalbara  	= parseInt(arrAlbarane[i]);
				aniofac		= arrAniofac[i];
				encuentra = 1;
				
			}
		}
		
		if (encuentra == 1){	
			document.seleAlba.idalbara.value= idalbara;
			document.seleAlba.aniofact.value= aniofac;
			document.seleAlba.factasoc.value= cdalbaran;
			document.seleAlba.idclient.value= idclient;
			
			document.seleAlba.submit();
		} else {
			alert("No se encuentra albaran");
		}
		
	}
	
	function regenera(idfactur){
		document.formRegenera.idfactur.value = idfactur;
		document.formRegenera.submit();
	}
	
	function borraFactura(idfactur, tpfactur, aniofact){
		
		document.formBorra.idfactur.value = idfactur;
		document.formBorra.tpfactur.value = tpfactur;
		document.formBorra.aniofact.value = aniofact;
		
		alertify.confirm("<p>&iquest;Seguro que desea borrar el albar&aacute;n", function (e) {
			if (e) {
				document.formBorra.submit();
			}
		}); 
		
		
	}
	
	
	cargaAlbaranes();	
</script>




	<!-- Descargar -->
	
	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
		<input type="hidden" name="idusuari" value="1"/>
		<input type="hidden" name="tipofile" value="FRA"/>
		<input type="hidden" name="pathfile" value=""/>
		<input type="hidden" name="filename" value=""/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
	</form>
 
 	<!-- Busqueda con filtros -->
 
	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value="ListAlbaranesEmisorSrv"/>
		<input type="hidden" name="view" value="factura/listadoAlbaranesEmisor.jsp"/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
		<input type="hidden" name="aniofact" value=""/>
		<input type="hidden" name="idcliere" value=""/>
		<input type="hidden" name="fhdesdex" value=""/>
		<input type="hidden" name="fhhastax" value=""/>
		<input type="hidden" name="predesde" value=""/>
		<input type="hidden" name="prehasta" value=""/>
		<input type="hidden" name="selaniof" value=""/>
		
	</form>
	
	
	<!-- Generar vista previa albaran paara factura -->
	<form method="post" name="seleAlba" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value="VistaPreviaAlbaSrv"/>
		<input type="hidden" name="view" value="factura/vistaPreviaAlba.jsp"/>
		<input type="hidden" name ="idalbara" value="">
		<input type="hidden" name ="idclient" value="">
		<input type="hidden" name ="aniofact" value="">
		<input type="hidden" name ="factasoc" value ="">
		<input type="hidden" name ="idemisor" value="<%=idemisor%>">
	
		<input type="hidden" name ="tipovist" value="AL">
	</form>	
	
	
	<form method="POST" name="formRegenera" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value="RegeneraFacturaSrv"/>
		<input type="hidden" name="view" 		value="factura/abrirFactura.jsp"/>
		<input type="hidden" name="idfactur" 	value="">
	</form>
	
	<form method="POST" name="formBorra" action="/JLet/App">
		<input type="hidden" name="controller"  value="FacturaHttpHandler"/>
		<input type="hidden" name="services" 	value="BorraFacturaSrv"/>
		<input type="hidden" name="view" 		value="factura/resulBorra.jsp"/>
		<input type="hidden" name="idfactur" 	value="">
		<input type="hidden" name="tpfactur" 	value="">
		<input type="hidden" name="aniofact" 	value="">
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>">
	</form>


<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/title-listado-albaran.png"/></div>	
<div class="nompanta" >Listado Conduces</div>	
	<div class="centradoFac" width="100%">
	
	<!-- INICIO FORMULARIO FILTRO -->
		<table width=100% align="center" >
			<tr>
				<td class="input-b1" nowrap width=18%>Cliente :</td>
				<td nowrap width=18%>
					<select id="selidcli" class="input-m" style="width:100%" >
						<option value="">-- Todos --</option> 
						<% 
				 			String idclierc ="-1";
				 
							for (int i = 0; i < gridClie.rowCount(); i++){
					 			idclierc = gridClie.getStringCell(i,"idclient");
								if(idclient.equals(idclierc)) {%>
									<option value="<%=idclierc  %>" selected="true"  > <%= gridClie.getStringCell(i,"rzsocial") %></option>
							<% }else{%>
		  							<option value="<%=idclierc  %>"  ><%= gridClie.getStringCell(i,"rzsocial") %></option>
							 <%}
				    		 }
					 		%>  
					</select>	
				</td>
				<td class="input-b1" nowrap width=20%>Fecha desde :</td>
				<td  nowrap width=20%><input style="position:relative;top:-5px" type="text" style="width:97%" id="fhdesd" class="input-m"  value="<%=fhdesdex%>">&nbsp;<img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Factura" id="lanzador"></td>
				<td class="input-b1" nowrap width=20%>Fecha Hasta :</td>
				<td nowrap width=15%><input style="position:relative;top:-5px" type="text" size=12 id="fhasta" class="input-m"  value="<%=fhhastax%>">&nbsp;<img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Factura" id="lanzador2"></td>
			</tr>
            <tr>
				<td colspan=6>&nbsp;</td>
			</tr>
			<tr>
				<td class="input-b1" nowrap width=15%>Importe desde :</td>
				<td ><input type="text" style="width:100%" id="impdesde" class="input-m"   value="<%=predesde%>"></td>
				<td class="input-b1" nowrap>Importe Hasta :</td>
				<td ><input type="text" style="width:100%" id="imphasta" class="input-m"  value="<%=prehasta%>"></td>
				<td class="input-b1" nowrap>AÑO :</td>
				<td >
					<select id="selaniof" class="input-m" style="width:100%;">
						<option value="">-- Todos --</option> 
						<% 
				 		
				 
							for (int i = 0; i < gdAniosx.rowCount(); i++){
					 			idclierc = gdAniosx.getStringCell(i,"aniofact");%>
									<option value="<%= gdAniosx.getStringCell(i,"aniofact") %>" > <%= gdAniosx.getStringCell(i,"aniofact") %></option>
							<% 
				    		 }
					 		%>  
					</select>	
				</td>
			</tr>
			<tr>
				<td colspan=6>&nbsp;</td>
			</tr>
			<tr>
					<td align="center" class="input-b1" colspan=2>LECTOR COD. BARRAS</td>
					<td align="center" colspan=2><input style="width:100%" class="input-m" type="text" onchange="leeAlbaran(this.value)" /></td>
			</tr>
			<tr>
				<td colspan=6>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=6 align="center" ><a class="boton" style="font-family:Arial, Helvetica, sans-serif" onclick="listAlbar()">Buscar</a></td>
			</tr>
		</table>
		<!-- FIN FORMULARIO FILTRO -->

			<table width=100% align="center" class="tab-listado">
				<tr>
		  			<td colspan="7"><div class="input-b1">Conduce</div></td>
		  		</tr>
				<tr>
					<td><div class="input-b1">&nbsp;</div></td>
					<td><div class="input-b1">&nbsp;</div></td>
					<td><div class="input-b1"> Codigo Cond.</div></td>
					<td><div class="input-b1"> Fecha</div></td>
					<td><div class="input-b1"> Cliente </div></td>
					<td><div class="input-b1"> Base Imp.</div></td>
					<td><div class="input-b1"> &nbsp; </div></td>
				</tr>
		<% 
		  String antTpFac = "";
		  String cdfactur = "";
		  int idalbara = 0;
		 
		 
		  
		  for (int i = 0; i < grfactur.rowCount(); i++){ 
			  	idalbara = Integer.parseInt(grfactur.getStringCell(i,"cdfactur"));
			  	pendient = Integer.parseInt(grfactur.getStringCell(i,"pendient"));
			  	devuelto = Integer.parseInt(grfactur.getStringCell(i,"devuelto"));
			  	String cddivisa = grfactur.getStringCell(i,"cddivisa");
			  	cdfactur = grfactur.getStringCell(i,"preffact")+" "+grfactur.getStringCell(i,"cdfactur");
		  %>
				<% if (i % 2 == 0) { %>
			  		<tr class="oddRow" style="border:1px solid">
				<% } else { %>
					<tr class="evenRow" style="border:1px solid">
				<% } %>
				
				<%  if (devuelto >0){%>
						<td> <img title="Logo" height="24px" src="/JLet/common/img/icons/icondev.png" style="background-color:#FFFFFF"></td>
					<% }else{ %>
						<td>&nbsp;</td>
				<% } %>
			
					<td class="fonts6jej" style="font-size:12px" align="center" onclick="abrirFactura('<%=grfactur.getStringCell(i,"filecrea") %>');" style="cursor:pointer">
						<img src="/JLet/common/img/varios/factura.png" width="20px" height="25px" title="Albarán"/></td>
					<td class="fonts6jej" style="font-size:12px"><%= grfactur.getStringCell(i,"preffact") %> <%= grfactur.getStringCell(i,"cdfactur") %></td>
					<td class="fonts6jej" style="font-size:12px" align="center"><%= grfactur.getStringCell(i,"fhfactur") %></td>
					<td class="fonts6jej" style="font-size:12px"><%= grfactur.getStringCell(i,"rzsocial") %></td>
					<td class="highlightedColumn" style="font-size:12px" align="right"><%= grfactur.getStringCell(i,"baseimpo") %> <%= cddivisa %> &nbsp;&nbsp;&nbsp; </td>
					<%if(pendient > 0) {%>
					
					<td class="fonts6jej" style="font-size:12px" align="center"><a class="boton" onclick="verAlbaran(<%= idalbara %>,<%= grfactur.getStringCell(i,"idclient") %>,<%= grfactur.getStringCell(i,"aniofact") %>,'<%=cdfactur%>')">Generar Factura</a></td>
					
					 <% }else{ %>
					<td class="fonts6jej" style="font-size:12px" align="center">FACTURADO</td>
					<% } %>
					<% if (permEmis.containsKey("REGEFACT")){ %>	
						<td style="cursor:pointer" onclick="regenera('<%= grfactur.getStringCell(i,"idfactur") %>')"><img width=20px src="/JLet/common/img/icons/barita.png"/></td>
					<% }
					
					  if(i == 0){
					 %>
						<td width="5%" class="fonts6jej"><img src="common/img/varios/delete.jpg" width=20 height=20 style="cursor:pointer" onclick="borraFactura('<%=grfactur.getStringCell(i,"idfactur")%>','<%=grfactur.getStringCell(i,"tipofact")%>','<%=grfactur.getStringCell(i,"aniofact")%>')"></td>
					<% }
		
					%>
				</tr>
		 <% } %>
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



				