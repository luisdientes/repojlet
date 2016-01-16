<%@ include file="/common/jsp/includeNOLat.jsp"%>
<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>

<%
	HttpSession sesion = request.getSession();
	String cdemisor = (String)sesion.getAttribute("cduserid");
	
	String tpevento = "";
	String idcodsub = "";
	String cduserxx = "";
	String resualta = "";
	String txtinfox = "";
	Grid gdPaises = null;
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			resualta = io.getStringValue("resualta");
			tpevento = io.getStringValue("tipoinfo");
			idcodsub = io.getStringValue("idcodsub");
			gdPaises = io.getGrid("gdPaises");
			cduserxx = io.getStringValue("cduserxx");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en subasta/altaInfoSubasta.jsp "+ e.getMessage());	
		}
	}

	System.out.println("Resualta:  "+ resualta);
	
%>

<script>
	
	<% if ((resualta != null) && (resualta.equals("OK"))){ %>
		window.opener.cerrarVentana();
	<% } %>

	function altaInfo(){
		
		document.altainfo.submit();
		
	}

</script>

<body>

	<div class="fondo2">
		<div class="centrado" style="width:90%">
		
			<% if (tpevento.equals("A")) {
					txtinfox = "Anotacion";
				} else if (tpevento.equals("C")) {
					txtinfox = "Comentario Cliente";
			    } else if (tpevento.equals("V")) {
			    	txtinfox = "Venta";
				} %>

				
				
			<form name="altainfo" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="SubastaHttpHandler"/>
				<input type="hidden" name="services"	value="AltaInfoSubastaSrv"/>
				<input type="hidden" name="view" 		value="subasta/altaInfoSubasta.jsp"/>
				<input type="hidden" name="idcodsub"	value="<%=idcodsub%>">
				<input type="hidden" name="tpevento"	value="<%=tpevento%>">
				<input type="hidden" name="iduserin"	value="<%=cduserxx%>">
			
				<br/>
				<table border="0" width="85%" align="center" border="1">
					<tr>
						<td colspan="3" align="center" class="input-txt-b">Alta <%=txtinfox%></td> 
					</tr>
					<tr>
						<td align="center" colspan="3">&nbsp;</td>
					</tr>
					<% if (tpevento.equals("C") || tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Usuario</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><input name="txusuari" type="text" class="input-m" style="width: 200px;"></td>
						</tr>
					<% } else {%>	
						<input type="hidden" name="txusuari"/>
					<% } %>
					
					<% if (tpevento.equals("C") || tpevento.equals("V")){%>	
						<tr class="fonts">						
							<td align="center" width="35%">Mail</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><input name="txmailxx" type="text" class="input-m" style="width: 200px;"></td>
						</tr>
					<% } else {%>	
						<input type="hidden" name="txmailxx"/>
					<% } %>
					
					<% if (tpevento.equals("C") || tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Teléfono</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><input name="telefono" type="text" class="input-m" style="width: 200px;"></td>
						</tr>
					<% } else {%>	
						<input name="telefono" type="hidden"/>
					<% } %>
					
					<% if (tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Pais</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%">
								<select name="isopaisx" style="width:120px;">
								<% for (int i = 0; i < gdPaises.rowCount(); i++){ %>
									<option value='<%=gdPaises.getStringCell(i,"isocodex")%>'><%=gdPaises.getStringCell(i,"txnombre")%></option>							
								<% } %>
							</select></td>
						</tr>
					<% } else {%>	
						<input name="isopaisx" type="hidden"/>
					<% } %>
					

					<% if (tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Precio Venta</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><input name="cantidad" type="text" class="input-m" style="width: 200px;"></td>
						</tr>
					<% } else {%>	
						<input name="cantidad" type="hidden" value="0"/>
					<% } %>
					
					<% if (tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Nº Unidades</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><input name="nunidade" type="text" class="input-m" style="width: 200px;"></td>
						</tr>
					<% } else {%>	
						<input name="nunidade" type="hidden" value="0"/>
					<% } %>
					
					<% if (tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Coste Trans.</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><input name="pretrans" type="text" class="input-m" style="width: 200px;"></td>
						</tr>
					<% } else {%>	
						<input name="pretrans" type="hidden" value="0" />
					<% } %>
					
					<% if (tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Divisa</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><select name="cddivisa">								
								<option value='EUR'>EUR - Euros</option>
								<option value='CHF'>CHF - Franco Suizo</option>
								<option value='GBP'>GBP - Libra</option>
								<option value='USD'>USD - Dolares</option>
								<option value='DOP'>DOP - Pesos Dominicanos</option>
							</select></td>
						</tr>
					<% } else {%>	
						<input name="cddivisa" type="hidden"/>
					<% } %>
					
					<% if (tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Dir. Envío</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><textarea name="direnvio" class="input-m" style="width: 200px; height: 120px;"/></textarea></td>
						</tr>
					<% } else {%>	
						<input name="direnvio" type="hidden">
					<% } %>
					
					<% if (tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Fecha Venta.</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><input type="text" name="fhventax" size=12 id="fecventa" class="input-m"><img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Venta" id="lanzador"></td></td>
						</tr>
					<% } else {%>	
						<input name="fhventax" type="hidden"/>
					<% } %>
					
					<% if (tpevento.equals("A") || tpevento.equals("C") || tpevento.equals("V")){%>
						<tr class="fonts">						
							<td align="center" width="35%">Comentario</td>						
							<td align="center" width="5%" >&nbsp;</td>
							<td align="center" width="60%"><textarea name="txcoment" class="input-m" style="width: 200px; height: 120px;"/></textarea></td>
						</tr>
					<% } else {%>	
						<input name="txcoment" type="hidden"/>
					<% } %>
					<tr>
						<td align="center" colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td align="center" colspan="3">
							<a class="boton" onClick="altaInfo()">Alta Info</a>
						</td>
					</tr>
				</table>	 					
			</form>			
		</div>
	</div>

	<% if (tpevento.equals("V")){%>
	

	<script>
		Calendar.setup({ 
	    	inputField     :"fecventa",     // id del campo de texto 
	    	ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
	    	button     :    "lanzador"     // el id del botón que lanzará el calendario 
		});

	</script>	
	<%} %>
</body>