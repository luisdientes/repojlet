<%@ include file="/common/jsp/include.jsp"%>
<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>

<%

	Grid gdSubast = null;
	Grid gdCotiza = null;
	Grid gdPaises = null;
	ArrayList<String> arpaises = null;
	ArrayList<String> arproduc = null;
	String txmensaj = "";
	String fechames = "";
	String mcactivo = "";
	String idprodwe = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gdSubast = io.getGrid("gdSubast");	
			gdCotiza = io.getGrid("gdCotiza");
			gdPaises = io.getGrid("gdPaises");
			arpaises = io.getArrayList("arpaises");
			arproduc = io.getArrayList("arproduc");
			
			try{
				fechames = io.getStringValue("fechames");
			}catch(Exception ex){
				System.err.println("ERROR - Recibiendo los valores en comercio/listEnvio.jsp "+ ex.getMessage());	
				
			}
			
			try{
				idprodwe = io.getStringValue("idprodwe");
				
				if(idprodwe.equals("null")){
					idprodwe ="";
				}
			}catch(Exception ex){
				idprodwe ="";
				System.err.println("ERROR - Recibiendo los valores en comercio/listEnvio.jsp "+ ex.getMessage());	
				
			}
			
			txmensaj = io.getStringValue("txmensaj");	
			mcactivo = io.getStringValue("mcactivo");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listEnvio.jsp "+ e.getMessage());	
		}
	}
	
%>


<head>
	<script>
		
		function verSubastaDetalle(idcodsub){
			document.formdetasuba.idcodsub.value = idcodsub;
			document.formdetasuba.submit();
		}
		
		function altaInfo(idcodsub,tipoinfo){
			
			document.formsuba.controller.value	= "SubastaHttpHandler";
			document.formsuba.services.value 	= "InitInfoSrv";
			document.formsuba.view.value		= "subasta/altaInfoSubasta.jsp";	
			document.formsuba.tipoinfo.value	= tipoinfo;		
			document.formsuba.idcodsub.value 	= idcodsub;
			
			ventana = window.open("/JLet/App?controller=SubastaHttpHandler&services=InitInfoSrv&view=subasta/altaInfoSubasta.jsp&tipoinfo="+ tipoinfo +"&idcodsub="+ idcodsub,'','toolbar=no,titlebar=no,menubar=no,scrollbars=auto,resizable=yes,maximize=yes,width=450,height=600');
			
		}
		
		function cerrarVentana(){
			ventana.close();
		}
		
		function filtrarListado(){
			document.formsuba.controller.value	= "SubastaHttpHandler";
			document.formsuba.services.value 	= "ListSubastaSrv";
			document.formsuba.view.value		= "subasta/listSubasta.jsp";
			document.formsuba.idcodsub.submit();
		}
		
		function selecTipoVenta(tiposele){
			document.formsuba.controller.value	= "SubastaHttpHandler";
			document.formsuba.services.value 	= "ListSubastaSrv";
			document.formsuba.view.value		= "subasta/listSubasta.jsp";
			document.formsuba.tipovent.value = tiposele;	
			document.formsuba.submit();
		}
		
		function selecEstado(estado){
			document.formsuba.controller.value	= "SubastaHttpHandler";
			document.formsuba.services.value 	= "ListSubastaSrv";
			document.formsuba.view.value		= "subasta/listSubasta.jsp";
			document.formsuba.mcactivo.value = estado;	
			document.formsuba.submit();
		}
		
		function resetFiltros(){
			document.formsuba.controller.value	= "SubastaHttpHandler";
			document.formsuba.services.value 	= "ListSubastaSrv";
			document.formsuba.view.value		= "subasta/listSubasta.jsp";
			document.formsuba.txnombre.value = "";
			document.formsuba.idpaisxx.value = "";
			document.formsuba.tipovent.value = "";
			document.formsuba.mcactivo.value = "";
			document.formsuba.idprodwe.value = "";
			document.formsuba.submit();
		}
		
		
		function filtar(){
			document.formsuba.controller.value	= "SubastaHttpHandler";
			document.formsuba.services.value 	= "ListSubastaSrv";
			document.formsuba.view.value		= "subasta/listSubasta.jsp";
			document.formsuba.submit();
		}
		
		function irUrl(urldestixx){
			
			window.open(urldestixx);
		}
		
		function desactivar(idsuba,estado){
			document.formsuba.controller.value	= "SubastaHttpHandler";
			document.formsuba.services.value 	= "ListSubastaSrv";
			document.formsuba.view.value		= "subasta/listSubasta.jsp";
			
			document.formAltaSub.idcodsub.value = idsuba;
			document.formAltaSub.desactiv.value = estado;
			document.formAltaSub.submit();
		}
		
	</script>
</head>


<body>
	<div class="fondo2">
	   <div class="testata"><img src="/JLet/common/img/icons/title-listado-subasta.png"></div>
	   <div class="nompanta" >Listado Subastas</div>
			<div class="centrado" style="width:95%">
			
				<form name="formsuba" action="/JLet/App" method="POST">
					<input type="hidden" name="controller" 	value="SubastaHttpHandler"/>
					<input type="hidden" name="services"	value="ListSubastaSrv"/>
					<input type="hidden" name="view" 		value="subasta/listSubasta.jsp"/>
					<input type="hidden" name="tipoinfo" 	value=""/>
					<input type="hidden" name="idcodsub" 	value=""/>
					<input type="hidden" name="tipovent" 	value=""/>
					<input type="hidden" name="txnomnbr" 	value=""/>
					<input type="hidden" name="mcactivo" 	value="<%=mcactivo%>"/>
	
					<table width="70%" align="center">
						<tr>
							<td class="input-b1" width="15%">País</td>
							<td>
								<select name="idpaisxx" style="width:100%;" class="input-m">
									<option value='' selected >&nbsp;</option>
									<% for (int i = 0; i < gdPaises.rowCount(); i++) { 
										if (arpaises.contains(gdPaises.getStringCell(i,"isocodex"))){ %>
											<option value='<%=gdPaises.getStringCell(i,"isocodex")%>'><%=gdPaises.getStringCell(i,"txnombre")%></option>
									<%  }
									} %>
								</select>
							</td>
							<td class="input-b1"  width="15%">Producto</td>
							<td>
								<select name="txnombre" style="width:100%;" class="input-m">
									<option value=''>&nbsp;</option>
									<% for (int i = 0; i < arproduc.size(); i++) { %>
											<option value='<%=arproduc.get(i)%>'><%=arproduc.get(i)%></option>
									<% } %>
								</select>
							</td>
							<td align="center">
								<img src="/JLet/common/img/icons/pantallas/subasta.png" width="24px" onclick="selecTipoVenta('S')" style="cursor:pointer"/>
							</td>
							<td align="center">
								<img src="/JLet/common/img/varios/carrito.png" width="24px" onclick="selecTipoVenta('V')" style="cursor:pointer"/>
							</td>
							<td ><img onclick="selecEstado('S')" style="cursor:pointer" src="/JLet/common/img/varios/botonverde.png" width="20px"/></td>
							<td ><img onclick="selecEstado('N')" style="cursor:pointer" src="/JLet/common/img/varios/botonrojo.png" width="20px"/></td>
							<td width="5%">&nbsp;</td>
							<td><a class="boton" onClick="resetFiltros()" style="cursor:pointer">Reset Filtro</a></td>
							<td width="5%">&nbsp;</td>
						</tr>
						<tr>
							<td class="input-b1"  width="15%">Fecha desde</td>
							<td style="width:20%"><input type="text" name="fhdesdex" size=12 id="fhdesd" class="input-m"  value="<%=fechames%>" style="width:85%"><img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:5px" width="24" height="24" border="0" title="Fecha Subasta" id="lanzador"></td></td>
							<td class="input-b1"  width="15%">Fecha hasta</td>
							<td><input type="text" name="fhhastax" size=12 id="fhasta" class="input-m"  value="" style="width:85%"><img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:5px" width="24" height="24" border="0" title="Fecha Subasta" id="lanzador2"></td></td>
						</tr>
						<tr>
							<td class="input-b1"  width="15%">ID product Web</td>
							<td><input type="text" name="idprodwe" value="<%=idprodwe %>" size=12 class="input-m" style="width:100%"></td>
					</tr>
						<tr>
						<td colspan=4 align="center">&nbsp;</td>
					</tr>
					<tr>
						<td colspan=4 align="center"><a class="boton" onClick="filtar()">Filtrar</a></td>
					</tr>	
					</table>
					
					<table width="90%" align="center">
						<tr class="fonts">
							<td width="5%"  align="center">&nbsp;</td>
							<td width="8%"  align="center"><div class="input-b1">Web</div></td>
							<td width="20%" align="center"><div class="input-b1">Nombre</div></td>
							<td width="8%"  align="center"><div class="input-b1">Precio Sal.</div></td>
							<td width="8%"  align="center"><div class="input-b1">Pr. Min.</div></td>
							<td width="7%"  align="center"><div class="input-b1">F. Venta</div></td>
							<td width="7%"  align="center"><div class="input-b1">H. Venta</div></td>
							<td width="7%"  align="center"><div class="input-b1">F. Fin</div></td>
							<td width="7%"  align="center"><div class="input-b1">H. Fin</div></td>
							<td width="8%"  align="center"><div class="input-b1">Cotizacion</div></td>
							<td width="15%" align="center"><div class="input-b1">Fh. Cotizacion</div></td>
						</tr>
						
						<% System.out.println(" EXISTEN  gdSubast.rowCount() ----------------------------- "+  gdSubast.rowCount()); %>
						
						<% for (int i = 0; i < gdSubast.rowCount(); i++) { 
							
							String idcodsub = "";
							String txwebxxx = "";
							String idpaisxx = "";
							String txusuari = "";
							String tipovent = "";
							String idproduc = "";
							String txnombre = "";
							String preciosa = "";
							String divisaxx = "";
							String cdintern = "";
							String urlexter = "";
							String preciomi = "";
							String fechvent = "";
							String horavent = "";
							String finfecve = "";
							String finhorve = "";
	
							String cotizaci = "";
							String fhcotiza = "";
							
							try {
								idcodsub = gdSubast.getStringCell(i,"idcodsub");
								txwebxxx = gdSubast.getStringCell(i,"txwebxxx");
								idpaisxx = gdSubast.getStringCell(i,"idpaisxx");
								txusuari = gdSubast.getStringCell(i,"txusuari");
								tipovent = gdSubast.getStringCell(i,"tipovent");
								idproduc = gdSubast.getStringCell(i,"idproduc");
								txnombre = gdSubast.getStringCell(i,"txnombre");
								preciosa = gdSubast.getStringCell(i,"preciosa");
								divisaxx = gdSubast.getStringCell(i,"divisaxx");
								cdintern = gdSubast.getStringCell(i,"cdintern");
								urlexter = gdSubast.getStringCell(i,"urlexter");
								preciomi = gdSubast.getStringCell(i,"preciomi");
								fechvent = gdSubast.getStringCell(i,"fechvent");
								horavent = gdSubast.getStringCell(i,"horavent");
								finfecve = gdSubast.getStringCell(i,"finfecve");
								finhorve = gdSubast.getStringCell(i,"finhorve");
								mcactivo = gdSubast.getStringCell(i,"mcactivo");
								
								for (int j = 0; j < gdCotiza.rowCount(); j++){
									if (gdCotiza.getStringCell(j,"idcodsub").equals(idcodsub)){
										cotizaci = gdCotiza.getStringCell(j,"cotizaci");
										fhcotiza = gdCotiza.getStringCell(j,"fechahor");
									} 
								}
								
								if ((cotizaci == null) || (cotizaci.equals(""))){
									cotizaci = "0";
									fhcotiza = "-";
								}
								
								double dbpreciomi = Double.parseDouble(preciomi);
								double dbcotizaci = Double.parseDouble(cotizaci);
								
								
								%>
								<tr style="cursor:pointer;font-size:12px">
									<% if (tipovent.equals("S")){ %>
										<td align="center"><img src="/JLet/common/img/icons/pantallas/subasta.png" width="16px" onclick="irUrl('<%=urlexter%>')" /></td>
									<% } else { %>
										<td align="center"><img src="/JLet/common/img/varios/carrito.png" width="16px" onclick="irUrl('<%=urlexter%>')"/></td>
									<% } %>
									
									<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')"><%=txwebxxx%></td>
									<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')"><%=txnombre%></td>
									<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')"><%=preciosa%> <%=divisaxx%></td>
									<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')"><%=preciomi%> <%=divisaxx%></td>								
									<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')"><%=fechvent%></td>
									<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')"><%=horavent%></td>
									
									<% if ((finfecve != null) && (finfecve.equals("00/00/0000"))){ %>
										<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')">-</td>
										<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')">-</td>
									<% } else { %>
										<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')"><%=finfecve%></td>
										<td align="center" class="fonts6" style="font-size:12px" onclick="verSubastaDetalle('<%=idcodsub%>')"><%=finhorve%></td>
									<% } %>		
									
									<% if (tipovent.equals("S")) { %>
										<% if (dbpreciomi > dbcotizaci) { %>
											<td align="center" class="fonts6" style="font-size:12px; color:#FF0000"><b><%=dbcotizaci%> <%=divisaxx%></b></td>
										<% } else { %>	
											<td align="center" class="fonts6" style="font-size:12px;color:#00FF00"><b><%=dbcotizaci%> <%=divisaxx%></b></td>
										<% } %>
									<% } else {%>
										<td align="center" class="fonts6">-</td>
									<% } %>
										<td align="center" class="fonts6" style="font-size:12px"><%=fhcotiza%></td>
										<td align="center" class="fonts6" style="font-size:12px">
											<table>
												<tr>
													<td onclick="altaInfo('<%=idcodsub%>','A');"><img src="/JLet/common/img/varios/exclamacion.png" width="16px"/></td>
													<td onclick="altaInfo('<%=idcodsub%>','C');"><img src="/JLet/common/img/varios/comentarios.png" width="16px"/></td>
													<td onclick="altaInfo('<%=idcodsub%>','V');"><img src="/JLet/common/img/varios/carrito.png" width="16px"/></td>
													
												<% if (mcactivo.equals("S")) { %>
													<td onclick="desactivar('<%=idcodsub%>','N');"><img src="/JLet/common/img/varios/botonverde.png" width="20px"/></td>
												<% }else{%>	
													<td onclick="desactivar('<%=idcodsub%>','S');"><img src="/JLet/common/img/varios/botonrojo.png" width="20px"/></td>
												<% }%>	
												</tr>
											</table>
										</td>
								</tr>
							<% } catch (Exception e) { 							
								System.out.println(" ERROR - Recuperando linea subasta: "+ i +" - "+ e.getMessage());
							   }
							
						} %>						
					
					</table>											
				</form>
				
				<form name="formdetasuba" action="/JLet/App" method="POST">
					<input type="hidden" name="controller" 	value="SubastaHttpHandler"/>
					<input type="hidden" name="services"	value="DetalleSubastaSrv"/>
					<input type="hidden" name="view" 		value="subasta/detalleSubasta.jsp"/>
					<input type="hidden" name="idcodsub" 	value=""/>
				</form>
				
				
				<form name="formAltaSub" method="POST" action="/JLet/App">
					<input type="hidden" name="controller" 	value="SubastaHttpHandler"/>
					<input type="hidden" name="services"	value="AltaSubastaSrv.ListSubastaSrv"/>
					<input type="hidden" name="view" 		value="subasta/listSubasta.jsp"/>
					<input type="hidden" name="idcodsub" 		value=""/>
					<input type="hidden" name="desactiv" 		value=""/>
				</form>
				
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
</body>
