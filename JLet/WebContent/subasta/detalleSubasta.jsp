<%@ include file="/common/jsp/include.jsp"%>

<%
	
	String aniofact = null;

	String codsubas = "";
	String txwebxxx = "";
	String txpaisxx = "";
	String txusuari = "";
	String tpventax = "";
	String idproweb = "";
	String txnombre = "";
	String preciosa = "";
	String iddivisa = "";
	String cdintern = "";
	String urlsubas = "";
	String preciomi = "";
	String pretrans = "";
	String fechaini = "";
	String horainic = "";
	String fechafin = "";
	String horafinx = "";
	String mcactivo = "";
	
	/*Detalle*/
	String idevento  = ""; 
	String tpevento  = "";
	String txusuard  = "";
	String txmailxx  = "";
	String telefono  = "";
	String isopaisx  = "";
	String cantidad  = "";
	String cddivisa  = "";
	String direnvio  = "";
	String txcoment  = "";
	String fhaltaxx  = "";
	
	String fhventax = "";
	String nunidade = "";
	String transpor = "";
	
	
	/*Detalle*/
	
	
	
	Grid gdSubast   = null;
	Grid gdDetSub   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gdSubast = io.getGrid("gdSubast");
			gdDetSub = io.getGrid("gdDetSub");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en alta Gimnasio/Al.jsp "+ e.getMessage());	
		}
	}
	
	try {
		
		codsubas = gdSubast.getStringCell(0,"idcodsub");
		txwebxxx = gdSubast.getStringCell(0,"txwebxxx"); 
		txpaisxx = gdSubast.getStringCell(0,"txpaisxx");
		txusuari = gdSubast.getStringCell(0,"txusuari");
		tpventax = gdSubast.getStringCell(0,"tipovent");
		idproweb = gdSubast.getStringCell(0,"idproduc");
		txnombre = gdSubast.getStringCell(0,"txnombre");
		preciosa = gdSubast.getStringCell(0,"preciosa");
		iddivisa = gdSubast.getStringCell(0,"divisaxx");
		cdintern = gdSubast.getStringCell(0,"cdintern");
		urlsubas = gdSubast.getStringCell(0,"urlexter");
		preciomi = gdSubast.getStringCell(0,"preciomi");
		pretrans = gdSubast.getStringCell(0,"preciomi"); 
		fechaini = gdSubast.getStringCell(0,"fechvent");
		horainic = gdSubast.getStringCell(0,"horavent");
		fechafin = gdSubast.getStringCell(0,"finfecve");
		horafinx = gdSubast.getStringCell(0,"finhorve");
		mcactivo = gdSubast.getStringCell(0,"mcactivo");
		
		if(iddivisa.equals("EUR")){
			iddivisa = "EUR - Euros";
		}else if(iddivisa.equals("GBP")){
			iddivisa = "GBP - Libra";
		}else if(iddivisa.equals("USD")){
			iddivisa = "USD - Dolares";
		}else if(iddivisa.equals("CHF")){
			iddivisa = "CHF - Franco";
		}else if(iddivisa.equals("DOP")){
			iddivisa = "DOP - Pesos Dominicanos";
		}
		
		if(tpventax.equals("V")){
			tpventax = "VENTA";
		}
		
		if(tpventax.equals("S")){
			tpventax = "SUBASTA";
		}
		
	} catch (Exception e) {
		System.out.println(" ERROR  Recuperando Valores");
	}

%>

<script>
	function desactivar(estado){
		document.formAltaSub.desactiv.value = estado;
		document.formAltaSub.submit();
	}
</script>

<head></head>
<body>

	<div class="fondo1">
       <div class="testata"><img src="/JLet/common/img/icons/title-alta-subasta.png"></div>
		<div class="centrado4" style="width:85%; margin-left:8%; height:auto;">
			<form name="formAltaSub" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="SubastaHttpHandler"/>
				<input type="hidden" name="services"	value="AltaSubastaSrv.ListSubastaSrv"/>
				<input type="hidden" name="view" 		value="subasta/listSubasta.jsp"/>
				<input type="hidden" name="idcodsub" 		value="<%=codsubas%>"/>
				<input type="hidden" name="desactiv" 		value=""/>
				<br>
				<br>
			
				<table border="0" width="70%" align="center" border="1">
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Web</td>
						<td class="input-m"> <%=txwebxxx %></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Pais </td>
						<td class="input-m"> <%=txpaisxx %></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b">Usuario</td>
						<td class="input-m"><input style="width:100%"  type="text" name="txusuari" value="<%=txusuari %>" size="20" class="input-m"/></td>		
					</tr>
					
					<tr class="fonts">
						<td align="center" class="input-txt-b">Tipo Venta</td>
						<td class="input-m"><input style="width:100%"  type="text" name="tpventax" value="<%=tpventax %>" size="20" class="input-m"/></td>		
					</tr>
					<tr>
						<td align="center" class="input-txt-b">ID producto</td>
						<td class="input-m"><input style="width:100%" type="text" name="idproweb" value="<%=idproweb %>" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Nombre</td>
						<td class="input-m" style="width:230px;"><%=txnombre %></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Precio Salida: </td>
						<td class="input-m" style="width:230px;"><input style="width:100%"  type="text" name="preciosa" value="<%=preciosa %>" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Divisa: </td>
						<td class="input-m" style="width:230px;"><input style="width:100%"  type="text" name="iddivisa" value="<%=iddivisa %>" size="20" class="input-m"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:100px;">Codigo interno: </td>
						<td class="input-m" style="width:230px;"><input style="width:100%"  type="text" name="cdintern" value="<%=cdintern %>" size="20" class="input-m"/></td>
					</tr>
						<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Url: </td>
						<td class="input-m" style="width:230px;cursor:pointer" onclick="javascript:window.open('<%=urlsubas %>')">
						<%=urlsubas %>			</td>
					</tr>
					<%if (!tpventax.equals("VENTA")) {%>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Precio mínimo: </td>
						<td class="input-m"><input style="width:100%" type="text" name="preciomi" value="<%=preciomi %>" maxlength=20  size="20" class="input-m"/></td>		
					</tr>
					<%}%>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Precio transporte: </td>
						<td class="input-m" ><input style="width:100%" type="text" name="pretrans" value="<%=pretrans %>"  size="20" class="input-m"/></td>		
					</tr>
					
					<%if (!tpventax.equals("VENTA")) {%>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Fecha inicio: </td>
						<td class="input-m" ><input style="width:100%" type="text" name="fechaini" value="<%=fechaini %>"  size="20" class="input-m"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Hora inicio: </td>
						<td class="input-m" ><input style="width:100%" type="text" name="horainic" value="<%=horainic %>"  size="20" class="input-m"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Fecha fin: </td>
						<td class="input-m" ><input style="width:100%" type="text" name="fechafin" value="<%=fechafin %>"  size="20" class="input-m"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-txt-b" style="width:110px;">Hora fin: </td>
						<td class="input-m" ><input style="width:100%" type="text" name="horafinx" value="<%=horafinx %>"  size="20" class="input-m"/></td>		
					</tr>
					<% }%>
					<tr>
						<td colspan="5">&nbsp;</td>						
					</tr>
				</table>
	
			</form>
			<table width="100%" align="center">
				<tr>
			<% 
				if(gdDetSub !=null && gdDetSub.rowCount()>0){
					String tipoEven = "";
					String txtEvent = "";	
				
		 			for (int j = 0; j < gdDetSub.rowCount(); j++){ 
		 				
		 				if (!tipoEven.equals(gdDetSub.getStringCell(j,"tpevento"))){ 
		 					if(gdDetSub.getStringCell(j,"tpevento").equals("A")){
		 						txtEvent = "ANOTACION";
		 					}else if(gdDetSub.getStringCell(j,"tpevento").equals("C")){
		 						txtEvent = "COMENTARIOS";
		 					}else if(gdDetSub.getStringCell(j,"tpevento").equals("V")){
		 						txtEvent = "VENTA";
		 					}
		 				%>
				  		<tr>
				  			<td colspan="10"><div class="cabecera input-b"><%= txtEvent %></div></td>
				  		</tr>
				  		<% if (txtEvent.equals("ANOTACION")){%>
				  				<td align="center" class="fonts5" >&nbsp;</td>
				  				<td align="center" colspan="8"><div class="cabecera input-b1">Comentario</div></td>
				  				<td align="center" class="fonts5" >&nbsp;</td>
				  			
				  			
				  		<%}else if (txtEvent.equals("COMENTARIOS")){%>
				  				<td align="center" width="2%">&nbsp;</td>	
					  			<td align="center" width="15%"><div class="cabecera input-b1">Usuario</div></td>						
								<td align="center" width="15%" ><div class="cabecera input-b1">Mail</div></td>
								<td align="center" width="15%" ><div class="cabecera input-b1">Telefono</div></td>
								<td align="center" colspan=5><div class="cabecera input-b1" >Comentario</div></td>
								<td align="center" width="2%">&nbsp;</td>	
				  		
				  		<%}else if (txtEvent.equals("VENTA")){%>
				  			<tr class="fonts">	
				  				<td align="center" width="2%">&nbsp;</td>				
								<td align="center" width="15%"><div class="cabecera input-b1">Usuario</div></td>						
								<td align="center" width="15%" ><div class="cabecera input-b1">Mail</div></td>
								<td align="center" width="15%" ><div class="cabecera input-b1">Telefono</div></td>
								<td align="center" width="15%"><div class="cabecera input-b1">Pais</div></td>						
								<td align="center" width="15%" ><div class="cabecera input-b1">Cantidad</div></td>
								<td align="center" width="15%" ><div class="cabecera input-b1">Divisa</div></td>
								<td align="center" width="15%" ><div class="cabecera input-b1">Dir.Envio</div></td>
								<td align="center" ><div class="cabecera input-b1">Comentario</div></td>
								<td align="center" width="15%"><div class="cabecera input-b1">F.Venta</div></td>						
								<td align="center" width="15%" ><div class="cabecera input-b1">Trans.</div></td>
								<td align="center" width="15%" ><div class="cabecera input-b1">Unid.</div></td>
								<td align="center" width="2%">&nbsp;</td>	
						<%} %>
							
					 		</tr>
					 		
		 				<% }
		 				
		 				idevento  = gdDetSub.getStringCell(j, "idevento");
						tpevento  = gdDetSub.getStringCell(j, "tpevento");
						txusuard  = gdDetSub.getStringCell(j, "txusuari");
					    txmailxx  = gdDetSub.getStringCell(j, "txmailxx");
						telefono  = gdDetSub.getStringCell(j, "telefono");
						isopaisx  = gdDetSub.getStringCell(j, "isopaisx");
						cantidad  = gdDetSub.getStringCell(j, "cantidad");
						cddivisa  = gdDetSub.getStringCell(j, "cddivisa");
						direnvio  = gdDetSub.getStringCell(j, "direnvio");
						txcoment  = gdDetSub.getStringCell(j, "txcoment");
						fhaltaxx  = gdDetSub.getStringCell(j, "fhaltaxx");
						tipoEven  = gdDetSub.getStringCell(j, "tpevento");
						nunidade  = gdDetSub.getStringCell(j, "nunidade");
						fhventax  = gdDetSub.getStringCell(j, "fhventax");
						transpor  = gdDetSub.getStringCell(j, "pretrans");
						
						if (fhventax.equals("0000-00-00")){
							fhventax ="--";
						}
						
						
						if (txtEvent.equals("ANOTACION")){%>
						<tr class="fonts">
							<td align="center" class="fonts5" >&nbsp;</td>
		  					<td align="center" class="fonts5" colspan="8"><%=txcoment %></td>
		  					<td align="center" class="fonts5" >&nbsp;</td>
		  				</tr>
		  				
		  				<%}else if (txtEvent.equals("COMENTARIOS")){%>
		  				<tr class="fonts">
		  					<td align="center" width="2%">&nbsp;</td>	
				  			<td align="center" class="fonts5"><%=txusuard %></td>						
							<td align="center" class="fonts5" ><%=txmailxx %></td>
							<td align="center" class="fonts5"><%=telefono %></td>
							<td align="center" class="fonts5" colspan=5><%=txcoment %></td>
							<td align="center" width="2%">&nbsp;</td>	
						</tr>
			  		
		  				<%}else if (txtEvent.equals("VENTA")){%>
		  				<tr class="fonts">
		  					<td align="center" width="2%">&nbsp;</td>							
							<td align="center" class="fonts5"><%=txusuard %></td>						
							<td align="center" class="fonts5"><%=txmailxx %></td>
							<td align="center" class="fonts5"><%=telefono %></td>
							<td align="center" class="fonts5"><%=isopaisx %></td>						
							<td align="center" class="fonts5" ><%=cantidad %></td>
							<td align="center" class="fonts5" ><%=cddivisa %></td>
							<td align="center" class="fonts5" ><%=direnvio %></td>
							<td align="center" class="fonts5"><%=txcoment %></td>
							<td align="center" class="fonts5" ><%=fhventax %></td>
							<td align="center" class="fonts5" ><%=transpor %></td>
							<td align="center" class="fonts5"><%=nunidade %></td>
							<td align="center" width="2%">&nbsp;</td>	
						<%} %>
					
			 		</tr>
 					<% }
						
					}
		 	
			%>
				
			<table width="100%" align="center">
				<tr>
				
				<%if (mcactivo.equals("S")){ %>
					<td align="center">
						<a class="boton button-envia" style="margin-left:0%; font-family:Arial, Helvetica, sans-serif; font-size:22px;" onClick="desactivar('N')">Desactivar</a>
					</td>
				<%}else{%>
					<td align="center">
					<a class="boton button-envia" style="margin-left:0%; font-family:Arial, Helvetica, sans-serif; font-size:22px;" onClick="desactivar('S')">Activar</a>
				</td>
					
				<%} %>
				</tr>
			</table>
					
		</div>
	</div>
</body>