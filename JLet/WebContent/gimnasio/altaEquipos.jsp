<%@ include file="/common/jsp/include.jsp"%>

<%
	

	String maxgimna = "";
	String idlineax = "";
	String rzsocial = "";
	String idemisor = "";
	String tpclient = "";
	String cdintern = "";
	String idfiscal = "";
	String txdirecc = "";
	String txciudad = "";
	String cdpostal = "";
	String txmailxx = "";
	String tfnofijo = "";
	String tfnomovi = "";
	String tfnofaxx = "";
	String txwebxxx = "";
	
	String accionxx = "";


	
	Grid gdEquipo   = null;
	Grid gdMarcas   = null;
	Grid gdEquipa   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			maxgimna = io.getStringValue("idclient");
			accionxx = io.getStringValue("accionxx");
			gdEquipo = io.getGrid("gdEquipo");
			gdMarcas = io.getGrid("gdMarcas");
			gdEquipa = io.getGrid("gdEquipa");
			
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Al.jsp "+ e.getMessage());	
		}
	}
	
	try {
		/*idclient = grClient.getStringCell(0,"idclient"); 
		rzsocial = grClient.getStringCell(0,"rzsocial");
		idemisor = grClient.getStringCell(0,"idemisor");
		cdintern = grClient.getStringCell(0,"cdintern");
		idfiscal = grClient.getStringCell(0,"idfiscal");
		txdirecc = grClient.getStringCell(0,"txdirecc");
		txciudad = grClient.getStringCell(0,"txciudad");
		cdpostal = grClient.getStringCell(0,"cdpostal");
		txmailxx = grClient.getStringCell(0,"txmailxx");
		tfnofijo = grClient.getStringCell(0,"tfnofijo");
		tfnomovi = grClient.getStringCell(0,"tfnomovi");
		tfnofaxx = grClient.getStringCell(0,"tfnofaxx");
		txwebxxx = grClient.getStringCell(0,"txwebxxx");*/
	} catch (Exception e) {
		System.out.println(" ERROR  Recuperando Valores");
	}
	

System.out.println("ACCION       --------------  "+accionxx);
	
%>


<head>

	<script>
	
	arraLinea = new Array();
	arraCdequ = new Array();
	arraMarca = new Array();
	arraGamax = new Array();
	arraModel = new Array();
	arraCanti = new Array();
	arraAnioc = new Array();
	arraEstad = new Array();

	
		<%
		if(gdEquipo !=null && gdEquipo.rowCount()>0){
			
			 for (int j = 0; j < gdEquipo.rowCount(); j++){ 
			 idlineax = gdEquipo.getStringCell(j, "cdequipa");
		   %>
		   		arraLinea[<%=j%>] = "<%=idlineax%>";
		   
		 <% }
		}
		 %>
	

		function validarEnvio(){
			clickCheckbox();
			document.formvaen.submit();	
		}
	
	
		function validarAlta(){
			
			var cadena = "";
			
			if(document.formAltaCli.txnombre.value == ""){
				cadena+="Campo Razon social no valido";
			}
    	  
    	  	if (cadena == ""){
    	   		document.formAltaCli.submit();
    	  	}else{
    		  alert(cadena);
    	  	}
	  	}	
		
	
		
	function rellenaArrays(){
		
		 i = 0;
		 model = 0;
		 canti = 0;
		 aniox = 0;
		 entra = 0;
		 
		 for (j = 0; j < arraLinea.length; j++){
			 
			 checkmar = "chktiene"+arraLinea[j];
			 idmarcax = "idmarcax"+arraLinea[j];
			 txmodelo = "txmodelo"+arraLinea[j];
			 cantidad = "cantidad"+arraLinea[j];
			 txanioco = "txanioco"+arraLinea[j];
			 cdestado = "cdestado"+arraLinea[j];
			 tipogama = "tipogama"+arraLinea[j];
		
			 if(document.getElementById(checkmar).checked){
				
				 entra =1;
				 arraCdequ[i] = arraLinea[j];	
				 arraMarca[i] = document.getElementById(idmarcax).value;
				 arraModel[i] = document.getElementById(txmodelo).value;
				 arraCanti[i] = document.getElementById(cantidad).value;
				 arraAnioc[i] = document.getElementById(txanioco).value;
				 arraEstad[i] = document.getElementById(cdestado).value;
				 arraGamax[i] = document.getElementById(tipogama).value;
				 
				 if(arraModel[i] == ""){
					model =1;
				 }
				 if(arraCanti[i] == ""){
					canti = 1;
				 }
				 if(arraAnioc[i] == ""){
					aniox = 1;
					 }
				 
				 i++;
			 }
		 } 
		 
		 document.formAltaEqu.cdequipo.value = arraCdequ;
		 document.formAltaEqu.idmarcax.value = arraMarca;
		 document.formAltaEqu.txmodelo.value = arraModel;
		 document.formAltaEqu.cantidad.value = arraCanti;
		 document.formAltaEqu.aniocomp.value = arraAnioc;
		 document.formAltaEqu.cdestado.value = arraEstad;
		 document.formAltaEqu.tipogama.value = arraGamax;
		 
		 var cadena = "";
		 
		 if(model == 1 ){
			cadena +="Debes rellenar los modelos de los equipos seleccionados\n";	 
		 }
		 
		 if(canti == 1 ){
			cadena +="Debes rellenar las cantidades de los equipos seleccionados\n";	 
		 }
		 
		 if(aniox == 1 ){
				cadena +="Debes rellenar el año de los equipos seleccionados\n";	 
		 }
		 
		 if(entra == 0 ){
				cadena +="Debes marcar si dispone del equipo\n";	 
		 }
	
		 if(cadena != "") {
			 alert(cadena);
		 }else{
			 document.formAltaEqu.submit(); 
		 }
		
		 
	}	 

	</script>
	
</head>


<body>

	<div class="fondo1">
    <div class="testata"><img src="/JLet/common/img/icons/title-alta-gym.png"></div>
		<div class="centrado4" style="width:85%; margin-left:8%; height:auto;">
			<form name="formAltaEqu" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="GimnasioHttpHandler"/>
				<input type="hidden" name="services"	value="AltaEquiposSrv"/>
				<input type="hidden" name="view" 		value="clientes/resultAltaCliente.jsp"/>
				<input type="hidden" name="idclient" 		value="<%=maxgimna%>"/>
				<input type="hidden" name="cdequipo" 		value=""/>
				<input type="hidden" name="idmarcax" 		value=""/>
				<input type="hidden" name="txmodelo" 		value=""/>
				<input type="hidden" name="cantidad" 		value=""/>
				<input type="hidden" name="aniocomp" 		value=""/>
				<input type="hidden" name="cdestado" 		value=""/>
				<input type="hidden" name="tipogama" 		value=""/>
				<input type="hidden" name="accionxx" 		value="<%=accionxx%>"/>
				
				<br>
				<br>
			<!--  
				<table border="0" width="70%" align="center" border="1">
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Nombre</td>
						<td class="input-m"><input type="text" name="txnombre" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">NIF /CIF </td>
						<td class="input-m"><input type="text" name="nifcifxx" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b">Categoria</td>
						<td class="input-m"><input style="width:100%"  type="text" name="txcatego" value="<%=txciudad %>" size="20" class="input-m"/></td>		
					</tr>
					
					<tr class="fonts">
						<td align="center" class="input-txt-b">Ciudad</td>
						<td class="input-m"><input style="width:100%"  type="text" name="txciudad" value="<%=txciudad %>" size="20" class="input-m"/></td>		
					</tr>
					<tr>
						<td align="center" class="input-txt-b">Direcci&oacute;n</td>
						<td class="input-m"><input style="width:100%" type="text" name="txdirecc" value="<%=txdirecc %>" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Cod. Postal</td>
						<td class="input-m" style="width:230px;"><input style="width:100%"  type="text" name="cdpostal" value="" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Teléfono</td>
						<td class="input-m" style="width:230px;"><input style="width:150px" type="text" name="telefono" value="<%= tfnofijo %>" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">E-MAIL: </td>
						<td class="input-m" style="width:230px;"><input style="width:100%"  type="text" name="txmailxx" value="<%=tfnofaxx %>" size="20" class="input-m"/></td>
					</tr>
						<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Persona responsable: </td>
						<td class="input-m" style="width:230px;"><input style="width:100%"  type="text" name="txrespon" value="<%=tfnofaxx %>" size="20" class="input-m"/></td>
					</tr>
						<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Sexo: </td>
						<td class="input-m" style="width:230px;"><input style="width:100%"  type="text" name="sexoxxxx" value="<%= tfnofaxx %>" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Movil responsable: </td>
						<td class="input-m"><input style="width:100%" type="text" name="txnomres" value="<%=txwebxxx %>"  size="20" class="input-m"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Movil responsable: </td>
						<td class="input-m"><input style="width:100%" type="text" name="tfnomovi" value="<%=txwebxxx %>"  size="20" class="input-m"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Mail responsable: </td>
						<td class="input-m" ><input style="width:100%" type="text" name="mailresp" value="<%=txwebxxx %>"  size="20" class="input-m"/></td>		
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>						
					</tr>
				</table>
			-->
			<table border="0" width="80%" align="center" border="1">
			<%	
				String cdequipa ="";
				String familiax ="";
				String famiAuxi ="";
				String nomfamil ="";
				
				
				/* variables recogidas para comparar*/
				String equipaxx = "";
				String marcaxxx = "";
				String modeloxx = "";
				String cantidxx = "";
				String anioreco = "";
				String tpgamaxx = "";
				String estadoxx = "";
				
				if(gdEquipo !=null && gdEquipo.rowCount()>0){
					
					for(int i = 0; i<gdEquipo.rowCount();i++){
						
							
						
						cdequipa = gdEquipo.getStringCell(i, "cdequipa");
						familiax = gdEquipo.getStringCell(i, "cdfamili");
						nomfamil = gdEquipo.getStringCell(i, "txnombre");
						
					
						if(gdEquipa !=null && gdEquipa.rowCount()>0){
							
							equipaxx = "";
							marcaxxx = "";
							modeloxx = "";
							cantidxx = "";
							anioreco = "";
							tpgamaxx = "";
							estadoxx = "";
							
							for(int z = 0; z<gdEquipa.rowCount();z++){
								if(cdequipa.equals(gdEquipa.getStringCell(z, "cdequipa"))){
									equipaxx = gdEquipa.getStringCell(z, "cdequipa"); 
									marcaxxx = gdEquipa.getStringCell(z, "idmarcax");
									modeloxx = gdEquipa.getStringCell(z, "txmodelo");
									cantidxx = gdEquipa.getStringCell(z, "cantidad");
									anioreco = gdEquipa.getStringCell(z, "aniocomp");
									tpgamaxx = gdEquipa.getStringCell(z, "tipogama");
									estadoxx = gdEquipa.getStringCell(z, "cdestado");
								}

						}
		              }
						
						if(!famiAuxi.equals(familiax)){
						 %>
								<tr>
									<td><div class="cabecera input-b1"><%=familiax %></div></td>
									<td><div class="cabecera input-b1">Nombre</div></td>
									<td><div class="cabecera input-b1">Tiene</div></td>
									<td><div class="cabecera input-b1">Marca</div></td>
									<td><div class="cabecera input-b1">Modelo</div></td>
									<td><div class="cabecera input-b1">Gama</div></td>
									<td><div class="cabecera input-b1">Cantidad</div></td>
									<td><div class="cabecera input-b1">Año compra</div></td>
									<td><div class="cabecera input-b1">Estado</div></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td><div class="cabecera input-b1"><%=nomfamil %></div></td>
									<td><input type="checkbox" id="chktiene<%=cdequipa%>" <% if(cdequipa.equals(equipaxx)){ %>checked="true"<%} %>></td>
									<td><select id="idmarcax<%=cdequipa%>">
									<%
										if(gdMarcas !=null && gdMarcas.rowCount()>0){
											for(int j = 0; j<gdMarcas.rowCount();j++){%>
												<option value="<%=gdMarcas.getStringCell(j, "idmarcax") %>" <%if(marcaxxx.equals(gdMarcas.getStringCell(j, "idmarcax"))){%> selected="true" <%} %>><%=gdMarcas.getStringCell(j, "txmarcax") %></option>
										<%
											}
										}	
										%>
										</select>
									</td>
									<td>
								
									
									
									<input type="text" id="txmodelo<%=cdequipa%>" value="<%=modeloxx%>"></td>
									<td><select id="tipogama<%=cdequipa%>">
											<option value="B" <%if(tpgamaxx.equals("B")){%> selected="true" <%} %> >Baja</option>
											<option value="M" <%if(tpgamaxx.equals("M")){%> selected="true" <%} %>>Media</option>
											<option value="A" <%if(tpgamaxx.equals("A")){%> selected="true" <%} %>>Alta</option>
										</select></td>
									<td><input type="text" id="cantidad<%=cdequipa%>" value="<%=cantidxx%>"></td>
									<td><input type="text" id="txanioco<%=cdequipa%>" value="<%=anioreco%>"></td>
									<td><select id="cdestado<%=cdequipa%>">
											<option value="MB" <%if(estadoxx.equals("MB")){%> selected="true" <%} %>>Muy bien</option>
											<option value="B"  <%if(estadoxx.equals("B")){%>  selected="true" <%} %>>Bien</option>
											<option value="R"  <%if(estadoxx.equals("R")){%>  selected="true" <%} %>>Regular</option>
											<option value="M"  <%if(estadoxx.equals("M")){%>  selected="true" <%} %>>Mal</option>
											<option value="MM" <%if(estadoxx.equals("MM")){%> selected="true" <%} %>>Muy Mal</option>
										</select></td>
								</tr>	
							  <%}else{
					           %>
							<tr>
								<td>&nbsp;</td>
									<td><div class="cabecera input-b1"><%=nomfamil %></div></td>
									<td><input type="checkbox" id="chktiene<%=cdequipa%>" <% if(cdequipa.equals(equipaxx)){ %>checked="true"<%} %>></td>
									<td>
									<select id="idmarcax<%=cdequipa%>">
									<%
										if(gdMarcas !=null && gdMarcas.rowCount()>0){
											for(int j = 0; j<gdMarcas.rowCount();j++){%>
												<option value="<%=gdMarcas.getStringCell(j, "idmarcax") %>" <%if(marcaxxx.equals(gdMarcas.getStringCell(j, "idmarcax"))){%> selected="true" <%} %> ><%=gdMarcas.getStringCell(j, "txmarcax") %></option>
										<%
											}
										}	
										%>
										</select>
									
									</td>
									<td>
									
									<input type="text" id="txmodelo<%=cdequipa%>" value="<%=modeloxx%>"></td>
									<td><select id="tipogama<%=cdequipa%>">
											<option value="B" <%if(tpgamaxx.equals("B")){%> selected="true" <%} %>>Baja</option>
											<option value="M" <%if(tpgamaxx.equals("M")){%> selected="true" <%} %>>Media</option>
											<option value="A" <%if(tpgamaxx.equals("A")){%> selected="true" <%} %>>Alta</option>
										</select></td>
									<td><input type="text" id="cantidad<%=cdequipa%>" value="<%=cantidxx%>"></td>
									<td><input type="text" id="txanioco<%=cdequipa%>" value="<%=anioreco%>"></td>
									<td><select id="cdestado<%=cdequipa%>">
											<option value="MB" <%if(estadoxx.equals("MB")){%> selected <%} %>>Muy bien</option>
											<option value="B"  <%if(estadoxx.equals("B")){%>  selected <%} %>>Bien</option>
											<option value="R"  <%if(estadoxx.equals("R")){%>  selected <%} %>>Regular</option>
											<option value="M"  <%if(estadoxx.equals("M")){%>  selected <%} %>>Mal</option>
											<option value="MM" <%if(estadoxx.equals("MM")){%> selected <%} %>>Muy Mal</option>
										</select></td>
							</tr>
							 	
							<%  }	
					
						famiAuxi = familiax;
					 	}
				}
				
			%>
			</table>
			</form>
			
			
			
			
			<table width="100%" align="center">
				<tr>
					<td align="center">
						<a class="boton button-envia" style="margin-left:0%; font-family:Arial, Helvetica, sans-serif; font-size:18px;" onClick="rellenaArrays()">Alta Equipos</a>
					</td>
				</tr>
			</table>
					
		</div>
	</div>
</body>